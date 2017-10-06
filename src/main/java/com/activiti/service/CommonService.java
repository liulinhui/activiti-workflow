package com.activiti.service;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by 12490 on 2017/8/14.
 */
public interface CommonService {

    JSONObject getQAFromGitHub(String qDir,String qNo);
}
