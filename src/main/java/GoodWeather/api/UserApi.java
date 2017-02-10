package GoodWeather.api;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.*;
import java.util.Optional;

import GoodWeather.logic.userAuthenticate;

@RestController
@RequestMapping("/api/user")
public class UserApi {
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity.BodyBuilder signup(@RequestParam("mailAddress") String mailAddress,
                                 @RequestParam("password") String password,
                                 @RequestParam("loc") String location){
        //DBに接続
        Connection connection = null;
        Statement statement = null;
        PreparedStatement ps = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:test.db");
            statement = connection.createStatement();

            String sql = "INSERT INTO users_table values(?, ?, ?)";
            //DBユーザデータつっこむ
            ps = connection.prepareStatement(sql);
            ps.setString(1, mailAddress);
            ps.setString(2, password);
            ps.setString(3, location);
            ps.executeUpdate();

            return ResponseEntity.status(200);

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
            try{
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
            return ResponseEntity.status(402);
    }

    //login処理
    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ResponseEntity.BodyBuilder signin(HttpServletRequest request,
                                             @RequestParam("mailAddress") String mailAddress,
                                             @RequestParam("password") String password){
        String result = userAuthenticate.Authenticate(mailAddress,password);
            if (result != "error"){
                System.out.println(result);
                //sessionの開始
                HttpSession session = request.getSession(true);
                //sessionにmailAddressとlocation追加
                session.setAttribute("mailAddress",mailAddress);
                session.setAttribute("location", result);
                return ResponseEntity.status(200);
            }
        return ResponseEntity.status(402);
    }


    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ResponseEntity.BodyBuilder edit(HttpServletRequest request,
                                           @RequestParam("oldPassword") Optional<String> oldPassword,
                                           @RequestParam("newPassword") Optional<String> newPassword,
                                           @RequestParam("loc") Optional<String> loc){
        HttpSession session = request.getSession(false);
        if(session != null){
            String result = userAuthenticate.Authenticate((String)session.getAttribute("mailAddress"),oldPassword.get());
            if (result != "error"){
                Connection connection = null;
                Statement statement = null;
                PreparedStatement ps;
                try {
                    Class.forName("org.sqlite.JDBC");
                    connection = DriverManager.getConnection("jdbc:sqlite:test.db");
                    statement = connection.createStatement();
                    String sql;
                    //パスワード更新処理
                    if(newPassword.isPresent()){
                        String userPassword = newPassword.get();
                        sql = "update users_table set password = ? where mailAddress = ?";
                        ps = connection.prepareStatement(sql);
                        ps.setString(1, userPassword);
                        ps.setString(2, (String)session.getAttribute("mailAddress"));
                        ps.executeUpdate();
                    }
                    //location更新処理
                    if(loc.isPresent()){
                        String userLocation = loc.get();
                        sql = "update users_table set location = ? where mailAddress = ?";
                        ps = connection.prepareStatement(sql);
                        ps.setString(1, userLocation);
                        ps.setString(2, (String)session.getAttribute("mailAddress"));
                        ps.executeUpdate();
                    }
                    return ResponseEntity.status(200);
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
             }
        }
        return ResponseEntity.status(402);
    }


    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResponseEntity.BodyBuilder logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
            if(session != null){
                session.invalidate();
                return ResponseEntity.status(200);
            }
            return ResponseEntity.status(402);
    }

    @RequestMapping(value = "/clothes", method = RequestMethod.POST, produces="application/json; charset=UTF-8")
    public ResponseEntity.BodyBuilder clothes(HttpServletRequest request,
                                              @RequestBody String json)throws UnsupportedEncodingException{
        HttpSession session = request.getSession(false);
//        HttpSession session = request.getSession(true);
//        session.setAttribute("mailAddress","aaa@a.jp");

        if(session != null){
            Gson gson = new Gson();
            String decodedResult = URLDecoder.decode(json, "UTF-8");
            decodedResult = decodedResult.substring(0,decodedResult.length()-1);
            JsonObject userClothes = gson.fromJson(decodedResult,JsonObject.class);
            String clothName = "";
            String clothColor = userClothes.getAsJsonPrimitive("color").toString();
            String clothGenre = userClothes.getAsJsonPrimitive("genre").toString();
            String clothIcon = userClothes.getAsJsonPrimitive("icon").toString();

            Connection connection = null;
            Statement statement = null;
            PreparedStatement ps;
            try {
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection("jdbc:sqlite:test.db");
                statement = connection.createStatement();
                String sql;
                if(clothName == ""){
                    sql = "select clothGenreName from CLOTH_GENRE_TABLE where clothGenreId = " + clothGenre;
                    ResultSet rs = statement.executeQuery(sql);
                    rs.next();
                    clothName = rs.getString(1);
                }else{
                    clothName = userClothes.getAsJsonPrimitive("name").toString();
                }
                //ユーザーの衣服登録処理
                sql = "insert into user_clothes(mailAddress,clothGenreId,clothName,clothColor,clothIcon) values(?,?,?,?,?)";
                ps = connection.prepareStatement(sql);
                ps.setString(1, (String)session.getAttribute("mailAddress"));
                ps.setString(2, clothGenre);
                ps.setString(3, clothName);
                ps.setString(4, clothColor);
                ps.setString(5, clothIcon);
                ps.executeUpdate();
                return ResponseEntity.status(200);
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
        }
        return ResponseEntity.status(402);
    }
}
