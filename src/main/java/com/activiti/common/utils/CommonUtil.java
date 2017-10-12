package com.activiti.common.utils;

import com.activiti.common.mail.MailService;
import com.activiti.common.sequence.Sequence;
import com.activiti.pojo.email.EmailDto;
import com.activiti.pojo.email.EmailType;
import com.activiti.pojo.schedule.ScheduleDto;
import com.activiti.service.ScheduleService;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.joda.time.DateTime;
import org.joda.time.DateTimeComparator;
import org.joda.time.DateTimeFieldType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 共同帮助类
 * Created by 12490 on 2017/8/13.
 */
@Component
public class CommonUtil {
    private static final Logger logger = LoggerFactory.getLogger(CommonUtil.class);
    private static Sequence sequence = new Sequence(0, 0);

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
     *
     * @return
     */
    public String getRandomUserName() {
        return getRandomString(10);
    }

    /**
     * 检验邮件格式
     *
     * @param email
     * @return
     */
    public boolean emailFormat(String email) {
        boolean flag = false;
        try {
            String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 获取随机数
     *
     * @return
     */
    public long getSequenceId() {
        return sequence.nextId();
    }

    /**
     * 验证部署时间的正确性
     *
     * @param a
     * @return
     */
    public boolean validateTime(ScheduleDto a) {
        Date[] dates = {a.getJudgeStartTime(), a.getJudgeEndTime(), a.getAuditStartTime(), a.getAuditEndTime()};
        DateTimeComparator comparator = DateTimeComparator.getInstance(DateTimeFieldType.secondOfDay());
        for (int i = dates.length - 1; i > 0; --i) {
            for (int j = 0; j < i; ++j) {
                if (comparator.compare(new DateTime(dates[j + 1]), new DateTime(dates[j])) <= 0) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 格式化日期
     *
     * @param date
     * @return
     */
    public static String dateToString(Date date) {
        DateTime dateTime = new DateTime(date);
        return dateTime.toString("yyyy/MM/dd HH:mm:ss");
    }

    /**
     * 判断a是否大于b
     * @param a
     * @param b
     * @return
     */
    public boolean compareDate(Date a, Date b) {
        DateTimeComparator comparator = DateTimeComparator.getInstance(DateTimeFieldType.secondOfDay());
        return comparator.compare(new DateTime(a), new DateTime(b)) > 0 ? true : false;
    }
}
