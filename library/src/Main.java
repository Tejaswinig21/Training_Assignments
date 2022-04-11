import java.sql.*;
import java.util.Scanner;

public class Main extends Library{
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        String c=sc.next();
        Connection connection=null;
        Statement statement=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/Library","root","Teja2000@");
            statement=connection.createStatement();
            switch (c){
                case "insert":
                    int id=sc.nextInt();
                    sc.nextLine();
                    String name=sc.nextLine();
                    String Auth=sc.nextLine();
                    PreparedStatement preparedStatement=connection.prepareStatement("insert into Book values(?,?,?)");
                    preparedStatement.setInt(1,id);
                    preparedStatement.setString(2,name);
                    preparedStatement.setString(3,Auth);
                    preparedStatement.executeUpdate();
                    break;
                case "delete":
                    int i=sc.nextInt();
                    preparedStatement=connection.prepareStatement("delete from Book where id=?");
                    preparedStatement.setInt(1,i);
                    preparedStatement.executeUpdate();
                    break;
                case "search":
                            int x=sc.nextInt();
                            ResultSet resultSet=statement.executeQuery("select * from Book where id="+x);
                            while (resultSet.next()){
                                System.out.println("ID "+resultSet.getInt(1));
                                System.out.println("Name "+resultSet.getString(2));
                                System.out.println("Author "+resultSet.getString(3));
                            }
                    break;
                case "Listall":
                    ResultSet result=statement.executeQuery("select * from Book");
                    while (result.next()){
                        System.out.println("ID "+result.getInt(1));
                        System.out.println("Name "+result.getString(2));
                        System.out.println("Author "+result.getString(3));
                    }
                default:
                    System.out.println("Over");
            }


        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try {
                if (statement!=null){
                    statement.close();
                }
            }
            catch (SQLException e){
                e.printStackTrace();
            }
            try {
                if (connection!=null){
                    connection.close();
                }
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
