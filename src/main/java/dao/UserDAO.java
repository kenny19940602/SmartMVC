package dao;

import entity.User;
import util.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * ClassName: UserDAO
 * Function:  DAO类:用与封装数据访问逻辑
 * Date:      2019/11/18 14:06
 * @author     Kenny
 * version    V1.0
 */
public class UserDAO {

    /**
     * 根据用户名查找用户的所有信息
     * 如果找不到,返回null.
     * @param userName
     * @return
     */
    public User find(String userName) throws Exception {
        User user = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConn();
            ps = conn.prepareStatement("SELECT * FROM t_user WHERE username=?");
            ps.setString(1, userName);
            rs = ps.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUserName(rs.getString("username"));
                user.setPassWord(rs.getString("password"));
                user.setEmail(rs.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            DBUtils.close(rs, ps, conn);
        }
        return user;
    }

    /**
     * 删除指定id的用户
     * @param id
     * @throws Exception
     */
    public void delete(int id) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtils.getConn();
            ps = conn.prepareStatement("DELETE FROM  t_user WHERE  id=?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            DBUtils.close(null, ps, conn);
        }
    }

    /**
     * 将用户信息添加到数据库中(t_user表)
     * @param user
     * @throws Exception
     */
    public void save(User user) throws Exception {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBUtils.getConn();
            ps = conn.prepareStatement("INSERT INTO t_user VALUES (Null,?,?,?)");
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassWord());
            ps.setString(3, user.getEmail());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            DBUtils.close(null, ps, conn);
        }
    }

    /**
     * 将用户表(t_user)中所有用户信息查询出来
     * @return
     * @throws Exception
     */
    public List<User> findAll() throws Exception {
        List<User> userList = new ArrayList<User>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs= null;
        try {
            conn = DBUtils.getConn();
            ps = conn.prepareStatement("SELECT  * FROM t_user");
            rs = ps.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String userName = rs.getString("username");
                String passWord = rs.getString("password");
                String email = rs.getString("email");

                User user = new User();
                user.setId(id);
                user.setUserName(userName);
                user.setPassWord(passWord);
                user.setEmail(email);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            DBUtils.close(rs, ps, conn);
        }
        return userList;
    }
}
