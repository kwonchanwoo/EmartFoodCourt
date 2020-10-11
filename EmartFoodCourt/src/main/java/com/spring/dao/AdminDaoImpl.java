package com.spring.dao;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class AdminDaoImpl implements AdminDao {
	
	@Inject
	private SqlSession sqlsession;
	private static String namespace = "com.spring.mapper.AdminMapper";
	@Override
	public List<HashMap> memberList(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		
		List<HashMap> val = sqlsession.selectList(namespace+".memberList",value);
		if(val.isEmpty()) {
			val=null;
		}
		return val;
	}
	@Override
	public int member_count(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		Integer val = sqlsession.selectOne(namespace+".member_count",value);
		if(val==null) {
			val=0;
		}
		return val;
	}
	@Override
	public List<HashMap> food_list(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		List<HashMap> val = sqlsession.selectList(namespace+".food_list",value);
		if(val.isEmpty()) {
			val = null;
		}
		return val;
	}
	@Override
	public int food_count(HashMap<String, Object> value) {
		Integer val = sqlsession.selectOne(namespace+".food_count",value);
		if(val==null) {
			val=0;
		}
		// TODO Auto-generated method stub
		return val;
	}
	@Override
	public List<HashMap> food_info(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		List<HashMap> val = sqlsession.selectList(namespace+".food_info",value);
		if(val.isEmpty()) {
			val=null;
		}
		return val;
	}
	@Override
	public List<HashMap> select_food() {
		// TODO Auto-generated method stub
		List<HashMap> val = sqlsession.selectList(namespace+".select_food");
		if(val.isEmpty()) {
			val=null;
		}
		return val;
	}
	@Override
	public List<HashMap> select_jijum() {
		// TODO Auto-generated method stub
		List<HashMap> val = sqlsession.selectList(namespace+".select_jijum");
		if(val.isEmpty()) {
			val=null;
		}
		return val;
	}
	@Override
	public boolean food_add(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		Integer val = sqlsession.selectOne(namespace+".food_add",value);
		System.out.println("val : " + val);
		boolean result = false;
		if(val>0) {
			result=true;
		}
		return result;
	}
	@Override
	public List<HashMap> food_mod_form(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		List<HashMap> val = sqlsession.selectList(namespace+".food_mod_form",value);
		if(val.isEmpty()){
			val = null;
		}
		return val;
	}
	@Override
	public int food_mod_pro(HashMap<String, Object> value) {
		Integer val = sqlsession.selectOne(namespace+".food_mod_pro",value);
		// TODO Auto-generated method stub
		if(val==null) {
			val = 0;
		}
		return val;
	}

}
