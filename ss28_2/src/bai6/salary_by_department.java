package bai6;

import java.sql.*;
import java.util.Scanner;

public class salary_by_department {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("nhập department_id: ");
            int deptId = scanner.nextInt();

            Connection conn = DriverManager.getConnection(ConnectionDB.url, ConnectionDB.username, ConnectionDB.password);

            CallableStatement cstmt = conn.prepareCall("{CALL calculateTotalSalaryByDept(?)}");
            cstmt.setInt(1, deptId);

            ResultSet rs = cstmt.executeQuery();

            if (rs.next()) {
                System.out.println("phòng ban: " + rs.getString("department_name"));
                System.out.println("tổng lương: " + rs.getDouble("total_salary"));
            } else {
                System.out.println("không tìm thấy dữ liệu phù hợp.");
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
