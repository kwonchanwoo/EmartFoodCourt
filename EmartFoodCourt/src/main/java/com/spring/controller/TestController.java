package com.spring.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.spring.service.TestService;
import com.spring.dao.TestDao;
@Controller
// test�뤃�뜑 留ㅽ븨
@RequestMapping("/test/*")
public class TestController {
	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(TestController.class);

	@Inject
	private TestService service;
	@Inject
	private TestDao dao;
	// test�뤃�뜑�븞�쓽 test.jsp瑜� 吏�移�
	@RequestMapping(value ="/test",method=RequestMethod.GET)
	public void testGet(Model model) {
		try {
			
			logger.info("test");
			 JSONObject obj = new JSONObject(); 
			StringBuilder str = new StringBuilder();
			List<HashMap> list = service.list();
			int tb = 10;
			str.append("<table>");
			str.append("<th>제목?</th>");
			str.append("<th>내용</th>");
			str.append("<th>날짜?</th>");

			// map�뿉 �엳�뒗 �뜲�씠�꽣 媛��졇�삤湲�
			for (int i = 0; i < list.size(); i++) {
				str.append("<tr>");
				str.append("<td>"+list.get(i).get("subject")+"</td>");
				str.append("<td>"+list.get(i).get("content")+"</td>");
				str.append("<td>"+list.get(i).get("curr_date")+"</td>");
				str.append("</tr>");
			}
			str.append("</table>");
			 obj.put("cnt", service.selectMemberCount()); 
			model.addAttribute("str",str);
			model.addAttribute("tb",10);
			 model.addAttribute("cnt",obj.get("cnt")); 
			model.addAttribute("list",list);
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	@RequestMapping(value ="/regist",method=RequestMethod.GET)
	public ModelAndView RegistForm(Locale locale) {
		logger.info("registForm",locale);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("test/regist");
		return mv;
	}
	@RequestMapping(value ="/registPro",method=RequestMethod.POST)
	public String RegistPro(@RequestParam(value="subject") String subject,@RequestParam(value="content") String content,Locale locale) {
		
		logger.info("registPro",locale);
		 HashMap<String, String> regist = new HashMap<>(); 
		System.out.println("subject : " +subject); 
		System.out.println("content : " + content);
		regist.put("subject",subject);
		regist.put("content",subject);
		System.out.println("확인용  : " + regist.get("subject"));
		 dao.regist(regist); 
			return "redirect:/test/test";
	}
}
