package com.wellsfargo.fsd.itsa.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wellsfargo.fsd.itsa.entity.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

	boolean existsByUserId(int userId);
	
	void deleteByUserId(int userId);
}
