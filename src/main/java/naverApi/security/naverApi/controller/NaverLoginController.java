package naverApi.security.naverApi.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import naverApi.security.naverApi.entity.NaverProfile;
import naverApi.security.naverApi.vo.NaverApi;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Controller
@RequiredArgsConstructor
public class NaverLoginController {

    private final NaverApi naverApi;

    @GetMapping("/login")
    public String loginForm(Model model) {
        model.addAttribute("naverClientId", naverApi.getNaverClientId());
        model.addAttribute("naverRedirectUri", naverApi.getNaverRedirectUri());
        model.addAttribute("state", UUID.randomUUID().toString());
        return "loginForm";
    }

    @ResponseBody
    @GetMapping("/naver/login/code")
    public Map<String, Object> naverLogin(@RequestParam(name = "code") String code,
                                          @RequestParam(name = "state") String state) {
        Map<String, Object> map = new HashMap<>();

        // 접근 토큰 발급 요청
        String accessToken = naverApi.getAccessToken(code, state);
        System.out.println("accessToken = " + accessToken);

        // 사용자 정보받기
        NaverProfile userInfo = naverApi.getUserInfo(accessToken);
        map.put("id", userInfo.getId());
        map.put("nickName", userInfo.getNickname());
        map.put("email", userInfo.getEmail());

        return map;
    }
}
