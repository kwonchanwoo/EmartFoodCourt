package com.spring.service;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.spring.dao.FunctionDao;


@Service
public class FunctionServiceImpl implements FunctionService {
	
	@Inject
	private FunctionDao dao;
	
	@Override
	public List<HashMap> jijum_menu() {
		// TODO Auto-generated method stub
		return dao.jijum_menu();
	}

	@Override
	public List<HashMap> food_menu() {
		// TODO Auto-generated method stub
		return dao.food_menu();
	}

	@Override
	public List<HashMap> recom_value(HashMap<String, String> recom) {
		// TODO Auto-generated method stub
		return dao.recom_value(recom);
	}

	@Override
	public int food_count() {
		// TODO Auto-generated method stub
		return dao.food_count();
	}

	@Override
	public List<HashMap> menu_view(HashMap<String, Object> jijum) {
		// TODO Auto-generated method stub
		return dao.menu_view(jijum);
	}

	@Override
	public List<HashMap> select_food_category() {
		// TODO Auto-generated method stub
		return dao.select_food_category();
	}

	@Override
	public List<HashMap> select_jijum_category() {
		// TODO Auto-generated method stub
		return dao.select_jijum_category();
	}

	@Override
	public List<HashMap> select_menuView(HashMap<String,Object> jf) {
		// TODO Auto-generated method stub
		return dao.select_menu_view(jf);
	}



	@Override
	public int getListCount(HashMap<String, Object> list) {
		// TODO Auto-generated method stub
		return dao.getListCount(list);
	}

	@Override
	public List<HashMap> review_select(HashMap<String, Object> review) {
		// TODO Auto-generated method stub
		return dao.review_select(review);
	}

	@Override
	public List<HashMap> menuViewDetail(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return dao.menuViewDetail(value);
	}

	@Override
	public List<HashMap> board_detail(HashMap<Object, Object> rev) {
		// TODO Auto-generated method stub
		return dao.board_detail(rev);
	}

	@Override
	public int updateCount(HashMap<Object, Object> rev) {
		// TODO Auto-generated method stub
		return dao.updateCount(rev);
	}

	@Override
	public int avg_grade(HashMap<Object, Object> avg) {
		// TODO Auto-generated method stub
		return dao.avg_grade(avg);
	}

	@Override
	public int dupl_check(HashMap<Object, Object> avg) {
		// TODO Auto-generated method stub
		return dao.dupl_check(avg);
	}

	@Override
	public int avg_grade_insert(HashMap<Object, Object> avg) {
		// TODO Auto-generated method stub
		return dao.avg_grade_insert(avg);
	}

	@Override
	public int recom_dupl_chk(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return dao.recom_dupl_chk(value);
	}

	@Override
	public int recom_del(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return dao.recom_del(value);
	}

	@Override
	public int recom_add(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return dao.recom_add(value);
	}

	@Override
	public List<HashMap> rev_board_detail(HashMap<Object, Object> value) {
		// TODO Auto-generated method stub
		return dao.rev_board_detail(value);
	}

	@Override
	public int rev_board_modify(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return dao.rev_board_modify(value);
	}

	@Override
	public int rev_board_write(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return dao.rev_board_write(value);
	}

	@Override
	public int rev_board_delete(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return dao.rev_board_delete(value);
	}

	@Override
	public List<HashMap> rev_board_check(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return dao.rev_board_check(value);
	}

	@Override
	public int rev_board_img_write(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return dao.rev_board_img_write(value);
	}

	@Override
	public int rev_board_pk() {
		// TODO Auto-generated method stub
		return dao.rev_board_pk();
	}

	@Override
	public List<HashMap> rev_image_select(HashMap<Object, Object> rev) {
		// TODO Auto-generated method stub
		List<HashMap> value = null;
		if(!(dao.rev_image_select(rev).isEmpty())) {
			 value = dao.rev_image_select(rev);
		}
		return value;
	}
	

}
