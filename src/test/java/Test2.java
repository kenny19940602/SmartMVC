import util.DBUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.sql.SQLException;

/**
 * ClassName: Test2
 * Function:  测试
 * Date:      2019/11/18 15:37
 * @author     Kenny
 * version    V1.0
 */
public class Test2 {

    public static void main(String[] args) throws UnsupportedEncodingException, SQLException {
        String name = "小白";

        /**
         * encode方法会按照指定的字符集进行编码。
         */
        String nameEncoder = URLEncoder.encode(name, "utf-8");
        System.out.println("nameEncoder: "+nameEncoder);

        /**
         * decode方法会按照指定的字符集进行解码。
         */
        String nameDecoder = URLDecoder.decode(nameEncoder, "iso-8859-1");
        System.out.println("nameDecoder: " + nameDecoder);

        System.out.println(DBUtils.getConn());
    }
}
