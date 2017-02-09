package GoodWeather.logic;

import java.sql.*;

public class userAuthenticate {
    public static String Authenticate(String mailAddress,String password){
        //DBに接続
        Connection connection = null;
        Statement statement = null;
        PreparedStatement ps;

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
                String location = result.getString(3);
                return location;
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
        return "error";
    }
}
