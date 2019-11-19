import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

/**
 * ClassName: Test5
 * Function:  测试
 * Date:      2019/11/19 7:42
 * @author     Kenny
 * version    V1.0
 */
public class Test5 {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        /**
         * 利用反射执行方法
         * 1. 加载类
         * 2. 在类找到要执行的方法
         * 3. 创建对象
         * 4. 在对象上执行方法
         */
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入类名:");
        String className = scanner.nextLine();

        /**
         * Class cls 是反射API的使用入口
         * 动态加载类
         */
        Class cls = Class.forName(className);
        /**
         * 输入方法名
         */
        System.out.println("输入方法名:");
        String name = scanner.nextLine();
        /**
         * 动态找到类上声明的方法,如果找不到就抛出异常!
         */
        Method method = cls.getDeclaredMethod(name);
        /**
         * 创建对象: 动态创建对象, Instance实例
         * newInstance的使用前提是类型必须包含无参构造器
         */
        Object obj = cls.newInstance();
        /**
         * 在对象obj上调用method方法:
         * 如果参数错误,或者对象上没有方法,或者方法执行
         * 期间出现故障,都会抛出异常.
         */

        /**
         * Access访问 Accessible:可访问的
         * setAccessible 打开方法的执行权限
         */
        method.setAccessible(true);
        Object val = method.invoke(obj);
        System.out.println(val);

    }
}
