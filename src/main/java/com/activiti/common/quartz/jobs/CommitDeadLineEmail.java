package com.activiti.common.quartz.jobs;


import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 最后提交期限邮件提醒
 * Created by 12490 on 2017/8/19.
 */
public class CommitDeadLineEmail extends JobCollection implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

    }
}
