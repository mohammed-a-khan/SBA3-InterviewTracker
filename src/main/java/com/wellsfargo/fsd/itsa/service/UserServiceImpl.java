package com.wellsfargo.fsd.itsa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.fsd.itsa.dao.UserRepository;
import com.wellsfargo.fsd.itsa.entity.Users;
import com.wellsfargo.fsd.itsa.exception.ItsException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Override
	public Users add(Users users) throws ItsException {
		if(users!=null) {
			//Users userdb = userRepo.existsByUserId(users.getUserId());
			if(userRepo.existsByUserId(users.getUserId())) {
				throw new ItsException("Users id already exists!");
			}
			
			userRepo.save(users);
		}
		return users;
	}

	@Override
	public Users save(Users users) throws ItsException {
		if(users!=null) {
			//Users userdb = userRepo.existsByUserId(users.getUserId());
			if(userRepo.existsByUserId(users.getUserId())) {
				throw new ItsException("User id already exists");
			}
			
			userRepo.save(users);
		}
		return users;
	}

	@Override
	public boolean deleteUser(int userId) throws ItsException {
		//Users userdb = userRepo.existsByUserId(userId);
		if(!userRepo.existsByUserId(userId)) {
			throw new ItsException("User id Not Found");
		}
	
		userRepo.deleteByUserId(userId);
		return true;
	}

//	@Override
//	public Item getItemById(int icode) throws ItsException {
//		return itemRepo.findById(icode).orElse(null);		
//	}

	@Override
	public List<Users> getAllUsers() throws ItsException {
		return userRepo.findAll();
	}

}
