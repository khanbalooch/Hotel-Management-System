import java.sql.*;
import javax.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import  java.sql.Statement;


class Reservation
{

	int ReserveId;
	int RoomNo;
	int cId;
	int NO_OF_GUESTS;
	String Date;
String create = 
"create table Reservation(ReserveId int not null AUTO_INCREMENT primary key,RoomNo int,FOREIGN KEY (RoomNo) REFERENCES Room(RoomNO) ,cId int,FOREIGN KEY (cId) REFERENCES Customer(cId) ,Nguests int  )"; 
	
	
	public Reservation(Statement stmt) throws SQLException
	{
	
		stmt.executeUpdate(create);
		System.out.println("create table Reservation: Successfull");
	
	} 
	
	public void insert(String room,int sid,int nogs,Statement stmt)
	{
	int rid =0;
	int rmn = Integer.parseInt(room);
	try{
		stmt.executeUpdate("insert into Reservation valeus("+rid+","+rmn+","+sid+","+nogs+ ")");
		}catch(SQLException SQLE)
		{
			System.out.println(SQLE);
		}
	}

}
