package Dao;

import Model.Users;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsersDAO extends DBContext {


        public Users getUserByUsername(String username) {
            String sql = "SELECT * FROM Users WHERE Username = ?";
            Users user = null;

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, username);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    user = new Users(rs.getInt("UserID"), rs.getString("Username"), rs.getString("PasswordHash"));
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
            return user;
        }
        public void addUser(Users user) { 
            String sql = "INSERT INTO Users (Username, PasswordHash) VALUES (?, ?)"; 
            try (PreparedStatement ps = connection.prepareStatement(sql)) { 
                ps.setString(1, user.getUsername()); 
                ps.setString(2, user.getPasswordHash()); 
                ps.executeUpdate(); 
            } catch (SQLException e) { 
                System.out.println(e);
            } }

        // Các phương thức khác như addUser, getAllUsers...
    }


