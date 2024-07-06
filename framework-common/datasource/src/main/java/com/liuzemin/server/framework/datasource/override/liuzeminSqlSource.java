package com.liuzemin.server.framework.datasource.override;

import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.session.Configuration;

public class liuzeminSqlSource implements SqlSource{
	
	private SqlSource sqlSource;
	
	private Configuration configuration;
	
	public liuzeminSqlSource(SqlSource sqlsource, Configuration config){
		this.sqlSource = sqlsource;
		this.configuration = config;
	}
	
	@Override
	public BoundSql getBoundSql(Object parameterObject) {

		return new liuzeminBoundSql(configuration, sqlSource.getBoundSql(parameterObject));
	}

	public SqlSource getSqlSource() {
		return sqlSource;
	}

	public void setSqlSource(SqlSource sqlSource) {
		this.sqlSource = sqlSource;
	}

	public Configuration getConfiguration() {
		return configuration;
	}

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}
	
}
