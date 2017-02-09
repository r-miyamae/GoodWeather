package GoodWeather.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class SignInController {
    @RequestMapping("/user/signin")
    public String index() {
        return "html/signinページ";
    }
}
