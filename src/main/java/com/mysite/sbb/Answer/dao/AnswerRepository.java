package com.mysite.sbb.Answer.dao;

import com.mysite.sbb.Answer.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
}
