package bai1;
import java.sql.*;
import java.util.Scanner;

public class employee_finder {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("nhập id nhân viên cần tìm: ");
            int inputId = scanner.nextInt();
            Connection conn = DriverManager.getConnection(ConnectionDB.url, ConnectionDB.username, ConnectionDB.password);

            String sql = "select id, name, salary from employees where id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, inputId);

            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("thông tin nhân viên:");
                System.out.println("id: " + rs.getInt("id"));
                System.out.println("tên: " + rs.getString("name"));
                System.out.println("lương: " + rs.getDouble("salary"));
            } else {
                System.out.println("không tìm thấy nhân viên với id: " + inputId);
            }

            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            System.out.println("lỗi khi truy vấn cơ sở dữ liệu: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("lỗi: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
