package orm;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.io.Serializable;

public class User implements Serializable {
    @JsonAlias(value = "SEX")
    private String sex;
    @JsonAlias(value = "ID")
    private Integer id;
    @JsonAlias(value = "NAME")
    private String name;
    @JsonAlias(value = "AGE")
    private Integer age;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
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
        return "[ id: "+id+", name: "+name+", sex: "+sex+", age: "+age+"]";
    }

    @Override
    public boolean equals(Object o){
        if(o.getClass().equals(this.getClass())){
            User user = (User)o;
            return user.getId().equals(this.getId())&&
                   user.getSex().equals(this.getSex())&&
                   user.getAge().equals(this.getAge())&&
                   user.getName().equals(this.getName());
        }
        return false;
    }

    @Override
    public int hashCode(){
        return this.getName().hashCode()+this.getAge().hashCode()+this.getSex().hashCode()+this.getId().hashCode();
    }
}
