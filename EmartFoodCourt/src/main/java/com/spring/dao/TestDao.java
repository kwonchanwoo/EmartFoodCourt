package com.spring.dao;

import java.util.HashMap;
import java.util.List;

public interface TestDao {
	public List<HashMap> list() throws Exception;
	
	public Integer selectMemberCount() throws Exception;

	public void regist(HashMap<String, String> regist);
}
