package com.wellsfargo.fsd.itsa.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.fsd.itsa.dao.InterviewRepository;
import com.wellsfargo.fsd.itsa.entity.Interview;
import com.wellsfargo.fsd.itsa.exception.ItsException;

@Service
public class InterviewServiceImpl implements InterviewService {

	@Autowired
	private InterviewRepository interviewRepo;

	@Override
	public Interview addInterview(Interview interview) throws ItsException {

		try {
			Interview Interviewdb = interviewRepo.findByInterviewId(interview.getInterviewId());
			if (Interviewdb != null) {
				throw new ItsException("Interview Id already exists!");
			}
			LocalDate dateIST = LocalDate.now(ZoneId.of("Asia/Kolkata"));
			interview.setDate(dateIST);
			LocalTime timeIST = LocalTime.now(ZoneId.of("Asia/Kolkata"));
			interview.setTime(timeIST);
			interviewRepo.save(interview);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ItsException("Exception while adding interview details");
		}

		return interview;
	}

	@Override
	public Interview save(Interview interview) throws ItsException {
		Interview Interviewdb = interviewRepo.findByInterviewId(interview.getInterviewId());
		if (Interviewdb != null) {

			throw new ItsException("Interview Id already exists!");
		}

		interviewRepo.save(interview);
		// }
		return interview;
	}

	@Override
	public Interview updateInterviewStatus(Interview interview) throws ItsException {
		Interview Interviewdb = interviewRepo.findByInterviewId(interview.getInterviewId());
		if (Interviewdb != null) {
			throw new ItsException("interviewId Not Found");
		}
		interview.setInterviewStatus(null);
		interviewRepo.save(interview);

		return interview;
	}

	@Override
	public boolean deleteInterview(int interviewId) throws ItsException {
		Interview Interviewdb = interviewRepo.findByInterviewId(interviewId);
		if (Interviewdb != null) {
			throw new ItsException("interviewId Not Found");
		}

		interviewRepo.deleteById(interviewId);
		return true;
	}

//	@Override
//	public Item getItemById(int icode) throws ItsException {
//		return itemRepo.findById(icode).orElse(null);		
//	}

	@Override
	public List<Interview> getAllInterviews() throws ItsException {
		return interviewRepo.findAll();
	}
	
	@Override
	public List<Interview> getAllInterviewsByInterviewName(String interviewName) throws ItsException {
		List<Interview> interviewdb = interviewRepo.findByInterviewName(interviewName);
		if (interviewdb != null) {
			throw new ItsException("interview Name Not Found");
		}
		return interviewdb;
	}
	
	@Override
	public List<Interview> getAllInterviewsByInterviewerName(String interviewerName) throws ItsException {
		List<Interview> interviewdb = interviewRepo.findByInterviewerName(interviewerName);
		if (interviewdb != null) {
			throw new ItsException("interviewer Name Not Found");
		}
		return interviewdb;
	}

	@Override
	public Interview addInterviewStatus(int interviewId) throws ItsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Interview addAttendeeToInterview(int interviewId, int userId) throws ItsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Interview showAllAttendeeToInterview(String interviewId) throws ItsException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Interview findByInterviewId(int interviewId) throws ItsException {
		// TODO Auto-generated method stub
		return null;
	}

}
