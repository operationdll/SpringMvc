package com.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
import org.springframework.stereotype.Repository; //import org.springframework.beans.factory.annotation.Qualifier;

import com.ibatis.sqlmap.client.SqlMapClient;

@Repository
public class BaseDao extends SqlMapClientDaoSupport {

	@Autowired
	// @Qualifier("sqlMapClient")
	public void createTemplate(SqlMapClient sqlMapClient) {
		setSqlMapClient(sqlMapClient);
	}
}
