package com.ranoshisdas.poostaar.service;

import com.ranoshisdas.poostaar.payload.Response;
import com.ranoshisdas.poostaar.payload.UserDto;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto userDto,Integer userId);
    UserDto getUserById(Integer userId);
    Response GetAllUser(Integer pageNumber, Integer pageSize, String sortBy, String sortDir);
    void deleteUser(Integer userId);
}
