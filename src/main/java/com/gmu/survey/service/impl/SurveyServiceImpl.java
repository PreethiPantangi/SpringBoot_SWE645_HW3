package com.gmu.survey.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gmu.survey.exception.ResourceNotFoundException;
import com.gmu.survey.model.Survey;
import com.gmu.survey.reposiitory.SurveyRepository;
import com.gmu.survey.service.SurveyService;

@Service
public class SurveyServiceImpl implements SurveyService{

	private SurveyRepository surveyRepository;
		
	public SurveyServiceImpl(SurveyRepository surveyRepository) {
		super();
		this.surveyRepository = surveyRepository;
	}

	@Override
	public Survey saveSurvey(Survey survey) {
		return surveyRepository.save(survey);
	}

	@Override
	public List<Survey> getAllSurveys() {
		return surveyRepository.findAll();
	}

	@Override
	public Survey getSurveyById(long id) {
		Optional<Survey> survey = surveyRepository.findById(id);
		if(survey.isPresent()) {
			return survey.get();
		} else {
			throw new ResourceNotFoundException("Survey", "id" , id);
		}
	}

	@Override
	public Survey updateSurveyById(long id, Survey survey) {
		Survey existingSurvey = surveyRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Survey", "id", id) 
				);
		existingSurvey.setFirstName(survey.getFirstName());
		existingSurvey.setLastName(survey.getLastName());
		existingSurvey.setStreetAddress(survey.getStreetAddress());
		existingSurvey.setCity(survey.getCity());
		existingSurvey.setState(survey.getState());
		existingSurvey.setZipcode(survey.getZipcode());
		existingSurvey.setEmail(survey.getEmail());
		existingSurvey.setMobile(survey.getMobile());
		existingSurvey.setSurveyDate(survey.getSurveyDate());
		existingSurvey.setComments(survey.getComments());
		existingSurvey.setLikedMost(survey.getLikedMost());
		existingSurvey.setReferenceThrough(survey.getReferenceThrough());
		existingSurvey.setRecommendation(survey.getRecommendation());
		surveyRepository.save(existingSurvey);
		return existingSurvey;
	}

	@Override
	public void deleteSurveyById(long id) {
		surveyRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("Survey", "id", id)
				);
		surveyRepository.deleteById(id);
	}

}
