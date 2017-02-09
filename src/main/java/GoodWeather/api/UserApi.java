package GoodWeather.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
