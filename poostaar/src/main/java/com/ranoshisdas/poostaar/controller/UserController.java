package com.ranoshisdas.poostaar.controller;

import com.ranoshisdas.poostaar.config.AppConstant;
import com.ranoshisdas.poostaar.payload.ApiResponse;
import com.ranoshisdas.poostaar.payload.Response;
import com.ranoshisdas.poostaar.payload.UserDto;
import com.ranoshisdas.poostaar.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("poostaar/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto newUser = this.userService.createUser(userDto);
return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDto> updateUser(@Valid@RequestBody UserDto userDto,
                                              @PathVariable Integer userId){
        UserDto updatedUser = this.userService.updateUser(userDto, userId);
        return ResponseEntity.ok(updatedUser);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getById(@PathVariable Integer userId){
        UserDto userById = this.userService.getUserById(userId);
        return ResponseEntity.ok(userById);
    }

    @GetMapping("/")
    public ResponseEntity<Response> getAllUser(
     @RequestParam(value = "pageNumber",defaultValue =AppConstant.PAGE_NUMBER,required = false)Integer pageNumber,
     @RequestParam(value = "pageSize",defaultValue = AppConstant.PAGE_SIZE,required = false)Integer pageSize,
     @RequestParam(value = "sortBy",defaultValue = "Id",required = false)String sortBy,
     @RequestParam(value = "sortDir",defaultValue = AppConstant.SORT_DIR,required = false)String sortDir){
        return ResponseEntity.ok(this.userService.GetAllUser(pageNumber,pageSize,sortBy,sortDir));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<ApiResponse>deleteUser(@PathVariable Integer userId){
        this.userService.deleteUser(userId);
        return new  ResponseEntity<>(new ApiResponse("User Deleted",true),HttpStatus.OK);
    }

}
