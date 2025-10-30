// AttendanceServlet.java
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class AttendanceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String studentId = request.getParameter("studentId");
        String status = request.getParameter("status");
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/dbname", "username", "password");
            PreparedStatement ps = con.prepareStatement(
                "INSERT INTO attendance(student_id, status) VALUES (?, ?)");
            ps.setString(1, studentId);
            ps.setString(2, status);
            ps.executeUpdate();
            con.close();
            response.getWriter().println("Attendance marked!");
        } catch (Exception e) {
            response.getWriter().println("Error: " + e.getMessage());
        }
    }
}
