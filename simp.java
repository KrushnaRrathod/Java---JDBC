import java.sql.Statement;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class simp {
    public static void main(String[] args) throws Exception {
        try{
            String url = "jdbc:mysql://localhost:3306/parth";
            String user = "root";
            String password = "root";

            Connection con = DriverManager.getConnection(url, user, password);

            // String q = "create table info(No int(20) primary key auto_increment, Name varchar(200) not null)";

            Statement stm = con.createStatement();

            // stm.executeUpdate(q);

            String q = "insert into info(No, Name) values(?,?)";

            PreparedStatement pstm = con.prepareStatement(q);

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Enter No");
            String no = br.readLine();

            System.out.println("Enter Name");
            String name = br.readLine();

            pstm.setString(1, no);
            pstm.setString(2, name);

            pstm.executeUpdate();

            String q1 = "select * from info";

            ResultSet set = stm.executeQuery(q1);

            while(set.next()){
                String no1 = set.getString(1);
                String name1 = set.getString(2);

                System.out.println(no1);
                System.out.println(name1);
            }

            con.close();

            if(con.isClosed()){
                System.out.println("not Connected");
            }else{
                System.out.println("Connected");
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
