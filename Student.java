import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class CreateUpdate {
    private int id;
    private String name;
    private int age;

    public CreateUpdate(int id,String name,int age){
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public void insertQuery(){
        try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/krushna", "root", "root")){
            String insert_query = "insert into curd(Id,Name,Age) values(?,?,?)" + "on duplicate key update Id = values(Id)";

            PreparedStatement pstm = con.prepareStatement(insert_query);

            pstm.setInt(1, id);
            pstm.setString(2, name);
            pstm.setInt(3, age);

            pstm.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void updateQuery(){
        try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/krushna", "root", "root")){
            String update_query = "update curd set name = ?, age = ? where id = ?";

            PreparedStatement pstm = con.prepareStatement(update_query);

            pstm.setString(1, name);
            pstm.setInt(2, age);
            pstm.setInt(3, id);

            pstm.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void readQuery(){
        try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/krushna", "root", "root")){
            String read_query = "select * from curd";

            PreparedStatement pstm = con.prepareStatement(read_query);

            ResultSet set = pstm.executeQuery();

            if(set.next()){
                int id = set.getInt("id");
                String name = set.getString("name");
                int age = set.getInt("age");

                System.out.println("Id : "+id);
                System.out.println("Name : "+name);
                System.out.println("Age : "+age);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void deleteQuery(){
        try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/krushna", "root", "root")){
            String delete_query = "delete from curd where Id = ?";

            PreparedStatement pstm = con.prepareStatement(delete_query);

            pstm.setInt(1,id);

            pstm.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
}

public class Student extends CreateUpdate{

    public Student(int id, String name, int age) {
        super(id, name, age);
    }
    public static void main(String[] args) {

        Student student = new Student(1,"Krushna",18);

        student.deleteQuery();
    }
    
}