import java.sql.Statement;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.sql.DriverManager;
class Main{
    public static void main(String [] args) throws ClassNotFoundException{
        String url="jdbc:mysql://localhost:3306/mydatabase";
        String username="root";
        String password="Abh@y2204";
        String query="INSERT INTO employee(id,name,job_title,salary) values(23,'kiran','full stack developer',75000); ";
        try{
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        try{
            Connection c=DriverManager.getConnection(url,username,password);
            System.out.println("Connection Done:)");
            Statement st=c.createStatement();
            int rowsaffected =st.executeUpdate(query); //if row is inserted than show 1 row affected otherwise 0 rows affected
            if(rowsaffected>0){
                System.out.println("Insertion succesfully" + rowsaffected +"row inserted ");
            }
            else{
                System.out.println("Insertion Failed");
            }
            st.close();
            c.close();
            System.out.println("Connection closed successfully");
        }
        catch(SQLException s){
            System.out.println(s.getMessage());
        }
    }
}
