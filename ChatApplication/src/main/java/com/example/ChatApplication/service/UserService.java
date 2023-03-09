package com.example.ChatApplication.service;

import com.example.ChatApplication.dao.UserRepository;
import com.example.ChatApplication.model.UserModel;
import org.apache.catalina.User;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    public int saveUser(UserModel user) {
       UserModel userObj= userRepository.save(user);
       return userObj.getUserId();
    }

    public JSONArray getUser(String userId) {
        JSONArray response=new JSONArray();

        if(userId!=null){
     List<UserModel> usersList= userRepository.getUserByUserId(Integer.valueOf(userId));
     for(UserModel user:usersList){
         JSONObject userObj=createResponse(user);
         response.put(userObj);
     }
        }
        else{
            List<UserModel> usersList= userRepository.getAllUsers();
            for(UserModel user:usersList){
                JSONObject userObj=createResponse(user);
                response.put(userObj);
            }
        }
        return response;
    }

    private JSONObject createResponse(UserModel user) {
        JSONObject jsonObj = new JSONObject();

        jsonObj.put("userId", user.getUserId());
        jsonObj.put("username", user.getUsername());
        jsonObj.put("firstName", user.getFirstName());
        jsonObj.put("lastName", user.getLastName());
        jsonObj.put("age", user.getAge());
        jsonObj.put("email", user.getEmail());
        jsonObj.put("phoneNumber", user.getPhoneNumber());
        jsonObj.put("gender", user.getGender());
        jsonObj.put("createdDate", user.getCreatedDate());

        return jsonObj;
    }

    public JSONObject login(String username, String password) {
        JSONObject response =new JSONObject();
        List<UserModel> user=userRepository.findByUsername(username);
        if(user.isEmpty()){
            response.put("error message","username doesn't exists");
            return response;
        }
        else{
            UserModel userObj=user.get(0);
            if(password.equals(userObj.getPassword())){
                response=createResponse(userObj);
            }
            else{
               response.put("error message","invalid password");
            }
            return response;
        }
    }

    public void deleteUser(String userId) {
        userRepository.deleteById(Integer.valueOf(userId));
    }

    public void updateUser(String userId, UserModel newUser) {
        userRepository.findById(Integer.valueOf(userId));
      UserModel user=new UserModel();
      user.setFirstName(newUser.getFirstName());
      user.setPhoneNumber(newUser.getPhoneNumber());
      user.setPassword(newUser.getPassword());
      user.setEmail(newUser.getEmail());
//      user.setPhoneNumber(newUser.getPhoneNumber());
//      user.setPhoneNumber(newUser.getPhoneNumber());
//      user.setPhoneNumber(newUser.getPhoneNumber());

    }
}
