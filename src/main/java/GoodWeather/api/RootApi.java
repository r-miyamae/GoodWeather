package GoodWeather.api;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import javax.servlet.http.HttpServletRequest;

import lombok.Getter;
import lombok.Setter;

import java.sql.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class RootApi {
    private final RestTemplate rt = new RestTemplate();
    // お天気APIの URL
    private final String url = "http://api.openweathermap.org/data/2.5/weather?q=";

    //パラメータ
    private final String parameter = "&units=metric";
    // APIキー
    private final String token = "&APPID=0239e2e4fc8a5c4ac9f5ddfde9d4c477";

    // 外部サービスの JSON を加工して返却。
    @RequestMapping("/top")
    public ResponseData top(@RequestParam("loc") Optional<String> loc) {
        // Userの場所
        String location = "Tokyo,jp";
        if(loc.isPresent()){
            location = loc.get();
        }
        // APIからJSONを取得
        Gson gson = new Gson();
        String rawData = rt.getForObject(url + location + parameter + token, String.class);
        //受け取ったJSONを加工
        //APIから返されるままのJSONくん
        JsonObject rawJson = gson.fromJson(rawData, JsonObject.class);
        //天気情報(main)
        JsonObject weather_json = rawJson.getAsJsonArray("weather").get(0).getAsJsonObject();
        String weather_data = weather_json.getAsJsonPrimitive("main").toString();
        String weather_description = weather_json.getAsJsonPrimitive("description").toString();
        String weather_icon = weather_json.getAsJsonPrimitive("icon").toString();
        System.out.println(weather_description);
        switch (weather_data) {
            case "\"Clear\"":
                weather_icon = "Clear.png";
                break;
            case "\"Thunderstorm\"":
                weather_icon = "Thunderstorm.png";
                break;
            case "\"Snow\"":
                if (weather_description.equals("\"light snow\"")) {
                    weather_icon = "lightsnow.png";
                    break;
                }
                weather_icon = "Snow.png";
                break;
            case "\"Clouds\"":
                if (weather_description.equals("\"few clouds\"")) {
                    weather_icon = "fewclouds.png";
                }
                weather_icon = "Clouds.png";
                break;
            case "\"Rain\"":
                if (weather_description.equals("\"light lain\"")) {
                    weather_icon = "lightlain.png";
                    break;
                } else if (weather_description.equals("\"moderate rain\"")) {
                    weather_icon = "moderaterain.png";
                    break;
                }
            default:
                weather_icon = "Clouds.png";
        }
        //気温と湿度
        JsonObject main = rawJson.getAsJsonObject("main").getAsJsonObject();
        String temp_min_data = main.getAsJsonPrimitive("temp_min").toString();
        String temp_max_data = main.getAsJsonPrimitive("temp_max").toString();
        String humidity_data = main.getAsJsonPrimitive("humidity").toString();

        //不快指数の計算
        long day_uncomfortablePts = Math.round(0.81 * Double.parseDouble(temp_max_data) + 0.01 * Double.parseDouble(humidity_data) * (0.99 * Double.parseDouble(temp_max_data) - 14.3) + 46.3);
        long night_uncomfortablePts = Math.round(0.81 * Double.parseDouble(temp_min_data) + 0.01 * Double.parseDouble(humidity_data) * (0.99 * Double.parseDouble(temp_min_data) - 14.3) + 46.3);

        System.out.println(day_uncomfortablePts);
        System.out.println(night_uncomfortablePts);
        Weather weather = new Weather();
        weather.setMax_temperature(temp_max_data);
        weather.setMin_temperature(temp_min_data);
        weather.setHumidity(humidity_data);
        weather.setWeather_icon(weather_icon);

        // TODO: DBから天気に合った服装を選ぶ処理

        //DBに接続
        Connection connection = null;
        Statement statement = null;

        ManClothes manClothes = new ManClothes();
        WomanClothes womanClothes = new WomanClothes();
        Clothes clothes = new Clothes();

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

            //clothesDescription
            String get_day_clothes_description = "select description from clothes_description_table where " + day_uncomfortablePts + " >= minUncomfortablePts and " + day_uncomfortablePts + " <= maxUncomfortablePts";
            String get_night_clothes_description = "select description from clothes_description_table where " + night_uncomfortablePts + " >= minUncomfortablePts and " + night_uncomfortablePts + " <= maxUncomfortablePts";

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

            //clothes_description
            rs_clothes = statement.executeQuery(get_day_clothes_description);
            rs_clothes.next();
            clothes.setDay_clothes_description(rs_clothes.getString(1));

            rs_clothes = statement.executeQuery(get_night_clothes_description);
            rs_clothes.next();
            clothes.setNight_clothes_description(rs_clothes.getString(1));

            clothes.setManClothes(manClothes);
            clothes.setWomanClothes(womanClothes);

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
        private String day_clothes_description;
        @Getter @Setter
        private String night_clothes_description;
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




