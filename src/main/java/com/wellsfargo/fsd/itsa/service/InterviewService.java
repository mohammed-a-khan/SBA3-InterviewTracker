package com.wellsfargo.fsd.itsa.service;

import java.util.List;

import com.wellsfargo.fsd.itsa.entity.Interview;

import com.wellsfargo.fsd.itsa.exception.ItsException;

public interface InterviewService {

	Interview addInterview(Interview interview) throws ItsException;

	Interview save(Interview interview) throws ItsException;

	boolean deleteInterview(int interviewId) throws ItsException;
	
	Interview findByInterviewId(int interviewId) throws ItsException;
	
	List<Interview> getAllInterviews() throws ItsException;
	
	Interview addInterviewStatus(int interviewId) throws ItsException;
	
	//Interview updateInterviewStatus(int interviewId) throws ItsException;
	Interview updateInterviewStatus(Interview interview) throws ItsException;
	
	Interview addAttendeeToInterview(int interviewId,int userId) throws ItsException;
	
	Interview showAllAttendeeToInterview(String interviewId) throws ItsException;

	List<Interview> getAllInterviewsByInterviewName(String interviewName) throws ItsException;

	List<Interview> getAllInterviewsByInterviewerName(String interviewerName) throws ItsException;

	//Item getItemById(int icode) throws ItsException;

	
}
