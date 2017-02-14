package GoodWeather.logic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class userAuthenticate {
    public static List<String> Authenticate(String mailAddress, String password){
        //DBに接続
        Connection connection = null;
        Statement statement = null;
        PreparedStatement ps;
        List <String> result_list = new ArrayList<>();
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:test.db");
            statement = connection.createStatement();

            String userInfo = "select * from users_table where mailAddress = ? and password = ?";
            ps = connection.prepareStatement(userInfo);
            ps.setString(1, mailAddress);
            ps.setString(2, password);

            ResultSet result = ps.executeQuery();
            if (result.next()){
                result_list.add(result.getString(3));
                result_list.add(result.getString(4));
                return result_list;
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
        return null;
    }
}
