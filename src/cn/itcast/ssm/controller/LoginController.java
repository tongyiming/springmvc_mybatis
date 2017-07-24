package cn.itcast.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by tym on 17-7-22.
 */
@Controller
public class LoginController {

    //登陆
    @RequestMapping("/login")
    public String login(HttpSession ssession,String username,String password)throws Exception{

        ssession.setAttribute("username",username);

        return "redirect:/items/queryItems.action";
    }

    //退出
    @RequestMapping("/logout")
    public String logout(HttpSession session)throws Exception{

        //清除session
        session.invalidate();

        return "redirect:/items/queryItems";
    }
}
