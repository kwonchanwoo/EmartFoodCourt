package com.spring.service;

import java.util.HashMap;
import java.util.List;




public interface FunctionService {

	List<HashMap> jijum_menu();

	List<HashMap> food_menu();

	List<HashMap> recom_value(HashMap<String, String> recom);

	int food_count();

	List<HashMap> menu_view(HashMap<String, Object> jijum);

	List<HashMap> select_food_category();

	List<HashMap> select_jijum_category();

	List<HashMap> select_menuView(HashMap<String,Object> jf);

	int getListCount(HashMap<String, Object> list);

	List<HashMap> review_select(HashMap<String, Object> review);

	List<HashMap> menuViewDetail(HashMap<String, Object> value);

	List<HashMap> board_detail(HashMap<Object, Object> rev);

	int updateCount(HashMap<Object, Object> rev);

	int avg_grade(HashMap<Object, Object> avg);

	int dupl_check(HashMap<Object, Object> avg);

	int avg_grade_insert(HashMap<Object, Object> avg);

	int recom_dupl_chk(HashMap<String, Object> value);

	int recom_del(HashMap<String, Object> value);

	int recom_add(HashMap<String, Object> value);

	List<HashMap> rev_board_detail(HashMap<Object, Object> value);

	int rev_board_modify(HashMap<String, Object> value);

	int rev_board_write(HashMap<String, Object> value);

	int rev_board_delete(HashMap<String, Object> value);

	List<HashMap> rev_board_check(HashMap<String, Object> value);

	int rev_board_img_write(HashMap<String, Object> value);

	int rev_board_pk();

	List<HashMap> rev_image_select(HashMap<Object, Object> rev);

}
