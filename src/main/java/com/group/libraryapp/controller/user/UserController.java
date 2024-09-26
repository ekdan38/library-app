package com.group.libraryapp.controller.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.service.user.UserServiceV1;
import com.group.libraryapp.service.user.UserServiceV2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private final UserServiceV2 userServiceV;

    public UserController(UserServiceV2 userService) {
        this.userServiceV = userService;
    }

    @PostMapping("/user")
    //이름 필수, 나이 선택
    public void saveUser(@RequestBody UserCreateRequest request){
        userServiceV.saveUser(request);
    }

    @GetMapping("/user")
    public List<UserResponse> users(){
        return userServiceV.getUsers();
    }


    @PutMapping("/user")
    public void updateName(@RequestBody UserUpdateRequest request){
        userServiceV.updateUser(request);
    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestParam("name") String name){
        userServiceV.deleteUser(name);
    }

}
