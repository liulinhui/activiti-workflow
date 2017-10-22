package com.activiti.common.utils;

import com.alibaba.fastjson.JSONArray;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.delegate.DelegateExecution;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * 相关操作
 */
@Component
public class ActivitiHelper {
    private final Logger logger = LoggerFactory.getLogger(ActivitiHelper.class);

    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;

    /**
     * activiti分配作业
     *
     * @param emailList
     */
    public void distributeTask(List<String> emailList) {
        Collections.shuffle(emailList);
        logger.info("启动作业分配流程");
        emailList.forEach(email -> {

        });
    }

    /**
     * 没有参与互评邮件提醒
     *
     * @param execution
     */
    public void emailAlert(DelegateExecution execution) {
        String emailList = (String) execution.getVariable("emailAlertList");
        JSONArray jsonArray = JSONArray.parseArray(emailList);
        System.out.println("开始向这些人发邮件" + emailList);
    }
}
