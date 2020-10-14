package com.spring.service;

import java.util.HashMap;
import java.util.List;



public interface AdminService {

	List<HashMap> memberList(HashMap<String,Object> vaalue);

	int member_count(HashMap<String, Object> value);

	List<HashMap> food_list(HashMap<String, Object> value);

	int food_count(HashMap<String,Object> value);

	List<HashMap> food_info(HashMap<String, Object> value);

	List<HashMap> select_food();

	List<HashMap> select_jijum();

	boolean food_add(HashMap<String, Object> value);

	List<HashMap> food_mod_form(HashMap<String, Object> value);

	int food_mod_pro(HashMap<String, Object> value);

	int food_delete(HashMap<String,Object> value);

	int jijum_count(HashMap<String, Object> value);

	List<HashMap> jijum_list(HashMap<String, Object> value);

	List<HashMap> jijum_info(HashMap<String, Object> value);



	

}
