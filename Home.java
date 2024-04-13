import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Home {
    private int adhar;
    private String name;
    private int age;
    private String gender;

    public Home(int adhar, String name, int age, String gender) {
        this.adhar = adhar;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public void insertOrUpdateMember() {
        String url = "jdbc:mysql://localhost/db";
        String user = "root";
        String password = "root";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String insertQuery = "INSERT INTO home (member_adhar_id, member_name, member_age, member_gender) VALUES (?, ?, ?, ?) " +
                    "ON DUPLICATE KEY UPDATE member_adhar_id = VALUES(member_adhar_id)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertQuery);
            preparedStatement.setInt(1, adhar);
            preparedStatement.setString(2, name);
            preparedStatement.setInt(3, age);
            preparedStatement.setString(4, gender);

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showMemberDetail() {
        String url = "jdbc:mysql://localhost/db";
        String user = "root";
        String password = "root";

        try (Connection connection = DriverManager.getConnection(url, user, password)) {
            String selectQuery = "SELECT * FROM home WHERE member_adhar_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(selectQuery);
            preparedStatement.setInt(1, adhar);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int memberId = resultSet.getInt("member_adhar_id");
                String memberName = resultSet.getString("member_name");
                int memberAge = resultSet.getInt("member_age");
                String memberGender = resultSet.getString("member_gender");
                System.out.println("Member ID: " + memberId);
                System.out.println("Name: " + memberName);
                System.out.println("Age: " + memberAge);
                System.out.println("Gender: " + memberGender);
            } else {
                System.out.println("Member not found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Home member1 = new Home(000, "Krushna", 19, "Male");
        member1.insertOrUpdateMember();
        member1.showMemberDetail();
    }
}
