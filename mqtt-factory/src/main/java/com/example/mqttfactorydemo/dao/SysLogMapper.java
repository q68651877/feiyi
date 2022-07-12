package com.example.mqttfactorydemo.dao;


import com.example.mqttfactorydemo.model.SysLog;
import com.smart.core.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysLogMapper extends Mapper<SysLog> {

    List<SysLog> findList(@Param("operatorId") Long operatorId,
                          @Param("startDate") String startDate,
                          @Param("endDate") String endDate,
                          @Param("sortType") String sortType);

    List<SysLog> findByTypeAndRefId(@Param("type") String type,
                                    @Param("refId") Long refId);
}