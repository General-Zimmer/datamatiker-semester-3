import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
public class TestSQL {
    public static void main(String[] args) {
// TODO Auto-generated method stub
        try {
            System.out.println("Program startet");
            Connection minConnection;
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            minConnection =
                    DriverManager.getConnection("jdbc:sqlserver://localhost;" +
                            "databaseName=L17;user=sa;password=M1a3g2e8;");
            Statement stmt =
                    minConnection.createStatement();
            ResultSet res=stmt.executeQuery("select * from konto");
            while (res.next()) {
                System.out.println("Konto " + res.getInt(1) +
                        " har saldo " + res.getInt(2) + " og ejer " + res.getString(3) );
            }
            if (stmt != null)
                stmt.close();
            if (minConnection != null)
                minConnection.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}