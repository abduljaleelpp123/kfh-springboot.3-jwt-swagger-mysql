package com.abdul.kfh.api.services;

import java.util.List;

import com.abdul.kfh.api.request.UserFilter;
import com.abdul.kfh.entity.User;

public interface UserService {

	public List<User> getUsersBy(UserFilter userFilter );
}
