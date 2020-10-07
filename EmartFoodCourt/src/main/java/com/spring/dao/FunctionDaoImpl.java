package com.spring.dao;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

@Repository
public class FunctionDaoImpl implements FunctionDao {

	// sql�젒洹쇱쓣 �쐞�븳 媛앹껜
		@Inject
		private SqlSession sqlsession;
		
		// mapper�뙆�씪�쓽 namespace
		private static String namespace = "com.spring.mapper.FunctionMapper";
	
	@Override
	public List<HashMap> jijum_menu() {
		// TODO Auto-generated method stub
		return sqlsession.selectList(namespace+".jijum_menu");
	}

	@Override
	public List<HashMap> food_menu() {
		// TODO Auto-generated method stub
		return sqlsession.selectList(namespace+".food_menu");
	}

	@Override
	public List<HashMap> recom_value(HashMap<String, String> recom) {
		List<HashMap> test = null;
		if(!(sqlsession.selectList(namespace+".recom", recom).isEmpty())) {
			 test = sqlsession.selectList(namespace+".recom", recom);
		}
		return test;
	}

	@Override
	public int food_count() {
		// TODO Auto-generated method stub
		return sqlsession.selectOne(namespace+".food_count");
	}

	@Override
	public List<HashMap> menu_view(HashMap<String, Object> jijum) {
		// TODO Auto-generated method stub

		return sqlsession.selectList(namespace+".menu_view",jijum);
	}

	@Override
	public List<HashMap> select_food_category() {
		// TODO Auto-generated method stub

		return sqlsession.selectList(namespace+".select_food_category");
	}

	@Override
	public List<HashMap> select_jijum_category() {
		// TODO Auto-generated method stub
		return sqlsession.selectList(namespace+".select_jijum_category");
	}

	@Override
	public List<HashMap> select_menu_view(HashMap<String,Object> jf) {
		// TODO Auto-generated method stub
		return sqlsession.selectList(namespace+".select_menu_view",jf);
	}

	@Override
	public int getListCount(HashMap<String, Object> list) {
		// TODO Auto-generated method stub
		return sqlsession.selectOne(namespace+".getListCount", list);
	}

	@Override
	public List<HashMap> review_select(HashMap<String, Object> review) {
		// TODO Auto-generated method stub
		return sqlsession.selectList(namespace+".review_select",review);
	}

	@Override
	public List<HashMap> menuViewDetail(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return sqlsession.selectList(namespace+".menuViewDetail",value);
	}

	@Override
	public List<HashMap> board_detail(HashMap<Object, Object> rev) {
		// TODO Auto-generated method stub
		return sqlsession.selectList(namespace+".board_detail",rev);
	}

	@Override
	public int updateCount(HashMap<Object, Object> rev) {
		// TODO Auto-generated method stub
		return sqlsession.update(namespace+".updateCount",rev);
	}

	@Override
	public int avg_grade(HashMap<Object, Object> avg) {
		// TODO Auto-generated method stub
		return sqlsession.update(namespace+".avg_grade",avg);
	}

	@Override
	public int dupl_check(HashMap<Object, Object> avg) {
		// TODO Auto-generated method stub
		return sqlsession.selectOne(namespace+".dupl_check",avg);
	}

	@Override
	public int avg_grade_insert(HashMap<Object, Object> avg) {
		// TODO Auto-generated method stub
		return sqlsession.insert(namespace+".avg_grade_insert", avg);
	}

	@Override
	public int recom_dupl_chk(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return sqlsession.selectOne(namespace+".recom_dupl_chk",value);
	}

	@Override
	public int recom_del(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return sqlsession.delete(namespace+".recom_del",value);
	}

	@Override
	public int recom_add(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return sqlsession.insert(namespace+".recom_add",value);
	}

	@Override
	public List<HashMap> rev_board_detail(HashMap<Object, Object> value) {
		// TODO Auto-generated method stub
		return sqlsession.selectList(namespace+".rev_board_detail",value);
	}

	@Override
	public int rev_board_modify(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return sqlsession.update(namespace+".rev_board_modify",value);
	}

	@Override
	public int rev_board_write(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return sqlsession.insert(namespace+".rev_board_write",value);
	}

	@Override
	public int rev_board_delete(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return sqlsession.delete(namespace+".rev_board_delete",value);
	}

	@Override
	public List<HashMap> rev_board_check(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return sqlsession.selectList(namespace+".rev_board_check",value);
	}

	@Override
	public int rev_board_img_write(HashMap<String, Object> value) {
		// TODO Auto-generated method stub
		return sqlsession.insert(namespace+".rev_board_img_write",value);
	}

	@Override
	public int rev_board_pk() {
		// TODO Auto-generated method stub
		return sqlsession.selectOne(namespace+".rev_board_pk");
	}

	@Override
	public List<HashMap> rev_image_select(HashMap<Object, Object> rev) {
		// TODO Auto-generated method stub
		return sqlsession.selectList(namespace+".rev_image_select",rev);
	}
}
