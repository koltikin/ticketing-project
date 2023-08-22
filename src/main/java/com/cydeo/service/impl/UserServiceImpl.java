package com.cydeo.service.impl;

import com.cydeo.dto.UserDTO;
import com.cydeo.service.UserService;

import java.util.List;

public class UserServiceImpl extends AbstractMapService<UserDTO,String> implements UserService {
    @Override
    public UserDTO save(UserDTO userDTO) {
        return super.save(userDTO.getUserName(),userDTO);
    }

    @Override
    public UserDTO findById(String user) {
        return super.findById(user);
    }
    @Override
    public List<UserDTO> findAll() {
        return super.findAll();
    }
    @Override
    public void deleteById(String username) {
        super.deleteById(username);

    }
}
