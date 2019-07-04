package com.example.demo.repositories;

import java.util.List;
import com.example.demo.entities.Review;
import com.example.demo.mappers.ReviewRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class ReviewRepository {
  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public ReviewRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<Review> getAllReviews() {
    String sql = "SELECT * FROM review";
    RowMapper<Review> rowMapper = new ReviewRowMapper();
    return this.jdbcTemplate.query(sql, rowMapper);
  }

  public List<Review> getReviewForProperty(Integer id) {
    String sql = "SELECT * FROM review WHERE property_id = ?";
    RowMapper<Review> rowMapper = new ReviewRowMapper();
    return this.jdbcTemplate.query(sql, new Object[] { id }, rowMapper);

  }

  public void createReview(Review review, Integer property_id) {
    String sql = "INSERT INTO review VALUES(?, ?, ?) ";
    Integer rating = review.getRating();
    String remark = review.getRemark();
    this.jdbcTemplate.update(sql, rating, remark, property_id);

    sql = "SELECT id FROM review WHERE property_id = ?";
    int id = jdbcTemplate.queryForObject(sql, Integer.class, property_id);

    review.setId(id);
  }
}