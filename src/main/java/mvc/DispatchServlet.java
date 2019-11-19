package mvc;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

/**
 * ClassName: DispatchServlet
 * Function:  核心前端控制器,处理任何的*.do 请求
 * 前端控制器处理全部的Web功能
 * Date:      2019/11/19 8:33
 * @author     Kenny
 * version    V1.0
 */
public class DispatchServlet extends HttpServlet {

    private HandlerMapping handlerMapping;

    @Override
    public void init() throws ServletException {
        try {
            handlerMapping = new HandlerMapping();
            /**
             *  handlerMapping.parseController("mvc.Controller")
             */
            SAXReader reader = new SAXReader();

            //读取web.xml中的Servlet配置参数
            String file = getInitParameter("config");
            System.out.println("file:" + file);

            InputStream in = getClass().getClassLoader().getResourceAsStream(file);
            Document doc = reader.read(in);
            Element root = doc.getRootElement();
            //找到全部 bean元素
            List<Element> list = root.elements("bean");
            for (Element element : list) {
                //获取bean元素上的class属性
                String className = element.attributeValue("class");
                System.out.println("Controller:" + className);
                //将得到的类名交给handlerMapping
                handlerMapping.parseController(className);
            }
            in.close();
            System.out.println(handlerMapping);

        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("控制器解析错误", e);
        }
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * 创建子控制器
         * 创建子控制器方法
         * 根据子控制器的返回值,转发到JSP页面
         */
        request.setCharacterEncoding("UTF-8");
        try {
            /**
             * 获取用户请求的路径
             */
            String path = request.getRequestURI();
            System.out.println(path);
            String contextPath = request.getContextPath();
            System.out.println(contextPath);
            path = path.substring(contextPath.length());
            System.out.println(path);
            //根据请求的URL找到Handler
            Handler handler = handlerMapping.get(path);
            //执行控制器方法(利用反射执行方法)
            path = handler.execute(request);
            /**
             * 创建子控制器
             * Controller controller = new Controller();
             * 调用子控制器的方法(封装业务逻辑)
             * String path = controller.execute(request)
             */
            if (path.startsWith("redirect:")) {
                //支持重定向
                path = path.substring("redirect:".length());
                if (path.startsWith("http")) {
                    //如果是http开头的就直接重定向
                    response.sendRedirect(path);
                } else {
                    /**
                     * 否则就拼接绝对路径
                     * /servlet/list.do
                     */
                    path = contextPath + path;
                    response.sendRedirect(path);
                }
            } else {
                //转发到JSP
                path = "/WEB-INF/jsp?" + path + ".jsp";
                request.getRequestDispatcher(path).forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("系统繁忙:"+e.getMessage());
        }
    }
}
