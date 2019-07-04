package com.example.demo.controllers;

import java.util.List;
import com.example.demo.entities.Property;
import com.example.demo.repositories.PropertyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class PropertyController {

  @Autowired
  PropertyRepository propertyRepository;

  @GetMapping(value = "/allproperties", produces = "application/json")
  public List<Property> displayProperties() {
    return propertyRepository.getAllProperties();
  }

  // @GetMapping(value = "/properties_address", produces = "application/json")
  // public List<Property> displayPropertiesByAddress(@RequestParam String
  // address) {
  // return propertyRepository.getAllPropertiesByAddress("%" + address + "%");
  // }

  // @GetMapping(value = "/properties_numRooms", produces = "application/json")
  // public List<Property> displayPropertiesByNumRooms(@RequestParam Integer
  // numRooms) {
  // return propertyRepository.getAllPropertiesByNumRooms(numRooms);
  // }

  // @GetMapping(value = "/properties_price", produces = "application/json")
  // public List<Property> displayPropertiesByPrice(@RequestParam Integer price) {
  // return propertyRepository.getAllPropertiesByPrice(price);
  // }

  @GetMapping(path = "/properties", produces = "application/json")
  public List<Property> searchProperties(@RequestParam("address") String address,
      @RequestParam("numRooms") int numRooms, @RequestParam("price") int price) {
    return propertyRepository.searchProperties(address, numRooms, price);
  }

  @GetMapping(path = "/bookedProperties", produces = "application/json")
  public List<Property> displayBookedProperties() {
    return propertyRepository.getBookedProperties();
  }

  @GetMapping(path = "/reviewedProperties", produces = "application/json")
  public List<Property> displayReviewedProperties() {
    return propertyRepository.getReviewedProperties();
  }

  // @GetMapping(path = "/create_property", produces = "application/json")
  // public void createProperty(@RequestParam("address") String address,
  // @RequestParam("numRooms") int numRooms,
  // @RequestParam("price") int price, @RequestParam("allowSmoking") boolean
  // allowSmoking,
  // @RequestParam("maxGuestNum") int maxGuestNum) {
  // Property property = new Property();
  // property.setAddress(address);
  // property.setAllowSmoking(allowSmoking);
  // property.setMaxGuestNum(maxGuestNum);
  // property.setNumRooms(numRooms);
  // property.setPrice(price);

  // propertyRepository.createProperty(property);
  // }

  @GetMapping(path = "/update_property")
  public void updateProperty() {
    propertyRepository.updateProperty(6, "%Tokyo%");
  }

  @GetMapping(path = "/delete_property")
  public void deleteProperty(@RequestParam("id") Integer id) {
    propertyRepository.deleteProperty(id);
  }

  @PostMapping(value = "/properties", produces = "application/json")
  public Property createProperty(@RequestBody Property property) {
    propertyRepository.createProperty(property);
    return property;
  }

}