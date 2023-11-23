//connect database using exceptional handling
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
class Main{
    public static void main(String [] args) throws ClassNotFoundException{
        //database Url
        String url="jdbc:mysql://localhost:3306/mydatabase";
        //credential information
        String username="root";
        String password="Abh@y2204";
        String Query="select * from employee";
        try{
            //using exception
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver Successfully Loaded");
        }
        catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        try{
            Connection connect=DriverManager.getConnection(url,username,password);
            System.out.println("Connected to the database");
            System.out.println(connect);
            Statement statement=connect.createStatement(); //createStatemnet method does not take any arguments
            ResultSet resultset=statement.executeQuery(Query);
            while(resultset.next()){
                //here we can retrieve the data by using getInt (),getString() and getDouble() methods
                //here you can change the local variable name(it,name,job_title,salary) but not the field name (columnLabel) otherwise you dont acees the data
                int id=resultset.getInt("id");
                String name=resultset.getString("name");
                String job_title=resultset.getString("job_title");
                Double salary=resultset.getDouble("salary");
                System.out.println("-----------------------------------------");
                System.out.println("ID:"+ id);
                System.out.println("Name of the person:"+ name);
                System.out.println("Proffession:"+ job_title);
                System.out.println("Package:"+ salary);
            }
            resultset.close();
            statement.close();
            connect.close();
            System.out.println("Connection closed successfully");
        }
        catch(SQLException e){
            System.err.println("Connection Failed:" + e.getMessage());
        }
    }
}