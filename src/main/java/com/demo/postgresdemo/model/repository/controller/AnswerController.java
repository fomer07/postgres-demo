package com.demo.postgresdemo.model.repository.controller;

import com.demo.postgresdemo.exception.ResourceNotFoundException;
import com.demo.postgresdemo.model.Answer;
import com.demo.postgresdemo.model.Question;
import com.demo.postgresdemo.model.repository.AnswerRepository;
import com.demo.postgresdemo.model.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AnswerController {

    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @GetMapping("/questions/{questionId}/answers")
    public List<Answer> getAnswersByQuestionId(@PathVariable Long questionId){
        return answerRepository.findByQuestionId(questionId);
    }
    @PostMapping("/questions/{questionId}/answers")
    public Answer addAnswer (@PathVariable Long questionId,
                             @Valid @RequestBody Answer answer){
    return questionRepository.findById(questionId)
            .map(question -> {
                answer.setQuestion(question);
                return answerRepository.save(answer);
            }).orElseThrow(()->new ResourceNotFoundException(("Question not found with id " + questionId)));
    }
    @PutMapping("/questions/{questionId}/answers")
    public Answer updateAnswer(@PathVariable Long questionId,
                               @PathVariable Long answerId,
                               @Valid@RequestBody Answer answerRequest){
        if (!questionRepository.existsById(questionId)) throw new ResourceNotFoundException("Question not found with id " + questionId);
        return answerRepository.findById(answerId)
                .map(answer -> {
                    answer.setText(answerRequest.getText());
                    return answerRepository.save(answer);
                }).orElseThrow(()-> new ResourceNotFoundException("Answer not found bro sorry !"));
    }
    @DeleteMapping("/questions/{questionId}/answers/{answerId}")
    public ResponseEntity<?> deleteAnswer(@PathVariable Long questionId,@PathVariable Long answerId){
        if (!questionRepository.existsById(questionId)) throw new ResourceNotFoundException("question not found sorry!");
        return answerRepository.findById(answerId)
                .map(answer -> {
                    answerRepository.delete(answer);
                    return ResponseEntity.ok().build();
                }).orElseThrow(()->new ResourceNotFoundException("answer not found bro"));
}






}
