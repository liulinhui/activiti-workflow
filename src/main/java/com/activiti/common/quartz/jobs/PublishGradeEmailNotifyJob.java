package com.activiti.common.quartz.jobs;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 邮件通知成绩
 */
public class PublishGradeEmailNotifyJob extends JobCollection implements Job {

    private static final Logger logger = LoggerFactory.getLogger(PublishGradeEmailNotifyJob.class);

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("执行定时任务定时任务>>>>>>>>>定时邮件通知成绩");
    }
}
