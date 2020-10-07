package com.spring.service;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.dao.MemberDao;

@Service
public class MemberServiceImpl implements MemberService {

	@Inject
	MemberDao dao;

	@Override
	public int login_chk(HashMap<Object, Object> member) {
		// TODO Auto-generated method stub
		return dao.login_chk(member);
	}

	@Override
	public List<HashMap> getMember(HashMap<Object, Object> id_chk) {
		// TODO Auto-generated method stub
		return dao.getMember(id_chk);
	}

	@Override
	public int dupl_chk(HashMap<Object, Object> member) {
		// TODO Auto-generated method stub
		return dao.dupl_chk(member);
	}

	@Override
	public int user_add(HashMap<Object, Object> member) {
		// TODO Auto-generated method stub

		return dao.user_add(member);
	}

	@Override
	public int dupl_sol(HashMap<Object, Object> member) {
		// TODO Auto-generated method stub
		return dao.dupl_sol(member);
	}

	@Override
	public List<HashMap> getUser(HashMap<Object, Object> member) {
		// TODO Auto-generated method stub
		return dao.getUser(member);
	}

	@Override
	public int session_db_del(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return dao.session_db_del(value);
	}

	@Override
	public int join(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return dao.join(value);
	}

	@Override
	public List<HashMap> search_id(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return dao.search_id(value);
	}

	@Override
	public List<HashMap> search_pass(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return dao.search_pass(value);
	}

	@Override
	public int modify_pass(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return dao.modify_pass(value);
	}

	@Override
	public int address_count(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return dao.address_count(value);
	}

	@Override
	public List<HashMap> select_mypage(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return dao.select_mypage(value);
	}

	@Override
	public int member_delete(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return dao.member_delete(value);
	}



	@Override
	public List<HashMap> selectMember(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return dao.selectMember(value);
	}

	@Override
	public int member_modify(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return dao.member_modify(value);
	}

}
