package com.spring.config;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileController {
   
	@GetMapping("/test")
	public String getTest() {
		return "vika mishra";
	}
	
	@PostMapping("/showfile")
	public String   showFile(@RequestParam("file") List<MultipartFile> fileList) throws IOException {
//		System.out.println(fileList.get(0).getOriginalFilename());
//		System.out.println(fileList.get(0).getSize());
		InputStream inputStream = fileList.get(0).getInputStream();
        String text = IOUtils.toString(inputStream); 
        System.out.println(text);
//        String updateSQL = "insert into DummyTable(id, fileName) values (?, ?)";
        try {
			
        	Class.forName("com.mysql.cj.jdbc.Driver");/*registration for the driver so that it could return connection of the JDBC API*/
        	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb","root","Krsna@007");//user name and password are the second last parameter are included here
        	/*Statement stmt=con.createStatement();
        	prepareStatement("insert into DummyTable values(8,'vikas')");
        	stmt.execute("insert into DummyTable values(8,'vikas')");
        	File file = new File("new.txt");
        	FileInputStream inputStream = new FileInputStream(file);
        	Statement stmt=con.createStatement();
        	  stmt.executeUpdate("insert into DummyTable values(1,'vikas')");*/
        	File f1=new File(fileList.get(0).getOriginalFilename());
        	FileInputStream fileInputStream=new FileInputStream(f1);
        	PreparedStatement pstmt = con.prepareStatement("insert into DummyTable(id, name, file_data) values(?, ?, ?)");
            pstmt.setInt(1, 2);
            pstmt.setString(2, fileList.get(0).getOriginalFilename());
            pstmt.setBlob(3, fileInputStream,(int)f1.length());
            pstmt.execute();
            System.out.println(pstmt.execute());
        	/*ResultSet rs=stmt.executeQuery("select * from Persons");
			while (rs.next()) {
				System.out.println(rs.getInt(1) + " " + rs.getString(2));
			}
        	
        	File file = new File(fileList.get(0).getOriginalFilename());
        	FileInputStream input = new FileInputStream(file);
        	pstmt.setInt(2, 12);
        	pstmt.setString(2,fileList.get(0).getOriginalFilename());*/
      
        } catch (Exception e) {
			// TODO: handle exception
		}
		
		/*try{  
		 *
		 * //step1 load the driver class
		 * Class.forName("oracle.jdbc.driver.OracleDriver");
		 * 
		 * //step2 create the connection object Connection
		 * con=DriverManager.getConnection(
		 * "oldbsvnswdev00.syscon.ca:1521:xe","oms_objects","oms_objects");
		 * 
		 * //step3 create the statement object Statement stmt=con.createStatement();
		 * 
		 * //step4 execute query ResultSet
		 * rs=stmt.executeQuery("select * from dummytable"); while(rs.next())
		 * System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getBlob(3));
		 * 
		 * //step5 close the connection object con.close();
		 * 
		 * }catch(Exception e){ System.out.println(e);}
		 */
	 
        return "Vikas";  
	}
	
	
	
	@PostMapping(value = "/fetchfile")
	public String   fetchFile() throws IOException
	{
		try{  
			 Class.forName("com.mysql.cj.jdbc.Driver");  
			 Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb","root","Krsna@007");              
			
			 FileOutputStream out= new FileOutputStream(new File("E:\\Repo\\ui-components\\rohit.txt")) ;
			 PreparedStatement pstmt=con.prepareStatement("select * from dummyTable");
			 ResultSet rs=pstmt.executeQuery();
			 while(rs.next()) {
		         Blob blob = rs.getBlob("file_data");
		         byte [] bytes = blob.getBytes(1, (int)blob.length());
		         for(int i=0; i<bytes.length;i++) {
		            System.out.print((char)bytes[i]);
		            out.write((char)bytes[i]);	
		         }
			 }
			
//			 byte[] buffer = new byte[4096];
//             while ((bytesRead = .read(buffer)) != -1) {
//                 outputStream.write(buffer, 0, bytesRead);
//             }
			 
//			 System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.get);
//			 byte[] byteArray = rs.getBytes(3);  
//			 InputStream r = c.getBinaryStream();
//			 out.write(byteArray);
//				byte[] buf = new byte[1024];
//				int len = 0;
//				while ((len = r.read(buf)) != -1) {
//					out.write(buf, 0, len);
//				}
//			 String text = IOUtils.toString(r); 
//		     System.out.println((text));           
//			 int i;  
//			 while((i=r.read())!=-1)  
//			 fw.write((char)i);               
//			 fw.close();  
//			 con.close(); 
		
		
		}
			 catch (Exception e) {

			System.out.println(e);
		}
		return 	"Mohit";
	}
 
}
