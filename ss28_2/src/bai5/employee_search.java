package bai5;

import java.sql.*;
import java.util.*;

public class employee_search {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            Connection conn = DriverManager.getConnection(ConnectionDB.url, ConnectionDB.username, ConnectionDB.password);

            StringBuilder sql = new StringBuilder("SELECT * FROM employee WHERE 1=1");
            List<Object> params = new ArrayList<>();

            System.out.print("nhập tên cần tìm (có thể bỏ trống): ");
            scanner.nextLine();
            String name = scanner.nextLine().trim();
            if (!name.isEmpty()) {
                sql.append(" AND name LIKE ?");
                params.add("%" + name + "%");
            }

            System.out.print("nhập department_id cần tìm (0 nếu không lọc): ");
            int departmentId = scanner.nextInt();
            if (departmentId != 0) {
                sql.append(" AND department_id = ?");
                params.add(departmentId);
            }

            System.out.print("nhập mức lương tối thiểu (0 nếu không lọc): ");
            double salaryMin = scanner.nextDouble();
            if (salaryMin > 0) {
                sql.append(" AND salary >= ?");
                params.add(salaryMin);
            }

            PreparedStatement pstmt = conn.prepareStatement(sql.toString());

            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(i + 1, params.get(i));
            }

            ResultSet rs = pstmt.executeQuery();

            System.out.println("\n--- kết quả tìm kiếm ---");
            while (rs.next()) {
                System.out.println("id: " + rs.getInt("id"));
                System.out.println("tên: " + rs.getString("name"));
                System.out.println("lương: " + rs.getDouble("salary"));
                System.out.println("phòng ban: " + rs.getInt("department_id"));
                System.out.println("------------------------");
            }

            rs.close();
            pstmt.close();
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

