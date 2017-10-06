package com.activiti.controller;

import com.activiti.common.aop.ApiAnnotation;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 12490 on 2017/8/1.
 */
@Controller
public class IndexController {
    private final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping("/login")
    public String greeting(HttpServletRequest request, ModelMap model) {
        String email = request.getParameter("email");
        String redirectUrl = request.getParameter("redirectUrl");
        if ("".equals(email) || null==email){
            model.put("redirectUrl",redirectUrl);
            return "login";
        }
        request.getSession().setAttribute("userEmail",email);
        if ("".equals(redirectUrl) || null == redirectUrl)
            return "redirect:/index";
        else
            return "redirect:"+redirectUrl;
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }
}
