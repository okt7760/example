package com.okt.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.okt.entity.Empolyee;
import com.okt.entity.Feedback;
import com.okt.entity.Inform;
import com.okt.entity.User;
import com.okt.service.EmpService;
import com.okt.service.FeedBackService;
import com.okt.util.DateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * Create by obeeey on 2021/3/20
 * 即使再小的帆也能远航
 */
@Controller
@RequestMapping("/feedback")
public class FeedbackController {
    @Autowired
    FeedBackService feedBackService;
    @Autowired
    EmpService empService;

    /**
     * 前往意见反馈页
     * @param request
     * @param map
     * @return
     */
    @RequestMapping("/toFeedback")
    public String toFeedback(HttpServletRequest request, ModelMap map){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        List<Feedback> feedbacks = feedBackService.selectUserAll(user.getR_username());
        for (Feedback feedback : feedbacks) {
            feedback.setFb_date_str(DateFormat.dateToString(feedback.getFb_date(), "yyyy-MM-dd"));
        }
        map.addAttribute("list", feedbacks);
        return "user/feedback";
    }

    @RequestMapping("/detail")
    public String toFeedbackDetail(@RequestParam(value = "fb_id")String fb_id,ModelMap map){
        Feedback feedback = feedBackService.selectOne(fb_id);
        feedback.setFb_date_str(DateFormat.dateToString(feedback.getFb_date(), "yyyy-MM-dd"));
        if(feedback.getFb_processor()!=null){
            String f_name = empService.findOneEmp(feedback.getFb_processor()).getF_name();
            feedback.setFb_processor_str(f_name);
        }
        map.addAttribute("detail", feedback);
        return "user/feedback_detail";
    }

    @RequestMapping("/toNewFeedback")
    public String toFeedbackDetail(){
        return "user/feedback_write";
    }

    /**
     * 用户添加意见反馈
     * @param context
     * @param map
     * @return
     */
    @RequestMapping("/new.do")
    public String writeFeedback(String context,ModelMap map,HttpServletRequest request){
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String username=user.getR_username();
        String fb_id= DateFormat.dateToString(new Date(), "yyyyMMddHHmmss")+username;
        feedBackService.userInsertFeedBack(fb_id,username ,context);
        return "redirect:/feedback/toFeedback";
    }

    @RequestMapping("/del.do")
    public String delFeedback(@RequestParam(value = "fb_id")String fb_id){
        System.out.println(fb_id);
        feedBackService.deleteOne(fb_id);
        return "redirect:/feedback/toFeedback";
    }

    @RequestMapping("/findAll")
    public String toAllFeedback(ModelMap map,@RequestParam(defaultValue = "1")int page){
        PageHelper.startPage(page, 10);
        List<Feedback> feedbackList = feedBackService.findAll();
        PageInfo<Feedback> feedbackPageInfo = new PageInfo<>(feedbackList);
        map.addAttribute("pageInfo", feedbackPageInfo);
        return "emp/emp_feedback";
    }

    @RequestMapping("/toReply")
    public String toReply(@RequestParam(value = "id",required = true) String id,ModelMap map){
        Feedback feedback = feedBackService.selectOne(id);
        map.addAttribute("feedback", feedback);
        return "emp/emp_feedback_reply";
    }


    @RequestMapping("/reply.do")
    public String empToReply(@RequestParam(value = "id",required = true) String id,String reply,HttpServletRequest request){
        HttpSession session = request.getSession();
        Empolyee emp = (Empolyee) session.getAttribute("emp_user");
        feedBackService.updateFeedbackById(id, reply, emp.getF_username());
        return "redirect:/feedback/toChecked";
    }

    @RequestMapping("/toChecked")
    public String toChecked(ModelMap map,HttpServletRequest request,@RequestParam(defaultValue = "1")int page){
        PageHelper.startPage(page, 10);
        HttpSession session = request.getSession();
        Empolyee emp = (Empolyee) session.getAttribute("emp_user");
        List<Feedback> feedbackList = feedBackService.findAllCheckedByEmpId(emp.getF_username());
        PageInfo<Feedback> feedbackPageInfo = new PageInfo<>(feedbackList);
        map.addAttribute("pageInfo", feedbackPageInfo);
        return "emp/emp_feedback_checked";
    }

    @RequestMapping("/rollback")
    public String toChecked(@RequestParam("id") String id){
        feedBackService.rollbackFeedback(id);
        return "redirect:/feedback/toChecked";
    }
}
