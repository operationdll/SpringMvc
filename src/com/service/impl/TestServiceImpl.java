package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.TestDao;
import com.dto.TestDto;
import com.service.TestService;

@Service
public class TestServiceImpl implements TestService {
	@Autowired
	private TestDao testDao;

	@Override
	public List<TestDto> init(String id) {
		return testDao.init(id);
	}

	public TestDao getTestDao() {
		return testDao;
	}

	public void setTestDao(TestDao testDao) {
		this.testDao = testDao;
	}
}
