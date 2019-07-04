package com.example.demo.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.example.demo.entities.Booking;

import org.springframework.jdbc.core.RowMapper;

/**
 * BookingRowMapper
 */
public class BookingRowMapper implements RowMapper<Booking> {

  @Override
  public Booking mapRow(ResultSet row, int rowNum) throws SQLException {
    Booking booking = new Booking();

    booking.setId(row.getInt("id"));
    booking.setCheckInDate(row.getString("checkInDate"));
    booking.setCheckOutDate(row.getString("checkOutDate"));
    booking.setTotalPrice(row.getInt("totalPrice"));
    booking.setRemarks(row.getString("remarks"));
    booking.setNumGuest(row.getInt("numGuest"));
    booking.setPropertyId(row.getInt("property_Id"));
    booking.setUserId(row.getInt("user_Id"));
    return booking;
  }
}