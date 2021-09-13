package com.okt.controller;

import com.okt.entity.Park;
import com.okt.service.ParkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Create by obeeey on 2021/3/20
 * 即使再小的帆也能远航
 */
@Controller
@RequestMapping("park")
public class ParkController {
    @Autowired
    ParkService parkService;

    @RequestMapping("/toPark")
    public String toPark(ModelMap map){
        List<Park> allMargin = parkService.findAllMargin();
        map.addAttribute("list",allMargin);
        return "user/park";
    }

    /**
     * 管理员查看停车场
     */
    @RequestMapping("/adminToPark")
    public String adminToPark(ModelMap map){
        List<Park> allPark = parkService.findAll();
        map.addAttribute("list",allPark);
        return "admin/admin_park_all";
    }


    /**
     * 删除停车场
     */
    @RequestMapping("/del.do")
    public String delPark(@RequestParam("id") String id){
        parkService.delPark(id);
        return "redirect:/park/adminToPark";
    }

    /**
     * 新增停车场
     */
    @RequestMapping("/toNew")
    public String toNewPark(){
        return "admin/admin_park_write";
    }

    /**
     * 新增停车场
     */
    @RequestMapping("/new.do")
    public String insertPark(String id,String name,String margin,HttpServletRequest request){
       if(parkService.parkDupicate(id)==2){
            request.setAttribute("flag", "该编号已存在，请重新输入");
            return "admin/admin_park_write";
        }
       else {
           parkService.insertPark(id,name,margin);
           return "redirect:/park/adminToPark";
       }
    }


    /**
     * 员工查看停车场
     */
    @RequestMapping("/empToPark")
    public String empToPark(ModelMap map){
        List<Park> allPark = parkService.findAll();
        map.addAttribute("list",allPark);
        return "emp/emp_park_all";
    }

    /**
     * 余量增加（员工）
     */
    @RequestMapping("/add.do")
    public String addMargin(@RequestParam("id") String id){
        int max = parkService.findMarginMax(id);
        int margin=parkService.findMargin(id);
        if (max==margin){
            return "redirect:/park/empToPark";
        }
        parkService.addMargin(id);
        return "redirect:/park/empToPark";
    }


    /**
     * 余量减少（员工）
     */
    @RequestMapping("/reduce.do")
    public String reduceMargin(@RequestParam("id") String id, HttpServletRequest request){
        int margin = parkService.findMargin(id);
        if(margin==0){
            request.setAttribute("error", "已达到最小余量");
            return "redirect:/park/empToPark";
        }
        parkService.reduceMargin(id);
        return "redirect:/park/empToPark";
    }
}
