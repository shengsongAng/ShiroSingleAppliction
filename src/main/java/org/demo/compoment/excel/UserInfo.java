package org.demo.compoment.excel;

public class UserInfo {
    private String id;
    private String username;
    private String fullname;
    private String sex;
    private int age;
    private String phone;

    public UserInfo(String id, String username, String fullname, String sex, int age, String phone) {
        this.id = id;
        this.username = username;
        this.fullname = fullname;
        this.sex = sex;
        this.age = age;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
