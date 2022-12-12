package com.fp.controller;

import com.fp.model.FraudIntakeValidValues;
import com.fp.repository.FraudIntakeCodesRepository;
import com.fp.repository.FraudIntakeValidValuesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class FraudIntakeValidValuesController {

    @Autowired
    private FraudIntakeCodesRepository tutorialRepository;

    @Autowired
    private FraudIntakeValidValuesRepository commentRepository;

    @GetMapping("/tutorials/{tutorialId}/comments")
    public ResponseEntity<List<FraudIntakeValidValues>> getAllCommentsByTutorialId(@PathVariable(value = "tutorialId") Long tutorialId) {
        if (!tutorialRepository.existsById(tutorialId)) {
            throw new ResourceNotFoundException("Not found Tutorial with id = " + tutorialId);
        }

        List<FraudIntakeValidValues> comments = commentRepository.findAll();
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @GetMapping("/comments/{id}")
    public ResponseEntity<FraudIntakeValidValues> getCommentsByTutorialId(@PathVariable(value = "id") Long id) {
        FraudIntakeValidValues comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Comment with id = " + id));

        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @PostMapping("/tutorials/{tutorialId}/comments")
    public ResponseEntity<FraudIntakeValidValues> createComment(@PathVariable(value = "tutorialId") Long tutorialId,
                                                                @RequestBody FraudIntakeValidValues commentRequest) {
        FraudIntakeValidValues comment = tutorialRepository.findById(tutorialId).map(tutorial -> {
            return commentRepository.save(commentRequest);
        }).orElseThrow(() -> new ResourceNotFoundException("Not found Tutorial with id = " + tutorialId));

        return new ResponseEntity<>(comment, HttpStatus.CREATED);
    }

    @PutMapping("/comments/{id}")
    public ResponseEntity<FraudIntakeValidValues> updateComment(@PathVariable("id") long id,
                                                                @RequestBody FraudIntakeValidValues commentRequest) {
        FraudIntakeValidValues comment = commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("CommentId " + id + "not found"));


        return new ResponseEntity<>(commentRepository.save(comment), HttpStatus.OK);
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<HttpStatus> deleteComment(@PathVariable("id") long id) {
        commentRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/tutorials/{tutorialId}/comments")
    public ResponseEntity<List<FraudIntakeValidValues>> deleteAllCommentsOfTutorial(@PathVariable(value = "tutorialId") Long tutorialId) {
        if (!tutorialRepository.existsById(tutorialId)) {
            throw new ResourceNotFoundException("Not found Tutorial with id = " + tutorialId);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}