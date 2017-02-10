package GoodWeather.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class UserClothesController {
    @RequestMapping("/clothes")
    public String index(HttpServletRequest request,
                        HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        if(session == null){
            return "html/loginページ";
        }
        return "html/userClothesページ";
    }
}
