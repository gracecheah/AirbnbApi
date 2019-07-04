package com.example.demo.controllers;

import java.util.List;

import com.example.demo.entities.Booking;
import com.example.demo.repositories.BookingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class BookingController {

  @Autowired
  BookingRepository bookingRepository;

  @GetMapping(value = "/bookings", produces = "application/json")
  public List<Booking> displayBookings() {
    return bookingRepository.getAllBookings();
  }

  @PostMapping(value = "/bookings/{id}", produces = "application/json")
  public Booking UpdateUserById(@RequestBody Booking booking, @PathVariable("id") Integer id) {
    bookingRepository.updatePriceById(booking, id);
    return booking;
  }

  @GetMapping(path = "/properties/{id}/bookings", produces = "application/json")
  public List<Booking> searchPropertiesForBooking(@PathVariable("id") Integer id) {
    return bookingRepository.getPropertyForBooking(id);
  }

}