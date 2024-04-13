import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class Employee {
    public static void main(String[] args) {
        try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee", "root", "root");

            Scanner sc = new Scanner(System.in);

            System.out.println("Insert yes | no");
            String insert_result = sc.nextLine();

            PreparedStatement pstm;

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            if(insert_result == "yes"){
                String insert_q = "insert into employes(E_Id,E_Name) values(?,?)";

                pstm = con.prepareStatement(insert_q);

                System.out.println("Enter id : ");
                String i_id = br.readLine();

                System.out.println("Enter name : ");
                String i_name = br.readLine();

                pstm.setString(1, i_id);
                pstm.setString(2, i_name);

                pstm.executeUpdate();

            }

            System.out.println("Update yes | no");
            String update_result = sc.nextLine();
            
            if(update_result == "yes"){
                String update_q = "update employes E_Name = ? where E_Id = ?";

                pstm = con.prepareStatement(update_q);

                System.out.println("Enter id to update : ");
                String u_id = sc.nextLine();

                System.out.println("Enter name to update : ");
                String u_name = sc.nextLine();

                pstm.setString(1, u_name);
                pstm.setString(2, u_id);

                pstm.executeUpdate();
            }


            if(con.isClosed()){
                System.out.println("Database is not connected");
            }else{
                System.out.println("Database is connected");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
