package GoodWeather.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.Getter;
import lombok.Setter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.*;
import java.util.Optional;

import GoodWeather.model.*;

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
    public ResponseData top(HttpServletRequest request,
                            @RequestParam("loc") Optional<String> loc) {
        String location = "Tokyo,jp";
        HttpSession session = request.getSession(false);
        //ログイン中ならlocationを設定
        if(session != null){
            location = (String)session.getAttribute("location");
        }
        // Userの場所
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
                if (weather_description.equals("\"light rain\"")) {
                    weather_icon = "lightrain.png";
                    System.out.println("lightrain");
                    break;
                } else if (weather_description.equals("\"moderate rain\"")) {
                    weather_icon = "moderaterain.png";
                    break;
                }
            default:
                System.out.println("default");
                weather_icon = "Clouds.png";
        }
        //気温と湿度
        JsonObject main = rawJson.getAsJsonObject("main").getAsJsonObject();
        String temp_min_data = main.getAsJsonPrimitive("temp_min").toString();
        String temp_max_data = main.getAsJsonPrimitive("temp_max").toString();
        String humidity_data = main.getAsJsonPrimitive("humidity").toString();

        //不快指数の計算
        long day_uncomfortablePts = Math.round(0.81 * Double.parseDouble(temp_max_data)
                + 0.01 * Double.parseDouble(humidity_data)
                * (0.99 * Double.parseDouble(temp_max_data) - 14.3) + 46.3);
        long night_uncomfortablePts = Math.round(0.81 * Double.parseDouble(temp_min_data)
                + 0.01 * Double.parseDouble(humidity_data)
                * (0.99 * Double.parseDouble(temp_min_data) - 14.3) + 46.3);

        Weather weather = new Weather();
        weather.setMax_temperature(temp_max_data);
        weather.setMin_temperature(temp_min_data);
        weather.setHumidity(humidity_data);
        weather.setWeather_icon(weather_icon);

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

            //DBから不快指数に応じた服を選ぶ
            String get_man_inner = "";
            String get_man_outer = "";
            String get_man_bottom = "";

            String get_woman_inner = "";
            String get_woman_outer = "";
            String get_woman_bottom = "";
            String user_clothes;
            Boolean user_clothes_exist = false;
            if(session != null){
                user_clothes = "select * from user_clothes where mailAddress= \"" + session.getAttribute("mailAddress").toString() + "\"";
                ResultSet rs_user_clothes = statement.executeQuery(user_clothes);
                if(rs_user_clothes.next() != false){
                    user_clothes_exist = true;
                }
            }

            if(day_uncomfortablePts <= 54) {//冬服
                if(user_clothes_exist){
                    get_man_inner = "select * from user_clothes where clothGenreId LIKE '_02' and mailAddress= \"" + session.getAttribute("mailAddress").toString() + "\"" + " ORDER BY RANDOM()";
                    get_man_outer = "select * from user_clothes where clothGenreId LIKE '_12' and mailAddress= \"" + session.getAttribute("mailAddress").toString() + "\"" + " ORDER BY RANDOM()";
                    get_man_bottom = "select * from user_clothes where clothGenreId LIKE '_22' and mailAddress= \"" + session.getAttribute("mailAddress").toString() + "\"" + " ORDER BY RANDOM()";
                }else{
                    get_man_inner = "select * from sample_clothes where clothGenreId LIKE '102' ORDER BY RANDOM()";//漢インナー(厚)
                    get_man_outer = "select * from sample_clothes where clothGenreId LIKE '112' ORDER BY RANDOM()";//漢アウター(厚)
                    get_man_bottom = "select * from sample_clothes where clothGenreId LIKE '122' ORDER BY RANDOM()";//漢ボトム(長)

                    get_woman_inner = "select * from sample_clothes where clothGenreId LIKE '202' ORDER BY RANDOM()";//女インナー(厚)
                    get_woman_outer = "select * from sample_clothes where clothGenreId LIKE '212' ORDER BY RANDOM()";//女アウター(厚)
                    get_woman_bottom = "select * from sample_clothes where clothGenreId LIKE '222' ORDER BY RANDOM()";//女ボトム(長)
                }
            }else if(day_uncomfortablePts >= 75){//夏服
                if(user_clothes_exist){
                    get_man_inner = "select * from user_clothes where clothGenreId LIKE '_01' and mailAddress= \"" + session.getAttribute("mailAddress").toString() + "\"" + " ORDER BY RANDOM()";
                    get_man_outer = "select * from user_clothes where clothGenreId LIKE '_11' and mailAddress= \"" + session.getAttribute("mailAddress").toString() + "\"" + " ORDER BY RANDOM()";
                    get_man_bottom = "select * from user_clothes where clothGenreId LIKE '_2_' and mailAddress= \"" + session.getAttribute("mailAddress").toString() + "\"" + " ORDER BY RANDOM()";
                }else {
                    get_man_inner = "select * from sample_clothes where clothGenreId LIKE '101' ORDER BY RANDOM()";//漢インナー(薄)
                    get_man_outer = "select * from sample_clothes where clothGenreId LIKE '111' ORDER BY RANDOM()";//漢アウター(薄)
                    get_man_bottom = "select * from sample_clothes where clothGenreId LIKE '12_' ORDER BY RANDOM()";//漢ボトム(短)or(長)

                    get_woman_inner = "select * from sample_clothes where clothGenreId LIKE '201' ORDER BY RANDOM()";//女インナー(薄)
                    get_woman_outer = "select * from sample_clothes where clothGenreId LIKE '211' ORDER BY RANDOM()";//女アウター(薄)
                    get_woman_bottom = "select * from sample_clothes where clothGenreId LIKE '22_' ORDER BY RANDOM()";//女ボトム(短)or(長)
                }
            }else{//春、秋服
                if(user_clothes_exist){
                    get_man_inner = "select * from user_clothes where clothGenreId LIKE '_0_' and mailAddress= \"" + session.getAttribute("mailAddress").toString() + "\"" + " ORDER BY RANDOM()";
                    get_man_outer = "select * from user_clothes where clothGenreId LIKE '_11' and mailAddress= \"" + session.getAttribute("mailAddress").toString() + "\"" + " ORDER BY RANDOM()";
                    get_man_bottom = "select * from user_clothes where clothGenreId LIKE '_22' and mailAddress= \"" + session.getAttribute("mailAddress").toString() + "\"" + " ORDER BY RANDOM()";
                }else {
                    get_man_inner = "select * from sample_clothes where clothGenreId LIKE '10_' ORDER BY RANDOM()";//漢インナー(薄)or(厚)
                    get_man_outer = "select * from sample_clothes where clothGenreId LIKE '111' ORDER BY RANDOM()";//漢アウター(薄)
                    get_man_bottom = "select * from sample_clothes where clothGenreId LIKE '122' ORDER BY RANDOM()";//漢ボトム(長)

                    get_woman_inner = "select * from sample_clothes where clothGenreId LIKE '20_' ORDER BY RANDOM()";//女インナー(薄)or(厚)
                    get_woman_outer = "select * from sample_clothes where clothGenreId LIKE '211' ORDER BY RANDOM()";//女アウター(薄)
                    get_woman_bottom = "select * from sample_clothes where clothGenreId LIKE '222' ORDER BY RANDOM()";//女ボトム(長)
                }
            }

            if(user_clothes_exist){
                //ユーザが登録している服を格納
                ResultSet rs_user_clothes = statement.executeQuery(get_man_inner);
                if(rs_user_clothes.next()){
                    manClothes.setInner_image(rs_user_clothes.getString(6));//(5):clothIcon
                    manClothes.setInner_color(rs_user_clothes.getString(5));//(4):clothColor
                }
                rs_user_clothes = statement.executeQuery(get_man_outer);
                if(rs_user_clothes.next()){
                    manClothes.setOuter_image(rs_user_clothes.getString(6));
                    manClothes.setOuter_color(rs_user_clothes.getString(5));
                }
                rs_user_clothes = statement.executeQuery(get_man_bottom);
                if(rs_user_clothes.next()){
                    manClothes.setBottom_image(rs_user_clothes.getString(6));
                    manClothes.setBottom_color(rs_user_clothes.getString(5));
                }
            }else{
                //サンプルの服を格納
                //漢
                ResultSet rs_clothes = statement.executeQuery(get_man_inner);
                rs_clothes.next();//.next:値が無いとfalseを返すのでwhileで全部出すことも可能
                manClothes.setInner_image(rs_clothes.getString(5));//(5):clothIcon
//            manClothes.setInner_color(rs_clothes.getString(4));//(4):clothColor

                rs_clothes = statement.executeQuery(get_man_outer);
                rs_clothes.next();
                manClothes.setOuter_image(rs_clothes.getString(5));
//            manClothes.setOuter_color(rs_clothes.getString(4));

                rs_clothes = statement.executeQuery(get_man_bottom);
                rs_clothes.next();
                manClothes.setBottom_image(rs_clothes.getString(5));
//            manClothes.setBottom_color(rs_clothes.getString(4));

//            rs_man.getString(1)//clothId
//            rs_man.getString(2)//clothGenreId
//            rs_man.getString(3)//clothName
//            rs_man.getString(4)//clothColor
//            rs_man.getString(5)//clothIcon

                //女
                rs_clothes = statement.executeQuery(get_woman_inner);
                rs_clothes.next();
                womanClothes.setInner_image(rs_clothes.getString(5));//(5):clothIcon
//            womanClothes.setInner_color(rs_clothes.getString(4));//(4):clothColor

                rs_clothes = statement.executeQuery(get_woman_outer);
                rs_clothes.next();
                womanClothes.setOuter_image(rs_clothes.getString(5));
//            womanClothes.setOuter_color(rs_clothes.getString(4));

                rs_clothes = statement.executeQuery(get_woman_bottom);
                rs_clothes.next();
                womanClothes.setBottom_image(rs_clothes.getString(5));
//            womanClothes.setBottom_color(rs_clothes.getString(4));

                //recommendColor
                String get_recommend_color = "select * from Color_table ORDER BY RANDOM()";
                rs_clothes = statement.executeQuery(get_recommend_color);
                rs_clothes.next();
                manClothes.setInner_color(rs_clothes.getString(2));
                manClothes.setOuter_color(rs_clothes.getString(3));
                manClothes.setBottom_color(rs_clothes.getString(4));

                rs_clothes.next();
                womanClothes.setInner_color(rs_clothes.getString(2));
                womanClothes.setOuter_color(rs_clothes.getString(3));
                womanClothes.setBottom_color(rs_clothes.getString(4));
            }

            //clothesDescription
            String get_day_clothes_description = "select description from clothes_description_table where "
                    + day_uncomfortablePts + " >= minUncomfortablePts and "
                    + day_uncomfortablePts + " <= maxUncomfortablePts";
            String get_night_clothes_description = "select description from clothes_description_table where "
                    + night_uncomfortablePts + " >= minUncomfortablePts and "
                    + night_uncomfortablePts + " <= maxUncomfortablePts";
            ResultSet rs_clothes = statement.executeQuery(get_day_clothes_description);
            rs_clothes.next();
            clothes.setDay_clothes_description(rs_clothes.getString(1));

            rs_clothes = statement.executeQuery(get_night_clothes_description);
            rs_clothes.next();
            clothes.setNight_clothes_description(rs_clothes.getString(1));

            clothes.setMan_clothes(manClothes);
            clothes.setWoman_clothes(womanClothes);

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
        responseData.setUser_gender(session.getAttribute("gender").toString());
        System.out.println(responseData.toString());
        return responseData;//springが勝手にJSONにしてくれるらしい
    }

    //以下jsに渡すデータ用のクラス
    private class ResponseData{
        @Getter @Setter
        private Weather weather;
        @Getter @Setter
        private Clothes clothes;
        @Getter @Setter
        private String user_place;
        @Getter @Setter
        private String user_gender;
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
}