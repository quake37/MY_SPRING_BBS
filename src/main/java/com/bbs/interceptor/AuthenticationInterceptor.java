package com.bbs.interceptor;

import javax.inject.Inject;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import com.bbs.domain.MemberVO;
import com.bbs.service.MemberService;

public class AuthenticationInterceptor extends HandlerInterceptorAdapter{
 
    @Inject
    MemberService service;
     
    // preHandle() : ��Ʈ�ѷ����� ���� ����Ǵ� �޼���
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
         
         
        // session ��ü�� ������
        HttpSession session = request.getSession();
        // loginó���� ����ϴ� ����� ������ ��� �ִ� ��ü�� ������
        Object obj = session.getAttribute("login");
         
        if ( obj == null ){ // �α��ε� ������ ���� ���...
            // �츮�� ����� �� ��Ű�� �����´�.
            Cookie loginCookie = WebUtils.getCookie(request, "loginCookie");
            if ( loginCookie != null ){ // ��Ű�� �����ϴ� ���(������ �α��ζ� ������ ��Ű�� �����Ѵٴ� ��)
                // loginCookie�� ���� �������� -> ��, �����س� ����Id�� ��������
                String sessionId = loginCookie.getValue();
                // ����Id�� checkUserWithSessionKey�� ������ ������ �α��������� �ִ��� üũ�ϴ� �޼��带 ���ļ�
                // ��ȿ�ð��� > now() �� �� ���� ��ȿ�ð��� ������ �����鼭 �ش� sessionId ������ ������ �ִ� ����� ������ ��ȯ�Ѵ�.
                MemberVO memberVO = service.checkUserWithSessionKey(sessionId);
                 
                if ( memberVO != null ){ // �׷� ����ڰ� �ִٸ�
                    // ������ �������� �ش�.
                    session.setAttribute("login", memberVO);
                    return true;
                }
            }
             
            // ���� �Ʒ��� �α��ε� �ȵ��ְ� ��Ű�� �������� �ʴ� ���ϱ� �ٽ� �α��� ������ ���������� �ȴ�.
            // �α����� �ȵǾ� �ִ� ���������� �α��� ������ �ٽ� ��������(redirect)
            response.sendRedirect("/member/memberLogin");
            return false; // ���̻� ��Ʈ�ѷ� ��û���� ���� �ʵ��� false�� ��ȯ��
        }
         
        // preHandle�� return�� ��Ʈ�ѷ� ��û uri�� ���� �ǳ� �ȵǳĸ� �㰡�ϴ� �ǹ���
        // ���� true���ϸ� ��Ʈ�ѷ� uri�� ���� ��.
        return true;
    }
 
    // ��Ʈ�ѷ��� ����ǰ� ȭ���� �������� ������ ����Ǵ� �޼���
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub
        super.postHandle(request, response, handler, modelAndView);
    }


}
