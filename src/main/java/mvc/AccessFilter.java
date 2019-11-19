package mvc;


import entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * ClassName: AccessFilter
 * Function:  Servlet Filter implementation class AccessFilter
 * Date:      2019/11/19 11:38
 * @author     Kenny
 * version    V1.0
 */
public class AccessFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //如果当前请求是/login 开始的,就放过
        String path = request.getRequestURI();
        String ctxPath = request.getContextPath();
        path = path.substring(ctxPath.length());
        if (path.startsWith("/login")) {
            filterChain.doFilter(request, response);
            return;
        }

        HttpSession session = request.getSession();
        //检查登录用户信息
        User user = (User) session.getAttribute("loginUser");
        if (user == null) {
            //没有登录过,重定向到登录页面
            response.sendRedirect(request.getContextPath() +
                    "/login-form.do");
        } else {
            //如果登录了就放过
            filterChain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {

    }
}
