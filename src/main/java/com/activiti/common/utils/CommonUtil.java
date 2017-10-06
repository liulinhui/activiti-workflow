package com.activiti.common.utils;

import com.activiti.common.mail.MailService;
import com.activiti.pojo.email.EmailDto;
import com.activiti.pojo.email.EmailType;
import com.activiti.service.ScheduleService;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Random;

/**
 * 共同帮助类
 * Created by 12490 on 2017/8/13.
 */
@Component
public class CommonUtil {
    private static final Logger logger = LoggerFactory.getLogger(CommonUtil.class);

    @Autowired
    private MailService mailService;
    @Autowired
    private ScheduleService scheduleService;


    /**
     * 请求gitlab获取题目与答案
     *
     * @param url
     * @return
     */
    public JSONObject getQAFromGitHub(String url) {
        //get请求返回结果
        JSONObject jsonResult = null;
        try {
            CloseableHttpClient client = HttpClients.createDefault();
            HttpGet request = new HttpGet(url);
            HttpResponse response = client.execute(request);

            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String strResult = EntityUtils.toString(response.getEntity());
                jsonResult = JSONObject.parseObject(strResult);
                url = URLDecoder.decode(url, "UTF-8");
            } else {
                logger.error("get请求提交失败:" + url);
            }
        } catch (IOException e) {
            logger.error("get请求提交失败:" + url, e);
        }
        return jsonResult;
    }

    /**
     * 发邮件
     *
     * @param emailDto
     */
    public void sendEmail(EmailDto emailDto) {
        if (emailDto.getType() == EmailType.simple) {
            mailService.sendSimpleMail(emailDto.getAddress(), emailDto.getSubject(), emailDto.getContent());
        } else if (emailDto.getType() == EmailType.html) {
            mailService.sendHtmlMail(emailDto.getAddress(), emailDto.getSubject(), emailDto.getContent());
        } else if (emailDto.getType() == EmailType.attachment) {
            mailService.sendAttachmentsMail(emailDto.getAddress(), emailDto.getSubject(), emailDto.getContent(), emailDto.getRscPath());
        } else if (emailDto.getType() == EmailType.resource) {
            mailService.sendInlineResourceMail(emailDto.getAddress(), emailDto.getSubject(), emailDto.getContent(), emailDto.getRscPath(), emailDto.getRscId());
        }
    }

    /**
     * 生成随机数
     *
     * @param length 表示生成字符串的长度
     * @return
     */
    public String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 生成随机用户名用来打乱用户
     * @return
     */
    public String getRandomUserName() {
        return getRandomString(10);
    }
}
