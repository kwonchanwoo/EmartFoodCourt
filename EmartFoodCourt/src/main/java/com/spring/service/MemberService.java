package com.spring.service;

import java.util.HashMap;
import java.util.List;

public interface MemberService {

	int login_chk(HashMap<Object, Object> member);

	List<HashMap> getMember(HashMap<Object, Object> id_chk);

	int dupl_chk(HashMap<Object, Object> member);

	int user_add(HashMap<Object, Object> member) ;

	int dupl_sol(HashMap<Object, Object> member);

	List<HashMap> getUser(HashMap<Object, Object> member);

	int session_db_del(HashMap<String, Object> value);

	int join(HashMap<String, Object> value);

	List<HashMap> search_id(HashMap<String, Object> value);

	List<HashMap> search_pass(HashMap<String, Object> value);

	int modify_pass(HashMap<String, Object> value);

	int address_count(HashMap<String, Object> value);

	List<HashMap> select_mypage(HashMap<String, Object> value);

	int member_delete(HashMap<String, Object> value);

	List<HashMap> selectMember(HashMap<String, Object> value);

	int member_modify(HashMap<String, Object> value);



}
