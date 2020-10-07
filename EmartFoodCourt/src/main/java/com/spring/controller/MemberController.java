package com.spring.controller;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.UUID;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.service.MemberService;

@Controller

public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Inject
	MemberService service;

	// 로그인
	@RequestMapping(value = "memberLogin.me", method = { RequestMethod.POST, RequestMethod.GET })
	ModelAndView loginForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("member/loginForm");
		return mv;
	}

	// 로그인 확인
	@RequestMapping(value = "memberLoginAction.me", method = { RequestMethod.POST, RequestMethod.GET })
	void Login(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "id", required = true) String id,
			@RequestParam(value = "pass", required = true) String pass) throws Exception {
		response.setContentType("text/html; charset=UTF-8");

		HashMap<Object, Object> member = new HashMap<>();
		member.put("id", id);
		member.put("pass", pass);
		int login_chk = service.login_chk(member);
		System.out.println("로그인 확인 유무" + login_chk);

		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		if (login_chk == 0) { // 접속 실패
			out.println("<script>");
			out.println("alert('로그인 실패');");
			out.println("location.href='./memberLogin.me';");
			out.println("</script>");
		} else { // 접속 성공
			int dupl_chk = service.dupl_chk(member);
			if (dupl_chk == 0) { // 중복 없음 정상접속
				int user_add = service.user_add(member);

				List<HashMap> member_value = service.getMember(member);
				List<HashMap> user_value = service.getUser(member);
				HashMap<String, Object> hash = member_value.get(0);
				HashMap<String, Object> user = user_value.get(0);

				session.setAttribute("id", id);

				session.setAttribute("user_num", user.get("user_num"));
				session.setAttribute("id_grade", hash.get("grade")); // 등급 세션으로 저장
				session.setMaxInactiveInterval(60 * 60);
				out.println("<script>");
				out.println("alert('" + member.get("id") + "님 환영합니다!');");
				out.println("location.href='./';");
				out.println("</script>");
			} else {
				int dupl_sol = service.dupl_sol(member); // 중복되는 값들 다제거
				int user_add = service.user_add(member); // 다시 값 추가

				List<HashMap> member_value = service.getMember(member);
				List<HashMap> user_value = service.getUser(member);
				HashMap<String, Object> hash = member_value.get(0);
				HashMap<String, Object> user = user_value.get(0);

				session.setAttribute("id", id);

				session.setAttribute("user_num", user.get("user_num"));
				session.setAttribute("id_grade", hash.get("grade")); // 등급 세션으로 저장
				session.setMaxInactiveInterval(60 * 60);

				out.println("<script>");
				out.println("alert('" + member.get("id") + "님 환영합니다!');");
				out.println("location.href='./';");
				out.println("</script>");

			}

		}
	}

	// 로그아웃
	@RequestMapping(value = "memberLogout.me", method = { RequestMethod.POST, RequestMethod.GET })
	void Logout(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html; charset=UTF-8");

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		HashMap<String, Object> value = new HashMap<>();
		value.put("id", id);
		int db_del = service.session_db_del(value);
		session.removeAttribute("id");
		session.removeAttribute("id_grade");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('로그아웃되었습니다.');");
		out.println("location.href='./';");
		out.println("</script>");

	}

	// 회원가입폼
	@RequestMapping(value = "memberJoinForm.me", method = { RequestMethod.POST, RequestMethod.GET })
	ModelAndView JoinForm() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("member/join");
		return mv;
	}

	// 회원가입
	@RequestMapping(value = "memberJoinPro.me", method = { RequestMethod.POST, RequestMethod.GET })
	void Join(HttpServletRequest request, HttpServletResponse response) throws Exception {
		System.out.println("왜안오냐?");
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String name = request.getParameter("name");
		String email = request.getParameter("email").trim().toLowerCase();
		String phone = request.getParameter("phone");
		String gender = request.getParameter("gender");
		String datefield = request.getParameter("datefield");
		String zip = request.getParameter("zip");
		String addr1 = request.getParameter("addr1");
		String addr2 = request.getParameter("addr2");
		String addr = addr1 + " " + addr2;
		SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy.MM.dd", Locale.KOREA);
		Date currentTime = new Date();
		String mTime = mSimpleDateFormat.format(currentTime);
		int age = Integer.parseInt(mTime.substring(0, 4)) - Integer.parseInt(datefield.substring(0, 4)) + 1;

		HashMap<String, Object> value = new HashMap<>();
		value.put("id", id);
		value.put("pass", pass);
		value.put("name", name);
		value.put("email", email);
		value.put("phone", phone);
		value.put("gender", gender);
		value.put("zip", zip);
		value.put("address", addr);
		value.put("age", age);

		PrintWriter out = response.getWriter();
		int address_count = service.address_count(value);
		if (address_count != 0) {
			out.println("<script>");
			out.println("alert('이메일이 중복되었습니다.');");
			out.println("location.href='memberJoinForm.me';");
			out.println("</script>");
		} else {
			int insert_result = service.join(value);
			if (insert_result > 0) {
				out.println("<script>");
				out.println("alert('회원가입에 성공하셧습니다!');");
				out.println("window.close();");
				out.println("</script>");
			} else {
				out.println("<script>");
				out.println("alert('회원가입에 실패하였습니다.');");
				out.println("location.href='memberJoinForm.me';");
				out.println("</script>");
			}
		}

	}

	// 아이디 중복 체크
	@RequestMapping(value = "memberIdCheck.me", method = { RequestMethod.POST, RequestMethod.GET })
	void Id_dupl_chk(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		JSONObject obj = new JSONObject();
		String openInit = request.getParameter("openInit");

		if (openInit == null) {
			String id_chk = request.getParameter("id");
			if (id_chk == null || id_chk.trim().equals("")) {
				request.setAttribute("usebleId", "error");
				response.setContentType("text/html;charset=UTF-8");

				PrintWriter out = response.getWriter();
				/*
				 * out.println("<script>");
				 * out.println("location='/EmartFoodCourt/member/idCheck.jsp';");
				 */

				// out.println("</script>");
				UUID uuid = UUID.randomUUID();

				uuid.toString();
				StringBuilder str = new StringBuilder();
				str.append("<script>");
				str.append("location='./idCheck.jsp';");
				str.append("</script>");
				out.print(str);

			} else {
				HashMap<Object, Object> value = new HashMap<Object, Object>();
				value.put("id", id_chk);
				List<HashMap> idcheck = service.getMember(value);
				if (idcheck.isEmpty()) {
					request.setAttribute("usebleId", "true");
					request.setAttribute("id", id_chk);

					obj.put("result", "1");

					response.setContentType("application/x-json; charset=UTF-8");
					response.getWriter().print(obj);

				} else {
					request.setAttribute("usebleId", "false");
					request.setAttribute("id", id_chk);

					obj.put("result", "0");

					response.setContentType("application/x-json; charset=UTF-8");
					response.getWriter().print(obj);
				}
			}
		} else {

			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("location='/EmartFoodCourt/member/idCheck.jsp?openInit=" + openInit + "';");
			out.println("</script>");
		}

	}

	// 아이디 찾기
	@RequestMapping(value = "search_id.me", method = { RequestMethod.POST, RequestMethod.GET })
	ModelAndView Search_ID(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = null;
		System.out.println("IdCheck : " + request.getParameter("IdCheck"));
		if (request.getParameter("IdCheck").equals("true")) {
			mv = new ModelAndView();
			mv.setViewName("member/search_id");
		} else {
			String email = request.getParameter("email");
			HashMap<String, Object> value = new HashMap<>();
			value.put("email", email);
			List<HashMap> total = service.search_id(value);
			if (!(total.isEmpty())) {
				HttpSession session = request.getSession();
				System.out.println("member Id : " + total.get(0).get("id"));

				mv = new ModelAndView();
				mv.addObject("id", total.get(0).get("id"));
				mv.addObject("email", request.getParameter("email"));
				mv.setViewName("member/searchIdResult");

			} else {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('없는 이메일입니다.');");
				out.println("location.href='./search_id.me?IdCheck=true';");
				out.println("</script>");
			}
		}
		return mv;
	}

	// 비밀번호 찾기
	@RequestMapping(value = "search_pwd.me", method = { RequestMethod.POST, RequestMethod.GET })
	ModelAndView Search_pass(HttpServletRequest request, HttpServletResponse response) throws Exception {

		ModelAndView mv = null;
		if (request.getParameter("PwdCheck").equals("true")) {
			mv = new ModelAndView();
			mv.setViewName("member/search_pwd");
		} else {
			String id = request.getParameter("id");
			HashMap<String, Object> value = new HashMap<>();
			value.put("id", id);
			List<HashMap> count = service.search_pass(value);
			if (!(count.isEmpty())) {
				Random r = new Random();
				int a = r.nextInt(9) + 1;
				int b = r.nextInt(9) + 1;
				int c = r.nextInt(9) + 1;
				int d = r.nextInt(9) + 1;
				String abcd = String.valueOf(a) + String.valueOf(b) + String.valueOf(c) + String.valueOf(d);
				value.put("pass", abcd);
				int modify_pass = service.modify_pass(value);
				if (modify_pass > 0) {
					HttpSession session = request.getSession();
					mv = new ModelAndView();
					mv.addObject("id", request.getParameter("id"));
					mv.addObject("pass", abcd);
					mv.addObject("email", count.get(0).get("email"));
					mv.setViewName("member/searchPwdResult");
				} else {
					response.setContentType("text/html;charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("alert('비밀번호를 수정하는것에 실패하였습니다.');");
					out.println("location.href='/EmartFoodCourt/search_pwd.me?PwdCheck=true';");
					out.println("</script>");
				}
			} else {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('없는 아이디입니다.');");
				out.println("location.href='/EmartFoodCourt/search_pwd.me?PwdCheck=true';");
				out.println("</script>");
			}
		}
		return mv;
	}
	
	//회원 마이페이지
	@RequestMapping(value="memberMyPage.me",method = {RequestMethod.POST,RequestMethod.GET})
	String mypage(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String str = null;
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HashMap<String,Object> value = new HashMap<>();
		value.put("id", session.getAttribute("id"));
		List<HashMap> select_mypage = service.select_mypage(value);
		if (session.getAttribute("id") == null || session.getAttribute("id") == null) {
			
			out.println("<script>");
			out.println("alert('로그인 해주십시오.');");
			out.println("location.href='/EmartFoodCourt/memberLogin.me';");
			out.println("</script>");
		} else {
			String session_id = (String) session.getAttribute("id");
			int id_grade = (int) session.getAttribute("id_grade");
			String id = request.getParameter("id");
			if (id_grade != 2) { // 관리자가 아닌 일반유저가
				if (!(id.equals(session_id))) { // 본인 아이디가 아닌 마이페이지에 접속하려고할 떄
					
					out.println("<script>");
					out.println("alert('잘못된 접근입니다.');");
					out.println("location.href='/EmartFoodCourt/';");
					out.println("</script>");
				} else {
					
					
					request.setAttribute("total", select_mypage);
					str = "member/myPage";
				}
			} else { // 관리자로 접속할때
				
				
				request.setAttribute("total", select_mypage);
				str = "member/myPage";
			}
		}
		return str;
	}
	
	//회원 탈퇴
	@RequestMapping(value="memberDelete.ad",method= {RequestMethod.POST,RequestMethod.GET})
	String member_delete(HttpServletRequest request,HttpServletResponse response) throws Exception{

		String str = null;
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String session_id = (String) session.getAttribute("id");
		String id = request.getParameter("id");
		int id_grade = (int) session.getAttribute("id_grade");
		HashMap<String,Object> value = new HashMap<>();
		value.put("id",id);
		if (session_id == null || id == null) {
			
			out.println("<script>");
			out.println("alert('로그인 해주십시오.');");
			out.println("location.href='/EmartFoodCourt/memberLogin.me';");
			out.println("</script>");
		} else {
			if (id_grade == 2) {
				if (Integer.parseInt(request.getParameter("grade")) == 2) {
					
					out.println("<script>");
					out.println("alert('관리자는 삭제할수 없습니다.')");
					out.println("location.href='memberList.ad'");
					out.println("</script>");
				} else {
					int member_delete = service.member_delete(value);
					
						out.println("<script>");
						out.println("alert('회원삭제가 완료되었습니다.');");
						out.println("location.href='memberList.ad'");
						out.println("</script>");
					/*
					 * } else { out.println("<script>");
					 * out.println("alert('회원삭제에 오류가 발생하였습니다. 다시 시도하세요.')");
					 * out.println("history.back()"); out.println("</script>"); }
					 */
				}
			} else {
				if (!(session_id.equals(id))) {
					
					out.println("<script>");
					out.println("alert('잘못된 경로입니다.')");
					out.println("location.href='memberMyPage.me'");
					out.println("</script>");
				} else {
					int member_delete = service.member_delete(value);
					
						out.println("<script>");
						out.println("alert('회원 탈퇴가 완료되었습니다.');");
						out.println("location.href='/EmartFoodCourt/memberLogout.me'");
						out.println("</script>");
					/*
					 * } else { out.println("<script>"); out.println("alert('회원탈퇴에 오루가 발생하였습니다.')");
					 * out.println("history.back()"); out.println("</script>"); }
					 */
				}
			}

		}
		return str;
	}
	
	
	@RequestMapping(value="memberInfo.ad", method= {RequestMethod.GET,RequestMethod.POST})
	String memberInfo(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String str = null;
			// TODO Auto-generated method stub
			request.setCharacterEncoding("UTF-8");
			HttpSession session = request.getSession();
			int id_grade = (int) session.getAttribute("id_grade");
			String session_id = (String) session.getAttribute("id");
			String id = request.getParameter("id");
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			HashMap<String,Object> value = new HashMap<>();
			
			
			int page = 1;
			if (request.getParameter("page") == null
					|| (request.getParameter("page").equals("null") || request.getParameter("page").trim().equals(""))) {
				page = 1;
			} else {
				page = Integer.parseInt(request.getParameter("page"));
			}

			if (session_id == null || id == null) {
				out.println("<script>");
				out.println("alert('로그인 해주십시오.');");
				out.println("location.href='/EmartFoodCourt/memberLogin.me';");
				out.println("</script>");
			} else {
				if (id_grade != 2) { // 관리자가 아닌 일반유저가
					if (!(id.equals(session_id))) { // 본인 아이디가 아닌 마이페이지에 접속하려고할 떄
						out.println("<script>");
						out.println("alert('잘못된 접근입니다.');");
						out.println("location.href='/EmartFoodCourt/';");
						out.println("</script>");
					} else {
						value.put("id", session_id);
						List<HashMap> member = service.selectMember(value);
						request.setAttribute("memberList", member);
						request.setAttribute("page", page);
						str="member/member_info";
					}
				} else {
					value.put("id", id);
					List<HashMap> member = service.selectMember(value);
					request.setAttribute("memberList", member);
					request.setAttribute("page", page);
					request.setAttribute("search", request.getAttribute("search"));
					request.setAttribute("choice", request.getAttribute("choice"));
					str="member/member_info";
				}
			}
		return str;
	}
	
	@RequestMapping(value="memberMod.ad", method= {RequestMethod.POST,RequestMethod.GET})
	String member_modify_form(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String str = null;
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		int page = 1;
		
		if (request.getParameter("page") == null) {
			page = 1;
		} else {
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		if (session.getAttribute("id") == null || session.getAttribute("id") == null) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인 하세요!!')");
			out.println("location.href='memberLogin.me'");
			out.println("</script>");
		}else {
			String session_id = (String) session.getAttribute("id");
			int id_grade = (int) session.getAttribute("id_grade");
			String id = request.getParameter("id");
			if (id_grade != 2) { // 관리자가 아닌 일반유저가
				if (!(id.equals(session_id))) { // 본인 아이디가 아닌 마이페이지에 접속하려고할 떄
					response.setContentType("text/html;charset=UTF-8");
					PrintWriter out = response.getWriter();
					out.println("<script>");
					out.println("alert('잘못된 접근입니다.');");
					out.println("location.href='/EmartFoodCourt/';");
					out.println("</script>");
				} else {
					HashMap<String,Object> value = new HashMap<>();
					value.put("id", id);
					List<HashMap> member = service.selectMember(value);
					
					request.setAttribute("memberList", member);
					request.setAttribute("page", page);
					str="admin/member_mod";
				}
			} else { // 관리자로 접속할때
				HashMap<String,Object> value = new HashMap<>();
				value.put("id", id);
				List<HashMap> member = service.selectMember(value);
				request.setAttribute("memberList", member);
				request.setAttribute("page", page);
				str="admin/member_mod";
			}
		}
		return str;
	}
	
	@RequestMapping(value="memberModifyProcess.ad",method= {RequestMethod.POST,RequestMethod.GET})
	public String member_modify_pro(HttpServletRequest request,HttpServletResponse response) throws Exception{
		String str = null;
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		int page = Integer.parseInt(request.getParameter("page"));
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String session_id = (String) session.getAttribute("id");
		if (request.getParameter("page") == null || request.getParameter("page").equals("null")) {
			page = 1;
		}

		if ((String) session.getAttribute("id") == null) {
			
			out.println("<script>");
			out.println("alert('로그인 하세요!!')");
			out.println("location.href='memberLogin.me'");
			out.println("</script>");
		} else {
			String datefield = "";
			int age = 0;
			SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy.MM.dd", Locale.KOREA);
			Date currentTime = new Date();
			String mTime = mSimpleDateFormat.format(currentTime);
			System.out.println("mTime : " +  mTime);
			HashMap<String,Object> value= new HashMap<>();
			value.put("id", request.getParameter("id"));
			value.put("pass", request.getParameter("pass"));
			value.put("name", request.getParameter("name"));

			if (request.getParameter("datefield") == null || request.getParameter("datefield").trim().equals("")) {
				age = Integer.parseInt(request.getParameter("age"));
			} else {
				datefield = request.getParameter("datefield");
				age = Integer.parseInt(mTime.substring(0, 4)) - Integer.parseInt(datefield.substring(0, 4)) + 1;
			}
			value.put("age", age);
			value.put("gender", request.getParameter("gender"));
			if (request.getParameter("grade") == null || request.getParameter("grade").equals("null")) {
				value.put("grade", 1);
			} else {
				value.put("grade", request.getParameter("grade"));
			}
			value.put("email", request.getParameter("email"));
			int id_grade = (int) session.getAttribute("id_grade");
			
			if (id_grade == 2) {
					int admin_count = Integer.parseInt(String.valueOf(service.selectMember(value).get(0).get("count_grade")));
					if(admin_count==1 && session_id.equals(request.getParameter("id")) && Integer.parseInt(request.getParameter("grade"))==1) {
						out.println("<script>");
						out.println("alert('관리자 계정은 최소 한개 존재해야합니다.');");
						out.println("location.href='memberMod.ad?id="+String.valueOf(request.getParameter("id"))+"&page="+page+"';");
						out.println("</script>");
					}else {
						int member_modify = service.member_modify(value);
						int getMemberidgrade=  Integer.parseInt(String.valueOf(service.selectMember(value).get(0).get("grade")));
						if (getMemberidgrade != 2 && session_id.equals(request.getParameter("id"))) {
							session.removeAttribute("id_grade");
							out.println("<script>");
							out.println("alert('더이상 관리자가 아니므로 로그아웃됩니다.');");
							out.println("location.href='memberLogout.me';");
							out.println("</script>");
						} else {
							request.setAttribute("page", page);
							out.println("<script>");
							out.println("alert('정보수정이 완료되었습니다.');");	
							out.println("location.href='memberList.ad';");
							out.println("</script>");
						}
					}
			} else {
					if(!(session_id.equals(request.getParameter("id")))){	
						out.println("<script>");
						out.println("alert('정보수정이 완료되었습니다.');");
						out.println("location.href='memberInfo.ad?id=" + request.getParameter("id") + "';");
						out.println("</script>");
					}else {
						int member_modify = service.member_modify(value);
						request.setAttribute("page", page);
						out.println("<script>");
						out.println("alert('정보수정이 완료되었습니다.');");
						out.println("location.href='memberInfo.ad?id=" + request.getParameter("id") + "';");
						out.println("</script>");
					}
			}
		}
		return str;
	}
}
