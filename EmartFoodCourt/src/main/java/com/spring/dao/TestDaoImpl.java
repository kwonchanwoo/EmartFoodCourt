package com.spring.dao;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class TestDaoImpl implements TestDao{

	// sql�젒洹쇱쓣 �쐞�븳 媛앹껜
	@Inject
	private SqlSession sqlsession;
	
	// mapper�뙆�씪�쓽 namespace
	private static String namespace = "com.spring.mapper.TestMapper";

	@Override
	public List<HashMap> list() throws Exception {
		// TODO Auto-generated method stub
		return sqlsession.selectList(namespace+".selectTest");
	}

	@Override
	public Integer selectMemberCount() throws Exception {
		// TODO Auto-generated method stub
		return sqlsession.selectOne(namespace+".memberCount");
	}

	@Override
	public void regist(HashMap<String, String> regist)  {
		// TODO Auto-generated method stub
		HashMap<String, String> reg = regist;
		System.out.println("daoImpl입니다."+reg.get("content"));
		sqlsession.insert(namespace+".regist",reg);
	}
}
