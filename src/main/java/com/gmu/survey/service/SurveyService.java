package com.gmu.survey.service;

import java.util.List;

import com.gmu.survey.model.Survey;

public interface SurveyService {
	Survey saveSurvey(Survey survey);
	List<Survey> getAllSurveys();
	Survey getSurveyById(long id);
	Survey updateSurveyById(long id, Survey suurvey);
	void deleteSurveyById(long id);
}
