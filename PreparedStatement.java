import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
class Main{
    public static void main(String [] args) throws ClassNotFoundException{
        String url="jdbc:mysql://localhost:3306/mydatabase";
        String username="root";
        String password="Abh@y2204";
        /*query for fetching the data
        String query="select* from employee where name=? ;"; */
        //insert query
        String query="INSERT INTO employee (id,name,job_title,salary) VALUES (?,?,?,?);";

        try{
            Class.forName("com.mysql.jdbc.Driver");
        }
       catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
       }
        try{
            Connection connect=DriverManager.getConnection(url,username,password);
            System.out.println("System succesfully connected to database");
            //now you make a prepared statement in place of statement
            //in normal statement you used the method createStatement()  here you use preparedStatement()
            PreparedStatement prest =connect.prepareStatement(query);
            prest.setInt(1,72);
            prest.setString(2,"Rishika");
            prest.setString(3,"Hasne wali ladki");
            prest.setDouble(4,100000);

            int roweffected =prest.executeUpdate();
            if(roweffected>0){
                System.out.println(roweffected +"row added");
            }else{
                System.out.println("noting inserted");
            }
            //Resultset is used for displaying the data
           /* ResultSet rs=prest.executeQuery();
            while(rs.next()){
                int id=rs.getInt("id");
                String name=rs.getString("name");
                String  job_title=rs.getString("job_title");
                double salary=rs.getDouble("salary");
                System.out.println("------------------------------");
                System.out.println("id:" + id);
                System.out.println("Name:" + name);
                System.out.println("Job Title:" + job_title);
                System.out.println("Income:" + salary);
            }
            rs.close(); */
            prest.close();
            connect.close();
            System.out.println("System successfully closed the connection");
        }
        catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }
}
