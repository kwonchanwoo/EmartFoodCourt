package com.spring.dao;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDaoImpl implements BoardDao {

	@Inject
	private SqlSession sqlsession;
	
	private static String namespace = "com.spring.mapper.BoardMapper";
	
	@Override
	public List<HashMap> select_free_board(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		List<HashMap> val = null;
		if(!(sqlsession.selectList(namespace+".select_free_board", value).isEmpty())) {
			val = sqlsession.selectList(namespace+".select_free_board", value);
		}
		return val;
	}

	@Override
	public int free_board_write(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return sqlsession.insert(namespace+".free_board_write",value);
	}

	@Override
	public List<HashMap> board_detail(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		List<HashMap> check = null;
		
		if(!(sqlsession.selectList(namespace+".free_board_detail",value).isEmpty())){
			check = sqlsession.selectList(namespace+".free_board_detail",value);
		}
		return check;
	}

	@Override
	public List<HashMap> board_reply(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		List<HashMap> check = null;
		
		if((!sqlsession.selectList(namespace+".free_board_reply",value).isEmpty())) {
			check = sqlsession.selectList(namespace+".free_board_reply",value);
		}
		return check;
	}

	@Override
	public int board_read(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return sqlsession.update(namespace+".free_board_read",value);
	}

	@Override
	public int board_modify(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return sqlsession.update(namespace+".board_modify",value);
	}

	@Override
	public int board_delete(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return sqlsession.delete(namespace+".board_delete",value);
	}

	@Override
	public int board_vote_delete(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return sqlsession.delete(namespace+".board_vote_delete",value);
	}

	@Override
	public int board_reply_delete(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return sqlsession.delete(namespace+".board_reply_delete",value);
	}

	@Override
	public List<HashMap> board_vote_select(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		List<HashMap> val = null;
		
		if(!(sqlsession.selectList(namespace+".board_vote_select",value).isEmpty())) {
			val = sqlsession.selectList(namespace+".board_vote_select",value);
		}
		System.out.println("val : " + val);
		return val;
	}

	@Override
	public int board_vote_up(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return sqlsession.insert(namespace+".board_vote_up",value);
	}

	@Override
	public int board_vote_down(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return sqlsession.insert(namespace+".board_vote_down",value);
	}



	@Override
	public int board_vote_plus(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return sqlsession.update(namespace+".board_vote_plus",value);
	}

	@Override
	public int board_vote_minus(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return sqlsession.update(namespace+".board_vote_minus",value);
	}

	@Override
	public int board_reply_write(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return sqlsession.insert(namespace+".board_reply_write",value);
	}

	@Override
	public int board_reply_max_pk() {
		// TODO Auto-generated method stub
		Integer val = sqlsession.selectOne(namespace+".board_reply_max_pk");
		if(val==null) {
			val = 0;
		}
		return val;
	}

	@Override
	public int board_reply_update(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return sqlsession.update(namespace+".board_reply_update",value);
	}

	@Override
	public int board_reply_modify(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return sqlsession.update(namespace+".board_reply_modify",value);
	}

	@Override
	public int free_board_count(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		Integer val = sqlsession.selectOne(namespace+".free_board_count",value);
		if(val==null) {
			val = 0;
		}
		return val;
	}

	@Override
	public int free_board_date_update(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return sqlsession.update(namespace+".free_board_date_update",value);
	}


}
