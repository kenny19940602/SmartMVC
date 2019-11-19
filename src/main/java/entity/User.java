package entity;

/**
 * ClassName: User
 * Function:  实体类:实体类与表的结构要一致,表有哪些字段,对应的
 * 实体类也要有对应的属性(类型要匹配).
 * Date:      2019/11/18 14:08
 * @author     Kenny
 * version    V1.0
 */
public class User {

    private int id;
    private String userName;
    private String passWord;
    private String email;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
