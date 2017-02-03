package GoodWeather.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.Getter;
import lombok.Setter;

import javax.xml.ws.Response;
import java.sql.*;
import java.util.List;

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

        // APIからJSONを取得
        Gson gson = new Gson();
        String rawData = rt.getForObject(url + location + parameter + token, String.class);
        //受け取ったJSONを加工

        //APIから返されるままのJSONくん
        JsonObject rawJson = gson.fromJson(rawData, JsonObject.class);
        //天気情報(main)
        JsonObject weather_json = rawJson.getAsJsonArray("weather").get(0).getAsJsonObject();
        String weather_data = weather_json.getAsJsonPrimitive("main").toString();
        //気温と湿度
        JsonObject main = rawJson.getAsJsonObject("main").getAsJsonObject();
        String temp_min_data = main.getAsJsonPrimitive("temp_min").toString();
        String temp_max_data = main.getAsJsonPrimitive("temp_max").toString();
        String humidity_data = main.getAsJsonPrimitive("humidity").toString();

        Weather weather = new Weather();
        weather.setMax_temperature(temp_max_data);
        weather.setMin_temperature(temp_min_data);
        weather.setHumidity(humidity_data);
        weather.setWeather_icon("weatherIconのURL");

        // TODO: DBから天気に合った服装を選ぶ処理

        //DBに接続
        Connection connection = null;
        Statement statement = null;

        ManClothes manClothes = new ManClothes();
        WomanClothes womanClothes = new WomanClothes();

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:test.db");
            statement = connection.createStatement();

            //漢と女の服を全部出す(テスト用)
            String get_man_inner = "select * from sample_clothes where clothGenreId LIKE '101'";//漢インナー(薄)
            String get_man_outer = "select * from sample_clothes where clothGenreId LIKE '111'";//漢アウター(薄)
            String get_man_bottom = "select * from sample_clothes where clothGenreId LIKE '121'";//漢ボトム(短)

            String get_woman_inner = "select * from sample_clothes where clothGenreId LIKE '201'";//女インナー(薄)
            String get_woman_outer = "select * from sample_clothes where clothGenreId LIKE '211'";//女アウター(薄)
            String get_woman_bottom = "select * from sample_clothes where clothGenreId LIKE '221'";//女ボトム(短)

            //最初に出てきた服を格納(テスト用)
            //漢
            ResultSet rs_clothes = statement.executeQuery(get_man_inner);
            rs_clothes.next();//.next:値が無いとfalseを返すのでwhileで全部出すことも可能
            manClothes.setInner_image(rs_clothes.getString(5));//(5):clothIcon
            manClothes.setInner_color(rs_clothes.getString(4));//(4):clothColor

            rs_clothes = statement.executeQuery(get_man_outer);
            rs_clothes.next();
            manClothes.setOuter_image(rs_clothes.getString(5));
            manClothes.setOuter_color(rs_clothes.getString(4));

            rs_clothes = statement.executeQuery(get_man_bottom);
            rs_clothes.next();
            manClothes.setBottom_image(rs_clothes.getString(5));
            manClothes.setBottom_color(rs_clothes.getString(4));

//            rs_man.getString(1)//clothId
//            rs_man.getString(2)//clothGenreId
//            rs_man.getString(3)//clothName
//            rs_man.getString(4)//clothColor
//            rs_man.getString(5)//clothIcon

            //女
            rs_clothes = statement.executeQuery(get_woman_inner);
            rs_clothes.next();
            womanClothes.setInner_image(rs_clothes.getString(5));//(5):clothIcon
            womanClothes.setInner_color(rs_clothes.getString(4));//(4):clothColor

            rs_clothes = statement.executeQuery(get_woman_outer);
            rs_clothes.next();
            womanClothes.setOuter_image(rs_clothes.getString(5));
            womanClothes.setOuter_color(rs_clothes.getString(4));

            rs_clothes = statement.executeQuery(get_woman_bottom);
            rs_clothes.next();
            womanClothes.setBottom_image(rs_clothes.getString(5));
            womanClothes.setBottom_color(rs_clothes.getString(4));

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        Clothes clothes = new Clothes();
        clothes.setManClothes(manClothes);
        clothes.setWomanClothes(womanClothes);
        clothes.setClothes_description("Clothes_descriptionってDBから参照するの?");

        ResponseData responseData = new ResponseData();
        responseData.setWeather(weather);
        responseData.setClothes(clothes);
        responseData.setUser_place(location);

        return responseData;//springが勝手にJSONにしてくれるらしい
    }
//{"coord":{"lon":139.69,"lat":35.69},"weather":[{"id":803,"main":"Clouds","description":"broken clouds","icon":"04d"}],"base":"stations","main":{"temp":285.177,"pressure":1029.04,"humidity":65,"temp_min":285.177,"temp_max":285.177,"sea_level":1032.91,"grnd_level":1029.04},"wind":{"speed":0.45,"deg":38.0004},"clouds":{"all":56},"dt":1485670067,"sys":{"message":0.0077,"country":"JP","sunrise":1485639813,"sunset":1485677135},"id":1850147,"name":"Tokyo","cod":200}

    //以下jsに渡すデータ用のクラス
    private class ResponseData{
        @Getter @Setter
        private Weather weather;
        @Getter @Setter
        private Clothes clothes;
        @Getter @Setter
        private String user_place;
    }
    private class Weather{
        @Getter @Setter
        private String weather_icon;
        @Getter @Setter
        private String max_temperature;
        @Getter @Setter
        private String min_temperature;
        @Getter @Setter
        private String humidity;
    }
    private class Clothes{
        @Getter @Setter
        private String clothes_description;
        @Getter @Setter
        private ManClothes manClothes;
        @Getter @Setter
        private WomanClothes womanClothes;
    }
    private class ManClothes{
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
    private class WomanClothes{
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




