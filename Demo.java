import java.sql.Statement;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Demo {
    public static void main(String[] args) throws Exception{
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee", "root", "root");

            // String q = "create table employes(E_Id int(20) primary key auto_increment, E_Name varchar(200) not null)";

            Statement stm = con.createStatement();

            // stm.executeUpdate(q);

            // String q = "insert into employes(E_Id,E_Name) values(?,?)";
            String q = "update employes set E_Name=? where E_Id=?";

            PreparedStatement pstm = con.prepareStatement(q);

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Enter Id of employee : ");
            String id = br.readLine();

            System.out.println("Enter the name of employee : ");
            String name = br.readLine();

            pstm.setString(1, name);
            pstm.setString(2, id);

            pstm.executeUpdate();

            String q1 = "select * from employes";

            ResultSet set = stm.executeQuery(q1);

            while(set.next()){
                String get_id = set.getString(1);
                String get_name = set.getString(2);
                System.out.println(get_id);
                System.out.println(get_name);
            }

            con.close();
 
            if(con.isClosed()){
                System.out.println("Data base is not connected");
            }else{
                System.out.println("Data base is connected");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
