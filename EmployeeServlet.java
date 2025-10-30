// EmployeeServlet.java
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class EmployeeServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        PrintWriter out = response.getWriter();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/dbname", "username", "password");
            String sql = (id != null && !id.isEmpty())
                ? "SELECT * FROM employee WHERE emp_id=?"
                : "SELECT * FROM employee";
            PreparedStatement ps = con.prepareStatement(sql);
            if (id != null && !id.isEmpty()) ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            out.println("<table border='1'><tr><th>ID</th><th>Name</th><th>Department</th></tr>");
            while (rs.next()) {
                out.println("<tr><td>"+rs.getInt(1)+"</td><td>"+rs.getString(2)+"</td><td>"+rs.getString(3)+"</td></tr>");
            }
            out.println("</table>");
            rs.close(); ps.close(); con.close();
        } catch (Exception e) {
            out.println("Error: " + e.getMessage());
        }
    }
}
