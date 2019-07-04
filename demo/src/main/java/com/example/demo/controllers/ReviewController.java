package com.example.demo.controllers;

import java.util.List;

import com.example.demo.entities.Review;
import com.example.demo.repositories.ReviewRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class ReviewController {

  @Autowired
  ReviewRepository reviewRepository;

  @GetMapping(value = "/reviews", produces = "application/json")
  public List<Review> displayReviews() {
    return reviewRepository.getAllReviews();
  }

  @GetMapping(path = "/properties/{id}/reviews", produces = "application/json")
  public List<Review> searchReviewForProperty(@PathVariable("id") Integer id) {
    return reviewRepository.getReviewForProperty(id);
  }

  @PostMapping(value = "/properties/{id}/reviews", produces = "application/json")
  public Review createReview(@RequestBody Review review, @PathVariable("id") Integer id) {
    reviewRepository.createReview(review, id);
    return review;
  }
}