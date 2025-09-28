package src.beans;

import java.sql.*;
import src.utils.DBUtil;

public class MemberDAO {

    // Login Authentication
    public Member authenticate(String nic, String password) {
        Member member = null;

        try (Connection con = DBUtil.getConnection()) {
            String sql = "SELECT * FROM members WHERE nic = ? AND password = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nic);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                member = new Member();
                member.setId(rs.getInt("id")); // ✅ id exists
                member.setNic(rs.getString("nic"));
                member.setName(rs.getString("name"));
                member.setPhone(rs.getString("phone"));
                member.setPassword(rs.getString("password"));
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Login error: " + e.getMessage());
        }

        return member;
    }

    // Member Registration
    public boolean registerMember(Member member) {
        try (Connection con = DBUtil.getConnection()) {
            String sql = "INSERT INTO members (nic, name, phone, password) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, member.getNic());
            ps.setString(2, member.getName());
            ps.setString(3, member.getPhone());
            ps.setString(4, member.getPassword());

            int result = ps.executeUpdate();
            System.out.println("Inserted rows: " + result);
            return result > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Registration failed: " + e.getMessage());
            return false;
        }
    }

    // Update Member Profile
    public boolean updateMember(Member member) {
        try (Connection con = DBUtil.getConnection()) {
            String sql = "UPDATE members SET name = ?, phone = ?, password = ? WHERE nic = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, member.getName());
            ps.setString(2, member.getPhone());
            ps.setString(3, member.getPassword());
            ps.setString(4, member.getNic());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Update error: " + e.getMessage());
            return false;
        }
    }
}
