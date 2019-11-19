package mvc;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * ClassName: Handler
 * Function:  处理者,处理器
 * Date:      2019/11/19 9:06
 * @author     Kenny
 * version    V1.0
 */
public class Handler {
    /**
     * 子控制器对象
     */
    private Object controller;
    /**
     * 子控制的方法
     */
    private Method method;

    public Handler() {
    }

    public Handler(Object controller, Method method) {
        this.controller = controller;
        this.method = method;
    }

    @Override
    public String toString() {
        return "Handler{" +
                "controller=" + controller +
                ", method=" + method +
                '}';
    }

    public String execute(HttpServletRequest request) throws InvocationTargetException, IllegalAccessException {
        return (String) method.invoke(controller, request);
    }
}
