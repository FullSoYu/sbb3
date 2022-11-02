package com.mysite.sbb.question.dao;

import com.mysite.sbb.question.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Integer> {


    Question findBySubject(String subject);


    List<Question> findAllBySubject(String subject);

    Question findBySubjectAndContent(String subject, String content);

    Question findBySubjectOrContent(String subject, String content);

    Optional<Question> findBySubjectLike(String subject);

    List<Question> findAllBySubjectLike(String subject);
}
