import mvc.HandlerMapping;

/**
 * ClassName: Test6
 * Function:  测试
 * Date:      2019/11/19 9:28
 * @author     Kenny
 * version    V1.0
 */
public class Test6 {

    public static void main(String[] args)
            throws Exception {
        HandlerMapping handlerMapping =
                new HandlerMapping();
        handlerMapping.parseController("Demo");
        System.out.println(handlerMapping.get("/add.do"));
    }
}
