import mvc.RequestMapping;

/**
 * ClassName: Demo
 * Function:  测试
 * Date:      2019/11/18 15:31
 * @author     Kenny
 * version    V1.0
 */
public class Demo {

    @RequestMapping("/list.do")
    public String list(){
        return "list";
    }

    @RequestMapping("/add.do")
    private String add() {
        return "add";
    }
}
