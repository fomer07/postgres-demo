package com.demo.postgresdemo.model.repository;

import com.demo.postgresdemo.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * CREATED BY Omer Faruk AY 2/13/2020
 */
@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

}
