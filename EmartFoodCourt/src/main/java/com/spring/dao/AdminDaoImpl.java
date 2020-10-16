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
	@Override
	public int food_delete(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return sqlsession.delete(namespace+".food_delete",value);
	}
	@Override
	public int jijum_count(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		Integer val = sqlsession.selectOne(namespace+".jijum_count",value);
		if(val==null) {
			val=0;
		}
		return val;
	}
	@Override
	public List<HashMap> jijum_list(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		List<HashMap> val = sqlsession.selectList(namespace+".jijum_list",value);
		if(val.isEmpty()) {
			val = null;
		}
		return val;
	}
	@Override
	public List<HashMap> jijum_info(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		List<HashMap> val = sqlsession.selectList(namespace+".jijum_info",value);
		if(val.isEmpty()) {
			val = null;
		}
		return val;
	}
	@Override
	public int jijum_insert(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return sqlsession.selectOne(namespace+".jijum_insert",value);
	}
	@Override
	public int jijum_modify(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return sqlsession.selectOne(namespace+".jijum_modify",value);
	}
	@Override
	public int jijum_delete(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return sqlsession.delete(namespace+".jijum_delete",value);
	}
	@Override
	public List<HashMap> Recom_status(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return sqlsession.selectList(namespace+".Recom_status",value);
	}
	@Override
	public int recom_count(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return sqlsession.selectOne(namespace+".recom_count",value);
	}
	@Override
	public List<HashMap> Recom_status_list(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return sqlsession.selectList(namespace+".Recom_status_list",value);
	}
	@Override
	public List<HashMap> status_grade(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return sqlsession.selectList(namespace+".status_grade",value);
	}
	@Override
	public List<HashMap> status_rev(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return sqlsession.selectList(namespace+".status_rev",value);
	}

}
