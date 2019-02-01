package com.bbs.controller;

import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.WebUtils;

import com.bbs.domain.MemberVO;
import com.bbs.service.MemberService;

@Controller
@RequestMapping("member")
public class MemberController {
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	@Autowired
	private MemberService service;

	@RequestMapping(value = "memberRegister", method = RequestMethod.GET)
	public String registerGET(MemberVO vo, Model model) throws Exception {
		logger.info("memberRegister get .....");
		return "member/memberRegister";
	}

	@RequestMapping(value = "memberRegister", method = RequestMethod.POST)
	public String registerPOST(MemberVO vo, Model model, RedirectAttributes ra) throws Exception {
		logger.info("memberRegister post .....");
		logger.info(vo.toString());
		service.create(vo);

		ra.addFlashAttribute("useremail", vo.getUseremail());
		ra.addFlashAttribute("username", vo.getUsername());
		ra.addFlashAttribute("userid", vo.getUserid());
		ra.addFlashAttribute("msg", "SUCCESS");
		return "redirect:/sboard/list";
	}

	@RequestMapping(value = "getMember", method = RequestMethod.GET)
	public void getMember(@RequestParam("userid") String userid, Model model) throws Exception {
		logger.info("getMember get .....");
		model.addAttribute(service.getMember(userid));
	}

	@ResponseBody
	@RequestMapping(value = "checkMemberid", method = RequestMethod.POST)
	public String chechMemberId(@RequestParam("userid") String userid, Model model) throws Exception {
		logger.info("checkMemberid post .....");
		return service.checkMemberId(userid) + "";
	}

	@RequestMapping(value = "memberLogin", method = RequestMethod.GET)
	public String loginForm() {
		logger.info("memberLogin get .....");
		return "member/memberLogin"; // /login/loginForm.jsp를 띄움.
	}

	@RequestMapping(value = "memberLogin", method = RequestMethod.POST)
	public String loginProcess(HttpSession session, MemberVO dto, HttpServletResponse response) {
		String returnURL = "";
		if (session.getAttribute("login") != null) {
			// 기존에 login이란 세션 값이 존재한다면
			session.removeAttribute("login"); // 기존값을 제거해 준다.
		}

		// 로그인이 성공하면 UserVO 객체를 반환함.
		MemberVO vo = service.login(dto);

		if (vo != null) { // 로그인 성공
			session.setAttribute("login", vo); // 세션에 login인이란 이름으로 UserVO 객체를 저장해 놈.

			returnURL = "redirect:/sboard/list"; // 로그인 성공시 게시글 목록페이지로 바로 이동하도록 하고

			/*
			 * [ 세션 추가되는 부분 ]
			 */
			// 1. 로그인이 성공하면, 그 다음으로 로그인 폼에서 쿠키가 체크된 상태로 로그인 요청이 왔는지를 확인한다.
			if (dto.isUseCookie()) { // dto 클래스 안에 useCookie 항목에 폼에서 넘어온 쿠키사용 여부(true/false)가 들어있을 것임
				// 쿠키 사용한다는게 체크되어 있으면...
				// 쿠키를 생성하고 현재 로그인되어 있을 때 생성되었던 세션의 id를 쿠키에 저장한다.
				Cookie cookie = new Cookie("loginCookie", session.getId());
				// 쿠키를 찾을 경로를 컨텍스트 경로로 변경해 주고...
				cookie.setPath("/");
				int amount = 60 * 60 * 24 * 7;
				cookie.setMaxAge(amount); // 단위는 (초)임으로 7일정도로 유효시간을 설정해 준다.
				// 쿠키를 적용해 준다.
				response.addCookie(cookie);

				// currentTimeMills()가 1/1000초 단위임으로 1000곱해서 더해야함
				Date sessionLimit = new Date(System.currentTimeMillis() + (1000 * amount));
				// 현재 세션 id와 유효시간을 사용자 테이블에 저장한다.
				service.keepLogin(vo.getUserid(), session.getId(), sessionLimit);

			}
		} else { // 로그인에 실패한 경우
			returnURL = "redirect:/member/memberLogin"; // 로그인 폼으로 다시 가도록 함
		}

		return returnURL; // 위에서 설정한 returnURL 을 반환해서 이동시킴
	}

	// 로그아웃 하는 부분
	@RequestMapping(value = "memberLogout")
	public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		Object obj = session.getAttribute("login");
		if (obj != null) {
			MemberVO vo = (MemberVO) obj;
			// null이 아닐 경우 제거
			session.removeAttribute("login");
			session.invalidate(); // 세션 전체를 날려버림
			// 쿠키를 가져와보고
			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
			if (loginCookie != null) {
				// null이 아니면 존재하면!
				loginCookie.setPath("/");
				// 쿠키는 없앨 때 유효시간을 0으로 설정하는 것 !!! invalidate같은거 없음.
				loginCookie.setMaxAge(0);
				// 쿠키 설정을 적용한다.
				response.addCookie(loginCookie);

				// 사용자 테이블에서도 유효기간을 현재시간으로 다시 세팅해줘야함.
				Date date = new Date(System.currentTimeMillis());
				service.keepLogin(vo.getUserid(), session.getId(), date);
			}
		}
		return "redirect:/sboard/list"; // 로그아웃 후 게시글 목록으로 이동하도록...함

	}

}
