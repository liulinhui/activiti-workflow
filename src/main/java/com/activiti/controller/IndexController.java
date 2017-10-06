package com.activiti.controller;

import com.activiti.common.utils.CommonUtil;
import com.activiti.common.utils.ConstantsUtils;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 12490 on 2017/8/1.
 */
@Controller
public class IndexController {
    private final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private CommonUtil commonUtil;

    @RequestMapping("/login")
    public String greeting(HttpServletRequest request, ModelMap model) {
        String email = request.getParameter("email");
        String redirectUrl = request.getParameter("redirectUrl");
        if ("".equals(email) || null == email) {
            model.put("redirectUrl", redirectUrl);
            return "login";
        } else if (!commonUtil.emailFormat(email)) {
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
}
