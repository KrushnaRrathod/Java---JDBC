import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Hospital {
    private int bed_id;
    private String patent_name;
    private int patent_contect_no;
    private String patent_decis;

    public Hospital(int bed_id ,String patent_name,int patent_contect_no, String patent_decis){
        this.bed_id = bed_id;
        this.patent_name = patent_name;
        this.patent_contect_no = patent_contect_no;
        this.patent_decis = patent_decis;
    }

    public void insertOrUpdatePatent(){
        try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "root")){
            String insert_query = "Insert into hospital(bed_id,patent_name,patent_contect_no,patent_decis) value(?,?,?,?)"+
                            "on duplicate key update bed_id = values(bed_id)";

            PreparedStatement pstm = con.prepareStatement(insert_query);

            pstm.setInt(1, bed_id);
            pstm.setString(2, patent_name);
            pstm.setInt(3, patent_contect_no);
            pstm.setString(4, patent_decis);

            pstm.executeUpdate();

            con.close();
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public void showMemberDetails(){
        try(Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "rathod148797")){
            String select_query = "select * from hospital where bed_id = ?";

            PreparedStatement pstm = con.prepareStatement(select_query);

            pstm.setInt(1, bed_id);

            ResultSet set = pstm.executeQuery();

            if(set.next()){
                int bed_id = set.getInt("bed_id");
                String patent_name = set.getString("patent_name");
                int patent_contect_no = set.getInt("patent_contect_no");
                String patent_decis = set.getString("patent_decis");

                System.out.println("Bed Id : " + bed_id);
                System.out.println("Patent Name : " + patent_name);
                System.out.println("Patent Contect No : " + patent_contect_no);
                System.out.println("Patent Decis : " + patent_decis);

                con.close();
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Hospital patent1 = new Hospital(01, "Ramu", 123456, "Meleria");
        patent1.insertOrUpdatePatent();
        patent1.showMemberDetails();

    }
}
