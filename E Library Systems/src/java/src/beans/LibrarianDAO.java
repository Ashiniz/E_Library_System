package src.beans;

import src.utils.DBUtil;
import java.sql.*;

public class LibrarianDAO {

    // Register a new librarian
    public boolean registerLibrarian(Librarian lib) {
        String sql = "INSERT INTO librarians(userID, name, password, phone, employeeID) VALUES (?, ?, ?, ?, ?)";
        try (Connection con = DBUtil.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, lib.getUserID());
            ps.setString(2, lib.getName());
            ps.setString(3, lib.getPassword());
            ps.setString(4, lib.getPhone());
            ps.setString(5, lib.getEmployeeID());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error registering librarian: " + e.getMessage());
            return false;
        }
    }

    // Authenticate librarian login
    public boolean authenticate(String userId, String password) {
        String sql = "SELECT 1 FROM librarians WHERE userID = ? AND password = ?";
        try (Connection con = DBUtil.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, userId);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            System.err.println("Authentication error: " + e.getMessage());
            return false;
        }
    }

    // Get librarian details by userId
    public Librarian getLibrarianById(String userId) {
        String sql = "SELECT * FROM librarians WHERE userID = ?";
        try (Connection con = DBUtil.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Librarian lib = new Librarian();
                lib.setUserID(rs.getString("userID"));
                lib.setName(rs.getString("name"));
                lib.setPassword(rs.getString("password"));
                lib.setPhone(rs.getString("phone"));
                lib.setEmployeeID(rs.getString("employeeID"));
                return lib;
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving librarian: " + e.getMessage());
        }
        return null;
    }

    // Update librarian information
    public boolean updateLibrarian(Librarian lib) {
        String sql = "UPDATE librarians SET phone = ?, employeeID = ?, password = ? WHERE userID = ?";
        try (Connection con = DBUtil.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, lib.getPhone());
            ps.setString(2, lib.getEmployeeID());
            ps.setString(3, lib.getPassword());
            ps.setString(4, lib.getUserID());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error updating librarian: " + e.getMessage());
            return false;
        }
    }

    // Optional: Delete librarian
    public boolean deleteLibrarian(String userId) throws Exception {
        String sql = "DELETE FROM librarians WHERE userID = ?";
        try (Connection con = DBUtil.getConnection(); 
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, userId);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting librarian: " + e.getMessage());
            return false;
        }
    }
}
