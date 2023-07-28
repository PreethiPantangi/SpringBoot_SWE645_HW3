package com.gmu.survey.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gmu.survey.model.Survey;
import com.gmu.survey.service.SurveyService;

@RestController
@RequestMapping("/api/surveys")
@CrossOrigin(origins = "http:localhost:4200")
public class SurveyController {

	private SurveyService surveyService;

	public SurveyController(SurveyService surveyService) {
		super();
		this.surveyService = surveyService;
	}
	
	@PostMapping
	public ResponseEntity<Survey> saveSurvey(@RequestBody Survey survey) {
		return new ResponseEntity<Survey>(surveyService.saveSurvey(survey), HttpStatus.CREATED);
	}
	
	@GetMapping
	public List<Survey> getAllSurveys() {
		return surveyService.getAllSurveys();
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Survey> getSurveyById(@PathVariable("id") long surveyId) {
		return new ResponseEntity<Survey>(surveyService.getSurveyById(surveyId), HttpStatus.OK);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Survey> updateSurvey(@PathVariable("id") long id, @RequestBody Survey survey) {
		return new ResponseEntity<Survey>(surveyService.updateSurveyById(id, survey), HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteStudentById(@PathVariable("id") long id) {
		surveyService.deleteSurveyById(id);
		return new ResponseEntity<String>("Survey deleted successfully", HttpStatus.OK);
	}
	
}
