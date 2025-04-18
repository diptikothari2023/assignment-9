
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDAO {

    // Adds a new student record into the database.
    public void addStudent(Student student) {
        String sql = "INSERT INTO students (name, age, email) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, student.getName());
            pstmt.setInt(2, student.getAge());
            pstmt.setString(3, student.getEmail());
            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new student was inserted successfully!");
            }
        } catch (SQLException e) {
            System.out.println("Error while adding student: " + e.getMessage());
        }
    }

    // Updates an existing student record in the database.
    public void updateStudent(Student student) {
        String sql = "UPDATE students SET name=?, age=?, email=? WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, student.getName());
            pstmt.setInt(2, student.getAge());
            pstmt.setString(3, student.getEmail());
            pstmt.setInt(4, student.getId());
            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Student record updated successfully!");
            } else {
                System.out.println("Student not found with ID: " + student.getId());
            }
        } catch (SQLException e) {
            System.out.println("Error while updating student: " + e.getMessage());
        }
    }

    // Deletes a student record from the database based on the student ID.
    public void deleteStudent(int studentId) {
        String sql = "DELETE FROM students WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, studentId);
            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Student deleted successfully!");
            } else {
                System.out.println("Student not found with ID: " + studentId);
            }
        } catch (SQLException e) {
            System.out.println("Error while deleting student: " + e.getMessage());
        }
    }

    // Retrieves and lists all student records from the database.
    public void listAllStudents() {
        String sql = "SELECT * FROM students";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            System.out.println("------ Student Records ------");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                String email = rs.getString("email");
                System.out.println("ID: " + id + " | Name: " + name + " | Age: " + age + " | Email: " + email);
            }
        } catch (SQLException e) {
            System.out.println("Error while retrieving students: " + e.getMessage());
        }
    }
}
