package com.spring.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.service.AdminService;

@Controller
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(FunctionController.class);
	@Inject
	AdminService service;

	@RequestMapping(value = "admin.ad", method = { RequestMethod.POST, RequestMethod.GET })
	public String admin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String str = null;
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		str = "admin/admin";
		return str;
	}

	@RequestMapping(value = "memberList.ad", method = { RequestMethod.POST, RequestMethod.GET })
	public String member_management(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String str = null;
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		if ((String) session.getAttribute("id") == null || (int) session.getAttribute("id_grade") != 2) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('관리자로 로그인하세요!!!')");
			out.println("location.href='memberLogin.me'");
			out.println("</script>");
		} else {
			HashMap<String, Object> value = new HashMap<>();
			// 페이지 계산하기
			int limit = 5; // 페이지에 보여줄 목록 수
			int limitpage = 5; // 페이지수
			int page = 1;
			String choice;
			String search;
			if (request.getParameter("choice") == null || request.getParameter("choice").trim().equals("")) {
				choice = null;
			} else {
				choice = request.getParameter("choice");
			}
			if (request.getParameter("search") == null || request.getParameter("search").trim().equals("")) {
				search = null;
			} else {
				search = request.getParameter("search");
			}

			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
			}
			value.put("choice", choice);
			value.put("search", search);
			value.put("page", page);
			value.put("limit", limit);

			int listCount = service.member_count(value); // 총 리스트 수를 받아옴.
			// 총 페이지 수.
			int maxPage = (int) ((double) listCount / limit + 0.95); // 0.95를 더해서 올림 처리.
			// 현재 페이지에 보여줄 시작 페이지 수(1, 11, 21 등...)
			int startPage = (((int) ((double) page / limitpage + 0.9)) - 1) * limitpage + 1;
			// 현재 페이지에 보여줄 마지막 페이지 수.(10, 20, 30 등...)
			int endPage = startPage + limitpage - 1;

			if (endPage > maxPage)
				endPage = maxPage;
			// 페이지설정하기

			request.setAttribute("endPage", endPage);
			request.setAttribute("listCount", listCount);
			request.setAttribute("maxPage", maxPage);
			request.setAttribute("nowPage", page);
			request.setAttribute("startPage", startPage);

			List<HashMap> memberList = service.memberList(value);
			request.setAttribute("choice", choice);
			request.setAttribute("search", search);
			request.setAttribute("memberList", memberList);

			str = "admin/member_list";

		}
		return str;
	}

	@RequestMapping(value = "foodList.ad", method = { RequestMethod.POST, RequestMethod.GET })
	String food_list(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String str = null;
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		if (((String) session.getAttribute("id") == null) || (int) session.getAttribute("id_grade") != 2) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('관리자로 로그인하세요!!!')");
			out.println("location.href='memberLogin.me'");
			out.println("</script>");
		} else {
			HashMap<String, Object> value = new HashMap<>();

			// 페이지 계산하기
			int page = 1;
			int limit = 10; // 페이지에 보여줄 음식 목록 수
			int limitpage = 5; // 페이지수
			String choice = request.getParameter("choice");
			String search = request.getParameter("search");

			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
			}
			if (choice == null || choice.trim().equals("")) {
				choice = null;
			}
			if (search == null || search.trim().equals("")) {
				search = null;
			}

			value.put("choice", choice);
			value.put("search", search);
			value.put("page", page);
			value.put("limit", limit);

			int listCount = service.food_count(value); // 총 리스트 수를 받아옴.
			List<HashMap> foodList = service.food_list(value);
			// 총 페이지 수.
			int maxPage = (int) ((double) listCount / limit + 0.95); // 0.95를 더해서 올림 처리.
			// 현재 페이지에 보여줄 시작 페이지 수(1, 11, 21 등...)
			int startPage = (((int) ((double) page / limitpage + 0.9)) - 1) * limitpage + 1;
			// 현재 페이지에 보여줄 마지막 페이지 수.(10, 20, 30 등...)
			int endPage = startPage + limitpage - 1;

			if (endPage > maxPage)
				endPage = maxPage;
			// 페이지설정하기
			request.setAttribute("endPage", endPage);
			request.setAttribute("listCount", listCount);
			request.setAttribute("maxPage", maxPage);
			request.setAttribute("nowPage", page);
			request.setAttribute("startPage", startPage);

			request.setAttribute("choice", choice);
			request.setAttribute("search", search);
			request.setAttribute("foodList", foodList);

			str = "admin/food_list";
		}
		return str;
	}

	@RequestMapping(value = "foodInfo.ad", method = { RequestMethod.POST, RequestMethod.GET })
	String food_info(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String str = null;
		HttpSession session = request.getSession();
		if (((String) session.getAttribute("id") == null) || ((int) session.getAttribute("id_grade")) != 2) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('관리자로 로그인하세요!!!')");
			out.println("location.href='memberLogin.me'");
			out.println("</script>");
		} else {
			int connect_num = Integer.parseInt(request.getParameter("connect_num"));
			int page = Integer.parseInt(request.getParameter("page"));
			HashMap<String, Object> value = new HashMap<>();
			value.put("connect_num", connect_num);
			List<HashMap> total = service.food_info(value);
			request.setAttribute("total", total);
			request.setAttribute("page", page);
			request.setAttribute("search", request.getAttribute("search"));
			request.setAttribute("choice", request.getAttribute("choice"));

			str = "admin/food_info";
		}
		return str;
	}

	@RequestMapping(value = "foodAddForm.ad", method = { RequestMethod.POST, RequestMethod.GET })
	String food_addForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String str = null;
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		if (((String) session.getAttribute("id") == null) || ((int) session.getAttribute("id_grade")) != 2) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('관리자 아이디로 접속해주십시오!')");
			out.println("location.href='/EmartFoodCourt/memberLogin.me';");
			out.println("</script>");
		} else {
			List<HashMap> food = service.select_food();
			List<HashMap> jijum = service.select_jijum();
			request.setAttribute("food", food);
			request.setAttribute("jijum", jijum);
			str = "admin/food_add";
		}
		return str;
	}

	@RequestMapping(value = "foodAddPro.ad", method = { RequestMethod.POST, RequestMethod.GET })
	String food_proForm(MultipartHttpServletRequest multi, HttpServletResponse response) throws Exception {
		String str = null;
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = multi.getSession();
		if (((String) session.getAttribute("id") == null) || ((int) session.getAttribute("id_grade")) != 2) {
			out.println("<script>");
			out.println("alert('관리자 아이디로 접속해주십시오!')");
			out.println("location.href='/EmartFoodCourt/memberLogin.me';");
			out.println("</script>");
		} else {
			String realPath = multi.getSession().getServletContext().getRealPath("/");
			String filePath = realPath + "resources/images/";
			File dir = new File(filePath);
			if (!dir.isDirectory()) {
				dir.mkdir();
			}

			List<MultipartFile> fileList = multi.getFiles("food_image");

			int fileSize = fileList.size();

			HashMap<String, Object> value = new HashMap<>();
			value.put("food_name", multi.getParameter("food_name"));
			value.put("food_category", multi.getParameter("food_category"));
			value.put("jijum_name", multi.getParameter("jijum_name"));

			for (MultipartFile mf : fileList) {

				String originFileName;
				logger.info("originalFilename : " + mf.getOriginalFilename());

				try {
					if (!(mf.getOriginalFilename().trim().equals("")) && mf.getOriginalFilename() != null) {
						originFileName = uploadFile(mf.getOriginalFilename(), mf.getBytes(), filePath);
						mf.transferTo(new File(filePath + originFileName));

						value.put("food_image", originFileName);

						logger.debug("------------- file start -------------");
						logger.debug("content_type : " + mf.getContentType());
						logger.debug("name : " + mf.getName());
						logger.debug("filename : " + mf.getOriginalFilename());
						logger.debug("size : " + mf.getSize());
						logger.debug("-------------- file end --------------\n");

					} else {
						value.put("food_image", "logo.png");
					}

				} catch (IllegalStateException e) { // TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) { // TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			value.put("food_name", multi.getParameter("food_name"));
			value.put("jijum_name", multi.getParameter("jijum_name"));
			value.put("con_price", multi.getParameter("con_price"));
			value.put("food_content", multi.getParameter("food_content"));
			boolean isAddFood = service.food_add(value);
			if (isAddFood) {
				out.println("<script>");
				out.println("alert('음식이 등록되었습니다.')");
				out.println("location.href='foodList.ad';");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('음식을 등록하는데 실패하였습니다.')");
				out.println("history.back();");
				out.println("</script>");
			}
		}
		return str;
	}

	@RequestMapping(value = "foodModForm.ad", method = { RequestMethod.POST, RequestMethod.GET })
	String food_modForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String str = null;
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		int page = Integer.parseInt(request.getParameter("page"));
		if (((String) session.getAttribute("id") == null) || (int) session.getAttribute("id_grade") != 2) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('관리자로 로그인하세요!!!')");
			out.println("location.href='memberLogin.me'");
			out.println("</script>");
		} else {
			int connect_num = Integer.parseInt(request.getParameter("connect_num"));
			HashMap<String, Object> value = new HashMap<>();
			value.put("connect_num", connect_num);
			List<HashMap> total = service.food_mod_form(value);
			List<HashMap> food = service.select_food();
			List<HashMap> jijum = service.select_jijum();
			if (total != null) {
				request.setAttribute("food", food);
				request.setAttribute("jijum", jijum);
				request.setAttribute("total", total);
				request.setAttribute("page", page);
				str = "admin/food_mod";
			} else {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('화면을 불러올수없습니다..!')");
				out.println("location.href='/EmartFoodCourt/index.jsp'");
				out.println("</script>");
			}
		}
		return str;
	}

	@RequestMapping(value = "foodModPro.ad", method = { RequestMethod.POST })
	String food_modPro(MultipartHttpServletRequest multi, HttpServletResponse response) throws Exception {
		String str = null;
		PrintWriter out = response.getWriter();
		multi.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String realPath = multi.getSession().getServletContext().getRealPath("/");
		String filePath = realPath + "resources/images/";

		HttpSession session = multi.getSession();
		if (((String) session.getAttribute("id") == null) || ((int) session.getAttribute("id_grade")) != 2) {
			response.setContentType("text/html;charset=UTF-8");

			out.println("<script>");
			out.println("alert('관리자 아이디로 접속해주십시오!')");
			out.println("location.href='/EmartFoodCourt/memberLogin.me';");
			out.println("</script>");
		} else {

			List<MultipartFile> fileList = multi.getFiles("food_image");

			String food_name = multi.getParameter("food_name");
			String old_food_name = multi.getParameter("old_food_name");
			String food_category = multi.getParameter("food_category");
			String jijum_name = multi.getParameter("jijum_name");
			int connect_num = Integer.parseInt(multi.getParameter("connect_num"));

			HashMap<String, Object> value = new HashMap<>();
			value.put("food_name", food_name);
			value.put("old_food_name", old_food_name);
			value.put("food_category", food_category);
			value.put("jijum_name", jijum_name);
			value.put("connect_num", connect_num);
			value.put("con_price", multi.getParameter("con_price"));
			value.put("food_content", multi.getParameter("food_content"));
			if (multi.getParameter("img_check").equals("true")) { // 아이디를 input text로받는지 file로받는지 구분하는 값

				if (fileList.size() == 0) {
					value.put("food_image", "logo.png");
				} else {
					String food_image = uploadFile(fileList.get(0).getOriginalFilename(), fileList.get(0).getBytes(),
							filePath);
					value.put("food_image", food_image);
				}
			} else {
				value.put("food_image", multi.getParameter("food_image"));
			}
			int page = Integer.parseInt(multi.getParameter("page"));
			int isModFood = service.food_mod_pro(value);
			if (isModFood > 0) {

				out.println("<script>");
				out.println("alert('음식이 수정되었습니다.')");
				out.println("location.href='foodList.ad?page=" + page + "';");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('지점에 이미있는 음식이거나 잘못 수정하셨습니다.')");
				out.println("history.back();");
				out.println("</script>");
			}
		}
		return str;
	}

	@RequestMapping(value = "foodDelete.ad", method = { RequestMethod.GET })
	String food_delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String str = null;
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (((String) session.getAttribute("id") == null) || (int) session.getAttribute("id_grade") != 2) {
			out.println("<script>");
			out.println("alert('관리자로 로그인하세요!!!')");
			out.println("location.href='memberLogin.me'");
			out.println("</script>");
		} else {
			int connect_num = Integer.parseInt(request.getParameter("connect_num"));
			String food_name = request.getParameter("food_name");
			HashMap<String, Object> value = new HashMap<>();
			value.put("connect_num", connect_num);
			value.put("food_name", food_name);
			int isDelete = service.food_delete(value);
			if (isDelete > 0) {
				out.println("<script>");
				out.println("alert('음식이 삭제되었습니다.')");
				out.println("location.href='foodList.ad';");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('음식을 삭제하는데 오류가 발생하였습니다.')");
				out.println("location.href='foodList.ad';");
				out.println("</script>");
			}
		}
		return str;
	}
	
	@RequestMapping(value ="jijumList.ad", method= {RequestMethod.GET,RequestMethod.POST})
	String jijum_list(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String str = null;
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		System.out.println((String) session.getAttribute("id"));
		if (((String) session.getAttribute("id") == null) || (int) session.getAttribute("id_grade") != 2) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('관리자로 로그인하세요!!!')");
			out.println("location.href='memberLogin.me'");
			out.println("</script>");
		} else {

			// 페이지 계산하기
			int page = 1;
			int limit = 5; // 페이지에 보여줄 지점 목록 수
			int limitpage = 5; // 페이지수
			String choice = request.getParameter("choice");
			String search = request.getParameter("search");
			
			if(choice==null || choice.trim().equals("")) {
				choice = null;
			}		

			if(search==null || search.trim().equals("")) {
				search = null;
			}
			System.out.println("page값 : " + request.getParameter("page"));
			if (request.getParameter("page") != null && !(request.getParameter("page").trim().equals(""))) {
				page = Integer.parseInt(request.getParameter("page"));
			}
			HashMap<String,Object> value = new HashMap<>();
			value.put("choice", choice);
			value.put("search", search);
			value.put("page", page);
			value.put("limit", limit);
			int listCount = service.jijum_count(value); // 총 리스트 수를 받아옴.
			// 총 페이지 수.
			int maxPage = (int) ((double) listCount / limit + 0.95); // 0.95를 더해서 올림 처리.
			// 현재 페이지에 보여줄 시작 페이지 수(1, 11, 21 등...)
			int startPage = (((int) ((double) page / limitpage + 0.9)) - 1) * limitpage + 1;
			// 현재 페이지에 보여줄 마지막 페이지 수.(10, 20, 30 등...)
			int endPage = startPage + limitpage - 1;

			if (endPage > maxPage)
				endPage = maxPage;
			// 페이지설정하기
			request.setAttribute("endPage", endPage);
			request.setAttribute("listCount", listCount);
			request.setAttribute("maxPage", maxPage);
			request.setAttribute("nowPage", page);
			request.setAttribute("startPage", startPage);
			
			List<HashMap> jijumList = service.jijum_list(value);
			request.setAttribute("choice", choice);
			request.setAttribute("search", search);
			request.setAttribute("jijumList", jijumList);	
			str = "admin/jijum_list";
		}
		return str;
	}
	
	@RequestMapping(value="jijumInfo.ad", method= {RequestMethod.GET})
	String jijum_info(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String str = null;
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		if (((String) session.getAttribute("id") == null) || ((int) session.getAttribute("id_grade")) != 2) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('관리자로 로그인하세요!!!')");
			out.println("location.href='memberLogin.me'");
			out.println("</script>");
		} else {
			String jijum_name = request.getParameter("jijum_name");
			int page = 1;
			if(request.getParameter("page") ==null || request.getParameter("page").trim().equals("")) {
				 page = 1;
			}else {
				page =  Integer.parseInt(request.getParameter("page"));
			}
			String choice = request.getParameter("choice");
			String search = request.getParameter("search");
			
			HashMap<String,Object> value = new HashMap<>();
			value.put("jijum_name", jijum_name);
			List<HashMap> total = service.jijum_info(value);
			System.out.println("total : " + total);
			request.setAttribute("total", total);
			request.setAttribute("page", page);	
			str="admin/jijum_info";
		}
		return str;
	}
	
	@RequestMapping(value = "jijumAddForm.ad", method = { RequestMethod.GET })
	String jijum_addForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String str = null;
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		System.out.println((String) session.getAttribute("id"));
		if (((String) session.getAttribute("id") == null) || (int) session.getAttribute("id_grade") != 2) {
			response.setContentType("text/html;charset=UTF-8");
			out.println("<script>");
			out.println("alert('관리자로 로그인하세요!!!')");
			out.println("location.href='memberLogin.me'");
			out.println("</script>");
		} else {
			str = "admin/jijum_add";
		}
		return str;
	}
	
	@RequestMapping(value = "jijumAddPro.ad", method = { RequestMethod.GET,RequestMethod.POST })
	String jijum_addPro(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String str = null;
		HttpSession session = request.getSession();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (((String) session.getAttribute("id") == null) || ((int) session.getAttribute("id_grade")) != 2) {	
			out.println("<script>");
			out.println("alert('관리자 아이디로 접속해주십시오!')");
			out.println("location.href='/EmartFoodCourt/memberLogin.me';");
			out.println("</script>");
		} else {
			String jijum_name = request.getParameter("jijum_name");
			String jijum_content = request.getParameter("jijum_content");
			String jijum_intro = request.getParameter("jijum_intro");
			HashMap<String,Object> value = new HashMap<>();
			value.put("jijum_name", jijum_name);
			value.put("jijum_content", jijum_content);
			value.put("jijum_intro", jijum_intro);
			int jijum_insert = service.jijum_insert(value);
			if (jijum_insert>0) {			
				out.println("<script>");
				out.println("alert('지점이 등록되었습니다.')");
				out.println("location.href='jijumList.ad';");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('지점이 등록하는데 실패하였습니다.')");
				out.println("history.back();");
				out.println("</script>");
			}
		}
		return str;
	}
	
	@RequestMapping(value = "jijumModForm.ad", method = { RequestMethod.GET,RequestMethod.POST })
	String jijum_ModForm(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String str = null;
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		System.out.println((String) session.getAttribute("id"));
		if (((String) session.getAttribute("id") == null) || (int) session.getAttribute("id_grade") != 2) {	
			out.println("<script>");
			out.println("alert('관리자로 로그인하세요!!!')");
			out.println("location.href='memberLogin.me'");
			out.println("</script>");
		} else {
			String jijum_name = request.getParameter("jijum_name");
			HashMap<String,Object> value = new HashMap<>();
			value.put("jijum_name", jijum_name);
			List<HashMap> total = service.jijum_info(value);
			int page = Integer.parseInt(request.getParameter("page"));
			if (total != null) {
				request.setAttribute("total", total);
				request.setAttribute("page", page);
				str = "admin/jijum_mod";
			}
		}
		return str;
	}
	
	@RequestMapping(value = "jijumModPro.ad", method = { RequestMethod.GET,RequestMethod.POST })
	String jijum_ModPro(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String str = null;
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (((String) session.getAttribute("id") == null) || (int) session.getAttribute("id_grade") != 2) {
			out.println("<script>");
			out.println("alert('관리자로 로그인하세요!!!')");
			out.println("location.href='memberLogin.me'");
			out.println("</script>");
		} else {
			String oldjijum_name = request.getParameter("oldjijum_name");
			String jijum_name = request.getParameter("jijum_name");
			String jijum_content = request.getParameter("jijum_content");
			String jijum_intro = request.getParameter("jijum_intro");
			int page = Integer.parseInt(request.getParameter("page"));
			HashMap<String,Object> value = new HashMap<>();
			value.put("oldjijum_name", oldjijum_name);
			value.put("jijum_name", jijum_name);
			value.put("jijum_content", jijum_content);
			value.put("jijum_intro", jijum_intro);
			int update_result = service.jijum_modify(value);
			System.out.println("update_result : " + update_result);
			if (update_result>0) {
				out.println("<script>");
				out.println("alert('지점이 수정되었습니다.')");
				out.println("location.href='jijumList.ad?page=" + page + "';");
				out.println("</script>");
			} else {	
				out.println("<script>");
				out.println("alert('지점이 수정하는데 실패하였습니다.')");
				out.println("location.href='jijumModForm.ad?jijum_name=" + oldjijum_name + "&page="+ page +"';");
				out.println("</script>");
			}
		}
		return str;
	}
	@RequestMapping(value="jijumDelete.ad", method= {RequestMethod.POST,RequestMethod.GET})
	String jijum_delete(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String str = null;
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		if (((String) session.getAttribute("id") == null) || (int) session.getAttribute("id_grade") != 2) {	
			out.println("<script>");
			out.println("alert('관리자로 로그인하세요!!!')");
			out.println("location.href='memberLogin.me'");
			out.println("</script>");
		} else {
			String jijum_name = request.getParameter("jijum_name");
			HashMap<String,Object> value = new HashMap<>();
			value.put("jijum_name", jijum_name);	
			int delete_result = service.jijum_delete(value);
			if (delete_result>0) {		
				out.println("<script>");
				out.println("alert('지점이 삭제되었습니다.')");
				out.println("location.href='jijumList.ad';");
				out.println("</script>");
			} else {	
				out.println("<script>");
				out.println("alert('지점을 삭제하는데 실패하였습니다.')");
				out.println("location.href='jijumList.ad';");
				out.println("</script>");
			}
		}
		return str;
	}
	
	@RequestMapping(value="recomstatusList.ad", method= {RequestMethod.POST,RequestMethod.GET})
	String RecomStatusList(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String str = null;
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		if (((String) session.getAttribute("id") == null) || ((int) session.getAttribute("id_grade")) != 2) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('관리자 아이디로 접속해주십시오!')");
			out.println("location.href='/EmartFoodCourt/memberLogin.me';");
			out.println("</script>");
		} else {
			// 페이지 계산하기
			int page = 1;
			int limit = 5; // 페이지에 보여줄 음식 목록 수
			int limitpage = 5; // 페이지수
			String choice = request.getParameter("choice");
			String search = request.getParameter("search");
			
			if(choice==null || choice.trim().equals("")) {
				choice = null;
			}		

			if(search==null || search.trim().equals("")) {
				search = null;
			}
			System.out.println("page값 : " + request.getParameter("page"));
			if (request.getParameter("page") != null && !(request.getParameter("page").trim().equals(""))) {
				page = Integer.parseInt(request.getParameter("page"));
			}
			HashMap<String,Object> value = new HashMap<>();
			value.put("choice", choice);
			value.put("search", search);
			value.put("page", page);
			value.put("limit", limit);
			int listCount = service.recom_count(value); // 총 리스트 수를 받아옴.
			// 총 페이지 수.
			int maxPage = (int) ((double) listCount / limit + 0.95); // 0.95를 더해서 올림 처리.
			// 현재 페이지에 보여줄 시작 페이지 수(1, 11, 21 등...)
			int startPage = (((int) ((double) page / limitpage + 0.9)) - 1) * limitpage + 1;
			// 현재 페이지에 보여줄 마지막 페이지 수.(10, 20, 30 등...)
			int endPage = startPage + limitpage - 1;
			if (endPage > maxPage)
				endPage = maxPage;
			// 페이지설정하기
			request.setAttribute("endPage", endPage);
			request.setAttribute("listCount", listCount);
			request.setAttribute("maxPage", maxPage);
			request.setAttribute("nowPage", page);
			request.setAttribute("startPage", startPage);

			List<HashMap> totalList = service.Recom_status_list(value);
			request.setAttribute("choice", choice);
			request.setAttribute("search", search);
			request.setAttribute("totalList", totalList);
			str = "admin/recom_status_list";
		}
		return str;
	}
	
	@RequestMapping(value="recomstatus_rec.ad", method= {RequestMethod.POST,RequestMethod.GET})
	String RecomStatus_rec(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String str = null;
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		int page = 1;

		if (request.getParameter("page") == null) {
			page = 1;
		} else {
			page = Integer.parseInt(request.getParameter("page"));
		}
		if (session.getAttribute("id") == null || session.getAttribute("id") == null) {
			out.println("<script>");
			out.println("alert('로그인 해주십시오.');");
			out.println("location.href='/EmartFoodCourt/memberLogin.me';");
			out.println("</script>");
		}else {
			String session_id = (String) session.getAttribute("id");
			int id_grade = (int) session.getAttribute("id_grade");
			String id = request.getParameter("id");
			if (id_grade != 2) {
				if (session_id.equals(id)) {
					HashMap<String,Object> value = new HashMap<>();
					value.put("id", id);
					List<HashMap> totalList = service.Recom_status(value);
					request.setAttribute("totalList", totalList);
					request.setAttribute("page", page);
					str = "admin/recom_status_recom";
				} else {
					out.println("<script>");
					out.println("alert('잘못된 경로입니다.')");
					out.println("location.href='/EmartFoodCourt/';");
					out.println("</script>");
				}
			} else {
				HashMap<String,Object> value = new HashMap<>();
				value.put("id", id);
				List<HashMap> totalList = service.Recom_status(value);
				request.setAttribute("totalList", totalList);
				request.setAttribute("page", page);
				
				str = "admin/recom_status_recom";
			}
		}
		return str;
	}
	
	@RequestMapping(value="recomstatus_grade.ad", method= {RequestMethod.POST,RequestMethod.GET})
	String RecomStatus_grade(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String str = null;
		HttpSession session = request.getSession();
		if (((String) session.getAttribute("id") == null) || ((int) session.getAttribute("id_grade")) != 2) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('관리자 아이디로 접속해주십시오!')");
			out.println("location.href='/EmartFoodCourt/memberLogin.me';");
			out.println("</script>");
		} else {
			String id = request.getParameter("id");
			int page = Integer.parseInt(request.getParameter("page"));
			HashMap<String,Object> value = new HashMap<>();
			value.put("id", id);
			List<HashMap>totalList = service.status_grade(value);
			request.setAttribute("totalList", totalList);
			request.setAttribute("page", page);
			str = "admin/recom_status_grade";
		}
		return str;
	}
	
	@RequestMapping(value="recomstatus_rev.ad", method= {RequestMethod.POST,RequestMethod.GET})
	String RecomStatus_rev(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String str = null;
		HttpSession session = request.getSession();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		int page = 1;
		if (session.getAttribute("id") == null || session.getAttribute("id") == null) {
			
			out.println("<script>");
			out.println("alert('로그인 해주십시오.');");
			out.println("location.href='/EmartFoodCourt/memberLogin.me';");
			out.println("</script>");
		}else {
			String session_id = (String) session.getAttribute("id");
			String id = request.getParameter("id");
			int id_grade = (int) session.getAttribute("id_grade");
			if (id_grade != 2) {
				if (session_id.equals(id)) {
					HashMap<String,Object> value = new HashMap<>();
					value.put("id", id);
					List<HashMap> totalList = service.status_rev(value);
					request.setAttribute("totalList", totalList);
					request.setAttribute("page", page);
					str = "admin/recom_status_rev";
				} else {
					out.println("<script>");
					out.println("alert('잘못된 경로입니다.')");
					out.println("location.href='/EmartFoodCourt/';");
					out.println("</script>");
				}
			} else {
				HashMap<String,Object> value = new HashMap<>();
				value.put("id", id);
				List<HashMap> totalList = service.status_rev(value);
				request.setAttribute("totalList", totalList);
				request.setAttribute("page", page);
				str = "admin/recom_status_rev";
			}
		}
		return str;
	}
	
	private String uploadFile(String originalName, byte[] fileData, String filePath) throws Exception {
		// uuid 생성(Universal Unique IDentifier, 범용 고유 식별자)
		UUID uuid = UUID.randomUUID();
		// 랜덤생성+파일이름 저장
		String savedName = uuid.toString() + "_" + originalName;
		File target = new File(filePath, savedName);
		// 임시디렉토리에 저장된 업로드된 파일을 지정된 디렉토리로 복사
		// File(CopyUtils.copy(바이트배열,파일객체)
		FileCopyUtils.copy(fileData, target);
		return savedName;
	}
}
