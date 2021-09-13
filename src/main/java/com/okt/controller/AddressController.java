package com.okt.controller;

import com.okt.entity.Address;
import com.okt.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Create by obeeey on 2021/3/23
 * 即使再小的帆也能远航
 */
@Controller
@RequestMapping("/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    /**
     * 前往地址管理
     */
    @RequestMapping("/toAddressFirst")
    public String toAddressFirst(ModelMap map){
        List<Address> allOneAddress = addressService.findAllOneAddress();
        map.addAttribute("first", allOneAddress);
        return "admin/address_management_first";
    }

    /**
     * 前往二级地址管理
     */
    @RequestMapping("/toAddressSecond")
    public String toAddressSecond(ModelMap map){
        List<Address> allTwoAddress = addressService.findAllTwoAddress();
        map.addAttribute("second", allTwoAddress);
        return "admin/address_management_second";
    }

    /**
     * 前往三级地址管理
     */
    @RequestMapping("/toAddressThird")
    public String toAddressThird(ModelMap map){
        List<Address> allThreeAddress = addressService.findAllThreeAddress();
        map.addAttribute("third", allThreeAddress);
        return "admin/address_management_third";
    }

    /**
     * 前往四级地址管理
     */
    @RequestMapping("/toAddressFourth")
    public String toAddressFourth(ModelMap map){
        List<Address> allFourAddress = addressService.findAllFourAddress();
        map.addAttribute("fourth", allFourAddress);
        return "admin/address_management_fourth";
    }

    /**
     * 前往地址添加页面
     */
    @RequestMapping("/toAdd")
    public String toAddAddress(@RequestParam("addMode") String addMode,ModelMap map){
        System.out.println(addMode);
        map.addAttribute("mode", addMode);
        return "admin/address_management_add";
    }

    /**
     * 添加地址操作
     */
    @RequestMapping("/add.do")
    public String addOne(@RequestParam(value = "table",required = true) String tableName, String id, String name,ModelMap map){
        System.out.println(tableName);
        if(addressService.findAddressByIdInTable(id,tableName)){
            addressService.addAddress(tableName,id,name);
            if("one".equals(tableName)){return "redirect:/address/toAddressFirst";}
            else if("two".equals(tableName)){return "redirect:/address/toAddressSecond";}
            else if("three".equals(tableName)){return "redirect:/address/toAddressThird";}
            else if("four".equals(tableName)){return "redirect:/address/toAddressFourth";}
            else {
                return "redirect:/address/toAddressFirst";
            }
        }
        else {
            map.addAttribute("flag", "该地址编号已存在");
            map.addAttribute("mode", tableName);
            return "admin/address_management_add";
        }
    }





    /**
     * 删除地址操作
     */
    @RequestMapping("/delAddress")
    public String delAddress(@RequestParam(value = "id",required = true)String id,@RequestParam(value = "mode",required = true)int mode){
        addressService.delAddress(id,mode);
       if (mode==1){
           return "redirect:/address/toAddressFirst";
       }
       else if(mode==2){
           return "redirect:/address/toAddressSecond";
       }
       else if(mode==3){
           return "redirect:/address/toAddressThird";
       }
       else if(mode==4){
           return "redirect:/address/toAddressFourth";
       }
       else {
           return "404";
       }
    }

    @RequestMapping("/returnAddressManagement")
    public String returnAddressManagement(@RequestParam(value = "mode",required = true)String mode){
        if ("one".equals(mode)){
            return "redirect:/address/toAddressFirst";
        }
        else if("two".equals(mode)){
            return "redirect:/address/toAddressSecond";
        }
        else if("three".equals(mode)){
            return "redirect:/address/toAddressThird";
        }
        else if("four".equals(mode)){
            return "redirect:/address/toAddressFourth";
        }
        else {
            return "redirect:/address/toAddressFourth";
        }
    }

}
