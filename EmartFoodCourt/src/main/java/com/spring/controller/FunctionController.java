package com.spring.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.core.util.FileUtils;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mobile.device.Device;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.spring.service.FunctionService;

/**
 * Handles requests for the application home page.
 */

@Controller

public class FunctionController {

	private static final Logger logger = LoggerFactory.getLogger(FunctionController.class);

	@Inject
	FunctionService service;

	/*
	 * @ExceptionHandler(Exception.class) public String common(Exception
	 * e,HttpServletResponse response) throws Exception {
	 * response.setContentType("text/html;charset=UTF-8");
	 * logger.info(e.toString()); String str = null; PrintWriter out =
	 * response.getWriter(); out.println("<script>");
	 * out.println("alert('오류가 발생하였거나 잘못된 경로입니다!');");
	 * out.println("location.href='/EmartFoodCourt/'"); out.println("</script>");
	 * return str; }
	 */

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(/* Device device */) {
		/*
		 * if (device.isMobile()) { return "mobile/index"; } else { return "index"; }
		 */
		return "index";
	}

	// 음식 추천
	@RequestMapping(value = "/recom.fc", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView recom(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "recom_check") String recom_check,
			@RequestParam(value = "food_category", required = false) String food_category,
			@RequestParam(value = "jijum_name", required = false) String jijum_name,
			@RequestParam(value = "choice", required = false) String choice) throws Exception {

		ModelAndView mv = new ModelAndView();

		List<HashMap> jijum_menu = service.jijum_menu();
		List<HashMap> food_menu = service.food_menu();
		HashMap<String, String> recom = new HashMap<>();
		recom.put("food_category", food_category);
		recom.put("jijum_name", jijum_name);
		recom.put("choice", choice);
		List<HashMap> recom_value = service.recom_value(recom);

		mv.setViewName("function/recommand");
		mv.addObject("recom_check", recom_check);
		mv.addObject("jijum_menu", jijum_menu);
		mv.addObject("food_menu", food_menu);
		mv.addObject("totalList", recom_value);

		return mv;
	}

	// 메뉴 소개
	@RequestMapping(value = "/menuView.fc", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView menuView(@RequestParam(value = "food_category", required = false) String[] food_category_value,
			@RequestParam(value = "jijum_name", required = false) String jijum_name) {
		ModelAndView mv = new ModelAndView();

		int count = service.food_count();

		if (jijum_name == null) {
			jijum_name = "all";
		}
		System.out.println("count :" + count);

		if (food_category_value != null) {
			for (int i = 0; i < food_category_value.length; i++) {
				System.out.println("food_category선택값 : " + food_category_value[i]);
			}
		}
		List<HashMap> jijum_category = service.select_jijum_category();
		List<HashMap> food_category = service.select_food_category();

		HashMap<String, Object> jijum = new HashMap<>();
		jijum.put("count", count);
		jijum.put("food_category", food_category_value);
		jijum.put("jijum_name", jijum_name);

		List<HashMap> menu_view = service.menu_view(jijum);

		HashMap<String, Object> jf = new HashMap<>();
		jf.put("food_category", food_category_value);
		jf.put("jijum_name", jijum_name);
		List<HashMap> menuView = service.select_menuView(jf);
		mv.setViewName("function/menuView");
		mv.addObject("count", count);
		mv.addObject("menu_view", menu_view);
		if (!(menuView.isEmpty())) {
			mv.addObject("totalList", menuView);
		}
		mv.addObject("jijum_category", jijum_category);
		mv.addObject("food_category", food_category);

		return mv;
	}

	// 메뉴 상세보기
	@RequestMapping(value = "/menuView_detail.fc", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView menuView_detail(@RequestParam(value = "connect_num", required = true) String pr_connect_num,
			@RequestParam(value = "page", required = false) String pr_page,
			@RequestParam(value = "choice", required = false) String pr_choice,
			@RequestParam(value = "star", required = false) String pr_star) {

		int connect_num = Integer.parseInt(pr_connect_num);
		HashMap<String, Object> value = new HashMap<>();
		value.put("connect_num", connect_num);
		List<HashMap> total = service.menuViewDetail(value);
		// 후기게시판 부분
		int page = 1;
		int limit = 5; // 페이지에 보여줄 목록수
		int limitPage = 10; // 페이지 수
		int choice = 0;
		if (pr_page != null) {
			page = Integer.parseInt(pr_page);
		}
		if (pr_choice == null || pr_choice.equals("null") || pr_choice.trim().equals("")) {
			choice = 0;
		} else {
			choice = Integer.parseInt(pr_choice);
		}
		int star = 0;
		if (pr_star == null || pr_star.equals("null") || Integer.parseInt(pr_star) > 5) {
			star = 0;
		} else {
			star = Integer.parseInt(pr_star);
		}

		HashMap<String, Object> list = new HashMap<>();
		list.put("connect_num", connect_num);
		list.put("star", star);
		int listCount = service.getListCount(list);

		HashMap<String, Object> review = new HashMap<>();
		review.put("page", page);
		review.put("limit", limit);
		review.put("connect_num", connect_num);
		review.put("choice", choice);
		review.put("star", star);

		List<HashMap> reviewResult = service.review_select(review);

		// 리스트를 받아옴
		// 총 페이지 수
		int maxPage = (int) ((double) listCount / limit + 0.95);
		// 0.95를 더해서 올림처리
		// 현재 페이지에 보여줄 시작 페이지 수(1,11,21 등...)
		int startPage = (((int) ((double) page / limitPage + 0.9)) - 1) * limitPage + 1;
		System.out.println("startPage : " + startPage);
		// 현재 페이지에 보여줄 마지막 페이지 수(10,20,30 등....)
		int endPage = startPage + limitPage - 1;
		if (endPage > maxPage)
			endPage = maxPage;

		ModelAndView mv = new ModelAndView();
		mv.addObject("total", total);
		mv.addObject("endPage", endPage);
		mv.addObject("listCount", listCount);
		mv.addObject("maxPage", maxPage);
		mv.addObject("nowPage", page);
		mv.addObject("startPage", startPage);
		mv.addObject("connect_num", connect_num); // 페이지로 넘겨주는 연결 번호
		mv.addObject("choice", choice); // 페이지로 넘겨주는 선택 항목
		mv.addObject("star", star); // 평점순 조회할때 페이지로 넘겨주는 평점갯수
		if (!(reviewResult.isEmpty())) {
			mv.addObject("board", reviewResult);
		}
		mv.setViewName("function/menuView_detail");

		return mv;
	}

	// 후기 조회
	@RequestMapping(value = "/Rev_Board_detail.fc", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView Rev_Board_detail(@RequestParam(value = "rev_num", required = true) int rev_num,
			@RequestParam(value = "connect_num", required = true) int connect_num,
			@RequestParam(value = "page", required = true) int page) {
		ModelAndView mv = new ModelAndView();

		HashMap<Object, Object> rev = new HashMap<>();
		rev.put("rev_num", rev_num);

		List<HashMap> board_detail = service.board_detail(rev);
		int updateCount = service.updateCount(rev);

		mv.setViewName("function/rev_board_detail");
		mv.addObject("rev_board", board_detail);
		mv.addObject("nowPage", page);
		mv.addObject("connect_num", connect_num);
		mv.addObject("rev_num", rev_num);
		return mv;
	}

	// 음식 평점 등록
	@RequestMapping(value = "/avg_grade.fc", method = { RequestMethod.POST, RequestMethod.GET })
	public String avg_grade(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		String str = null;
		if (session.getAttribute("id") == null) {
			out.println("<script>");
			out.println("alert('로그인 하여주십시오!.');");
			out.println("location.href='/EmartFoodCourt/memberLogin.me';");
			out.println("</script>");
		} else {
			HashMap<Object, Object> avg = new HashMap<>();
			avg.put("connect_num", request.getParameter("connect_num"));
			avg.put("id", session.getAttribute("id"));
			avg.put("grade", request.getParameter("grade"));
			int grade_search = service.dupl_check(avg);
			if (grade_search == 1) {
				int modifyCount = service.avg_grade(avg);
				str = "redirect:/menuView_detail.fc?connect_num=" + request.getParameter("connect_num");
			} else {
				int insertCount = service.avg_grade_insert(avg);
				str = "redirect:/menuView_detail.fc?connect_num=" + request.getParameter("connect_num");
			}

		}
		return str;
	}

	// 음식 추천
	@RequestMapping(value = "count_rec.fc", method = { RequestMethod.POST, RequestMethod.GET })
	public String count_rec(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		String str = null;
		if (session.getAttribute("id") == null) {
			out.println("<script>");
			out.println("alert('로그인 하여주십시오!.');");
			out.println("location.href='/EmartFoodCourt/memberLogin.me';");
			out.println("</script>");
		} else {
			String id = (String) session.getAttribute("id");
			HashMap<String, Object> value = new HashMap<>();
			value.put("id", id);
			value.put("connect_num", request.getParameter("connect_num"));

			int dupl_check = service.recom_dupl_chk(value);
			System.out.println("dupl_check : " + dupl_check);
			if (dupl_check == 1) {
				int recom_del = service.recom_del(value);
				str = "redirect:/menuView_detail.fc?connect_num=" + request.getParameter("connect_num");
			} else {
				int recom_add = service.recom_add(value);
				str = "redirect:/menuView_detail.fc?connect_num=" + request.getParameter("connect_num");
			}
		}
		return str;
	}

	// 후기게시판 등록 화면
	@RequestMapping(value = "Rev_Board_writeForm.fc", method = { RequestMethod.GET, RequestMethod.POST })
	public String rev_board_writeForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String str = null;
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();

		if (session.getAttribute("id") != null) {
			request.setAttribute("food_name", request.getParameter("food_name"));
			request.setAttribute("jjum_name", request.getParameter("jijum_name"));
			request.setAttribute("connect_num", request.getParameter("connect_num"));
			str = "function/rev_board_write";
		} else {
			out.println("<script>");
			out.println("alert('로그인 해주십시오.');");
			out.println("location.href='/EmartFoodCourt/memberLogin.me';");
			out.println("</script>");
		}

		return str;
	}

	// 후기게시판 등록 기능
	@RequestMapping(value = "Rev_Board_writePro.fc", method = { RequestMethod.GET, RequestMethod.POST })
	public String rev_board_writePro(MultipartHttpServletRequest multi, HttpServletResponse response) throws Exception {
		String str = null;
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = multi.getSession();
		PrintWriter out = response.getWriter();

		if (session.getAttribute("id") != null) {

			int connect_num = Integer.parseInt(multi.getParameter("connect_num"));
			String id = multi.getParameter("id");
			int rev_satisfaction = Integer.parseInt(multi.getParameter("rev_satisfaction"));
			String rev_subject = multi.getParameter("rev_subject");
			String rev_content = multi.getParameter("rev_content").trim().equals("") ? "제목없음"
					: multi.getParameter("rev_content");

			String realPath = multi.getSession().getServletContext().getRealPath("/");
			String filePath = realPath + "resources\\images\\";

			HashMap<String, Object> value = new HashMap<>();
			value.put("connect_num", connect_num);
			value.put("id", id);
			value.put("rev_satisfaction", rev_satisfaction);
			value.put("rev_subject", rev_subject);
			value.put("rev_content", rev_content);

			int rev_board_write = service.rev_board_write(value);

			/*
			 * File dir = new File(filePath); if (!dir.isDirectory()) { dir.mkdir(); }
			 * 
			 * List<MultipartFile> fileList = multi.getFiles("img_name");
			 * logger.info("file 갯수 :" + fileList.size()); int fileSize = fileList.size();
			 * System.out.println("fileSize : " + fileSize);
			 * 
			 * 
			 * System.out.println("pk : " + value.get("pk")); for (MultipartFile mf :
			 * fileList) {
			 * 
			 * String originFileName; logger.info("originalFilename : " +
			 * mf.getOriginalFilename());
			 * 
			 * try { if (!(mf.getOriginalFilename().trim().equals("")) &&
			 * mf.getOriginalFilename() != null) { originFileName =
			 * uploadFile(mf.getOriginalFilename(), mf.getBytes()); mf.transferTo(new
			 * File(filePath + originFileName));
			 * 
			 * value.put("img_name", originFileName); int rev_board_image =
			 * service.rev_board_img_write(value); logger.info("rev_board_write:" +
			 * rev_board_write);
			 * 
			 * logger.debug("------------- file start -------------");
			 * logger.debug("content_type : " + mf.getContentType()); logger.debug("name : "
			 * + mf.getName()); logger.debug("filename : " + mf.getOriginalFilename());
			 * logger.debug("size : " + mf.getSize());
			 * logger.debug("-------------- file end --------------\n");
			 * 
			 * }
			 * 
			 * } catch (IllegalStateException e) { // TODO Auto-generated catch block
			 * e.printStackTrace(); } catch (IOException e) { // TODO Auto-generated catch
			 * block e.printStackTrace(); } }
			 */

			if (rev_board_write == 0) {

				out.println("<script>");
				out.println("alert('등록실패');");
				out.println("history.back();");
				out.println("</script>");
			} else {

				str = "redirect:/menuView_detail.fc?connect_num=" + multi.getParameter("connect_num");
			}
		} else {
			out.println("<script>");
			out.println("alert('로그인 해주십시오.');");
			out.println("location.href='/EmartFoodCourt/memberLogin.me';");
			out.println("</script>");
		}
		return str;
	}

	// 후기 게시판 수정 화면
	@RequestMapping(value = "Rev_Board_modifyForm.fc", method = { RequestMethod.POST, RequestMethod.GET })
	public String rev_board_modifyForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String str = null;
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();

		if (session.getAttribute("id") == null) {
			out.println("<script>");
			out.println("alert('로그인 하여주십시오!.');");
			out.println("location.href='/EmartFoodCourt/memberLogin.me';");
			out.println("</script>");
		} else {
			int id_grade = (int) session.getAttribute("id_grade");

			HashMap<Object, Object> value = new HashMap<>();
			value.put("id", (String) session.getAttribute("id"));
			value.put("rev_num", request.getParameter("rev_num"));
			value.put("id_grade", id_grade);
			List<HashMap> rev_board = service.rev_board_detail(value);
			System.out.println("rev_board : " + rev_board);
			if (!(rev_board.isEmpty())) {
				session.setAttribute("rev_board", rev_board);
				request.setAttribute("rev_num", request.getParameter("rev_num"));
				request.setAttribute("connect_num", request.getParameter("connect_num"));
				request.setAttribute("page", request.getParameter("page"));
				str = "function/rev_board_modify";
			} else {
				out.println("<script>");
				out.println("alert('잘못된 접근경로입니다.');");
				out.println("location.href='/EmartFoodCourt/memberLogin.me';");
				out.println("</script>");
			}

		}
		return str;
	}

	// 후기게시판 수정 기능
	@RequestMapping(value = "Rev_Board_modifyPro.fc", method = { RequestMethod.POST, RequestMethod.GET })
	public String rev_board_modifyPro(MultipartHttpServletRequest multi, HttpServletResponse response)
			throws Exception {

		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = multi.getSession();
		PrintWriter out = response.getWriter();

		String str = null;
		System.out.println("session id : " + session.getAttribute("id"));
		if (session.getAttribute("id") != null) {
			int rev_num = Integer.parseInt(multi.getParameter("rev_num"));
			int connect_num = Integer.parseInt(multi.getParameter("connect_num"));
			String id = multi.getParameter("id");
			int rev_satisfaction = Integer.parseInt(multi.getParameter("rev_satisfaction"));
			String rev_subject = multi.getParameter("rev_subject");
			String rev_content = multi.getParameter("rev_content").trim().equals("") ? "제목없음"
					: multi.getParameter("rev_content");

			String realPath = multi.getSession().getServletContext().getRealPath("/");
			String filePath = realPath + "resources\\upload\\";

			HashMap<String, Object> value = new HashMap<>();
			value.put("rev_num", rev_num);
			value.put("connect_num", connect_num);
			value.put("id", id);
			value.put("rev_satisfaction", rev_satisfaction);
			value.put("rev_subject", rev_subject);
			value.put("rev_content", rev_content);

			File dir = new File(filePath);
			if (!dir.isDirectory()) {
				dir.mkdir();
			}

			Iterator<String> files = multi.getFileNames();
			while (files.hasNext()) {
				String uploadFile = files.next();
				MultipartFile mFile = multi.getFile(uploadFile);
				String fileName_original = mFile.getOriginalFilename();

				String file_save_path = realPath + "resources\\images\\";

				System.out.println("file_save_path : " + file_save_path);

				try {
					if (!(mFile.getOriginalFilename().trim().equals(""))) {
						fileName_original = uploadFile(fileName_original, mFile.getBytes());
						mFile.transferTo(new File(file_save_path + fileName_original));
						value.put("rev_image", fileName_original);
					}
					logger.debug("------------- file start -------------");
					logger.debug("content_type : " + mFile.getContentType());
					logger.debug("name : " + mFile.getName());
					logger.debug("filename : " + mFile.getOriginalFilename());
					logger.debug("size : " + mFile.getSize());
					logger.debug("-------------- file end --------------\n");

				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			int rev_board_modify = service.rev_board_modify(value);
			System.out.println("rev_board_modify : " + rev_board_modify);
			if (rev_board_modify == 0) {

				out.println("<script>");
				out.println("alert('수정실패');");
				out.println("history.back();");
				out.println("</script>");
			} else {
				int page = Integer.parseInt(multi.getParameter("page"));

				str = "redirect:/menuView_detail.fc?connect_num=" + multi.getParameter("connect_num") + "&page=" + page;
			}
		} else {
			out.println("<script>");
			out.println("alert('로그인 해주십시오.');");
			out.println("location.href='/EmartFoodCourt/memberLogin.me';");
			out.println("</script>");
		}

		return str;
	}

	// 후기게시판 삭제 기능
	@RequestMapping(value = "Rev_Board_delete.fc", method = { RequestMethod.POST, RequestMethod.GET })
	public String rev_board_delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String str = null;
		HttpSession session = request.getSession();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		if (session.getAttribute("id") != null) {
			String id_grade = String.valueOf(session.getAttribute("id_grade"));
			logger.info("id_grade : " + id_grade);
			HashMap<String, Object> value = new HashMap<>();
			value.put("id", session.getAttribute("id"));
			value.put("rev_num", request.getParameter("rev_num"));
			if (id_grade.equals("1")) { // 일반회원
				
				List<HashMap> rev_board_check = service.rev_board_check(value);
				if (!(rev_board_check.isEmpty())) {
					int delete_result = service.rev_board_delete(value);
					System.out.println("delete_result : " + delete_result);
					if (delete_result > 0) {
						out.println("<script>");
						out.println("alert('삭제되었습니다.');");
						out.println("location.href='/EmartFoodCourt/menuView_detail.fc?connect_num="
								+ request.getParameter("connect_num") + "';");
						out.println("</script>");
					} else {
						out.println("<script>");
						out.println("alert('삭제하는데 실패하였습니다.');");
						out.println("location.href='history.back();"
								+ request.getParameter("connect_num") + "';");
						out.println("</script>");
					}
				} else {
					out.println("<script>");
					out.println("location.href='/EmartFoodCourt/';");
					out.println("alert('본인 계정이 아닙니다.');");
					out.println("</script>");
				}

			} else if (id_grade.equals("2")) { // 관리자
				int delete_result = service.rev_board_delete(value);
				System.out.println("delete_result : " + delete_result);
				if (delete_result > 0) {
					out.println("<script>");
					out.println("alert('삭제되었습니다.');");
					out.println("location.href='/EmartFoodCourt/menuView_detail.fc?connect_num="
							+ request.getParameter("connect_num") + "';");
					out.println("</script>");
				} else {
					out.println("<script>");
					out.println("alert('삭제하는데 실패하였습니다.');");
					out.println("location.href='history.back();"
							+ request.getParameter("connect_num") + "';");
					out.println("</script>");
				}
			}
		} else {
			out.println("<script>");
			out.println("location.href='/EmartFoodCourt/';");
			out.println("alert('로그인 하여주십시오.');");
			out.println("</script>");
		}
		return str;
	}

	@PostMapping(value = "uploadSummernoteImageFile", produces = "application/json")

	@ResponseBody
	public JSONObject uploadSummernoteImageFile(MultipartHttpServletRequest multi,
			@RequestParam("file") MultipartFile multipartFile) throws Exception {

		JSONObject jsonObject = new JSONObject();

		String realPath = multi.getSession().getServletContext().getRealPath("/");
		String filePath = realPath + "resources\\images\\";
		logger.info("filePath : " + filePath);
		String originalFileName = multipartFile.getOriginalFilename(); // 오리지날 파일명
		String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
		File dir = new File(filePath);
		if (!dir.isDirectory()) {
			dir.mkdir();
		}

		try {
			if (!(originalFileName.trim().equals("")) && multipartFile.getOriginalFilename() != null) {
				originalFileName = uploadFile(originalFileName, multipartFile.getBytes());
				multipartFile.transferTo(new File(filePath + originalFileName));
				jsonObject.put("url", "resources\\images\\" + originalFileName);
				jsonObject.put("responseCode", "success");
			}

		} catch (IOException e) {
			dir.delete(); // 저장된 파일 삭제
			jsonObject.put("responseCode", "error");
			e.printStackTrace();
		}

		return jsonObject;
	}

	// 파일명 랜덤생성 메서드

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
