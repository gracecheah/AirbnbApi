package com.example.demo.repositories;

import java.util.List;
import com.example.demo.entities.Payment;
import com.example.demo.mappers.PaymentRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class PaymentRepository {
  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public PaymentRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<Payment> getAllPayments() {
    String sql = "SELECT * FROM payment";
    RowMapper<Payment> rowMapper = new PaymentRowMapper();
    return this.jdbcTemplate.query(sql, rowMapper);
  }

  public List<Payment> getPaymentsForBooking(int id) {
    String sql = "SELECT * FROM payment where bookingId = ?";
    RowMapper<Payment> rowMapper = new PaymentRowMapper();
    return this.jdbcTemplate.query(sql, new Object[] { id }, rowMapper);
  }
}