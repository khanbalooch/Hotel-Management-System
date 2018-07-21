import java.sql.*;
import java.util.*;

class Conection
{

	private final String userName = "root";
	private final String password = "1khurasan90";
	private final String serverName = "localhost";
	private final String DBName = "khan";
	private final int portNumber = 3306;
	private final  String tableName	= "someTable";
	private Connection conn = null;
	private Statement stmt = null;
		
		
	
	public Statement getConnection() throws SQLException 
	{
	
		
		Properties connectionProps = new Properties();
		connectionProps.put("user",this.userName);
		connectionProps.put("password",this.password);
		System.out.println("Connecting to database...");
		conn = DriverManager.getConnection("jdbc:mysql://"+this.serverName+":"+this.portNumber+"/"+this.DBName,connectionProps);
		System.out.println("Connected to Database successfully..!");
		stmt = conn.createStatement();
		
		return stmt;
	
	}
	public Connection getConnection(int x) throws SQLException 
	{
		
		return conn;
	
	}

}
