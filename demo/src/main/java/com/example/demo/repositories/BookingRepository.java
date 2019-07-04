package com.example.demo.repositories;

import java.util.List;
import com.example.demo.entities.Booking;
import com.example.demo.mappers.BookingRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class BookingRepository {
  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public BookingRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<Booking> getAllBookings() {
    String sql = "SELECT * FROM booking";
    RowMapper<Booking> rowMapper = new BookingRowMapper();
    return this.jdbcTemplate.query(sql, rowMapper);
  }

  public void updatePriceById(Booking booking, Integer id) {
    Integer totalPrice = booking.getTotalPrice();
    String sql = "Update booking Set totalPrice = ? WHERE id = ?";
    this.jdbcTemplate.update(sql, totalPrice, id);

    String select = "SELECT * FROM booking WHERE id = ?";
    RowMapper<Booking> rowMapper = new BookingRowMapper();
    Booking bookings = this.jdbcTemplate.queryForObject(select, rowMapper, id);
    booking.setId(id);
    booking.setCheckInDate(bookings.getCheckInDate());
    booking.setCheckOutDate(bookings.getCheckOutDate());
    booking.setRemarks(bookings.getRemarks());
    booking.setNumGuest(bookings.getNumGuest());
    booking.setPropertyId(bookings.getPropertyId());
    booking.setUserId(bookings.getUserId());
  }

  public List<Booking> getPropertyForBooking(Integer id) {
    String sql = "SELECT * FROM booking WHERE property_id = ?";
    RowMapper<Booking> rowMapper = new BookingRowMapper();
    return this.jdbcTemplate.query(sql, new Object[] { id }, rowMapper);

  }
}