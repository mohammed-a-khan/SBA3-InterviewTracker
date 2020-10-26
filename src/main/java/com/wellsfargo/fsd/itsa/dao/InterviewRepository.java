package com.wellsfargo.fsd.itsa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wellsfargo.fsd.itsa.entity.Interview;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Integer> {

	
	

	Interview findByInterviewId(int interviewId);

	List<Interview> findAllByOrderByInterviewId();

	List<Interview> findByInterviewName(String interviewName);

	List<Interview> findByInterviewerName(String interviewerName);
}
