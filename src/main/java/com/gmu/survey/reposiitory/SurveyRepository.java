package com.gmu.survey.reposiitory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gmu.survey.model.Survey;

public interface SurveyRepository extends JpaRepository<Survey, Long> {

}
