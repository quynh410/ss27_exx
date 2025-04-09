package bai2;

import java.sql.*;
import java.util.Scanner;

public class student_inserter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("nhập id: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("nhập tên: ");
            String name = scanner.nextLine();

            System.out.print("nhập tuổi: ");
            int age = scanner.nextInt();
            scanner.nextLine();

            System.out.print("nhập chuyên ngành: ");
            String major = scanner.nextLine();

            Connection conn = DriverManager.getConnection(ConnectionDB.url, ConnectionDB.username, ConnectionDB.password);
            String sql = "insert into students (id, name, age, major) values (?, ?, ?, ?)";

            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.setString(2, name);
            pstmt.setInt(3, age);
            pstmt.setString(4, major);

            int rowsInserted = pstmt.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("thêm sinh viên thành công.");
            } else {
                System.out.println("thêm sinh viên thất bại.");
            }
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("lỗi khi truy vấn csdl: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("lỗi: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
