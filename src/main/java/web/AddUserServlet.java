package web;

import dao.UserDAO;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * ClassName: AddUserServlet
 * Function:  添加用户
 * Date:      2019/11/18 14:45
 * @author     Kenny
 * version    V1.0
 */
public class AddUserServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 设置处理post请求时,使用指定的字符集
         * 对请求参数值进行解码.
         * 注:
         *  这行代码一定要添加到所有的getParameter
         *  方法的最前面。
         */
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        /**
         * 读取用户信息
         */
        String userName = request.getParameter("username");
        String passWord = request.getParameter("password");
        String email = request.getParameter("email");

        System.out.println(
                "userName:" + userName + " passWord:"
                        + passWord + " email:" + email);

        /**
         * 先查看用户名是否存在，如果已经存在，
         * 则返回到添加用户页面并提示用户；
         * 否则，将用户信息插入到数据库
         */
        UserDAO dao = new UserDAO();
        try {
            User user = dao.find(userName);
            if (user != null) {
                //用户名存在
                request.setAttribute("msg", "用户名已经被占用");
                request.getRequestDispatcher("addUser.jsp").forward(request, response);
            } else {
                //用户名不存在
                user = new User();
                user.setUserName(userName);
                user.setPassWord(passWord);
                user.setEmail(email);
                dao.save(user);
                response.sendRedirect("list");
            }
        } catch (Exception e) {
            /**
             * step1。记日记（保留现场）
             * 日志一般会记录在文件里面
             */
            e.printStackTrace();
            /**
             * step2.看异常是否恢复，如果不能够恢复
             * （比如数据库服务停止、网路中断等等、这样
             * 的异常一般称之为系统异常），则提示用户
             * 稍后重试。如果能够恢复，则立即恢复。
             */
            out.println("系统繁忙，稍后重试");
        }
    }
}
