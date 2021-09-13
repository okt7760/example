package com.okt.controller;

import com.okt.entity.Address;
import com.okt.entity.Empolyee;
import com.okt.service.AddressService;
import com.okt.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Create by obeeey on 2021/3/16
 * 即使再小的帆也能远航
 */
@Controller
@RequestMapping("/emplogin")
public class EmpLoginController {

    @Autowired
    EmpService empService;

    @Autowired
    AddressService addressService;

    @RequestMapping("/toEmpLogin")
    public String toLogin(){
        return "emp/elogin";
    }

    @RequestMapping("/login.do")
    public String login(String username, String password, HttpServletRequest request){
        Empolyee oneEmp = empService.findOneEmp(username);
        if(oneEmp!=null&&oneEmp.getF_password().equals(password)){
            HttpSession session = request.getSession();
            if("admin".equals(oneEmp.getF_username())){
                session.setAttribute("emp_admin", oneEmp);
                List<Address> allOneAddress = addressService.findAllOneAddress();
                List<Address> allTwoAddress = addressService.findAllTwoAddress();
                List<Address> allThreeAddress = addressService.findAllThreeAddress();
                List<Address> allFourAddress = addressService.findAllFourAddress();
                session.setAttribute("one",allOneAddress );
                session.setAttribute("two",allTwoAddress );
                session.setAttribute("three",allThreeAddress );
                session.setAttribute("four",allFourAddress );
                return "admin/adminmain";
            }
            session.setAttribute("emp_user", oneEmp);
            List<Address> allOneAddress = addressService.findAllOneAddress();
            List<Address> allTwoAddress = addressService.findAllTwoAddress();
            List<Address> allThreeAddress = addressService.findAllThreeAddress();
            List<Address> allFourAddress = addressService.findAllFourAddress();
            session.setAttribute("one",allOneAddress );
            session.setAttribute("two",allTwoAddress );
            session.setAttribute("three",allThreeAddress );
            session.setAttribute("four",allFourAddress );
            return "emp/empmain";
        }
        else{
            request.setAttribute("error", "输入的帐号或密码有误");
            return "emp/elogin";
        }
    }
    /**
     * 登出
     */
    @RequestMapping("/logout.do")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.invalidate();
        return "emp/elogin";
    }

    /**
     * 职工主页按钮
     * @return
     */
    @RequestMapping("/empMain")
    public String toMain(){
        return "emp/empmain";
    }
    /**
     * 管理员主页按钮
     * @return
     */
    @RequestMapping("/adminMain")
    public String toAdminMain(){
        return "admin/adminmain";
    }

    /**
     * 前往忘记密码页面
     * @return
     */
    @RequestMapping("/toForgot")
    public String toForgot(){
        return "emp/emp_forgot";
    }

    @RequestMapping("/recover.do")
    public String recover(String username, String uid, String newpwd, String repeatpwd, ModelMap map){
        Empolyee empolyee = empService.findOneEmp(username);
        //用户不存在
        if(empolyee==null){
            map.addAttribute("flag", "该用户不存在");
            return "emp/emp_forgot";
        }
        //身份证不对
        else if(!uid.equals(empolyee.getF_id())){
            map.addAttribute("flag", "身份证号码有误，请重新输入");
            return "emp/emp_forgot";
        }
        else if (!newpwd.equals(repeatpwd)){
            map.addAttribute("flag", "两次密码不相同，请重新输入");
            return "emp/emp_forgot";
        }
        empService.updatePassword(newpwd, username);
        map.addAttribute("flag", "修改成功，点击下方回到登录界面");
        return "emp/emp_success";
    }

    @RequestMapping("toUpdatePwd")
    public String toUpdatePwd(){
        return "emp/emp_updatepwd";
    }

    @RequestMapping("updatePwd.do")
    public String updatePassword(String password,String repeatPassword,HttpServletRequest request,ModelMap map){
        HttpSession session = request.getSession();
        Empolyee empolyee=(Empolyee) session.getAttribute("emp_user");
        if(password.equals(repeatPassword)){
            map.addAttribute("flag", "修改成功");
            empService.updatePassword(password, empolyee.getF_username());
            return "emp/emp_updatepwd";
        }
        else {
            map.addAttribute("flag", "两次输入密码不正确，请重新输入");
            return "emp/emp_updatepwd";
        }
    }



}


