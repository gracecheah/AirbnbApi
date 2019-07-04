package com.example.demo.mappers;

import org.springframework.jdbc.core.RowMapper;
import com.example.demo.entities.User;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * UserRowMapper
 */
public class UserRowMapper implements RowMapper<User> {
  @Override
  public User mapRow(ResultSet row, int rowNum) throws SQLException {
    User user = new User();
    user.setId(row.getInt("id"));
    user.setFirstName(row.getString("firstName"));
    user.setLastName(row.getString("lastName"));
    user.setEmail(row.getString("email"));
    user.setPhone(row.getString("phone"));
    return user;
  }

}