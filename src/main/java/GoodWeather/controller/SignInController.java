package GoodWeather.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signin")
public class SignInController {
    @RequestMapping("/signin")
    public String index() {
        return "html/signinページ";
    }
}
