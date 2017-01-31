package GoodWeather.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.Getter;
import lombok.Setter;

@RestController
@RequestMapping("/api/v1")
public class RootApi {
    private final RestTemplate rt = new RestTemplate();
    // お天気APIの URL
    private final String url = "http://api.openweathermap.org/data/2.5/weather?q=";
    // Userの場所
    private String location = "Tokyo,jp";
    //パラメータ
    private final String parameter = "&units=metric";
    // APIキー
    private final String token = "&APPID=0239e2e4fc8a5c4ac9f5ddfde9d4c477";

    // 外部サービスの JSON を加工して返却。
    @RequestMapping("/top")
    public ResponseData top() {
        ResponseData responseData = new ResponseData();
        // APIからJSONを取得
        Gson gson = new Gson();
        String rawData = rt.getForObject(url + location + parameter + token, String.class);
        //受け取ったJSONを加工

        //APIから返されるままのJSONくん
        JsonObject rawJson = gson.fromJson(rawData, JsonObject.class);
        //天気情報(main)
        JsonObject weather_json = rawJson.getAsJsonArray("weather").get(0).getAsJsonObject();
        String weather = weather_json.getAsJsonPrimitive("main").toString();
        //気温と湿度
        JsonObject main = rawJson.getAsJsonObject("main").getAsJsonObject();
        String temp_min = main.getAsJsonPrimitive("temp_min").toString();
        String temp_max = main.getAsJsonPrimitive("temp_max").toString();
        String humidity = main.getAsJsonPrimitive("humidity").toString();

        // TODO: DBから天気に合った服装を選ぶ処理


//        System.out.println(main);
//        System.out.println(temp_min);
//        System.out.println(temp_max);
//        System.out.println(humidity);


//        responseData.setUser_place(location);
        return null;
    }
//{"coord":{"lon":139.69,"lat":35.69},"weather":[{"id":803,"main":"Clouds","description":"broken clouds","icon":"04d"}],"base":"stations","main":{"temp":285.177,"pressure":1029.04,"humidity":65,"temp_min":285.177,"temp_max":285.177,"sea_level":1032.91,"grnd_level":1029.04},"wind":{"speed":0.45,"deg":38.0004},"clouds":{"all":56},"dt":1485670067,"sys":{"message":0.0077,"country":"JP","sunrise":1485639813,"sunset":1485677135},"id":1850147,"name":"Tokyo","cod":200}

    //以下jsに渡すデータ用のクラス
    public class ResponseData{
        @Getter @Setter
        private Weather weather;
        @Getter @Setter
        private Clothes clothes;
        @Getter @Setter
        private String user_place;
    }
    public class Weather{
        @Getter @Setter
        private String weather_icon;
        @Getter @Setter
        private String max_temperature;
        @Getter @Setter
        private String min_temperature;
        @Getter @Setter
        private String humidity;
    }
    public class Clothes{
        @Getter @Setter
        private String clothes_description;
        @Getter @Setter
        private ManClothes manClothes;
        @Getter @Setter
        private WomanClothes womanClothes;
    }
    public  class ManClothes{
        @Getter @Setter
        private String inner_image;
        @Getter @Setter
        private String inner_color;
        @Getter @Setter
        private String outer_image;
        @Getter @Setter
        private String outer_color;
        @Getter @Setter
        private String bottom_image;
        @Getter @Setter
        private String bottom_color;
    }
    public class WomanClothes{
        @Getter @Setter
        private String inner_image;
        @Getter @Setter
        private String inner_color;
        @Getter @Setter
        private String outer_image;
        @Getter @Setter
        private String outer_color;
        @Getter @Setter
        private String bottom_image;
        @Getter @Setter
        private String bottom_color;
    }
}




