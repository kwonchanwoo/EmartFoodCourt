package com.spring.dao;

import java.util.HashMap;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class UtilDaoImpl implements UtilDao {
	
	@Inject
	private SqlSession sqlsession;
	
	// mapper�뙆�씪�쓽 namespace
	private static String namespace = "com.spring.mapper.UtilMapper";
	
	@Override
	public int dupl_cnt(HashMap<String, Object> member) {
		// TODO Auto-generated method stub
		return sqlsession.selectOne(namespace+".dupl_cnt",member);
	}

	@Override
	public int session_db_del(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return sqlsession.delete(namespace+".session_db_del",value);
	}

}
