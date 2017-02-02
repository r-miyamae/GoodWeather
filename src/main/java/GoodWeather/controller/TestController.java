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

    List<Map<String, Object>> home() {
        String resource = "mybatis-config.xml";
        try (Reader in = Resources.getResourceAsReader(resource)) {
            // ★設定ファイルを元に、 SqlSessionFactory を作成する
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);

            // ★SqlSessionFactory から SqlSession を生成する
            SqlSession session = factory.openSession();
            // ★SqlSession を使って SQL を実行する
            List<Map<String, Object>> result = session.selectList("sample.mybatis.selectTest");
            return result;
        } catch (IOException e) {
            // nop
        }

        return null;
    }

}