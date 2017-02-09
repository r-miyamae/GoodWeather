package GoodWeather.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class UserInfoEditController {
    @RequestMapping("/user/edit")
    public String index() {
        return "html/userEditページ";
    }
}


