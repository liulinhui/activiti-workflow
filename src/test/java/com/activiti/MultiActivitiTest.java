package com.activiti;

import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MultiActivitiTest {
    private final Logger logger = LoggerFactory.getLogger(ActivitiTest.class);
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;

    /**
     * 启动流程
     */
    @Test
    public void start() throws InterruptedException {
        Map<String, Object> variables = new HashMap<>();
        variables.put("courseCode", "1502");
        String businessKey="assessment";
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("answerToAssessmentNojudge",businessKey, variables);
        logger.info(">>>>>>>>>>>>>>>>>>>>启动流程：" + processInstance.getId());
        Thread.sleep(15000);
    }

}
