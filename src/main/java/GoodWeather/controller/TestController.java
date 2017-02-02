package GoodWeather.controller;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.Reader;
import java.sql.*;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/test")
public class TestController {
    @RequestMapping
    @ResponseBody
//    public String index() {
//        return "これは /test のページです。";
//    }

    public String index() {
        Connection connection = null;
        Statement statement = null;

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:test.db");
            statement = connection.createStatement();
            String sql = "select * from cloth_genre_table";
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                System.out.println(rs.getString(1));
                System.out.println(rs.getString(2));
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