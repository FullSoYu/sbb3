package com.mysite.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @RequestMapping("/sbb")
    @ResponseBody
    public String index() {
        System.out.println("정상 동작");
        return "정상 출력";
    }

    @GetMapping("/page1")
    @ResponseBody
    public String showPage1() {
        return """
                <form method = "POST" action="/page1">
                    <input type='number' />
                    <input type = "submit" value = "page1로 POST방식으로 이동" />
                </form>
                """;


    }

    @PostMapping("/page1")
    @ResponseBody
    public String showPage2() {
        return """
                <form method = "POST" action= "/page3">
                <h1> 입력된 나이 : %d</h1>
                <h1> POST 방식으로 옴 </h1>
                <input type = "submit" value = "page3로 POST방식으로 이동" />
                </form>   
                """;
    }
    @PostMapping("/page3")
    @ResponseBody
    public String showPage3() {
        return """
                <h1> POST방식(2)으로옴</h1>
                """;
    }


}
