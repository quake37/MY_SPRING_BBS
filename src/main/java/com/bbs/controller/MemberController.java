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
		return "member/memberLogin"; // /login/loginForm.jsp�� ���.
	}

	@RequestMapping(value = "memberLogin", method = RequestMethod.POST)
	public String loginProcess(HttpSession session, MemberVO dto, HttpServletResponse response) {
		String returnURL = "";
		if (session.getAttribute("login") != null) {
			// ������ login�̶� ���� ���� �����Ѵٸ�
			session.removeAttribute("login"); // �������� ������ �ش�.
		}

		// �α����� �����ϸ� UserVO ��ü�� ��ȯ��.
		MemberVO vo = service.login(dto);

		if (vo != null) { // �α��� ����
			session.setAttribute("login", vo); // ���ǿ� login���̶� �̸����� UserVO ��ü�� ������ ��.

			returnURL = "redirect:/sboard/list"; // �α��� ������ �Խñ� ����������� �ٷ� �̵��ϵ��� �ϰ�

			/*
			 * [ ���� �߰��Ǵ� �κ� ]
			 */
			// 1. �α����� �����ϸ�, �� �������� �α��� ������ ��Ű�� üũ�� ���·� �α��� ��û�� �Դ����� Ȯ���Ѵ�.
			if (dto.isUseCookie()) { // dto Ŭ���� �ȿ� useCookie �׸� ������ �Ѿ�� ��Ű��� ����(true/false)�� ������� ����
				// ��Ű ����Ѵٴ°� üũ�Ǿ� ������...
				// ��Ű�� �����ϰ� ���� �α��εǾ� ���� �� �����Ǿ��� ������ id�� ��Ű�� �����Ѵ�.
				Cookie cookie = new Cookie("loginCookie", session.getId());
				// ��Ű�� ã�� ��θ� ���ؽ�Ʈ ��η� ������ �ְ�...
				cookie.setPath("/");
				int amount = 60 * 60 * 24 * 7;
				cookie.setMaxAge(amount); // ������ (��)������ 7�������� ��ȿ�ð��� ������ �ش�.
				// ��Ű�� ������ �ش�.
				response.addCookie(cookie);

				// currentTimeMills()�� 1/1000�� ���������� 1000���ؼ� ���ؾ���
				Date sessionLimit = new Date(System.currentTimeMillis() + (1000 * amount));
				// ���� ���� id�� ��ȿ�ð��� ����� ���̺� �����Ѵ�.
				service.keepLogin(vo.getUserid(), session.getId(), sessionLimit);

			}
		} else { // �α��ο� ������ ���
			returnURL = "redirect:/member/memberLogin"; // �α��� ������ �ٽ� ������ ��
		}

		return returnURL; // ������ ������ returnURL �� ��ȯ�ؼ� �̵���Ŵ
	}

	// �α׾ƿ� �ϴ� �κ�
	@RequestMapping(value = "memberLogout")
	public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
		Object obj = session.getAttribute("login");
		if (obj != null) {
			MemberVO vo = (MemberVO) obj;
			// null�� �ƴ� ��� ����
			session.removeAttribute("login");
			session.invalidate(); // ���� ��ü�� ��������
			// ��Ű�� �����ͺ���
			Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
			if (loginCookie != null) {
				// null�� �ƴϸ� �����ϸ�!
				loginCookie.setPath("/");
				// ��Ű�� ���� �� ��ȿ�ð��� 0���� �����ϴ� �� !!! invalidate������ ����.
				loginCookie.setMaxAge(0);
				// ��Ű ������ �����Ѵ�.
				response.addCookie(loginCookie);

				// ����� ���̺����� ��ȿ�Ⱓ�� ����ð����� �ٽ� �����������.
				Date date = new Date(System.currentTimeMillis());
				service.keepLogin(vo.getUserid(), session.getId(), date);
			}
		}
		return "redirect:/sboard/list"; // �α׾ƿ� �� �Խñ� ������� �̵��ϵ���...��

	}

}
