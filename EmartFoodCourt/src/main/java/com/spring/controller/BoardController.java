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

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.spring.service.BoardService;

@Controller

public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(FunctionController.class);

	@Inject
	BoardService service;

	// 자유 게시판 목록조회
	@RequestMapping(value = "/boardList.bd", method = { RequestMethod.POST, RequestMethod.GET })
	public String boardList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String str = null;

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		String board_category = null;
		
		if (request.getParameter("board_category") != null
				&& !(request.getParameter("board_category").trim().equals(""))) {
			board_category = request.getParameter("board_category");
		} else {
			board_category = "all";
		}

		String board_orderby = null;

		if (request.getParameter("board_orderby") != null
				&& !(request.getParameter("board_orderby").trim().equals(""))) {
			board_orderby = request.getParameter("board_orderby");
		} else {
			board_orderby = "popularity";
		}
		String category =request.getParameter("category");
		if(category==null || category.trim().equals("")) {
			category = null;
		}
		String search = request.getParameter("search");
		if(search==null || search.trim().equals("")) {
			search = null;
		}
		int limit = 10;
		int page;
		if (request.getParameter("page") == null) {
			page = 1;
		} else {
			page = Integer.parseInt(request.getParameter("page"));
		}
		

		
		
		HashMap<String, Object> value = new HashMap<>();
		value.put("board_category", board_category);
		value.put("board_orderby", board_orderby);
		value.put("category", category);
		value.put("search", search);
		value.put("page", page);
		value.put("limit", limit);

		int board_count = service.free_board_count(value);
		System.out.println("board_count : " + board_count);
		if (board_count % limit == 0) {
			board_count = board_count / limit;
		} else {
			board_count = (board_count / limit) + 1;
		}

		List<HashMap> free_board = service.select_free_board(value);

		

		request.setAttribute("board_category", board_category);

		if (category != null) {
			request.setAttribute("category", category);
		}
		
		
		if (search != null) {
			request.setAttribute("search", search);
		}
		request.setAttribute("board_orderby", board_orderby);
		if (free_board != null) {
			request.setAttribute("free_board", free_board);

		}
		request.setAttribute("mypage", page);
		request.setAttribute("board_count", board_count);
		str = "board/free_board_list";
		return str;
	}

	// 자유 게시판 글등록 폼
	@RequestMapping(value = "/boardList_writeForm.bd", method = { RequestMethod.POST, RequestMethod.GET })
	public String board_write_Form(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String str = null;
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();

		if (((String) session.getAttribute("id") == null)) {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인 해주십시오.')");
			out.println("location.href='/EmartFoodCourt/memberLogin.me';");
			out.println("</script>");
		} else {

			str = "board/free_board_write";
		}
		return str;
	}

	// 자유 게시판 글등록 기능
	@RequestMapping(value = "/boardList_writePro.bd", method = { RequestMethod.POST, RequestMethod.GET })
	public String board_write_Pro(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String str = null;
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		if (((String) session.getAttribute("id") == null)) {

			out.println("<script>");
			out.println("alert('로그인 해주십시오.')");
			out.println("location.href='/EmartFoodCourt/memberLogin.me';");
			out.println("</script>");
		} else {
			String id = request.getParameter("id");
			String board_category = request.getParameter("board_category");
			String board_subject = request.getParameter("board_subject");
			String board_content = request.getParameter("board_content");
			System.out.println("board_content : " + board_content);
			HashMap<String, Object> value = new HashMap<>();
			value.put("id", id);
			value.put("board_category", board_category);
			value.put("board_subject", board_subject);
			value.put("board_content", board_content);
			int free_board_write = service.free_board_write(value);
			System.out.println("free_board_write: " + free_board_write);
			if (free_board_write > 0) {
				out.println("<script>");
				out.println("alert('게시판 글이 등록되었습니다.');");
				out.println("location.href='/EmartFoodCourt/boardList.bd';");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('게시판 등록이 실패하였습니다.');");
				out.println("location.href='history.back()';");
				out.println("</script>");
			}
		}
		return str;
	}

	// 자유 게시판 상세조회
	@RequestMapping(value = "boardList_detail.bd", method = { RequestMethod.GET, RequestMethod.POST })
	public String board_detail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String str = null;
		HttpSession session = request.getSession();
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		PrintWriter out = response.getWriter();
		HashMap<String, Object> value = new HashMap<>();
		value.put("board_num", board_num);
		List<HashMap> free_board_detail = service.board_detail(value);
		List<HashMap> free_board_reply = service.board_reply(value);
		if (free_board_reply != null) {
			request.setAttribute("free_board_reply", free_board_reply);
			/* request.setAttribute("free_board_replyCount", free_board_reply.size()); */
		}
		if (free_board_detail != null) {
			int free_board_read = service.board_read(value);
			request.setAttribute("free_board", free_board_detail);
			str = "board/free_board_info";

		} else {

			out.println("<script>");
			out.println("alert('잘못된 경로입니다!')");
			out.println("location.href='/EmartFoodCourt/boardList.bd';");
			out.println("</script>");
		}
		return str;
	}

	// 자유 게시판 수정화면
	@RequestMapping(value = "boardList_modForm.bd", method = { RequestMethod.GET, RequestMethod.POST })
	public String board_modForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String str = null;
		HttpSession session = request.getSession();
		String session_id = (String) session.getAttribute("id");
		String id = (String) request.getParameter("id");

		int id_grade = (int) session.getAttribute("id_grade");
		int board_num = Integer.parseInt(request.getParameter("board_num"));

		PrintWriter out = response.getWriter();
		if (((String) session.getAttribute("id") == null)) {

			out.println("<script>");
			out.println("alert('로그인 해주십시오.')");
			out.println("location.href='/EmartFoodCourt/memberLogin.me';");
			out.println("</script>");
		} else {

			if (id_grade == 2) {

				HashMap<String, Object> value = new HashMap<>();
				value.put("board_num", board_num);
				List<HashMap> free_board = service.board_detail(value);
				request.setAttribute("free_board", free_board);
				str = "board/free_board_modify";
			} else {
				if (session_id.equals(id)) {

					HashMap<String, Object> value = new HashMap<>();
					value.put("board_num", board_num);
					List<HashMap> free_board = service.board_detail(value);
					request.setAttribute("free_board", free_board);
					str = "board/free_board_modify";
				} else {

					out.println("<script>");
					out.println("alert('잘못된 경로입니다.')");
					out.println("location.href='/EmartFoodCourt/boardList.bd';");
					out.println("</script>");
				}
			}
		}
		return str;

	}

	// 자유 게시판 수정 기능
	@RequestMapping(value = "boardList_modPro.bd", method = { RequestMethod.POST, RequestMethod.GET })
	public String boardList_modPro(HttpServletRequest request, HttpServletResponse response) throws Exception {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String str = null;
		HttpSession session = request.getSession();

		PrintWriter out = response.getWriter();
		String session_id = (String) session.getAttribute("id");
		String id = request.getParameter("id");
		System.out.println("id : " + id);
		int id_grade = (int) session.getAttribute("id_grade");
		int board_num = Integer.parseInt(request.getParameter("board_num"));

		String board_subject = request.getParameter("board_subject");

		String board_content = request.getParameter("board_content");

		String board_category = request.getParameter("board_category");
		HashMap<String, Object> value = new HashMap<>();
		value.put("id", id);
		value.put("board_num", board_num);
		value.put("board_subject", board_subject);
		value.put("board_content", board_content);
		value.put("board_category", board_category);

		if (((String) session.getAttribute("id") == null)) {
			out.println("<script>");
			out.println("alert('로그인 해주십시오.')");
			out.println("location.href='/EmartFoodCourt/memberLogin.me';");
			out.println("</script>");
		} else {

			if (id_grade == 2) {

				int board_modify = service.board_modify(value);

				if (board_modify > 0) {

					out.println("<script>");
					out.println("alert('수정되었습니다.')");
					out.println("location.href='/EmartFoodCourt/boardList_detail.bd?board_num=" + board_num + "'");
					out.println("</script>");
				} else {

					out.println("<script>");
					out.println("alert('수정이 실패하였습니다.')");
					out.println("location.href='/EmartFoodCourt/boardList_detail.bd?board_num=" + board_num + "'");
					out.println("</script>");
				}
			} else {
				if (session_id.equals(id)) {
					int board_modify = service.board_modify(value);

					if (board_modify > 0) {

						out.println("<script>");
						out.println("alert('수정되었습니다.')");
						out.println("location.href='/EmartFoodCourt/boardList_detail.bd?board_num=" + board_num + "'");
						out.println("</script>");
					} else {

						out.println("<script>");
						out.println("alert('수정이 실패하였습니다.')");
						out.println("location.href='/EmartFoodCourt/boardList_detail.bd?board_num=" + board_num + "'");
						out.println("</script>");
					}
				} else {

					out.println("<script>");
					out.println("alert('잘못된 경로입니다.')");
					out.println("location.href='/EmartFoodCourt/boardList.bd';");
					out.println("</script>");
				}
			}
		}

		return str;
	}

	// 자유 게시판 삭제 기능
	@RequestMapping(value = "boardList_delete.bd", method = { RequestMethod.POST, RequestMethod.GET })
	String board_delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String str = null;
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		HashMap<String, Object> value = new HashMap<>();
		String session_id = (String) session.getAttribute("id");
		String id = (String) request.getParameter("id");
		System.out.println("id : " + id);
		int id_grade = (int) session.getAttribute("id_grade");
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		value.put("board_num", board_num);
		if (((String) session.getAttribute("id") == null)) {

			out.println("<script>");
			out.println("alert('로그인 해주십시오.')");
			out.println("location.href='/EmartFoodCourt/memberLogin.me';");
			out.println("</script>");
		} else {

			if (id_grade == 2) {

				int delete_result = service.board_delete(value);
				out.println("<script>");
				out.println("alert('게시글이 삭제되었습니다.')");
				out.println("location.href='/EmartFoodCourt/boardList.bd';");
				out.println("</script>");
			} else {
				if (session_id.equals(id)) {
					int delete_result = service.board_delete(value);

					out.println("<script>");
					out.println("alert('게시글이 삭제되었습니다.')");
					out.println("location.href='/EmartFoodCourt/boardList.bd';");
					out.println("</script>");
				} else {

					out.println("<script>");
					out.println("alert('잘못된 접근방식입니다.')");
					out.println("location.href='/EmartFoodCourt/boardList_detail.bd?board_num=" + board_num + "'");
					out.println("</script>");
				}
			}
		}

		return str;
	}

	// 자유 게시판 up 기능
	@RequestMapping(value = "boardList_vote_up.bd", method = { RequestMethod.POST, RequestMethod.GET })
	String board_vote_up(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String str = null;
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String session_id = (String) session.getAttribute("id");
		String id = request.getParameter("id");
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		System.out.println("id : " + id + "board_num : " + board_num);
		HashMap<String, Object> value = new HashMap<>();
		value.put("board_num", board_num);
		value.put("id", session_id);
		if ((String) session.getAttribute("id") == null) {

			out.println("<script>");
			out.println("alert('로그인 해주십시오.')");
			out.println("location.href='/EmartFoodCourt/memberLogin.me';");
			out.println("</script>");
		} else {

			if (session_id.equals(id)) {

				out.println("<script>");
				out.println("alert('자신의 게시판은 평가할수없습니다.')");
				out.println("location.href='/EmartFoodCourt/boardList_detail.bd?board_num=" + board_num + "'");
				out.println("</script>");
			} else {
				// 본인이 반대를 눌럿는지 확인

				List<HashMap> board_vote = service.board_vote_select(value);
				if (board_vote == null) {
					int board_vote_up = service.board_vote_up(value);
				} else {
					if ((int) board_vote.get(0).get("vote_up") == 1) {
						int board_vote_delete = service.board_vote_delete(value);
					} else if ((int) board_vote.get(0).get("vote_down") == 1) {
						int board_vote_plus = service.board_vote_plus(value);
					}
				}
				str = "redirect:/boardList_detail.bd?board_num=" + board_num;
			}
		}

		return str;
	}

	// 자유 게시판 down 기능
	@RequestMapping(value = "boardList_vote_down.bd", method = { RequestMethod.POST, RequestMethod.GET })
	String board_vote_down(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String str = null;
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String session_id = (String) session.getAttribute("id");
		String id = request.getParameter("id");
		int board_num = Integer.parseInt(request.getParameter("board_num"));
		HashMap<String, Object> value = new HashMap<>();
		value.put("board_num", board_num);
		value.put("id", session_id);
		if ((String) session.getAttribute("id") == null) {

			out.println("<script>");
			out.println("alert('로그인 해주십시오.')");
			out.println("location.href='/EmartFoodCourt/memberLogin.me';");
			out.println("</script>");
		} else {

			if (session_id.equals(id)) {

				out.println("<script>");
				out.println("alert('자신의 게시판은 평가할수없습니다.')");
				out.println("location.href='/EmartFoodCourt/boardList_detail.bd?board_num=" + board_num + "'");
				out.println("</script>");
			} else {
				// 본인이 반대를 눌럿는지 확인

				List<HashMap> board_vote = service.board_vote_select(value);
				if (board_vote == null) {
					int board_vote_down = service.board_vote_down(value);
				} else {
					if ((int) board_vote.get(0).get("vote_down") == 1) {
						int board_vote_delete = service.board_vote_delete(value);
					} else if ((int) board_vote.get(0).get("vote_up") == 1) {
						int board_vote_minus = service.board_vote_minus(value);
					}
				}

				str = "redirect:/boardList_detail.bd?board_num=" + board_num;
			}
		}

		return str;
	}

	// 자유 게시판 댓글 등록
	@RequestMapping(value = "boardList_reply_write.bd", method = { RequestMethod.POST, RequestMethod.GET })
	String board_reply_write(MultipartHttpServletRequest multi, HttpServletResponse response) throws Exception {
		String str = null;
		multi.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = multi.getSession();
		PrintWriter out = response.getWriter();
		String realPath = multi.getSession().getServletContext().getRealPath("/");
		String filePath = realPath + "resources\\images\\";
		if (((String) session.getAttribute("id") == null)) {

			out.println("<script>");
			out.println("alert('로그인 해주십시오.')");
			out.println("location.href='/EmartFoodCourt/memberLogin.me';");
			out.println("</script>");
		} else {

			String id = multi.getParameter("id");
			int board_num = Integer.parseInt(multi.getParameter("board_num"));
			String reply_content = multi.getParameter("reply_content");

			File dir = new File(filePath);
			if (!dir.isDirectory()) {
				dir.mkdir();
			}

			List<MultipartFile> fileList = multi.getFiles("reply_file");
			logger.info("file 갯수 :" + fileList.size());
			int fileSize = fileList.size();
			System.out.println("fileSize : " + fileSize);

			HashMap<String, Object> value = new HashMap<>();

			String reply_re_id = null;
			int reply_re_ref = 0;
			int reply_re_seq = 0;

			if (multi.getParameter("reply_re_id") != null) {
				reply_re_id = multi.getParameter("reply_re_id");
			}
			if (multi.getParameter("reply_re_ref") != null) {
				reply_re_ref = Integer.parseInt(multi.getParameter("reply_re_ref"));
			}
			if (multi.getParameter("reply_re_seq") != null) {
				reply_re_seq = Integer.parseInt(multi.getParameter("reply_re_seq"));
			}
			int id_grade = (int) session.getAttribute("id_grade");
			value.put("id", id);
			value.put("board_num", board_num);
			value.put("reply_content", reply_content);
			value.put("reply_re_id", reply_re_id);
			value.put("reply_re_ref", reply_re_ref);
			value.put("reply_re_seq", reply_re_seq);
			value.put("reply_re_lev", id_grade);

			for (MultipartFile mf : fileList) {

				String originFileName;
				logger.info("originalFilename : " + mf.getOriginalFilename());

				try {
					if (!(mf.getOriginalFilename().trim().equals("")) && mf.getOriginalFilename() != null) {
						originFileName = uploadFile(mf.getOriginalFilename(), mf.getBytes());
						mf.transferTo(new File(filePath + originFileName));

						value.put("reply_file", originFileName);

						logger.debug("------------- file start -------------");
						logger.debug("content_type : " + mf.getContentType());
						logger.debug("name : " + mf.getName());
						logger.debug("filename : " + mf.getOriginalFilename());
						logger.debug("size : " + mf.getSize());
						logger.debug("-------------- file end --------------\n");

					}

				} catch (IllegalStateException e) { // TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) { // TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (reply_re_id == null) {
				int reply_max_pk = service.board_reply_max_pk();
				System.out.println("reply_max_pk : " + reply_max_pk);
				value.put("reply_re_ref", reply_max_pk + 1);
				int free_board_reply_write = service.board_reply_write(value);
				if (free_board_reply_write > 0) {
					JSONObject obj = new JSONObject();
					try {
						obj.put("result", "1");
					} catch (Exception e) { // TODO Auto-generated catch block
						e.printStackTrace();
					}
					response.setContentType("application/x-json; charset=UTF-8");
					response.getWriter().print(obj);
				}
			} else {
				int free_board_reply_update = service.board_reply_update(value);
				reply_re_seq++;
				value.put("reply_re_seq", reply_re_seq);
				int free_board_reply_write = service.board_reply_write(value);
				if (free_board_reply_write > 0) {
					JSONObject obj = new JSONObject();
					try {
						obj.put("result", "1");
					} catch (Exception e) { // TODO Auto-generated catch block
						e.printStackTrace();
					}
					response.setContentType("application/x-json; charset=UTF-8");
					response.getWriter().print(obj);
				}
			}

		}
		return str;
	}

	// 자유 게시판 댓글 수정
	@RequestMapping(value = "boardList_reply_modify.bd", method = { RequestMethod.POST, RequestMethod.GET })
	String board_reply_modify(MultipartHttpServletRequest multi, HttpServletResponse response) throws Exception {
		String str = null;
		multi.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = multi.getSession();
		PrintWriter out = response.getWriter();
		String realPath = multi.getSession().getServletContext().getRealPath("/");
		String filePath = realPath + "resources\\images\\";

		boolean id_check = id_check2(multi.getParameter("id"), (String) session.getAttribute("id"),
				(int) session.getAttribute("id_grade"));

		if (id_check != false) {

			int reply_num = Integer.parseInt(multi.getParameter("reply_num"));
			String reply_content = multi.getParameter("reply_content");

			File dir = new File(filePath);
			if (!dir.isDirectory()) {
				dir.mkdir();
			}

			List<MultipartFile> fileList = multi.getFiles("reply_file");
			logger.info("file 갯수 :" + fileList.size());
			int fileSize = fileList.size();
			System.out.println("fileSize : " + fileSize);

			HashMap<String, Object> value = new HashMap<>();

			value.put("reply_num", reply_num);
			value.put("reply_content", reply_content);

			for (MultipartFile mf : fileList) {

				String originFileName;
				logger.info("originalFilename : " + mf.getOriginalFilename());

				try {
					if (!(mf.getOriginalFilename().trim().equals("")) && mf.getOriginalFilename() != null) {
						originFileName = uploadFile(mf.getOriginalFilename(), mf.getBytes());
						mf.transferTo(new File(filePath + originFileName));
						value.put("reply_file", originFileName);

						logger.debug("------------- file start -------------");
						logger.debug("content_type : " + mf.getContentType());
						logger.debug("name : " + mf.getName());
						logger.debug("filename : " + mf.getOriginalFilename());
						logger.debug("size : " + mf.getSize());
						logger.debug("-------------- file end --------------\n");

					}

				} catch (IllegalStateException e) { // TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) { // TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			int free_board_reply_modify = service.board_reply_modify(value);
			if (free_board_reply_modify > 0) {
				JSONObject obj = new JSONObject();
				try {
					obj.put("result", "1");
				} catch (Exception e) { // TODO Auto-generated catch block
					e.printStackTrace();
				}
				response.setContentType("application/x-json; charset=UTF-8");
				response.getWriter().print(obj);
			} else {
				JSONObject obj = new JSONObject();
				try {
					obj.put("result", "2");
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				response.setContentType("application/x-json; charset=UTF-8");
				response.getWriter().print(obj);
			}
		} else {
			out.println("<script>");
			out.println("alert('잘못된 접근 방식입니다.");
			out.println("location.href='/EmartFoodCourt/memberLogin.me';");
			out.println("</script>");
		}
		return str;
	}

	// 자유 게시판 댓글 삭제
	@RequestMapping(value = "boardList_reply_delete.bd", method = { RequestMethod.GET, RequestMethod.POST })
	String board_reply_delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String str = null;

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();

		String session_id = (String) session.getAttribute("id");
		String id = (String) request.getParameter("id");

		int id_grade = (int) session.getAttribute("id_grade");
		int reply_num = Integer.parseInt(request.getParameter("reply_num"));
		HashMap<String, Object> value = new HashMap<>();
		value.put("reply_num", reply_num);
		if (((String) session.getAttribute("id") == null)) {

			out.println("<script>");
			out.println("alert('로그인 해주십시오.')");
			out.println("location.href='/EmartFoodCourt/memberLogin.me';");
			out.println("</script>");
		} else {

			if (id_grade == 2) {

				int board_reply_delete = service.board_reply_delete(value);

				if (board_reply_delete > 0) {

					out.println("<script>");
					out.println("alert('게시글이 삭제되었습니다.')");
					out.println("location.href='/EmartFoodCourt/boardList.bd';");
					out.println("</script>");
				} else {

					out.println("<script>");
					out.println("alert('게시글을 삭제하는데 실패하였습니다.')");
					out.println("location.href='/EmartFoodCourt/boardList_detail.bd';");
					out.println("</script>");
				}
			} else {
				if (session_id.equals(id)) {
					int board_reply_delete = service.board_reply_delete(value);
					if (board_reply_delete > 0) {

						out.println("<script>");
						out.println("alert('게시글이 삭제되었습니다.')");
						out.println("location.href='/EmartFoodCourt/boardList.bd';");
						out.println("</script>");
					} else {

						out.println("<script>");
						out.println("alert('게시글을 삭제하는데 실패하였습니다.')");
						out.println("location.href='/EmartFoodCourt/boardList_detail.bd';");
						out.println("</script>");
					}
				} else {

					out.println("<script>");
					out.println("alert('잘못된 경로입니다.')");
					out.println("location.href='/EmartFoodCourt/boardList.bd';");
					out.println("</script>");
				}
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

	private boolean id_check1(String id) {
		boolean val = false;
		if (id != null) {
			val = true;
		}
		return val;
	}

	private boolean id_check2(String id, String session_id, int id_grade) {
		boolean val = false;

		if (id_grade == 2) {
			val = true;
		} else {
			if (id.equals(session_id)) {
				val = true;
			}
		}
		return val;
	}

}
