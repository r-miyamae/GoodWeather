package GoodWeather.api;

import com.sun.org.apache.xpath.internal.SourceTree;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
            int i = ps.executeUpdate();

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
                                             HttpServletResponse response,
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
                                             HttpServletResponse response,
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
    public ResponseEntity.BodyBuilder logout(HttpServletRequest request,
                                             HttpServletResponse response){
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
            return ResponseEntity.status(200);
        }
        return ResponseEntity.status(402);
    }
}
