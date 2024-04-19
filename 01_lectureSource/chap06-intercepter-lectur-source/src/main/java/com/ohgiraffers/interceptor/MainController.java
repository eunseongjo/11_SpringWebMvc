package com.ohgiraffers.interceptor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/*")
public class MainController {

    @RequestMapping("/")
    public String defaultLocation(){
        return "main"; }
    //전체에 들어오면 메인으로 포워딩

    @RequestMapping("/main")
    public  void main(){} //메인에 요청들어오면 메인페이지로 포워딩
}
