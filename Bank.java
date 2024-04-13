import java.sql.Connection;
import java.sql.DriverManager;

public class Bank {

    public void Bank(float account, float holder, float balance){

    }

    public static void main(String[] args) {

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Bank", "root", "root");

            if(con.isClosed()){
                System.out.println("Database is not connected");
            }else{
                System.out.println("Database is connected");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
