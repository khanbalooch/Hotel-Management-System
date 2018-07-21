import java.sql.*;
import javax.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import  java.sql.Statement;


class Room 
{
	int status;
	int floor;
	int RoomNo;
	String create =
"create table Room(RoomNo int not  null AUTO_INCREMENT primary key,RoomType int ,isReserved int,FOREIGN KEY (RoomType) REFERENCES RoomTypes(typeId))";
	
	public Room(Statement stmt,Connection conn) throws SQLException
	{
		
		stmt.executeUpdate(create);
		System.out.println("create table Room: Successfull");
		add(conn);
	
	}
	
	public ResultSet Searchby(Statement stmt)
	{
		ResultSet rs=null;
		try{
		 rs = stmt.executeQuery("SELECT RoomNo from Room where isReserved = 0 or isReserved = null");
		}catch(SQLException SQLE)
		{
			System.out.println(SQLE);
		}
		return rs;
	}
	
	public void add(Connection conn)
	{
		int rmn=1,type=1111,st=0;
		String Query = "insert into Room values(?,?,?)";
		
		
		try
		{
			PreparedStatement pstmt = conn.prepareStatement(Query);
			for(int i=0; i<50; i++){
			pstmt.setInt(1,rmn);
			pstmt.setInt(2,type);
			pstmt.setInt(3,st);
			pstmt.executeUpdate();
			rmn++;
						}
			
		}catch(SQLException SQLE)
		{
			System.out.println(SQLE);
		}	
		
	}



}

	
