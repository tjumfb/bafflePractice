package controller;

import dao.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import orm.User;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Controller
public class HelloController {
    List<User> list = new ArrayList<>();

    @RequestMapping(value = "/ram/add", method = RequestMethod.GET)
    @ResponseBody
    public Object addRam(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "sex") String sex,
            @RequestParam(value = "age") int age
    ){
        User user = new User(age,sex,name);
        System.out.println("add "+user+" to ram");
        boolean flag = list.add(user);
        return flag;
    }

    @RequestMapping(value = "/mysql/add", method = RequestMethod.GET)
    @ResponseBody
    public Object addMysql(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "sex") String sex,
            @RequestParam(value = "age") int age
    ){
        User user = new User(age,sex,name);
        System.out.println("add "+user+" to mysql");
        boolean flag = UserManager.add(user);
        return flag;
    }

    @RequestMapping(value = "/mysql/update", method = RequestMethod.GET)
    @ResponseBody
    public Object updateMysql(
            @RequestParam(value = "id") int id,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "sex") String sex,
            @RequestParam(value = "age") int age
    ){
        User user = new User(age,sex,name);
        user.setId(id);
        System.out.println(user+"update mysql");
        boolean flag = UserManager.update(user);
        return flag;
    }

    @RequestMapping(value = "/mysql/delete", method = RequestMethod.GET)
    @ResponseBody
    public Object deleteMysql(@RequestParam(value = "id", required = true) int id){
        System.out.println("delete"+id);
        boolean flag = UserManager.delete(id);
        return flag;
    }

    @RequestMapping(value = "/mysql/select/one")
    @ResponseBody
    public Object selectOne(@RequestParam(value = "id") int id){
        System.out.println("select "+id);
        User user = UserManager.selectById(id);
        return user;
    }

    @RequestMapping(value = "/ram/select/all", method = RequestMethod.GET)
    @ResponseBody
    public Object selectAllRam(){
        return list;
    }

    @RequestMapping(value = "/mysql/select/all", method = RequestMethod.GET)
    @ResponseBody
    public Object selectAllMysql(){
        return UserManager.selectAll();
    }


}
