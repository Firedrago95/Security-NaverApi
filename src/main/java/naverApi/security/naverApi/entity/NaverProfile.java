package naverApi.security.naverApi.entity;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import lombok.Getter;

@Getter
public class NaverProfile {

    private String id;
    private String nickname;
    private String email;

    public NaverProfile(String jsonResponseBody) {
        JsonParser jsonParser = new JsonParser();
        JsonElement element = jsonParser.parse(jsonResponseBody);

        this.id = element.getAsJsonObject().get("response").getAsJsonObject().get("id").getAsString();
        this.nickname = element.getAsJsonObject().get("response").getAsJsonObject().get("nickname").getAsString();
        this.email = element.getAsJsonObject().get("response").getAsJsonObject().get("email").getAsString();
    }
}
