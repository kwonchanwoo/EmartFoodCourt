package com.spring.service;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.dao.AdminDao;



@Service
public class AdminServiceImpl implements AdminService {
	
	@Inject
	private AdminDao dao;
	
	@Override
	public List<HashMap> memberList(HashMap<String,Object> value) {
		// TODO Auto-generated method stub
		return dao.memberList(value);
	}

	@Override
	public int member_count(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return dao.member_count(value);
	}

	@Override
	public List<HashMap> food_list(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return dao.food_list(value);
	}

	@Override
	public int food_count(HashMap<String,Object> value) {
		// TODO Auto-generated method stub
		return dao.food_count(value);
	}

	@Override
	public List<HashMap> food_info(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return dao.food_info(value);
	}

	@Override
	public List<HashMap> select_food() {
		// TODO Auto-generated method stub
		return dao.select_food();
	}

	@Override
	public List<HashMap> select_jijum() {
		// TODO Auto-generated method stub
		return dao.select_jijum();
	}

	@Override
	public boolean food_add(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return dao.food_add(value);
	}

	@Override
	public List<HashMap> food_mod_form(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return dao.food_mod_form(value);
	}

	@Override
	public int food_mod_pro(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return dao.food_mod_pro(value);
	}

	@Override
	public int food_delete(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return dao.food_delete(value);
	}

	@Override
	public int jijum_count(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return dao.jijum_count(value);
	}

	@Override
	public List<HashMap> jijum_list(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return dao.jijum_list(value);
	}

	@Override
	public List<HashMap> jijum_info(HashMap<String,Object> value) {
		// TODO Auto-generated method stub
		return dao.jijum_info(value);
	}



	

}
