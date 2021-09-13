package com.okt.controller;

import com.okt.entity.User;
import com.okt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Create by obeeey on 2021/3/10
 * 即使再小的帆也能远航
 */
@Controller
@RequestMapping("/userlogin")
public class UserLoginController {
    @Autowired
    UserService userService;

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "user/ulogin";
    }

    @RequestMapping("login.do")
    public String login(String username, String password, HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = userService.findOne(username);
        if(user!=null&&user.getR_password().equals(password)){
            //帐号未审核
            if(user.getR_status()==0){
                request.setAttribute("flag", "您的帐号尚未审核,请耐心等待");
                return "user/ulogin";
            }
            if(user.getR_status()==2){
                request.setAttribute("flag", "您的帐号审核异常请查看审核状态");
                return "user/ulogin";
            }
            session.setAttribute("user", user);
            return "user/usermain";
        }
       else {
           //帐号密码错误
           request.setAttribute("flag", "您输入的帐号或密码有误,请重新输入");
           return "user/ulogin";
        }
    }
    /**
     * 登出
     */
    @RequestMapping("/logout.do")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        return "user/ulogin";
    }

}
