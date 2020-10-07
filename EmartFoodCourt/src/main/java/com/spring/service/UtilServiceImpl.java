package com.spring.service;

import java.util.HashMap;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dao.UtilDao;
import com.spring.dao.UtilDaoImpl;



@Service
public class UtilServiceImpl implements UtilService {
	
	@Inject
	private UtilDao dao;
	
	@Override
	public int dupl_cnt(HashMap<String, Object> member) {
		// TODO Auto-generated method stub
		return dao.dupl_cnt(member);
	}

	@Override
	public int session_db_del(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return dao.session_db_del(value);
	}

}
