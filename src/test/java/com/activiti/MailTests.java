package com.activiti;

import com.activiti.common.mail.MailServiceImpl;
import com.activiti.pojo.user.User;
import com.activiti.service.UserService;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.ResourceUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MailTests {
    private static Logger logger = LoggerFactory.getLogger(MailTests.class);
    @Autowired
    private MailServiceImpl mailService;

    @Value("${test.mail.receiveAddr}")
    private String receiveAddr;
    @Autowired
    private TemplateEngine templateEngine;

    /**
     * 简单邮件测试
     */
    @Test
    public void sendSimpleMailTest() {
        mailService.sendSimpleMail(receiveAddr, "Activiti", JSONObject.toJSONString(CommonTestUtil.getUserInfo()));
    }

    /**
     * 发送HTML邮件测试
     */
    @Test
    public void sendHtmlMailTest() {
        String content = "<html>\n" +
                "<body>\n" +
                "    <h3>hello world ! 这是一封Html邮件!hahaha" + JSONObject.toJSONString(CommonTestUtil.getUserInfo()) + "</h3>\n" +
                "</body>\n" +
                "</html>";
        mailService.sendHtmlMail(receiveAddr, "Activiti", content);
    }

    /**
     * 发送带附件的邮件测试
     */
    @Test
    public void sendAttachmentsMailTest() throws FileNotFoundException {
        File cfgFile = ResourceUtils.getFile("classpath:mail/attachments/test.txt");
        mailService.sendAttachmentsMail(receiveAddr,
                "主题：带附件的邮件", JSONObject.toJSONString(CommonTestUtil.getUserInfo()), cfgFile.getAbsolutePath());
    }

    /**
     * 发送静态资源测试
     */
    @Test
    public void sendInlineResourceMailTest() throws FileNotFoundException {
        File cfgFile = ResourceUtils.getFile("classpath:mail/static/meinv.jpeg");
        String rscId = "beauty001";
        String content = "<html><body>这是有图片的邮件：<img src=\'cid:" + rscId + "\' ></body></html>";
        mailService.sendInlineResourceMail(receiveAddr, "Activiti", content,
                cfgFile.getAbsolutePath(), rscId);
    }

    @Test
    public void sendTemplateMailTest() {
        //创建邮件正文
        Context context = new Context();
        context.setVariable("name", "beauty");
        String emailContent = templateEngine.process("mail/emailTemplate", context);
        mailService.sendHtmlMail(receiveAddr, "主题：这是美女图片", emailContent);
    }

}
