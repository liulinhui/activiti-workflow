package com.activiti.service.serviceImpl;

import com.activiti.common.utils.CommonUtil;
import com.activiti.common.utils.ConstantsUtils;
import com.activiti.service.CommonService;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

/**
 * Created by 12490 on 2017/8/14.
 */
@Service
@EnableCaching
public class CommonServiceImpl implements CommonService {
    @Autowired
    private CommonUtil commonUtil;

    /**
     * 从gitlab取题目数据
     *
     * @param githubUrl 题目地址
     * @return
     */
    @Override
    @Cacheable(value = "ehCache300", key = "'QAFromGitLab_'+#githubUrl")
    public JSONObject getQAFromGitHub(String githubUrl)  {
        return commonUtil.getQAFromGitHub(githubUrl);
    }
}
