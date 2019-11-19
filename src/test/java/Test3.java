import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * ClassName: Test3
 * Function:  测试
 * Date:      2019/11/19 7:14
 * @author     Kenny
 * version    V1.0
 */
public class Test3 {

    public static void main(String[] args) {
        /**
         * 利用反射检查对象的类型和内部结构
         */
        test("ABC");
        test(5);
        ArrayList list = new ArrayList();
        Iterator i = list.iterator();
        test(i);
    }

    private static void test(Object obj) {
        /**
         * obj 引用对象的类型,以及内部结构是什么?
         * 利用反射就可以动态检查对象的类型和内部结构
         */

        //gatClass() 获取当前对象的实际类型
        Class cls = obj.getClass();
        System.out.println(cls);
        //检查类型的内部结构
        //Declared 声明的 Field 字段 属性
        Field[] fields = cls.getDeclaredFields();

        for (Field field : fields) {
            System.out.println(field);
        }
        //Method:方法 检查类型中声明的方法
        Method[] methods = cls.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println(method);
        }

    }
}
