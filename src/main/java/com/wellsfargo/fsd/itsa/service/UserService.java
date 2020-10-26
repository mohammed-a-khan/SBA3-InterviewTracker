package com.wellsfargo.fsd.itsa.service;

import java.util.List;


import com.wellsfargo.fsd.itsa.entity.Users;
import com.wellsfargo.fsd.itsa.exception.ItsException;

public interface UserService {

	Users add(Users users) throws ItsException;

	Users save(Users users) throws ItsException;

	boolean deleteUser(int userId) throws ItsException;

	//Item getItemById(int icode) throws ItsException;

	List<Users> getAllUsers() throws ItsException;
}
