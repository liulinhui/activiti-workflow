package com.activiti.mapper;

import com.activiti.pojo.tools.InvokeLog;
import org.springframework.stereotype.Repository;

@Repository
public interface ToolsMapper {
    int insertInvokeLog(InvokeLog invokeLog);
}
