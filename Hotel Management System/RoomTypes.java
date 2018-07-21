import java.sql.*;
import javax.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import  java.sql.Statement;

class RoomTypes
{

	int typeId;//primry key
	String Tname;
	int NO_OF_ROOMS;
	int price;//not null
	String description;
	String  create  = "create table RoomTypes(typeId int not null primary key,Tname varchar(20),NO_OF_ROOMS int, price int not null,description varchar(30) )";

	public RoomTypes(){}
	public RoomTypes(Statement stmt) throws SQLException //Connection conn)
	{

		stmt.executeUpdate(create);
		System.out.println("create table RoomTypes: Successfull");
		insert("Superior Room","1111","2","3000","peacefull",stmt);
	}
	
	public void insert(String RTName,String RTId,String NOR,String price,String Description,Statement stmt) throws SQLException
	{
		int Price = Integer.parseInt(price);
		int rid = Integer.parseInt(RTId);
		int no_of_rooms = Integer.parseInt(NOR);
	stmt.executeUpdate( "INSERT into RoomTypes values(" +rid+",'"+RTName+"' ,"+no_of_rooms+" ," +price+ ",'" +Description+ "')" );
	}
	
}
/*class Room 
{
	int status;
	int price;
	int floor;
	int RoomNo;
	String create = "create table student(";
	
	Room()
	{
	System.out.println("called");
	}
	Room(int RoomNo,int price,int floor, int status)
	{
		 
		 status = 0;
		 this.status = status;
		 this.price = price;
		 this.floor = floor;
		 this.RoomNo = RoomNo;
	}
	public String toString()
	{
		String rno=Integer.toString(RoomNo),pr=Integer.toString(price),st=getStatusString();	
		String x =rno+"   Rs:"+pr+"/24hr   "+st;
		return x;
	}
	public String getStatusString()
	{
	
		String x ="";
		if(this.status == 1)
		{
			x = "Reserved";//System.out.println("Room No "+RoomNo+ " is reserved ");
		}
		if(this.status == 2)
		{
			x = "Checked-In";//System.out.println("Room No "+RoomNo+ " is checked-in ");
		}
		if(this.status == 0)
		{
			x= "Empty";//System.out.println("Room No "+RoomNo+ " is empty ");
		}
		return x;
	}
	
	public int getStatus()
	{
		int temp = -1;
		if(this.status == 1)
		{
			temp = 1;
			
		}else
		{	
			if(this.status == 2)
			{
				temp = 2;
			}else
			{
				if(this.status == 0)
				{
					temp = 0;		
				}
			}
		}		
		return temp;
	}
	
	public  void Reserve()
	{
		status = 1;
		System.out.println("Room Reserved Successfully");
	}

	public void Check_in()
	{
		status = 2;
	}
	public void Check_out()
	{
		status = 0;
	}
}*/
