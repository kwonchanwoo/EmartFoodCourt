package com.spring.service;

import java.util.HashMap;

import javax.annotation.Resource;


public interface UtilService {

	int dupl_cnt(HashMap<String, Object> member);

	int session_db_del(HashMap<String, Object> value);

}
