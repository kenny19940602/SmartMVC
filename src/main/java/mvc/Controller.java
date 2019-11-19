package mvc;

import dao.UserDAO;
import entity.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * ClassName: Controller
 * Function:  子控制器
 * Date:      2019/11/19 11:25
 * @author     Kenny
 * version    V1.0
 */
public class Controller {

    /**
     * 子控制器方法
     * @param request 用于在控制器和JSP直接传递数据
     * @return 转发的目标JSP页面
     */
    @RequestMapping("/list.do")
    public String execute(HttpServletRequest request) throws Exception {
        UserDAO dao = new UserDAO();
        List<User> userList = dao.findAll();
        //将userList数据传递到JSP
        request.setAttribute("users", userList);
        return "list";
    }

    @RequestMapping("/add.do")
    public String add(HttpServletRequest request) {
        return "add";
    }

    public String save(HttpServletRequest request) throws Exception {
        String userName = request.getParameter("uname");
        String passWord = request.getParameter("pwd");
        String email = request.getParameter("email");

        UserDAO dao = new UserDAO();
        //检查用户名是否是同名的
        User user = dao.find(userName);
        if (user != null) {
            //返回到添加页面,继续输入信息
            request.setAttribute("uname", userName);
            request.setAttribute("email", email);
            request.setAttribute("unameError", "用户名重复");
            return "add";
        }

        User insertUser = new User();
        user.setUserName(userName);
        user.setPassWord(passWord);
        user.setEmail(email);
        dao.save(insertUser);
        request.setAttribute("message", "成功");
        //return "success"; //success.jsp
        return "redirect:/list.do";
        //return "redirect:http://tmooc.cn";
    }

    @RequestMapping("/delete.do")
    public String delete(HttpServletRequest request) throws Exception {
        String str = request.getParameter("id");
        int id = Integer.parseInt(str);
        UserDAO dao = new UserDAO();
        dao.delete(id);
        return "redirect:/list.do";

    }
}
