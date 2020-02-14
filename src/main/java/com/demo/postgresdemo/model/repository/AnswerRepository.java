package com.demo.postgresdemo.model.repository;

import com.demo.postgresdemo.model.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * CREATED BY Omer Faruk AY 2/13/2020
 */
@Repository
public interface AnswerRepository extends JpaRepository<Answer,Long> {
    List<Answer> findByQuestionId(Long questionId);
}
