package com.spring.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

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
	@RequestMapping(value="admin.ad",method= {RequestMethod.POST,RequestMethod.GET})
	public String admin(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String str =null;
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		str = "admin/admin";
		return str;
	}
	
	@RequestMapping(value="memberList.ad",method= {RequestMethod.POST,RequestMethod.GET})
		public String member_management(HttpServletRequest request,HttpServletResponse response) throws Exception{
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
				HashMap<String,Object> value = new HashMap<>();
				// 페이지 계산하기
				int limit = 5; // 페이지에 보여줄 목록 수
				int limitpage = 5; // 페이지수
				int page = 1;
				String choice;
				String search;
				if(request.getParameter("choice")==null || request.getParameter("choice").trim().equals("")) {
					choice = null;
				}else {
					choice = request.getParameter("choice");
				}
				if(request.getParameter("search")==null || request.getParameter("search").trim().equals("")) {
					search=null;
				}else {
					search = request.getParameter("search");
				}
				
				
				
				if (request.getParameter("page") != null) {
					page = Integer.parseInt(request.getParameter("page"));
				}
				value.put("choice",choice);
				value.put("search", search);
				value.put("page", page);
				value.put("limit", limit);
				
				int listCount = service.member_count(value); // 총 리스트 수를 받아옴.
				System.out.println("listCount : " + listCount);
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

				str="admin/member_list";
				
			}
			return str;
		}
		
	@RequestMapping(value="foodList.ad",method= {RequestMethod.POST,RequestMethod.GET})
	String food_list(HttpServletRequest request,HttpServletResponse response) throws Exception{
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
			HashMap<String,Object> value = new HashMap<>();
			

			// 페이지 계산하기
			int page = 1;
			int limit = 10; // 페이지에 보여줄 음식 목록 수
			int limitpage = 5; // 페이지수
			String choice = request.getParameter("choice");
			String search = request.getParameter("search");

			if (request.getParameter("page") != null) {
				page = Integer.parseInt(request.getParameter("page"));
			}
			if(choice==null || choice.trim().equals("")) {
				choice=null;
			}
			if(search==null || search.trim().equals("")) {
				search=null;
			}
			
			value.put("choice",choice);
			value.put("search", search);
			value.put("page", page);
			value.put("limit", limit);
			
			int listCount = service.food_count(value); // 총 리스트 수를 받아옴.
			System.out.println("listCount : " + listCount);
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
			
			str="admin/food_list";
		}
		return str;
	}
	
	@RequestMapping(value="foodInfo.ad", method= {RequestMethod.POST,RequestMethod.GET})
	String food_info(HttpServletRequest request,HttpServletResponse response) throws Exception{
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
			HashMap<String,Object> value = new HashMap<>();
			value.put("connect_num", connect_num);
			List<HashMap> total = service.food_info(value);
			request.setAttribute("total", total);
			request.setAttribute("page", page);
			request.setAttribute("search", request.getAttribute("search"));
			request.setAttribute("choice", request.getAttribute("choice"));
		
			str="admin/food_info";
		}
		return str;
	}
	@RequestMapping(value="foodAddForm.ad",  method= {RequestMethod.POST,RequestMethod.GET})
	String food_addForm(HttpServletRequest request,HttpServletResponse response) throws Exception{
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
	@RequestMapping(value="foodAddPro.ad",method= {RequestMethod.POST,RequestMethod.GET})
	String food_proForm(MultipartHttpServletRequest multi,HttpServletResponse response) throws Exception{
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
			String filePath = realPath + "resources\\images\\";
			File dir = new File(filePath);
			if (!dir.isDirectory()) {
				dir.mkdir();
			}

			List<MultipartFile> fileList = multi.getFiles("food_image");
			logger.info("file 갯수 :" + fileList.size());
			int fileSize = fileList.size();
			System.out.println("fileSize : " + fileSize);

			HashMap<String,Object> value = new HashMap<>();
			value.put("food_name", multi.getParameter("food_name"));
			value.put("food_category", multi.getParameter("food_category"));
			value.put("jijum_name", multi.getParameter("jijum_name"));
			
			for (MultipartFile mf : fileList) {

				String originFileName;
				logger.info("originalFilename : " + mf.getOriginalFilename());

				try {
					if (!(mf.getOriginalFilename().trim().equals("")) && mf.getOriginalFilename() != null) {
						originFileName = uploadFile(mf.getOriginalFilename(), mf.getBytes());
						mf.transferTo(new File(filePath + originFileName));

						value.put("food_image", originFileName);

						logger.debug("------------- file start -------------");
						logger.debug("content_type : " + mf.getContentType());
						logger.debug("name : " + mf.getName());
						logger.debug("filename : " + mf.getOriginalFilename());
						logger.debug("size : " + mf.getSize());
						logger.debug("-------------- file end --------------\n");

					}else {
						value.put("food_image", "logo.png");
					}

				} catch (IllegalStateException e) { // TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) { // TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			value.put("food_name",multi.getParameter("food_name"));
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
	
	private String uploadFile(String originalName, byte[] fileData) throws Exception {
		// uuid 생성(Universal Unique IDentifier, 범용 고유 식별자)
		String uploadPath = "C:\\Users\\Administrator\\Documents\\workspace-spring-tool-suite-4-4.5.1.RELEASE\\EmartFoodCourt\\src\\main\\webapp\\resources\\images";
		UUID uuid = UUID.randomUUID();
		// 랜덤생성+파일이름 저장
		String savedName = uuid.toString() + "_" + originalName;
		File target = new File(uploadPath, savedName);
		// 임시디렉토리에 저장된 업로드된 파일을 지정된 디렉토리로 복사
		// File(CopyUtils.copy(바이트배열,파일객체)
		FileCopyUtils.copy(fileData, target);
		return savedName;
	}
}
	
