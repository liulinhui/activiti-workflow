package com.activiti.mapper;

import com.activiti.pojo.user.JudgementLs;
import com.activiti.pojo.user.StudentWorkInfo;
import com.activiti.pojo.user.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JudgementMapper {

    /**
     * 提交作业
     *
     * @param studentWorkInfo
     * @return
     */
    int commitWork(StudentWorkInfo studentWorkInfo);

    /**
     * 更新学生成绩
     *
     * @param studentWorkInfo
     * @return
     */
    int updateStuGrade(StudentWorkInfo studentWorkInfo);

    /**
     * 学生参与互评后更新学生互评时间
     *
     * @param studentWorkInfo
     * @return
     */
    int updateStuJudgeTime(StudentWorkInfo studentWorkInfo);

    /**
     * 插入学生互评记录
     *
     * @param list
     * @return
     */
    int insertJudgementLs(List<JudgementLs> list);

    /**
     * 查询学生作业信息
     *
     * @param studentWorkInfo
     * @return
     */
    StudentWorkInfo selectStudentWorkInfo(StudentWorkInfo studentWorkInfo);

    /**
     * 查询互评流水
     *
     * @param judgementLs
     * @return
     */
    List<JudgementLs> selectJudgementLs(JudgementLs judgementLs);

    /**
     * 查询提交作业的总人数
     * @param courseCode
     * @return
     */
    int countAllWorks(String courseCode);

    /**
     * 查询打乱顺序后的学生ID
     * @param emailAddress
     * @return
     */
    int selectChaosId(String emailAddress);

}
