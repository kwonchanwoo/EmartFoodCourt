package com.spring.dao;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDaoImpl implements MemberDao {
	
	@Inject
	private SqlSession sqlsession;
	
	private static String namespace = "com.spring.mapper.MemberMapper";
	
	@Override
	public int login_chk(HashMap<Object, Object> member) {
		// TODO Auto-generated method stub
		return sqlsession.selectOne(namespace+".login_chk", member);
	}

	@Override
	public List<HashMap> getMember(HashMap<Object, Object> id_chk) {
		// TODO Auto-generated method stub
		return sqlsession.selectList(namespace+".getMember",id_chk );
	}

	@Override
	public int dupl_chk(HashMap<Object, Object> member) {
		// TODO Auto-generated method stub
		return sqlsession.selectOne(namespace+".dupl_chk",member);
	}

	@Override
	public int user_add(HashMap<Object, Object> member)  {
		// TODO Auto-generated method stub
		
		return sqlsession.insert(namespace+".user_add",member);
	}

	@Override
	public int dupl_sol(HashMap<Object, Object> member) {
		// TODO Auto-generated method stub
		return sqlsession.delete(namespace+".dupl_sol",member);
	}

	@Override
	public List<HashMap> getUser(HashMap<Object, Object> member) {
		// TODO Auto-generated method stub
		return sqlsession.selectList(namespace+".getUser", member);
	}

	@Override
	public int session_db_del(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return sqlsession.delete(namespace+".session_db_del",value);
	}

	@Override
	public int join(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return sqlsession.insert(namespace+".join",value);
	}

	@Override
	public List<HashMap> search_id(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return sqlsession.selectList(namespace+".search_id",value);
	}

	@Override
	public List<HashMap> search_pass(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return sqlsession.selectList(namespace+".search_pass", value);
	}

	@Override
	public int modify_pass(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return sqlsession.update(namespace+".modify_pass",value);
	}

	@Override
	public int address_count(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		Integer val = sqlsession.selectOne(namespace+".address_count",value);
		if(val==null) {
			val=0;
		}
		return val;
	}

	@Override
	public List<HashMap> select_mypage(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		List<HashMap> val = null;
		if(!(sqlsession.selectList(namespace+".select_mypage",value).isEmpty())) {
			val = sqlsession.selectList(namespace+".select_mypage",value);
		}
		return val;
	}

	@Override
	public int member_delete(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return sqlsession.delete(namespace+".member_delete",value);
	}

	@Override
	public List<HashMap> selectMember(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		List<HashMap> val = null;
		if(!(sqlsession.selectList(namespace+".selectMember",value).isEmpty())) {
			val = sqlsession.selectList(namespace+".selectMember",value);
		}
		return val;
	}

	@Override
	public int member_modify(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return sqlsession.update(namespace+".member_modify",value);
	}
}
