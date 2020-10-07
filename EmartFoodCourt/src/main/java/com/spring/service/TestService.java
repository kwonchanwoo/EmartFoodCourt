package com.spring.service;

import java.util.HashMap;
import java.util.List;

public interface TestService {

	public List<HashMap> list() throws Exception;
	
	public Integer selectMemberCount() throws Exception;
	
}
