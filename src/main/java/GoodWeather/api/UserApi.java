package GoodWeather.api;

import GoodWeather.model.HttpStatus400Exeption;
import GoodWeather.model.HttpStatus200;
import GoodWeather.model.ManClothes;
import GoodWeather.model.UserClothes;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import GoodWeather.logic.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserApi {
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity.BodyBuilder signup(@RequestParam("mailAddress") String mailAddress,
                                 @RequestParam("password") String password,
                                 @RequestParam("gender") String gender,
                                 @RequestParam("loc") String location){
        //DBに接続
        Connection connection = null;
        Statement statement = null;
        PreparedStatement ps = null;

        // パスワードを暗号化する
        String encodedPassword = passwordEncode.encrypt(password);

//        StandardPasswordEncoder encoder = new StandardPasswordEncoder()
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:test.db");
            statement = connection.createStatement();
            System.out.println(password);
            System.out.println(encodedPassword);
            String sql = "INSERT INTO users_table values(?, ?, ?, ?)";
            //DBユーザデータつっこむ
            ps = connection.prepareStatement(sql);
            ps.setString(1, mailAddress);
            ps.setString(2, encodedPassword);
            ps.setString(3, location);
            ps.setString(4, gender);
            ps.executeUpdate();

            throw new HttpStatus200();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new HttpStatus400Exeption();
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
        throw new HttpStatus400Exeption();
    }


    @RequestMapping(value = "/signin", method = RequestMethod.GET)
    public ResponseEntity.BodyBuilder isUser(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if(session != null){
            throw new  HttpStatus200();
        }else{
            throw new HttpStatus400Exeption();
        }
    }


    //login処理
    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ResponseEntity.BodyBuilder signin(HttpServletRequest request,
                                             @RequestParam("mailAddress") String mailAddress,
                                             @RequestParam("password") String password){
        String encordedPassword = passwordEncode.encrypt(password);
        List <String> result = userAuthenticate.Authenticate(mailAddress,encordedPassword);
            if (result != null){
                System.out.println(result);
                //sessionの開始
                HttpSession session = request.getSession(true);
                //sessionにmailAddressとlocation追加
                session.setAttribute("mailAddress",mailAddress);
                session.setAttribute("location", result.get(0));
                session.setAttribute("gender", result.get(1));

                throw new HttpStatus200();
            }
        throw new HttpStatus400Exeption();
    }


    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ResponseEntity.BodyBuilder edit(HttpServletRequest request,
                                           @RequestParam("oldPassword") Optional<String> oldPassword,
                                           @RequestParam("newPassword") Optional<String> newPassword,
                                           @RequestParam("loc") Optional<String> loc){
        HttpSession session = request.getSession(false);
        if(session != null){
            List <String> result = userAuthenticate.Authenticate((String)session.getAttribute("mailAddress"),oldPassword.get());
            if (result == null){
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
                    throw new HttpStatus200();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new HttpStatus400Exeption();
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
        throw new HttpStatus400Exeption();
    }


    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public ResponseEntity.BodyBuilder logout(HttpServletRequest request){
        HttpSession session = request.getSession(false);
            if(session != null){
                session.invalidate();
                throw new HttpStatus200();
            }
        throw new HttpStatus400Exeption();
    }


    @RequestMapping(value = "/clothes", method = RequestMethod.GET)
    public String clothesEdit(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            //DBに接続
            Connection connection = null;
            Statement statement = null;
            PreparedStatement ps = null;
            try {
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection("jdbc:sqlite:test.db");
                statement = connection.createStatement();
                String mailAddress = session.getAttribute("mailAddress").toString();
                String sql = "select * from user_clothes where mailAddress = \'" + session.getAttribute("mailAddress") +"\'";
                ResultSet rs_clothes = statement.executeQuery(sql);
                List<UserClothes> clothesList = new ArrayList<>();
                while(rs_clothes.next()){
                    UserClothes clothes = new UserClothes();
                    clothes.setClothId(rs_clothes.getInt(1));
                    clothes.setMailAddress(rs_clothes.getString(2));
                    clothes.setClothGenreId(rs_clothes.getString(3));
                    clothes.setClothName(rs_clothes.getString(4));
                    clothes.setClothColor(rs_clothes.getString(5));
                    clothes.setClothIcon(rs_clothes.getString(6));
                    clothesList.add(clothes);
                }
                Gson gson = new Gson();
                return gson.toJson(clothesList);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new HttpStatus400Exeption();
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
                try {
                    if (ps != null) {
                        ps.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        throw new HttpStatus400Exeption();
    }


    @RequestMapping(value = "/clothes", method = RequestMethod.POST, produces="application/json; charset=UTF-8")
    public ResponseEntity.BodyBuilder clothes(HttpServletRequest request,
                                              @RequestBody String json)throws UnsupportedEncodingException{
        HttpSession session = request.getSession(false);
//        HttpSession session = request.getSession(true);
//        session.setAttribute("mailAddress","aaa@a.jp");
        System.out.println(json);
        if(session != null){
            Gson gson = new Gson();
            String decodedResult = URLDecoder.decode(json, "UTF-8");
            JsonObject userClothes = gson.fromJson(decodedResult,JsonObject.class);
            String clothName = "";
            String clothColor = userClothes.getAsJsonPrimitive("color").toString().replace("\"","");
            String clothGenre = userClothes.getAsJsonPrimitive("genre").toString().replace("\"","");
            String clothIcon = userClothes.getAsJsonPrimitive("icon").toString().replace("\"","");
            System.out.println(clothIcon);
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
                throw new HttpStatus200();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new HttpStatus400Exeption();
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
        throw new HttpStatus400Exeption();
    }


    @RequestMapping(value = "/clothes", method = RequestMethod.DELETE)
    public ResponseEntity.BodyBuilder delete(HttpServletRequest request,
                                             @RequestParam("id") String id){
        HttpSession session = request.getSession(false);
        if(session != null){
            //DBに接続
            Connection connection = null;
            Statement statement = null;
            PreparedStatement ps = null;
            try {
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection("jdbc:sqlite:test.db");
                statement = connection.createStatement();

                String sql = "select * from user_clothes where clothId = \'" + id +"\'";
                ResultSet rs_user_clothes = statement.executeQuery(sql);
                rs_user_clothes.next();
                rs_user_clothes.deleteRow();
                connection.commit();
                throw new HttpStatus200();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new HttpStatus400Exeption();
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
                try {
                    if (ps != null) {
                        ps.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        throw new HttpStatus400Exeption();
    }
}
