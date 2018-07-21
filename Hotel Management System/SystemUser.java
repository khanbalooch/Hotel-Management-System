import java.sql.*;
import javax.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import  java.sql.Statement;


class SystemUser
{

	String Uname;
	int uId;
	int Age;
	String Gender;
	String CNIC;
	String Email;
	private String password;
String create =
 "create table SystemUser(uId int not null primary key,Uname varchar(30), Age int,Gender ENUM('M','F'),CNIC varchar(13),Email varchar(35),password varchar(4) )";
	
	SystemUser(Statement stmt) throws SQLException
	{
	
		stmt.executeUpdate(create);
		System.out.println("create table SystemUser: Successfull");
		stmt.executeUpdate("insert into SystemUser values(131821,'Manager',20,'M','3620349431775','khan.balooch@outlook.com',1234)");
	
	}
	
	public boolean confirmUser(String Name,String Pwd,Statement stmt) 
	{
	
		boolean result =false;
		String Query = new String("select * from SystemUser");
		try
		{
		ResultSet rs  = stmt.executeQuery(Query);
		
		while(rs.next() )
		{
		
			if(Name.equals(rs.getString("Uname")) &&  Pwd.equals(Integer.toString(rs.getInt("password"))))
			{
				result =true;
			}				
		}
		}catch(SQLException SQLE)
		{
			System.out.println("Wrong Query");
		}
	return result;
	}
	
	public void update(String cp,String np,Connection conn) 
	{
		try
		{
			
			String Query= "UPDATE SystemUser SET password = ? where password =  ?;";
			PreparedStatement pstmt = conn.prepareStatement(Query);
			pstmt.setString(1,np);
			pstmt.setString(2,cp);
			pstmt.executeUpdate();
		}catch(SQLException SQLE)
		{
			System.out.println(SQLE);
		}
	}
	public ResultSet getUser(String cp,Statement stmt) throws SQLException
	{
	
		ResultSet rs= stmt.executeQuery("SELECT * from SystemUser where password = " +cp+ ";" );
		return rs;
	}

}

