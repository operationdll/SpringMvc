package com.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dao.BaseDao;
import com.dao.TestDao;
import com.dto.TestDto;

@Repository
public class TestDaoImpl implements TestDao {
	@Autowired
	private BaseDao baseDao;

	@SuppressWarnings("unchecked")
	@Override
	public List<TestDto> init(String id) {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id", id);
		return baseDao.getSqlMapClientTemplate().queryForList("test.init", map);
	}

	public BaseDao getBaseDao() {
		return baseDao;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
}
