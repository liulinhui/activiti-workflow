package com.activiti.common.utils;

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

    public void execute(DelegateExecution execution) {
        String operationType = (String) execution.getVariable("operationType");
        System.out.println("收到了传来的参数" + operationType);
    }
}
