package com.wellsfargo.fsd.itsa.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.fsd.itsa.entity.Interview;
import com.wellsfargo.fsd.itsa.exception.ItsException;
import com.wellsfargo.fsd.itsa.service.InterviewService;

@RestController
@RequestMapping("/interview")
public class InterviewRestController {

	@Autowired
	private InterviewService interviewService;

	@GetMapping
	public ResponseEntity<List<Interview>> getAllInterviews() throws ItsException {
		return new ResponseEntity<List<Interview>>(interviewService.getAllInterviews(), HttpStatus.OK);
	}

	
	@GetMapping("/count")
	public ResponseEntity<Integer> getCountInterviews() throws ItsException {
		return new ResponseEntity<Integer>(interviewService.getAllInterviews().size(), HttpStatus.OK);
	}

	@GetMapping("/fetchByInterview/{interviewName}")
	public ResponseEntity<List<Interview>> fetchByInterview(@PathVariable("interviewName") String interviewName)
			throws ItsException {
		return new ResponseEntity<List<Interview>>(interviewService.getAllInterviewsByInterviewName(interviewName),
				HttpStatus.OK);
	}

	
	@GetMapping("/fetchByInterviewerName/{interviewerName}")
	public ResponseEntity<List<Interview>> fetchByInterviewerName(
			@PathVariable("interviewerName") String interviewerName) throws ItsException {
		return new ResponseEntity<List<Interview>>(interviewService.getAllInterviewsByInterviewerName(interviewerName),
				HttpStatus.OK);
	}

	@DeleteMapping("/{interviewId}")
	public ResponseEntity<Void> deleteInterview(@PathVariable("interviewId") int interviewId) throws ItsException {

		Interview interviewdb = interviewService.findByInterviewId(interviewId);
		if (interviewdb == null) {
			throw new ItsException("Interview Id doesn't exists");
		}

		boolean isDeleted = interviewService.deleteInterview(interviewId);

		if (isDeleted) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	public ResponseEntity<Interview> addInterview(@RequestBody Interview interview) throws ItsException {
		Interview interviewdb = interviewService.findByInterviewId(interview.getInterviewId());
		if (interviewdb != null) {
			throw new ItsException("Interview Id already exists");
		}
		Interview interview1;
		try {
			interview1 = interviewService.addInterview(interview);
		} catch (Exception e) {
			throw new ItsException("User already exists");
		}
		return new ResponseEntity<Interview>(interview1, HttpStatus.OK);
	}

	@PatchMapping("/{interviewId}")
	public ResponseEntity<Interview> updateInterviewStatus(@RequestBody Interview interview,
			@PathVariable("interviewId") int interviewId) throws ItsException {

		Interview interviewdb = interviewService.findByInterviewId(interviewId);
		if (interviewdb == null) {
			throw new ItsException("Interview Id doesn't exists");
		}

		interviewdb.setInterviewStatus(interview.getInterviewStatus());

		LocalDate dateIST = LocalDate.now(ZoneId.of("Asia/Kolkata"));
		interview.setDate(dateIST);
		LocalTime timeIST = LocalTime.now(ZoneId.of("Asia/Kolkata"));
		interview.setTime(timeIST);
		return new ResponseEntity<Interview>(interviewService.save(interviewdb), HttpStatus.OK);
	}
	
	@GetMapping("{interviewId}")
	public ResponseEntity<Interview> fetchAttendeeByInterview(@PathVariable("interviewId") int interviewId)
			throws ItsException {
		Interview interviewdb = interviewService.findByInterviewId(interviewId);
		if (interviewdb == null) {
			throw new ItsException("Interview Id doesn't exists");
		}
		return new ResponseEntity<Interview>(interviewdb, HttpStatus.OK);
	}
	
}
