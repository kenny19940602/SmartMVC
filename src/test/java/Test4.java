import mvc.RequestMapping;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Scanner;

/**
 * ClassName: Test4
 * Function:  测试
 * Date:      2019/11/19 7:30
 * @author     Kenny
 * version    V1.0
 */
public class Test4 {

    public static void main(String[] args) throws ClassNotFoundException {
        /**
         * 动态解析类方法上标注的注解
         */
        Scanner in = new Scanner(System.in);
        System.out.println("输入类名: ");
        String className = in.nextLine();

        /**
         * 获取Demo的类型信息
         * Class cls = Demo.class;
         * forName() 可以动态加载一个类到内存中
         */
        Class cls = Class.forName(className);

        /**
         * 获取类型上全部的方法
         */
        Method[] methods = cls.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method);
            /**
             * Annotation:注解
             * method.getAnnotations() 获取在方法上
             * 标注的注解.此注解必须是RUNTIME注解
             */
            Annotation[] anns = method.getAnnotations();
            /**
             * 方法上只标注了一个注解,获取第一注解
             */
            Annotation ann = anns[0];
            System.out.println(ann);
            if (ann instanceof RequestMapping) {
                RequestMapping requestMapping = (RequestMapping) ann;
                System.out.println(requestMapping.value());
            }
        }


    }
}
