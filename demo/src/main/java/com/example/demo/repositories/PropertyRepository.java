package com.example.demo.repositories;

import java.util.List;
import com.example.demo.entities.Property;
import com.example.demo.mappers.PropertyRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class PropertyRepository {
  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public PropertyRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<Property> getAllProperties() {
    String sql = "SELECT * FROM property";
    RowMapper<Property> rowMapper = new PropertyRowMapper();
    return this.jdbcTemplate.query(sql, rowMapper);
  }

  // public List<Property> getAllPropertiesByAddress(String address) {
  // String sql = "SELECT * FROM property where address like ? ";
  // RowMapper<Property> rowMapper = new PropertyRowMapper();
  // return this.jdbcTemplate.query(sql, new Object[] { address }, rowMapper);
  // }

  // public List<Property> getAllPropertiesByNumRooms(Integer numRooms) {
  // String sql = "SELECT * FROM property where numRooms = ? ";
  // RowMapper<Property> rowMapper = new PropertyRowMapper();
  // return this.jdbcTemplate.query(sql, new Object[] { numRooms }, rowMapper);
  // }

  // public List<Property> getAllPropertiesByPrice(Integer price) {
  // String sql = "SELECT * FROM property where price >= ? ";
  // RowMapper<Property> rowMapper = new PropertyRowMapper();
  // return this.jdbcTemplate.query(sql, new Object[] { price }, rowMapper);
  // }

  // http://localhost:8080/api/properties/?address=tokyo&numRooms=4&price=35000
  public List<Property> searchProperties(String address, int numRooms, int price) {
    String sql = "SELECT * FROM property WHERE address LIKE ? AND numRooms = ? AND price = ? ";
    RowMapper<Property> rowMapper = new PropertyRowMapper();
    return this.jdbcTemplate.query(sql, new Object[] { "%" + address + "%", numRooms, price }, rowMapper);
  }

  public List<Property> getBookedProperties() {
    String sql = "SELECT DISTINCT property.* FROM property JOIN booking ON booking.property_id = property.id";
    RowMapper<Property> rowMapper = new PropertyRowMapper();
    return this.jdbcTemplate.query(sql, rowMapper);

  }

  public List<Property> getReviewedProperties() {
    String sql = "SELECT DISTINCT property.* FROM property JOIN review ON review.property_id = property.id";
    RowMapper<Property> rowMapper = new PropertyRowMapper();
    return this.jdbcTemplate.query(sql, rowMapper);

  }

  // public void createProperty(Property property) {
  // String sql = "INSERT INTO property VALUES (?,?,?,?,?)";
  // this.jdbcTemplate.update(sql, property.getAddress(), property.getNumRooms(),
  // property.getPrice(),
  // property.getAllowSmoking(), property.getMaxGuestNum());

  // sql = "SELECT id FROM property WHERE address = ?";
  // int id = jdbcTemplate.queryForObject(sql, Integer.class,
  // property.getAddress());

  // property.setId(id);
  // }

  public void updateProperty(Integer numRooms, String address) {
    String sql = "UPDATE property SET numRooms = ? WHERE address like ?";
    this.jdbcTemplate.update(sql, numRooms, address);
  }

  public void deleteProperty(int id) {
    String sql = "DELETE FROM property WHERE id = ?";
    this.jdbcTemplate.update(sql, id);
  }

  public void createProperty(Property property) {
    String sql = "INSERT INTO property VALUES(?, ?, ?, ?, ?) ";
    String address = property.getAddress();
    Integer numRooms = property.getNumRooms();
    Integer price = property.getPrice();
    Boolean allowSmoking = property.getAllowSmoking();
    Integer maxGuestNum = property.getMaxGuestNum();
    this.jdbcTemplate.update(sql, address, numRooms, price, allowSmoking, maxGuestNum);

    sql = "SELECT id FROM property WHERE address = ?";
    int id = jdbcTemplate.queryForObject(sql, Integer.class, property.getAddress());

    property.setId(id);
  }
}