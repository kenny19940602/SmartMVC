package web;

import dao.UserDAO;
import entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * ClassName: ListUserServlet
 * Function:  获取用户列表
 * Date:      2019/11/18 15:11
 *
 * @author Kenny
 * version    V1.0
 */
public class ListUserServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=utf-8");
        PrintWriter out = response.getWriter();

        //将所有用户信息查询出来
        UserDAO dao = new UserDAO();
        try {
            List<User> userList = dao.findAll();
            /**
             * 因为Servlet不擅长处理表示逻辑，
             * 所以使用转发机制，将数据绑定到
             * request对象上，然后转法给jsp来处理。
             */
            request.setAttribute("users", userList);
            request.getRequestDispatcher("listUser.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            out.println("系统繁忙，稍后重试");
        }
    }
}
