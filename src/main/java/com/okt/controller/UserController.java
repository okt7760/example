package com.okt.controller;


import com.okt.entity.*;
import com.okt.service.AddressService;
import com.okt.service.CostService;
import com.okt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * Create by obeeey on 2021/3/10
 * 即使再小的帆也能远航
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    AddressService addressService ;
    @Autowired
    CostService costService;

    @RequestMapping("/toReg")
    public String toRegister(HttpServletRequest request){
        List<Address> allOneAddress = addressService.findAllOneAddress();
        List<Address> allTwoAddress = addressService.findAllTwoAddress();
        List<Address> allThreeAddress = addressService.findAllThreeAddress();
        List<Address> allFourAddress = addressService.findAllFourAddress();
        HttpSession session = request.getSession();
        session.setAttribute("one",allOneAddress );
        session.setAttribute("two",allTwoAddress );
        session.setAttribute("three",allThreeAddress );
        session.setAttribute("four",allFourAddress);
        System.out.println(allFourAddress);
        return "user/register";
    }

    /**
     * 注册
     * @param username
     * @param password
     * @param uid
     * @param phone
     * @param gender
     * @param name
     * @param addOne
     * @param addTwo
     * @param addThree
     * @param addFour
     * @param map
     * @return
     */
    @RequestMapping("/reg.do")
    public String register(String username, String password, String uid, String phone, int gender, String name, String addOne, String addTwo, String addThree, String addFour, ModelMap map){
        User user = new User();
        user.setR_username(username);
        user.setR_password(password);
        user.setR_name(name);
        user.setR_phone(phone);
        user.setR_uid(uid);
        user.setR_gender(gender);
        int flag = userService.addUser(user);
        if(flag==1){
            String firstName=addressService.findAddressOneNameById(addOne);
            String secondName=addressService.findAddressTwoNameById(addTwo);
            String thirdName=addressService.findAddressThreeNameById(addThree);
            String fourthName=addressService.findAddressFourNameById(addFour);
            addressService.addAddressAndUser(new UserAndAddress(addOne+addTwo+addThree+addFour,username,firstName,secondName,thirdName,fourthName==null?"":fourthName));
            map.addAttribute("flag", "注册成功，请等待审核");
            return "user/success";
        }
        //身份证相同
        else if(flag==2){
            map.addAttribute("flag", "该身份证已存在，请重新输入");
            return "user/register";
        }
        //帐号相同
        else {
            map.addAttribute("flag", "该账号已存在请重新输入");
            return "user/register";
        }
    }

    /**
     * 前往忘记密码页面
     * @return
     */
    @RequestMapping("/toForgot")
    public String toForgot(){
        return "user/forgot";
    }

    @RequestMapping("/recover.do")
    public String recover(String username,String uid,String newpwd,String repeatpwd,ModelMap map){
        User user = userService.findOne(username);
        //用户不存在
        if(user==null){
            map.addAttribute("flag", "该用户不存在");
            return "user/forgot";
        }
        //身份证不对
        else if(!uid.equals(user.getR_uid())){
            map.addAttribute("flag", "身份证号码有误，请重新输入");
            return "user/forgot";
        }
        else if (!newpwd.equals(repeatpwd)){
            map.addAttribute("flag", "两次密码不相同，请重新输入");
            return "user/forgot";
        }
        userService.updatePassword(newpwd, username);
        map.addAttribute("flag", "修改成功，点击下方回到登录界面");
        return "user/success";
    }

    /**
     * 前往审核页面
     * @return
     */
    @RequestMapping("/toCheck")
    public String toCheck(){
        return "user/stucheck";
    }

    /**
     * 审核查询
     * @param username
     * @param uid
     * @param map
     * @return
     */
    @RequestMapping("/check.do")
    public String check(String username,String uid,ModelMap map) {
        User user = userService.findOne(username);
        if (user == null) {
            map.addAttribute("flag", "帐号不存在");
            return "user/stucheck";
        }
        else if (!user.getR_uid().equals(uid)) {
            map.addAttribute("flag", "身份证号码不正确，请重新输入");
            return "user/stucheck";
        }
        else if (user.getR_status() == 1) {
            map.addAttribute("flag", "帐号审核完毕：成功");
            return "user/checkresult";
        }
        else if (user.getR_status() == 2) {
            userService.deleteUser(username);
            map.addAttribute("flag", "帐号审核完毕：业主信息有误请重新注册(仅显示一次)");
            return "user/checkresult";
        }
        else {
            map.addAttribute("flag", "帐号审核中：请等待");
        return "user/checkresult";
        }
    }

    /**
     * 用户个人资料
     */
    @RequestMapping("/toUserDetail")
    public String toUserDetail(HttpServletRequest request, ModelMap map){
        HttpSession session = request.getSession();
        User user=(User) session.getAttribute("user");
        List<UserAndAddress> addressList = user.getAddress();
        List<ParkFee> carList = costService.findOneParkFee(user.getR_username());
        for (UserAndAddress address : addressList) {
            address.setH_name(addressService.findAddressNameById(address.getH_id()));
        }
        map.addAttribute("addressList", addressList);
        map.addAttribute("detail",user);
        map.addAttribute("carList",carList);
        return "user/user_detail";
    }


    /**
     * 员工个人资料
     */
    @RequestMapping("/toEmpDetail")
    public String toEmpDetail(HttpServletRequest request, ModelMap map){
        HttpSession session = request.getSession();
        Empolyee empolyee=(Empolyee) session.getAttribute("emp_user");
        map.addAttribute("detail",empolyee);
        return "emp/emp_detail";
    }

    /**
     * 修改密码页面
     */
    @RequestMapping("/toUpdatePwd")
    public String toUpdatePassword(){
        return "user/updatePwd";
    }

    /**
     * 修改密码
     */
    @RequestMapping("/updatePwd.do")
    public String updatePassword(String password,String repeatPassword,HttpServletRequest request,ModelMap map){
        HttpSession session = request.getSession();
        User user=(User) session.getAttribute("user");
         if(password.equals(repeatPassword)){
            map.addAttribute("flag", "修改成功");
            userService.updatePassword(password, user.getR_username());
            return "user/updatepwd";
        }
        else {
            map.addAttribute("flag", "两次输入密码不正确，请重新输入");
            return "user/updatepwd";
        }
    }


    /**
     * 管理员个人资料
     */
    @RequestMapping("/toAdminDetail")
    public String toAdminDetail(HttpServletRequest request, ModelMap map){
        HttpSession session = request.getSession();
        Empolyee empolyee=(Empolyee) session.getAttribute("emp_admin");
        map.addAttribute("detail",empolyee);
        return "admin/admin_detail";
    }

    /**
     * 前往主页
     */
    @RequestMapping("/toMain")
    public String main(){
        return "user/usermain";
    }
}
