package com.activiti.common.utils;

import com.activiti.common.kafka.MailProducer;
import com.activiti.common.mail.MailService;
import com.activiti.common.quartz.QuartzManager;
import com.activiti.common.quartz.jobs.AssessmentStartJob;
import com.activiti.common.quartz.jobs.PublishGradeEmailNotifyJob;
import com.activiti.common.quartz.jobs.UnAssessmentNotifyJob;
import com.activiti.common.sequence.Sequence;
import com.activiti.pojo.email.EmailDto;
import com.activiti.pojo.email.EmailType;
import com.activiti.pojo.schedule.ScheduleDto;
import com.activiti.pojo.user.JudgementLs;
import com.activiti.service.ScheduleService;
import com.activiti.service.UserService;
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

import javax.mail.MessagingException;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 共同帮助类
 * Created by 12490 on 2017/8/13.
 */
@Component("CommonUtil")
public class CommonUtil {
    private static final Logger logger = LoggerFactory.getLogger(CommonUtil.class);
    private static Sequence sequence = new Sequence(0, 0);

    @Autowired
    private MailService mailService;
    @Autowired
    private QuartzManager quartzManager;
    @Autowired
    private MailProducer mailProducer;
    @Autowired
    private UserService userService;


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
    public void sendEmail(EmailDto emailDto) throws MessagingException {
        switch (emailDto.getType()) {
            case html:
                mailService.sendHtmlMail(emailDto.getAddress(), emailDto.getSubject(), emailDto.getContent());
                break;
            case simple:
                mailService.sendSimpleMail(emailDto.getAddress(), emailDto.getSubject(), emailDto.getContent());
                break;
            case resource:
                mailService.sendInlineResourceMail(emailDto.getAddress(), emailDto.getSubject(), emailDto.getContent(), emailDto.getRscPath(), emailDto.getRscId());
            case attachment:
                mailService.sendAttachmentsMail(emailDto.getAddress(), emailDto.getSubject(), emailDto.getContent(), emailDto.getRscPath());
            default:
                break;
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
     *
     * @param a
     * @param b
     * @return
     */
    public boolean compareDate(Date a, Date b) {
        DateTimeComparator comparator = DateTimeComparator.getInstance(DateTimeFieldType.secondOfDay());
        return comparator.compare(new DateTime(a), new DateTime(b)) > 0 ? true : false;
    }

    /**
     * 添加一个新流程任务
     *
     * @param scheduleDto
     */
    public void addNewActivitiJob(ScheduleDto scheduleDto) {
        if (compareDate(scheduleDto.getJudgeStartTime(), new Date()))
            quartzManager.addJob(ConstantsUtils.NOTIFY_TO_ASSESSMENT, scheduleDto.getCourseCode(),
                    scheduleDto.getCourseCode() + ConstantsUtils.NOTIFY_TO_ASSESSMENT, ConstantsUtils.TRIGGER_GROUP_NAME, AssessmentStartJob.class, CronUtils.getCron(scheduleDto.getJudgeStartTime()));
        if (compareDate(scheduleDto.getJudgeEndTime(), new Date()))
            quartzManager.addJob(ConstantsUtils.NOTIFY_HAVE_NOT_JOIN_ASSESSMENT, scheduleDto.getCourseCode(),
                    scheduleDto.getCourseCode() + ConstantsUtils.NOTIFY_HAVE_NOT_JOIN_ASSESSMENT, ConstantsUtils.TRIGGER_GROUP_NAME, UnAssessmentNotifyJob.class, CronUtils.getCron(scheduleDto.getJudgeEndTime()));
        if (compareDate(scheduleDto.getPublishTime(), new Date()))
            quartzManager.addJob(ConstantsUtils.NOTIFY_PUBLISH_GRADE, scheduleDto.getCourseCode(),
                    scheduleDto.getCourseCode() + ConstantsUtils.NOTIFY_PUBLISH_GRADE, ConstantsUtils.TRIGGER_GROUP_NAME, PublishGradeEmailNotifyJob.class, CronUtils.getCron(scheduleDto.getPublishTime()));
    }

    /**
     * 删除一个新流程任务
     *
     * @param scheduleDto
     */
    public void removeNewActivitiJob(ScheduleDto scheduleDto) {
        if (compareDate(scheduleDto.getJudgeStartTime(), new Date()))
            quartzManager.removeJob(ConstantsUtils.NOTIFY_TO_ASSESSMENT, scheduleDto.getCourseCode(),
                    scheduleDto.getCourseCode() + ConstantsUtils.NOTIFY_TO_ASSESSMENT, ConstantsUtils.TRIGGER_GROUP_NAME);
        if (compareDate(scheduleDto.getJudgeEndTime(), new Date()))
            quartzManager.removeJob(ConstantsUtils.NOTIFY_HAVE_NOT_JOIN_ASSESSMENT, scheduleDto.getCourseCode(),
                    scheduleDto.getCourseCode() + ConstantsUtils.NOTIFY_HAVE_NOT_JOIN_ASSESSMENT, ConstantsUtils.TRIGGER_GROUP_NAME);
        if (compareDate(scheduleDto.getPublishTime(), new Date()))
            quartzManager.removeJob(ConstantsUtils.NOTIFY_PUBLISH_GRADE, scheduleDto.getCourseCode(),
                    scheduleDto.getCourseCode() + ConstantsUtils.NOTIFY_PUBLISH_GRADE, ConstantsUtils.TRIGGER_GROUP_NAME);
    }

    /**
     * 生成表名
     *
     * @param courseCode
     * @return
     */
    public String generateTableName(String courseCode) {
        return "`" + ConstantsUtils.tablePrefixName + courseCode.toUpperCase() + "`";
    }


    /**
     * 通知参加互评，并且打乱学生提交顺序
     *
     * @param courseCode 课程代码
     */
    public void assessmentStartJob(String courseCode) {
        logger.info("课程ID=" + courseCode + ">>>>>>>执行定时任务>>>>>>>>>定时通知参加互评，并且打乱学生提交顺序");
        String tableName = generateTableName(courseCode);
        userService.chaosUserInfo(tableName, courseCode);   //打乱学生信息表
        List<String> emailList = userService.selectAllStuInCourse(courseCode); //查询发邮件的对象
        String subject = "互评提醒";
        String content = "课程" + courseCode + "目前已经可以互评了，马上去互评吧！";
        emailList.forEach(email -> {
            mailProducer.send(new EmailDto(email, EmailType.simple, subject, content));
        });
    }

    /**
     * 邮件通知成绩
     *
     * @param courseCode 课程代码
     */
    public void publishGradeEmailNotifyJob(String courseCode) {
        logger.info("课程ID=" + courseCode + ">>>>>>>执行定时任务定时任务>>>>>>>>>定时邮件通知成绩");
    }

    /**
     * 邮件提醒没有参加互评的人以及将它们的流程结束
     *
     * @param courseCode 课程代码
     */
    public void unAssessmentNotifyJob(String courseCode) {
        logger.info("课程ID=" + courseCode + ">>>>>>>执行定时任务>>>>>>>>>邮件提醒没有参加互评的人以及将它们的流程结束");
    }

    /**
     * 计算成绩中位数
     *
     * @param num
     * @param judgementLsList
     * @return
     */
    public double getMiddleNum(double num, List<JudgementLs> judgementLsList) {
        List<Double> doubleList = new ArrayList<>();
        doubleList.add(num);
        judgementLsList.forEach(a -> {
            doubleList.add(a.getGrade());
        });
        Collections.sort(doubleList);
        return doubleList.get(doubleList.size() / 2);
    }

    /**
     * 判断是否为管理员账号
     *
     * @param email
     * @return
     */
    public boolean isManageRole(String email) {
        return ConstantsUtils.defaultManager.equals(email) || userService.selectAllUserRole().stream().anyMatch(a -> email.equals(a.getEmail()));
    }
}
