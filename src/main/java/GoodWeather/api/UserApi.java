package GoodWeather.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.*;

@RestController
@RequestMapping("/api/user")
public class UserApi {
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity.BodyBuilder signup(@RequestParam("mailAddress") String mailAddress,
                                 @RequestParam("password") String password,
                                 @RequestParam("location") String location){
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


    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ResponseEntity.BodyBuilder signin(HttpServletRequest request,
                                             HttpServletResponse response,
                                             @RequestParam("mailAddress") String mailAddress,
                                             @RequestParam("password") String password){
        //DBに接続
        Connection connection = null;
        Statement statement = null;
        PreparedStatement ps = null;

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:test.db");
            statement = connection.createStatement();

            String userInfo = "select * from users_table where mailAddress = ? and password = ?";
            ps = connection.prepareStatement(userInfo);
            ps.setString(1, mailAddress);
            ps.setString(2, password);

            ResultSet result = ps.executeQuery();
            if (result.next() != false){
                HttpSession session = request.getSession(true);
                session.setAttribute("mailAddress",mailAddress);
                System.out.println(session.getAttribute("mailAddress"));
                return ResponseEntity.status(200);
            }
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
        return ResponseEntity.status(402);
    }
}
