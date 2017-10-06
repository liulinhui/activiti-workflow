package com.activiti.service.serviceImpl;

import com.activiti.mapper.UserMapper;
import com.activiti.pojo.user.StudentWorkInfo;
import com.activiti.pojo.user.User;
import com.activiti.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by 12490 on 2017/8/6.
 */
@Service
@EnableCaching
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    @Cacheable(value = "ehCache60", keyGenerator = "keyGenerator")
    public User findUserInfo(String email_address) {
        return userMapper.findUserInfo(email_address);
    }

    @Override
    public int insertUser(User user) {
       return userMapper.insertUser(user);
    }

    @Override
    public int insertUserWork(StudentWorkInfo studentWorkInfo) {
       return userMapper.insertUserWork(studentWorkInfo);
    }

    @Override
    public StudentWorkInfo selectStudentWorkInfo(StudentWorkInfo studentWorkInfo) {
       return userMapper.selectStudentWorkInfo(studentWorkInfo);
    }

    @Override
    public List<String> selectNoGradeUser(String courseCode) {
        return userMapper.selectNoGradeUser(courseCode);
    }

    @Override
    public List<String> selectUnFinishJudgeUser(String courseCode) {
        return userMapper.selectUnFinishJudgeUser(courseCode);
    }

    @Override
    public int chaosUserInfo() {
        return userMapper.chaosUserInfo();
    }

    @Override
    public int deleteChaosUserInfo() {
       return userMapper.deleteChaosUserInfo();
    }
}
