package com.spring.service;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.dao.BoardDao;



@Service
public class BoardServiceImpl implements BoardService {

	@Inject
	private BoardDao dao;
	
	@Override
	public List<HashMap> select_free_board(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return dao.select_free_board(value);
	}

	@Override
	public int free_board_write(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return dao.free_board_write(value);
	}

	@Override
	public List<HashMap> board_detail(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return dao.board_detail(value);
	}

	@Override
	public List<HashMap> board_reply(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return dao.board_reply(value);
	}

	@Override
	public int board_read(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return dao.board_read(value);
	}

	@Override
	public int board_modify(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return dao.board_modify(value);
	}

	@Override
	public int board_delete(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return dao.board_delete(value);
	}

	@Override
	public int board_vote_delete(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return dao.board_vote_delete(value);
	}

	@Override
	public int board_reply_delete(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return dao.board_reply_delete(value);
	}

	@Override
	public List<HashMap> board_vote_select(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return dao.board_vote_select(value);
	}

	@Override
	public int board_vote_up(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return dao.board_vote_up(value);
	}

	@Override
	public int board_vote_down(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return dao.board_vote_down(value);
	}


	@Override
	public int board_vote_plus(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return dao.board_vote_plus(value);
	}

	@Override
	public int board_vote_minus(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return dao.board_vote_minus(value);
	}

	@Override
	public int board_reply_write(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return dao.board_reply_write(value);
	}

	@Override
	public int board_reply_max_pk() {
		// TODO Auto-generated method stub
		return dao.board_reply_max_pk();
	}

	@Override
	public int board_reply_update(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return dao.board_reply_update(value);
	}

	@Override
	public int board_reply_modify(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return dao.board_reply_modify(value);
	}

	@Override
	public int free_board_count(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return dao.free_board_count(value);
	}

	@Override
	public int free_board_date_update(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return dao.free_board_date_update(value);
	}


}
