package mvc;

import dao.UserDAO;
import entity.User;

import javax.servlet.http.HttpServletRequest;

/**
 * ClassName: LoginController
 * Function:  登录控制器
 * Date:      2019/11/19 11:13
 * @author     Kenny
 * version    V1.0
 */
public class LoginController {

    @RequestMapping("/login-form.do")
    public String form(HttpServletRequest request) {
        return "login";
    }

    @RequestMapping("/login.do")
    public String login(HttpServletRequest request) throws Exception {
        //检查用户名和密码是否正确
        String userName = request.getParameter("uname");
        String passWord = request.getParameter("pwd");
        UserDAO dao = new UserDAO();
        User user = dao.find(userName);
        //数据中没有用户信息,拒绝登录返回"login"
        if (user == null) {
            request.setAttribute("unameError","用户名或密码错误");
            request.setAttribute("usname", userName);
            return "login";
        }
        if (user.getPassWord().equals(passWord)) {
            //密码也一样则登录成功!
            request.getSession().setAttribute("loginUser", user);
            return "redirect:/list.do";
        }
        //密码不同,返回登录页面继续登录
        request.setAttribute("pwdError", "用户名或密码错误");
        request.setAttribute("uname", userName);
        return "login";
    }
}
