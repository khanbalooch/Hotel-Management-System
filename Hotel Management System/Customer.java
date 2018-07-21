import java.sql.*;
import javax.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import  java.sql.Statement;


class Customer
{

	String Cname;
	int cId;
	int Age;
	String Gender;
	String CNIC;
	String Email;
String create =
 "create table Customer(cId int not null primary key,Cname varchar(30), Age int,Gender ENUM('M','F'),CNIC varchar(13),Email varchar(35) )";
	
	Customer(Statement stmt) throws SQLException
	{
	
		stmt.executeUpdate(create);
		System.out.println("create table Customer: Successfull");
	
	}
	
	public void insert(String CName,String cId,String Age,String Gender,String CNIC,String Email,Statement stmt) throws SQLException
	{
		int age = Integer.parseInt(Age);
		int cid = Integer.parseInt(cId);
		stmt.executeUpdate( "INSERT into Customer values(" +cId+",'"+CName+"' ,"+age+" ,'" +Gender+ "','" +CNIC+ "','" +Email+ "')" );
	}
	public void update(String CName,String cId,String Age,String Gender,String CNIC,String Email,Statement stmt,int sid) throws SQLException
	{
		int age = Integer.parseInt(Age);
		int cid = Integer.parseInt(cId);
		stmt.executeUpdate("DELETE from Customer where cId = " +sid+ ";");
		insert(CName,cId,Age,Gender,CNIC,Email,stmt);
	}
	
	public ResultSet search(String sid,Statement stmt) throws SQLException
	{
		ResultSet rs = stmt.executeQuery("SELECT * FROM Customer WHERE cId =  "+sid+";");
		
		return rs;	
	} 
	
	public ResultSet search(Statement stmt) throws SQLException
	{
		ResultSet rs = stmt.executeQuery("SELECT * FROM Customer;" );
		
		return rs;	
	} 
	
	public void delete(String sid,Statement stmt)
	{
		
		int id = Integer.parseInt(sid);
		try
		{
			stmt.executeUpdate("DELETE FROM Customer where cId = " +id+ ";" );
	
		}catch(SQLException SQLE)
		{
			
			System.out.println(SQLE);
		}
	}
}	
	
