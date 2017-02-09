package GoodWeather.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class SignUpController {
    @RequestMapping("/signup")
    public String index() {
        return "html/signupページ";
    }
}
