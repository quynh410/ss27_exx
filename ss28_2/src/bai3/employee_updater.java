package bai3;

import java.sql.*;
import java.util.Scanner;

public class employee_updater {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("nhập id nhân viên cần cập nhật lương: ");
            int id = scanner.nextInt();

            System.out.print("nhập mức lương mới: ");
            double newSalary = scanner.nextDouble();

            Connection conn = DriverManager.getConnection(ConnectionDB.url, ConnectionDB.username, ConnectionDB.password);

            String sql = "update employees set salary = ? where id = ?";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setDouble(1, newSalary);
            pstmt.setInt(2, id);

            int rowsUpdated = pstmt.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("cập nhật lương thành công.");
            } else {
                System.out.println("không tìm thấy nhân viên với id: " + id);
            }

            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("lỗi khi truy cập csdl: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("lỗi: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
