package com.spring.dao;

import java.util.HashMap;

public interface UtilDao {

	int dupl_cnt(HashMap<String, Object> member);

	int session_db_del(HashMap<String, Object> value);

}
