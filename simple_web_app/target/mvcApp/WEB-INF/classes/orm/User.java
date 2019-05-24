package main.resources.orm;

import java.io.Serializable;

public class User implements Serializable {
    private Integer id;
    private Integer age;
    private String sex;
    private String name;

    public User(){
        super();
    }

    public User(Integer age, String sex, String name) {
        this.age = age;
        this.sex = sex;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return "["+name+", "+sex+", "+age+"]";
    }

}
