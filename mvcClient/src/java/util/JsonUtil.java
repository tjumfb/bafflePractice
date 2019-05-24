package util;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import orm.User;

import java.io.IOException;
import java.util.List;

public class JsonUtil {
    static JsonGenerator jsonGenerator = null;
    static ObjectMapper objectMapper = new ObjectMapper();
    JsonUtil(){
    }
    public static String userToString(User user){
        String json = "err:user to json failed";
        try {
            json = objectMapper.writeValueAsString(user);
//            System.out.println(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }
    public static User stringToUser(String json){
        User us = null;
        try {
            us = objectMapper.readValue(json, User.class);
//            System.out.println(us);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return us;
    }
    public static List<User> getUsers(String jsonArray){
        List<User> userList = null;
        try {
          userList = objectMapper.readValue(jsonArray,new TypeReference<List<User>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userList;
    }
}
