package com.activiti.controller;

import com.activiti.common.utils.CommonUtil;
import com.activiti.common.utils.ConstantsUtils;
import com.activiti.pojo.schedule.ScheduleDto;
import com.activiti.pojo.user.UserRole;
import com.activiti.service.ScheduleService;
import com.activiti.service.UserService;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 12490 on 2017/8/1.
 */
@Controller
public class IndexController {
    private final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private CommonUtil commonUtil;
    @Autowired
    private UserService userService;
    @Autowired
    private ScheduleService scheduleService;

    @RequestMapping("/login")
    public String greeting(HttpServletRequest request, ModelMap model) {
        String email = request.getParameter("email");
        String redirectUrl = request.getParameter("redirectUrl");
        if ("".equals(email) || null == email) {
            model.put("redirectUrl", redirectUrl);
            return "login";
        } else if (!commonUtil.emailFormat(email)) {
            logger.info("邮箱地址错误：" + email);
            model.put("redirectUrl", redirectUrl);
            model.put("emailValidate", "false");
            return "login";
        } else {
            request.getSession().setAttribute(ConstantsUtils.sessionEmail, email);
            if ("".equals(redirectUrl) || null == redirectUrl)
                return "redirect:/index";
            else
                return "redirect:" + redirectUrl;
        }
    }

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    /**
     * 退出登录
     *
     * @param request
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().removeAttribute(ConstantsUtils.sessionEmail);
        return "redirect:/login";
    }

    /**
     * 工作流配置
     *
     * @param request
     * @return
     */
    @RequestMapping("/activitiConf")
    public String activitiConf(HttpServletRequest request) {
        return "submodule/activiti";
    }

    /**
     * 配置时间表页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/timeConf")
    public String timeConf(HttpServletRequest request) {
        return "submodule/timeConf";
    }

    /**
     * 已完成的任务
     *
     * @param request
     * @return
     */
    @RequestMapping("/jobDone")
    public String jobDone(HttpServletRequest request) {
        return "submodule/jobDone";
    }

    /**
     * 已完成的任务
     *
     * @param request
     * @return
     */
    @RequestMapping("/gradeInfo")
    public String gradeInfo(HttpServletRequest request) {
        return "submodule/gradeInfo";
    }

    /**
     * 答题页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/answer")
    public String answer(HttpServletRequest request,ModelMap modelMap) {
        List<ScheduleDto> scheduleDtoList = scheduleService.selectAllScheduleTime(0, 5000);
        modelMap.put("scheduleDtoList",scheduleDtoList);
        return "submodule/answer";
    }

    /**
     * 互评页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/assessment")
    public String assessment(HttpServletRequest request) {
        return "submodule/assessment";
    }

    /**
     * 成绩审核页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/judgement")
    public String judgement(HttpServletRequest request) {
        return "submodule/judgement";
    }

    /**
     * 管理员配置页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/userRole")
    public String userRole(HttpServletRequest request, ModelMap modelMap) {
        modelMap.put("userRoleList", userService.selectAllUserRole());
        return "submodule/userRole";
    }

    /**
     * 管理员配置页面
     *
     * @param request
     * @return
     */
    @RequestMapping("/logView")
    public String logView(HttpServletRequest request, ModelMap modelMap) {
        return "submodule/logView";
    }
}
