package com.example.ChatApplication.controller;

import com.example.ChatApplication.dao.StatusRepository;
import com.example.ChatApplication.dao.UserRepository;
import com.example.ChatApplication.model.StatusModel;
import com.example.ChatApplication.model.UserModel;
import com.example.ChatApplication.service.UserService;
import com.example.ChatApplication.util.CommonUtils;
import io.micrometer.common.lang.Nullable;
import io.swagger.v3.core.util.Json;
import org.apache.catalina.User;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequestMapping("app/v1/user")
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    StatusRepository statusRepository;
    @Autowired
    UserService userService;
    @PostMapping("create-user")
    public ResponseEntity<String>  createUser(@RequestBody String userData){
        JSONObject isValid=validateUserRequest(userData);
        UserModel user =null;
        if(isValid.isEmpty()){
            user = setUser(userData);
            userService.saveUser(user);

        }
        else{
            return new ResponseEntity<>(isValid.toString(), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>("saved",HttpStatus.CREATED);
    }
    @GetMapping("get-user")
    public ResponseEntity<String>  getUser(@Nullable @RequestParam String userId) {
      JSONArray userArr= userService.getUser(userId);
        return new ResponseEntity<>(userArr.toString(), HttpStatus.OK);
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody String requestData){
        JSONObject requestJson=new JSONObject(requestData);
        JSONObject isValidLogin=validateLogin(requestJson);
        if(isValidLogin.isEmpty()){
            String username=requestJson.getString("username");
            String password=requestJson.getString("password");
           JSONObject responseObj= userService.login(username,password);
           if(responseObj.has("errorMessage")){
               return new ResponseEntity<>(responseObj.toString(), HttpStatus.BAD_REQUEST);
           }
           else {
               return new ResponseEntity<>(responseObj.toString(), HttpStatus.OK);
           }
        }else{
            return new ResponseEntity<>(isValidLogin.toString(), HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/delete-user")
    public ResponseEntity<String>  deleteUser(@Nullable @RequestParam String userId) {
        userService.deleteUser(userId);
        return new ResponseEntity<String>("user-deleted",HttpStatus.OK);
    }
    @PutMapping("/update-user")
    public ResponseEntity<String> updateUser(@RequestParam String userId, @RequestBody UserModel newUser){
        userService.updateUser(userId,newUser);
        return new ResponseEntity<String>("user-updated successfully",HttpStatus.OK);
    }

    private JSONObject validateLogin(JSONObject requestJson) {

        JSONObject errorList=new JSONObject();
        if(!requestJson.has("username")){
            errorList.put("username","missing parameter");
        }
        if(!requestJson.has("password")){
            errorList.put("password","missing parameter");
        }
        return errorList;
    }

    private JSONObject validateUserRequest(String userData) {
        JSONObject userJson=new JSONObject(userData);
        JSONObject errorList =new JSONObject();
        if(userJson.has("username")){
          String username=userJson.getString("username");
        List<UserModel> userList = userRepository.findByUsername(username);
        if(userList.size()>0){
            errorList.put("username","This username already exists");
            return errorList;
        }
        }
        else{
          errorList.put("username","MISSING PARAMETER");
        }
        if(userJson.has("firstName")){
            String firstName=userJson.getString("firstName");
        }
        else{
            errorList.put("firstName","MISSING PARAMETER");
        }
        if(userJson.has("email")){
            String email=userJson.getString("email");
            if(!CommonUtils.isValidEmail(email)){
                errorList.put("email","please enter valid email");
            }
        }
        else{
            errorList.put("email","MISSING PARAMETER");
        }
        if(userJson.has("password")){
            String password=userJson.getString("password");
            if(!CommonUtils.isValidPassword(password)){
                errorList.put("password","please enter valid password");
            }
        }
        else{
            errorList.put("password","MISSING PARAMETER");
        }
        if(userJson.has("phoneNumber")){
            String phoneNumber=userJson.getString("phoneNumber");
            if(!CommonUtils.isValidPhoneNumber(phoneNumber)){
                errorList.put("phoneNumber","please enter valid phone number");
            }
        }
        else{
            errorList.put("phoneNumber","MISSING PARAMETER");
        }
        if(userJson.has("age")){
            String age=userJson.getString("age");
        }
        if(userJson.has("lastName")){
            String lastName=userJson.getString("lastName");
        }
        return errorList;
    }

    private UserModel setUser(String userData) {
        UserModel user=new UserModel();
        JSONObject json=new JSONObject(userData);
        user.setUsername(json.getString("username"));
        user.setFirstName(json.getString("firstName"));
        user.setPassword(json.getString("password"));
        user.setPhoneNumber(json.getString("phoneNumber"));
        user.setEmail(json.getString("email"));
        if(json.has("age")){
            user.setAge(json.getInt("age"));
        }
        if(json.has("lastName")){
            user.setLastName(json.getString("lastName"));
        }
        if(json.has("gender")){
            user.setGender(json.getString("gender"));
        }
        Timestamp createdTime=new Timestamp(System.currentTimeMillis());
        user.setCreatedDate(createdTime);
         StatusModel status= statusRepository.findById(1).get();
         user.setStatusId(status);
        return user;
    }
}
