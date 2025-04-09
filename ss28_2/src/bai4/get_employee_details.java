package bai4;

import java.sql.*;
import java.util.Scanner;

public class get_employee_details {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("nhập id nhân viên: ");
            int id = scanner.nextInt();

            Connection conn = DriverManager.getConnection(ConnectionDB.url, ConnectionDB.username, ConnectionDB.password);

            CallableStatement cstmt = conn.prepareCall("{call getEmployeeDetails(?)}");
            cstmt.setInt(1, id);

            ResultSet rs = cstmt.executeQuery();

            if (rs.next()) {
                System.out.println("id: " + rs.getInt("id"));
                System.out.println("tên: " + rs.getString("name"));
                System.out.println("lương: " + rs.getDouble("salary"));
            } else {
                System.out.println("không tìm thấy nhân viên với id: " + id);
            }

            rs.close();
            cstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("lỗi truy vấn csdl: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("lỗi: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
