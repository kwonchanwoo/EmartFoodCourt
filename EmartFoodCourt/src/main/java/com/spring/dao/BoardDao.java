package com.spring.dao;

import java.util.HashMap;
import java.util.List;

public interface BoardDao {

	List<HashMap> select_free_board(HashMap<String, Object> value);

	int free_board_write(HashMap<String, Object> value);

	List<HashMap> board_detail(HashMap<String, Object> value);

	List<HashMap> board_reply(HashMap<String, Object> value);

	int board_read(HashMap<String, Object> value);

	int board_modify(HashMap<String, Object> value);

	int board_delete(HashMap<String, Object> value);

	int board_vote_delete(HashMap<String, Object> value);

	int board_reply_delete(HashMap<String, Object> value);

	List<HashMap> board_vote_select(HashMap<String, Object> value);

	int board_vote_up(HashMap<String, Object> value);

	int board_vote_down(HashMap<String, Object> value);

	int board_vote_plus(HashMap<String, Object> value);

	int board_vote_minus(HashMap<String, Object> value);

	int board_reply_write(HashMap<String, Object> value);

	int board_reply_max_pk();

	int board_reply_update(HashMap<String, Object> value);

	int board_reply_modify(HashMap<String, Object> value);

	int free_board_count(HashMap<String, Object> value);

	int free_board_date_update(HashMap<String, Object> value);

}
