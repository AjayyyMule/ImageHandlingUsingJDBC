
/*

// Image insertation into Databse

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws IOException {

        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "root";
        String password = "root";
        String image_path = "C:\\Users\\Admin\\Desktop\\IMG20240506182739.jpg";
        String query = "INSERT INTO image_table(image_data) VALUES (?);";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivers loaded successfully");
        }catch(ClassNotFoundException cnfe){
            System.out.println("Drivers failed to load");
        }

        try{
            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("Connection established  successfully!!!");

            FileInputStream fis = new FileInputStream(image_path);
            byte[] imageData = new byte[fis.available()];
            fis.read(imageData);

            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setBytes(1, imageData);

            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows>0){
                System.out.println("Image inserted into DataBase successfully!!!");
            }else{
                System.out.println("Image insertion failed ");
            }


        }catch(SQLException sqle){
            System.out.println("Connection failed to establish");
            System.out.println(sqle.getMessage());
        }catch(FileNotFoundException fnf){
            System.out.println("File Not Found");
            System.out.println(fnf.getMessage());
        }catch(RuntimeException rte){
            System.out.println(rte.getMessage());
        }




        }
}

 */

// Copying image from Database to Folder

//import java.xml.transform.Result;
import java.io.*;
import java.sql.*;

public class Main {
    public static void main(String[] args) throws IOException {

        String url = "jdbc:mysql://localhost:3306/mydatabase";
        String username = "root";
        String password = "root";
        String folder_path = "E:\\AJAY\\Java Projects\\";
        String query = "SELECT image_data FROM image_table WHERE image_id =(?);";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Drivers loaded successfully");
        }catch(ClassNotFoundException cnfe){
            System.out.println("Drivers failed to load");
        }

        try{
            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("Connection established  successfully!!!");

            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1,1);
            ResultSet rs = preparedStatement.executeQuery();

            if(rs.next()){

                byte [] image_data = rs.getBytes("image_data");
                String image_path = folder_path+"extractedimage.jpg";

                OutputStream outputStream = new FileOutputStream(image_path);
                outputStream.write(image_data);

            }else {
                System.out.println("Image not found ");
            }


        }catch(SQLException sqle){
            System.out.println("Connection failed to establish");
            System.out.println(sqle.getMessage());
        }catch(FileNotFoundException fnf){
            System.out.println("File Not Found");
            System.out.println(fnf.getMessage());
        }catch(RuntimeException rte){
            System.out.println(rte.getMessage());
        }




    }
}