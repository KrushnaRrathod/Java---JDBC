import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HomeDetail {
    private int adhar;
    private String name;
    private int age;
    private String gender;

    public HomeDetail(int adhar, String name, int age, String gender){
        this.adhar = adhar;
        this.name = name;
        this.age = age;
        this.gender = gender;
    }

    public void insertOrUpdateMember(){

        try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "root");){
            String insertQuery = "INSERT INTO home (member_adhar_id, member_name, member_age, member_gender) VALUES (?, ?, ?, ?) " +
                    "ON DUPLICATE KEY UPDATE member_adhar_id = VALUES(member_adhar_id)";

            PreparedStatement pstm = con.prepareStatement(insertQuery);

            pstm.setInt(1, adhar);
            pstm.setString(2, name);
            pstm.setInt(3, age);
            pstm.setString(4, gender);

            pstm.executeUpdate();

        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void showMemberDetails(){
        try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "rathod148797");){
            String select_query = "select * from home where member_adhar_id = ?";

            PreparedStatement pstm = con.prepareStatement(select_query);

            pstm.setInt(1, adhar);

            ResultSet set = pstm.executeQuery();

            if(set.next()){
                int memberId = set.getInt("member_adhar_id");
                String memberName = set.getString("member_name");
                int memberAge = set.getInt("member_age");
                String memberGender = set.getString("member_gender");
                System.out.println("Member ID: " + memberId);
                System.out.println("Name: " + memberName);
                System.out.println("Age: " + memberAge);
                System.out.println("Gender: " + memberGender);
            }else{
                System.out.println("Member is not found");
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        HomeDetail member1 = new HomeDetail(98747, "Divya Rathod", 16, "Female");
        member1.insertOrUpdateMember();
        member1.showMemberDetails(); 
    }
}
