package com.ranoshisdas.poostaar.service.implimentation;

import com.ranoshisdas.poostaar.config.AppConstant;
import com.ranoshisdas.poostaar.entity.User;
import com.ranoshisdas.poostaar.exception.ResourceNotFoundException;
import com.ranoshisdas.poostaar.payload.Response;
import com.ranoshisdas.poostaar.payload.UserDto;
import com.ranoshisdas.poostaar.repo.UserRepo;
import com.ranoshisdas.poostaar.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepo userRepo;

    //Create A New User
    @Override
    public UserDto createUser(@Valid UserDto userDto) {
        User user=AppConstant.modelMapper.map(userDto, User.class);
        User saveUser = this.userRepo.save(user);
        return AppConstant.modelMapper.map(saveUser, UserDto.class);
    }

    //Update The User
    @Override
    public UserDto updateUser(@Valid UserDto userDto, Integer userId) {
        User user = this.userRepo.findById(userId).
                orElseThrow(() -> new ResourceNotFoundException("User", AppConstant.ID, userId));
        user.setName(userDto.getName());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setCollege(userDto.getCollege());
        user.setName(userDto.getName());
        User updateUser = this.userRepo.save(user);
        return AppConstant.modelMapper.map(updateUser, UserDto.class);
    }

    //Get User By Id
    @Override
    public UserDto getUserById(Integer userId) {
        User user = this.userRepo.findById(userId).
                orElseThrow(() -> new ResourceNotFoundException("User", AppConstant.ID, userId));
        return AppConstant.modelMapper.map(user, UserDto.class);
    }

    //Get All User
    @Override
    public Response GetAllUser(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
Sort sort= !sortDir.equalsIgnoreCase(AppConstant.SORT_DIR)?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();

        Pageable p= PageRequest.of(pageNumber,pageSize,sort);
        Page<User> all = this.userRepo.findAll(p);
        List<User> allUser=all.getContent();

        List<UserDto> collect = allUser.stream().map((cat) -> AppConstant.modelMapper.
                map(cat, UserDto.class)).collect(Collectors.toList());
        return AppConstant.getResponse(all,collect);
    }

    //Delete The User
    @Override
    public void deleteUser(Integer userId) {
        User user = userRepo.findById(userId).
                orElseThrow(() -> new ResourceNotFoundException("User", AppConstant.ID, userId));
        this.userRepo.delete(user);
    }
}
