package com.okt.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.okt.entity.Empolyee;
import com.okt.entity.Inform;
import com.okt.service.EmpService;
import com.okt.service.InformService;
import com.okt.util.DateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Create by obeeey on 2021/3/20
 * 即使再小的帆也能远航
 */
@Controller
@RequestMapping("/inform")
public class InformController {

    @Autowired
    InformService informService;
    @Autowired
    EmpService empService;

    /**
     * 用户进入通知管理页面
     * @param modelMap
     * @return
     */
    @RequestMapping("/toInform")
    public String toInform(ModelMap modelMap,@RequestParam(defaultValue = "1")int page){
        PageHelper.startPage(page, 10);
        List<Inform> allInform = informService.findAllInform(1);
        PageInfo<Inform> informPageInfo = new PageInfo<>(allInform);
        modelMap.addAttribute("pageInfo", informPageInfo);
        return "user/inform";
    }

    /**
     * 用户进入通知详情
     * @param inf_id
     * @param map
     * @return
     */
    @RequestMapping("/detail")
    public String toInformDetail(@RequestParam(value = "inf_id",required = true)String inf_id,ModelMap map){
        Inform inform = informService.informDetail(inf_id);
        inform.setDesc_date_str(DateFormat.dateToString(inform.getDesc_date(), "yyyy-MM-dd"));
        inform.setInf_writer_str(empService.findOneEmp(inform.getInf_writer()).getF_name());
        map.addAttribute("list", inform);
        return "user/inform_detail";
    }

    /**
     * 前往编写通知
     */
    @RequestMapping("/toWrite")
    public String empToWriteInform(){
        return "emp/emp_inform_write";
    }

    /**
     * 提交编写
     */
    @RequestMapping("/write.do")
    public String writeInform(String title, String context,HttpServletRequest request){
        HttpSession session = request.getSession();
        Empolyee user = (Empolyee) session.getAttribute("emp_user");
        informService.insertInform(title,context,user.getF_username(),1);
        return "redirect:/inform/toOwnInform";
    }

    /**
     * 删除通知
     */
    @RequestMapping("/del.do")
    public String delInform(@RequestParam("id") String id){
        informService.delInformById(id);
        return "redirect:/inform/toOwnInform";
    }
    /**
     * 查看全部发布的通知
     */
    @RequestMapping("/empToAll")
    public String empAllInform(ModelMap map,@RequestParam(defaultValue = "1")int page){
        PageHelper.startPage(page, 10);
        List<Inform> informs = informService.findAllInform(1);
        for (Inform inform : informs) {
            inform.setInf_writer_str(empService.findOneEmp(inform.getInf_writer()).getF_name());
        }
        PageInfo<Inform> informPageInfo = new PageInfo<>(informs);
        map.addAttribute("pageInfo",informPageInfo);
        return "emp/emp_inform_all";
    }


    /**
     * 查看已发布通知
     */
    @RequestMapping("/toOwnInform")
    public String toEmpInform(ModelMap map,HttpServletRequest request,@RequestParam(defaultValue = "1")int page){
        PageHelper.startPage(page, 10);
        HttpSession session = request.getSession();
        Empolyee user = (Empolyee) session.getAttribute("emp_user");
        List<Inform> informs=informService.findAllInformById(user.getF_username());
        PageInfo<Inform> informPageInfo = new PageInfo<>(informs);
        map.addAttribute("pageInfo", informPageInfo);
        return "emp/emp_inform";
    }

    /**
     * 列出内部通知
     */
    @RequestMapping("/toInside")
     public String toEmpInsideInform(ModelMap map,@RequestParam(defaultValue = "1")int page){
        PageHelper.startPage(page, 10);
        List<Inform> informs=informService.findAllInform(2);
        PageInfo<Inform> informPageInfo = new PageInfo<>(informs);
        map.addAttribute("pageInfo", informPageInfo);
        return "emp/emp_inform_inside";
     }

    /**
     * 查看通知中心详细内容
     */
    @RequestMapping("/toAllInformDetail")
    public String empToInformDetail(@RequestParam(value = "id",required = true)String id,ModelMap map){
        Inform inform = informService.informDetail(id);
        inform.setDesc_date_str(DateFormat.dateToString(inform.getDesc_date(), "yyyy-MM-dd"));
        inform.setInf_writer_str(empService.findOneEmp(inform.getInf_writer()).getF_name());
        map.addAttribute("inform",inform);
        return "emp/emp_allInform_detail";
    }

    /**
     * 查看已发布通知详细内容
     */
    @RequestMapping("/toOwnInformDetail")
    public String empToOwnInformDetail(@RequestParam(value = "id",required = true)String id,ModelMap map){
        Inform inform = informService.informDetail(id);
        inform.setDesc_date_str(DateFormat.dateToString(inform.getDesc_date(), "yyyy-MM-dd"));
        inform.setInf_writer_str(empService.findOneEmp(inform.getInf_writer()).getF_name());
        map.addAttribute("inform",inform);
        return "emp/emp_ownInform_detail";
    }

    /**
     * 查看内部通知详细内容
     */
    @RequestMapping("/toInsideInformDetail")
    public String empToInsideInformDetail(@RequestParam(value = "id",required = true)String id,ModelMap map){
        Inform inform = informService.informDetail(id);
        inform.setDesc_date_str(DateFormat.dateToString(inform.getDesc_date(), "yyyy-MM-dd"));
        inform.setInf_writer_str(empService.findOneEmp(inform.getInf_writer()).getF_name());
        map.addAttribute("inform",inform);
        return "emp/emp_insideInform_detail";
    }

    /**
     * 管理员查看所有通知
     */
    @RequestMapping("/adminToInform")
    public String adminFindAllInform(ModelMap map,@RequestParam(defaultValue = "1")int page){
        PageHelper.startPage(page, 10);
        List<Inform> allInform = informService.adminFindAllInform();
        PageInfo<Inform> informPageInfo = new PageInfo<>(allInform);
        map.addAttribute("pageInfo", informPageInfo);
        map.addAttribute("mode", "all");
        return "admin/admin_inform";
    }
    /**
     * 管理员前往添加通知
     */
    @RequestMapping("/adminToAdd")
    public String adminToAdd(){
        return "admin/admin_inform_writer";
    }

    /**
     * 管理员添加通知
     */
    @RequestMapping("/adminAdd")
    public String adminAdd(String title, String context,String type,HttpServletRequest request){
        HttpSession session = request.getSession();
        Empolyee empolyee = (Empolyee) session.getAttribute("emp_admin");
        informService.insertInform(title,context,empolyee.getF_username(),Integer.parseInt(type));
        return "redirect:/inform/adminToInform";
    }


    /**
     * 管理员删除通知
     */
    @RequestMapping("/adminDel.do")
    public String adminDelInform(@RequestParam("id") String id){
        informService.delInformById(id);
        return "redirect:/inform/adminToInform";
    }

    /**
     * 查看通知详细内容
     */
    @RequestMapping("/adminToInsideDetail")
    public String adminToInformDetail(@RequestParam(value = "id",required = true)String id,ModelMap map){
        Inform inform = informService.informDetail(id);
        inform.setInf_writer_str(empService.findOneEmp(inform.getInf_writer()).getF_name());
        map.addAttribute("inform",inform);
        return "admin/admin_inform_detail";
    }

    /**
     * 管理员查看内部全部通知
     */
    @RequestMapping("/adminFindInside")
    public String adminFindInsideInform(ModelMap map,@RequestParam(defaultValue = "1")int page){
        PageHelper.startPage(page, 10);
        List<Inform> allInform = informService.findAllInform(2);
        PageInfo<Inform> informPageInfo = new PageInfo<>(allInform);
        map.addAttribute("pageInfo",informPageInfo);
        map.addAttribute("mode", "inside");
        return "admin/admin_inform";
    }

    /**
     * 管理员查看内部全部通知
     */
    @RequestMapping("/adminFindPublic")
    public String adminFindPublicInform(ModelMap map,@RequestParam(defaultValue = "1")int page){
        PageHelper.startPage(page, 10);
        List<Inform> allInform = informService.findAllInform(1);
        PageInfo<Inform> informPageInfo = new PageInfo<>(allInform);
        map.addAttribute("pageInfo",informPageInfo);
        map.addAttribute("mode", "public");
        return "admin/admin_inform";
    }

    @RequestMapping("/informSelect")
    public String informSelect(@RequestParam(defaultValue = "1")int page,String mode){
       if("all".equals(mode)){
           return "redirect:/inform/adminToInform?page="+page;
       }
        else if ("public".equals(mode)){
            return "redirect:/inform/adminFindPublic?page="+page;
       }
       else{
           return "redirect:/inform/adminFindInside?page="+page;
       }
    }
}
