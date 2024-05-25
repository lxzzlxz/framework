package com.liuzemin.server.framework.security.user.dao;


import org.springframework.data.repository.query.Param;

public interface IUserStatisticsDao {

    int statisticsUserCount(@Param("enterpriseCategory") Integer enterpriseCategory);
}
