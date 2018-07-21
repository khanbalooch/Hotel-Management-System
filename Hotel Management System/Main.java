import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.lang.*;
import java.util.*;
import java.sql.*;
import javax.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import  java.sql.Statement;
import javax.swing.*;

interface Functions
{

public void addNewCustomer();
public void generateReports();
public void searchCustomer(String operation);
public void updateCustomer();
public void deleteCustomer();
public void addRoomType();
public void deleteRoomType();
public void setting(); 

}

//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

class PopUp extends JFrame implements Runnable
{

	public PopUp(String Message,String Operation)
	{ 
     		super(Operation);
     		JLabel text = new JLabel(Message);
		add(text);
		setSize(300,75);
		setLocation(300,250);
		show();
	}
	
	public void run() 
	{
	
		try
		{
		Thread.sleep(700);
		
		dispose();
		}catch(Exception E)
		{
			System.out.println(E);
		}
	
	}


}


//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

class Main extends JFrame implements ActionListener,Functions
{

		
	Conection conn;
	Connection connection;
	Statement stmt = null;
	ResultSet rs = null;
	RoomTypes roomTypes;// = new RoomTypes(stmt);
	Room room;// = new Room(stmt);
	Customer customer;// = new Customer(stmt);
	SystemUser systemUser;// = new SystemUser(stmt); 
	Reservation reservation;// = new Reservation(stmt);
		
	
	JPanel CPanel,NPanel,EPanel,SPanel,WPanel;
	
	static boolean LogIn = false;

//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	public void createDatabase() throws SQLException
	{
	
		conn  = new Conection();
		stmt = conn.getConnection();
		connection  = conn.getConnection(1);
		
		
		roomTypes    = new RoomTypes(stmt);
		room    = new Room(stmt,connection);
		customer  = new Customer(stmt);
		systemUser    = new SystemUser(stmt); 
		reservation     = new Reservation(stmt);
			
	
	}

//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	public Main(String title)
	{
		super(title);
		
		try
		{
			createDatabase();
		}catch(SQLException E)
		{
			System.out.println(E);
		}
		setSize(700,500);
		setLocation(150,150);
		
		
		
		
		
		CPanel = new JPanel();
		SPanel = new JPanel();
		EPanel = new JPanel();
		WPanel = new JPanel();
		NPanel = new JPanel();
		
		JLabel Clabel =new  JLabel("central");
		JLabel Slabel=new  JLabel("south");
		JLabel Nlabel=new  JLabel("north");
		JLabel Elabel=new  JLabel("east");
		JLabel Wlabel=new  JLabel("west"); 
		
		//CPanel.setSize(200,200);
		CPanel.add(Clabel);
		EPanel.add(Elabel);
		SPanel.add(Slabel);
		NPanel.add(Nlabel);
		WPanel.add(Wlabel);
		
		
		
		CPanel.setLayout(null);
		
		
		
		add(CPanel,BorderLayout.CENTER);
		add(NPanel,BorderLayout.NORTH);
		add(EPanel,BorderLayout.EAST);
		add(WPanel,BorderLayout.WEST);
		add(SPanel,BorderLayout.SOUTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
		
	}	

//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	public void actionPerformed(ActionEvent ae)
	{
	}

//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	public void MainMenu()
	{
	
		System.out.println("MainMenu");
		String [] ButtonNames= new String[]{"Add new Customer","Generate Reports","Search Customer Record",
		"Delete Cutomer Record","Update Customer Record","Add Room Type","Reserve Room","setting(change Password)","LogOut"};
		
		CPanel.removeAll(); 
		JButton [] MainMenuButtons = new JButton[9];
		
		
		GridLayout boxes = new GridLayout(9,1);
		CPanel.setLayout(boxes);
		
		for(int i=0; i<9; i++)
		{
		
			MainMenuButtons[i] = new JButton();
			MainMenuButtons[i].setText(ButtonNames[i]);
			MainMenuButtons[i].setSize(30,70);
			
			CPanel.add( MainMenuButtons[i] );
			
		}
		
		MainMenuButtons[0].addActionListener(new ActionListener()
		{
		
			public void actionPerformed(ActionEvent E)
			{
			
			addNewCustomer();
			}
		
		});
		MainMenuButtons[1].addActionListener(new ActionListener()
		{
		
			public void actionPerformed(ActionEvent E)
			{
			
			//generate reorts
			}
		
		});
		MainMenuButtons[2].addActionListener(new ActionListener()
		{
		
			public void actionPerformed(ActionEvent E)
			{
			
			searchCustomer("Search");
			}
		
		});
		MainMenuButtons[3].addActionListener(new ActionListener()
		{
		
			public void actionPerformed(ActionEvent E)
			{
			
			deleteCustomer();
			}
		
		});
		MainMenuButtons[4].addActionListener(new ActionListener()
		{
		
			public void actionPerformed(ActionEvent E)
			{
			searchCustomer("Update");
			
			}
		
		});
		MainMenuButtons[5].addActionListener(new ActionListener()
		{
		
			public void actionPerformed(ActionEvent E)
			{
			
			addRoomType();
			}
		
		});
		MainMenuButtons[6].addActionListener(new ActionListener()
		{
		
			public void actionPerformed(ActionEvent E)
			{
			
			//reserveRoom();	
			
			}
		
		});
		MainMenuButtons[7].addActionListener(new ActionListener()
		{
		
			public void actionPerformed(ActionEvent E)
			{
			setting();
			
			}
		
		});
		MainMenuButtons[8].addActionListener(new ActionListener()
		{
		
			public void actionPerformed(ActionEvent E)
			{
			LogIn();
			
			}
		
		});
		
		CPanel.setSize(250,250);
		CPanel.setLocation(100,100);
		add(CPanel,BorderLayout.CENTER); 
		CPanel.validate();
		CPanel.repaint();
		
	}	

//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	
	//public void 
	
	public void addNewCustomer()
	{
	
		CPanel.removeAll();
		
		String []arr = new String[]{"Customer Name","Customer Id","Age","Gender","CNIC","E-mail"};
		JButton submit = new JButton("Submit");
		JTextField []fields =new JTextField[]{
		
			new JTextField( "Customer Name"),
			new JTextField( "Customer Id"),
			new JTextField( "Age"),
			new JTextField( "Gender"),
			new JTextField( "CNIC"),
			new JTextField( "E-mail") };
			
		CPanel.setLayout(new GridLayout(7,1));
		
		//submit.setLocation(250,250);
		for(int i=0; i<6; i++)
		{
	
			JTextField x = new JTextField(arr[i]);
		
			x.addFocusListener(new FocusListener()
			{
    
    				public void focusGained(FocusEvent e)
    				{
        				x.setText("");	
    				}
	
    				public void focusLost(FocusEvent e)
    				{
    						
    				}
	
			});
			
		fields[i] = x;
		
		CPanel.add(fields[i]);
		}
		CPanel.add(submit);
		submit.addActionListener(new ActionListener()
		{
		
			public void actionPerformed(ActionEvent E)
			{
				String []data =new String [6];
				
				for(int i=0; i<6; i++ )
				{
				
				
					data[i]  = new String(fields[i].getText());
					
				} 
				try
				{
					customer.insert(data[0],data[1],data[2],data[3],data[4],data[5],stmt);
					 
					PopUp added = new PopUp(data[0]+ " Added Successfully","Operation Successful");
					Thread t = new Thread(added);
					t.start();
					MainMenu();
				}catch(SQLException SQLE)
				{
				
					System.out.println(SQLE);
				
				}
			}
		
		});
		
		add(CPanel,BorderLayout.CENTER);
		CPanel.validate();
		CPanel.repaint();
		
		
	
	}

//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	public void generateReports()
	{
		
		
	}

//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	
	public void searchCustomer(String operation)
	{
	
	
		
		CPanel.removeAll();
		CPanel.setLayout(null);
		JTextField id = new JTextField("User ID");
		JButton search = new JButton(operation);
		JButton back = new JButton("Main Menu");
		
		id.setSize(120,25);
		id.setLocation(100,100);
		back.addActionListener(new ActionListener()
		{
		
			public void actionPerformed(ActionEvent ae)
			{
				
				MainMenu();
			}
		});
		id.addFocusListener(new FocusListener()
			{
    
    				public void focusGained(FocusEvent e)
    				{
        				id.setText("");	
    				}
	
    				public void focusLost(FocusEvent e)
    				{
    						
    				}
	
			});
		search.setSize(120,25);
		search.setLocation(100,125);
		search.addActionListener(new ActionListener()
		{
		
			public void actionPerformed(ActionEvent ae)
			{
				String sid = id.getText();
				
				try
				{
				rs =	customer.search(sid,stmt);
				
				if(!rs.next() )
				{
					PopUp result = new PopUp("Incorrect id: "+sid,"Result Not Found");	
					Thread t = new Thread(result);
					t.start();
					MainMenu();			
					
				}else
				{
					PopUp result = new PopUp("Customer with id: "+sid+" found","Result Found");	
					Thread t = new Thread(result);
					t.start();
					
					CPanel.removeAll();
				if(operation.equals("Search")){
					CPanel.setLayout(new GridLayout(7,2));
					JLabel []nameAndData =new JLabel[12];
					
						nameAndData[0] =new  JLabel("Customer Name: ");
						nameAndData[1] = new JLabel(rs.getString("CName"));
						nameAndData[2] = new JLabel("Customer Id: ");
						nameAndData[3] = new JLabel(rs.getString("cId"));
						nameAndData[4] = new JLabel("Age: ");
						nameAndData[5] = new JLabel(rs.getString("Age"));
						nameAndData[6] = new JLabel("Gender: ");
						nameAndData[7] = new JLabel(rs.getString("Gender"));
						nameAndData[8] = new JLabel("CNIC: ");
						nameAndData[9] = new JLabel(rs.getString("CNIC")); 
						nameAndData[10]= new JLabel("Email: ");
						nameAndData[11] = new JLabel(rs.getString("Email"));
					for(int i=0; i<12; i++)
					{
						CPanel.add(nameAndData[i]);
					}
					CPanel.add(back);	
					CPanel.validate();
					CPanel.repaint();		
					
				}else
				{
					
					updateCustomer();
				}}
				}catch(SQLException E)
				{
					
					System.out.println(E);
				}
			
				
			}
		});
		
		CPanel.add(id);
		CPanel.add(search);
		CPanel.validate();
		CPanel.repaint();
		
		
		
		
	}

//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	public void updateCustomer()
	{
	
		JButton update = new JButton("Update");
		String []arr = new String[6];
		try
		{
			arr[0] = new String(rs.getString("CName"));
			arr[1] = new String(rs.getString("cId"));
			arr[2] = new String(rs.getString("Age"));
			arr[3] = new String(rs.getString("Gender"));
			arr[4] = new String(rs.getString("CNIC"));
			arr[5] = new String(rs.getString("Email"));
		}catch(SQLException SQLE)
		{
			
			System.out.println(SQLE);
		}
		
		JTextField []fields =new JTextField[]{
		
			new JTextField( arr[0]),
			new JTextField( arr[1]),
			new JTextField( arr[2]),
			new JTextField( arr[3]),
			new JTextField( arr[4]),
			new JTextField( arr[5]) };
			
		CPanel.setLayout(new GridLayout(7,1));
		
		//submit.setLocation(250,250);
		for(int i=0; i<6; i++)
		{
	
			JTextField x = new JTextField(arr[i]);
		
			x.addFocusListener(new FocusListener()
			{
    
    				public void focusGained(FocusEvent e)
    				{
        				x.setText("");	
    				}
	
    				public void focusLost(FocusEvent e)
    				{
    						
    				}
	
			});
			
		fields[i] = x;
		
		CPanel.add(fields[i]);
		}
		CPanel.add(update);
		
				
				String []data =new String [6];
				
				
		
		update.addActionListener(new ActionListener()
		{
		
			public void actionPerformed(ActionEvent E)
			{
				
				for(int i=0; i<6; i++ )
				{
				
				
					data[i]  = new String(fields[i].getText());
					
				} 
		
				
				
		
				try
				{
			customer.update(data[0],data[1],data[2],data[3],data[4],data[5],stmt,Integer.parseInt(rs.getString("cId")));
					 
					PopUp added = new PopUp("Record Updated Successfully","Operation Successful");
					Thread t = new Thread(added);
					t.start();
					MainMenu();
				}catch(SQLException SQLE)
				{
				
					System.out.println(SQLE);
				
				}
			}
		
		});
		
		add(CPanel,BorderLayout.CENTER);
		CPanel.validate();
		CPanel.repaint();
		
		
	
	}

//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	public void deleteCustomer()
	{
	
		
		CPanel.removeAll();
		CPanel.setLayout(null);
		JTextField id = new JTextField("User ID");
		JButton delete = new JButton("Delete");
		JButton back = new JButton("Main Menu");
		
		id.setSize(120,25);
		id.setLocation(100,100);
		back.addActionListener(new ActionListener()
		{
		
			public void actionPerformed(ActionEvent ae)
			{
				
				MainMenu();
			}
		});
		id.addFocusListener(new FocusListener()
			{
    
    				public void focusGained(FocusEvent e)
    				{
        				id.setText("");	
    				}
	
    				public void focusLost(FocusEvent e)
    				{
    						
    				}
	
			});
		delete.setSize(120,25);
		delete.setLocation(100,125);
		delete.addActionListener(new ActionListener()
		{
		
			public void actionPerformed(ActionEvent ae)
			{
				String sid = id.getText();
				
			
				
				JFrame pop = new JFrame("Confirmation");
				JButton yes = new JButton("Yes");
				JButton no = new JButton("No");
				JLabel text = new JLabel("Do you really want to delete..?");
				
				yes.setSize(75,25);
				yes.setLocation(100,50);
					
				no.setSize(75,25);
				no.setLocation(175,50);
				
				pop.setLayout(new FlowLayout());
				pop.setSize(250,78);
				pop.setLocation(300,250);
				
				
				//text.setLocation(300,250);
				
				pop.add(new JPanel().add(text));
				pop.add(text);
				pop.add(yes);
				pop.add(no);
				
				
				yes.addActionListener(new ActionListener()
				{
				
					public void actionPerformed(ActionEvent ae )
					{
						customer.delete(sid,stmt);
						
						pop.dispose();
						PopUp deleted = new PopUp("Customer deleted Succeefully","Operatioon Succefull");
						Thread t= new Thread (deleted);
						t.start();
						MainMenu();
						
					}
				
				});
				no.addActionListener(new ActionListener()
				{
				
					public void actionPerformed(ActionEvent ae )
					{
						pop.dispose();
						PopUp aborted = new PopUp("Operation aborted","Message");
						Thread t= new Thread (aborted);
						t.start();
						MainMenu();
						
					}
				
				});
				
				pop.show();
					
				
			
				
			}
		});
		
		CPanel.add(id);
		CPanel.add(delete);
		CPanel.validate();
		CPanel.repaint();
		
		
		
	}

//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	public void addRoomType()
	{
		
		CPanel.removeAll();
		
		String []arr = new String[]{"Room Type Name","Room Type Id","No of Rooms","Price","Description"};
		JButton submit = new JButton("Add");
		JTextField []fields =new JTextField[]{
		
			new JTextField( "Room Type Name"),
			new JTextField( "Room Type Id"),
			new JTextField( "No of Rooms"),
			new JTextField( "Price"),
			new JTextField( "Description") };
			
		CPanel.setLayout(new GridLayout(6,1));
		
		//submit.setLocation(250,250);
		for(int i=0; i<5; i++)
		{
	
			JTextField x = new JTextField(arr[i]);
		
			x.addFocusListener(new FocusListener()
			{
    
    				public void focusGained(FocusEvent e)
    				{
        				x.setText("");	
    				}
	
    				public void focusLost(FocusEvent e)
    				{
    						
    				}
	
			});
			
		fields[i] = x;
		
		CPanel.add(fields[i]);
		}
		CPanel.add(submit);
		submit.addActionListener(new ActionListener()
		{
		
			public void actionPerformed(ActionEvent E)
			{
				String []data =new String [5];
				
				for(int i=0; i<5; i++ )
				{
				
				
					data[i]  = new String(fields[i].getText());
					
				} 
				try
				{
					roomTypes.insert(data[0],data[1],data[2],data[3],data[4],stmt);
					 
					PopUp added = new PopUp("New Room Type Added Successfully","Operation Successful");
					Thread t = new Thread(added);
					t.start();
					MainMenu();
				}catch(SQLException SQLE)
				{
				
					System.out.println(SQLE);
				
				}
			}
		
		});
		
		add(CPanel,BorderLayout.CENTER);
		CPanel.validate();
		CPanel.repaint();
		
		
	
	}

//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	public void deleteRoomType()
	{
	
	}

//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	public void setting()
	{
	
		CPanel.removeAll();
		CPanel.setLayout(null);
		CPanel.setSize(500,500);
		JPasswordField [] pwds = new JPasswordField[3];
		JLabel [] names= new JLabel[3];
		names[0] = new JLabel("Current Password: ");
		names[1] = new JLabel("New Password: ");
		names[2] = new JLabel("Confirm New Password");
		
		pwds[0] = new JPasswordField("****");
		pwds[1] = new JPasswordField("****");
		pwds[2] = new  JPasswordField("****");
			
				
		
		JButton update = new JButton("Update Password");
		update.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				String cp,np,cnp;
				char [] temp1 = pwds[0].getPassword();
				char [] temp2 = pwds[1].getPassword();
				char [] temp3 = pwds[2].getPassword();
				
				cp = new String(String.valueOf(temp1));
				np = new String(String.valueOf(temp2));
				cnp = new String(String.valueOf(temp3));
			
				
				try
				{ResultSet rs = systemUser.getUser(cp,stmt);
					if(rs.next() )
					{
						
						String ccp =new String(rs.getString("password"));
						System.out.println(ccp+"  "+cp+" "+np+"  "+cnp);
						if(  ( cp.equals(ccp) ) && ( cnp.equals(np) )   )
						{
						
							systemUser.update(cp,np,connection);
							PopUp updated = new PopUp("Password Updated Successfully","Updating Completed");
							Thread t = new Thread(updated);
							t.start();
						
						
						}else
						{
							
							PopUp updated = new PopUp("Operation aborted","Message");
							Thread t = new Thread(updated);
							t.start();
						}
						
					
					}
					MainMenu();
				}catch(SQLException SQLE)
				{
					
					System.out.println(SQLE);
				}
			}
			
		});
		
		pwds[0].addFocusListener(new FocusListener()
		{
    
    			public void focusGained(FocusEvent e)
    			{
        			pwds[0].setText("");	
    			}

    			public void focusLost(FocusEvent e)
    			{
    					
    			}
	
		});
		pwds[1].addFocusListener(new FocusListener()
		{
    
    			public void focusGained(FocusEvent e)
    			{
        			pwds[1].setText("");	
    			}

    			public void focusLost(FocusEvent e)
    			{
    					
    			}
	
		});
		
			pwds[2].addFocusListener(new FocusListener()
		{
    
    			public void focusGained(FocusEvent e)
    			{
        			pwds[2].setText("");	
    			}

    			public void focusLost(FocusEvent e)
    			{
    					
    			}
	
		});
		int x =100,y=50;
		for(int i=0; i<3; i++)
		{
		
			names[i].setLocation(x-50,y);
			pwds[i].setLocation(x+120,y);
			
			pwds[i].setSize(100,30);
			names[i].setSize(180,30);
			y+=50;
			
			CPanel.add(names[i]);
			CPanel.add(pwds[i]);
		}
		update.setLocation(190,200);
		update.setSize(190,30);
		CPanel.add(update);
		CPanel.validate();
		CPanel.repaint();
	}

//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

	/*public void reserveRoom()
	{
	
		CPanel.removeAll();
		String sname=null,sroom=null;
		int nogs=0;
       		JTextField nog = new JTextField("No of Guests");
       		JLabel [] l= new JLabel[3]; 
 		JButton reserve = new JButton("Reserve");
 		String [] RoomNo=null;
 		String [] cname = null;
 		int [] cid = null;
 		JComboBox name= null;
 		
 		JComboBox croom= null;
 		ResultSet rs=null;
       		try
       		
       		{
       			 rs =room.Searchby(stmt);
       		
       			int i=0;
       			while(rs.next())
       			{
       				i++;
       			
       			}
       		//System.out.println("rooms: "+i);
       		rs.beforeFirst();
       		RoomNo =new String[i];
       		i=0;
       		while(rs.next())
       		{
       			RoomNo[i] = new String(rs.getString("RoomNo"));
       		i++;
       		}
       		
       		
       		
       		rs =null; i=0;
       		rs =customer.search(stmt);
       		
       		while(rs.next() )
       		{
       			i++;
       		}
       		
       		rs.beforeFirst();
       		 cname =new String[i];
       		 cid =new int[i];
       		i=0;
       		while(rs.next() )
       		{
       		cname[i] = new String(rs.getString("CName"));
       		cid[i] = Integer.parseInt(rs.getString("cId"));
       		i++;
       		}
       		 croom = new JComboBox(RoomNo);
       		 name = new JComboBox(cname);
       		
       		CPanel.add(croom);
       		CPanel.add(name);
       		
        	}catch(SQLException SQLE)
        	{
        	
        		System.out.println(SQLE);
        	}
        	
        	reserve.addActionListener(new ActionListener()
        	{
        	
        		public void actionPerformed(ActionEvent ae)
        		{
        			sname = name.getSelectedItem().toString();
        			 sroom = croom.getSelectedItem().toString();
        			 nogs = Integer.parseInt(nog.getText());
        			int x=0;
       			for(int i=0; i<cname.length && !(sname.equals(cid[i]) ) ; i++)
       			{
       				x=i;
       		
       			}
       				int sid = cid[x];
       				reservation.insert(sroom,sid,nogs,stmt);
        		}
        			
        	});
       		CPanel.add(reserve);
       		CPanel.validate();
       		CPanel.repaint();
       
	
	} */

//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		
	public void LogIn()
	{
	
		CPanel.removeAll();
		//CPanel.setLayout(null);
		JTextField userId = new JTextField("User Id");
		JPasswordField pwd = new JPasswordField("Passwrd",4);
		JButton submit = new JButton("Submit"); 
		//JLabel userIdl = new JLabel("User ID");
		//userId.add(userIdl);
		userId.addFocusListener(new FocusListener()
		{
    
    			public void focusGained(FocusEvent e)
    			{
        			userId.setText("");	
    			}

    			public void focusLost(FocusEvent e)
    			{
    					
    			}
	
		});
		
		pwd.addFocusListener(new FocusListener()
		{
    
    			public void focusGained(FocusEvent e)
    			{
        			pwd.setText("");	
    			}

    			public void focusLost(FocusEvent e)
    			{
    			
    			}
	
		});
		
		submit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
			
				String UserId = userId.getText();
				char [] temp = 	pwd.getPassword();
				String Pwd = String.valueOf(temp);
			
					
				if(systemUser.confirmUser(UserId,Pwd,stmt))
				{
					System.out.println("User confirmed");
					PopUp successful =new  PopUp(UserId+" Logged In succefully","LoIn Successful");
					Thread t = new Thread(successful);
					t.start();
						
					LogIn = true;
						
					CPanel.removeAll();
					CPanel.validate();
					CPanel.repaint();
					MainMenu();
						
				}else
				{
						
					PopUp failure = new  PopUp("Sorry..Incorrect User Id or Password","LogIn Failure");
					Thread t = new Thread(failure);
					t.start();
					
					
					
				}
								
			}
		});
		
		CPanel.add(userId);
		CPanel.add(pwd);
		CPanel.add(submit);
		
		userId.setSize(100,30);
		pwd.setSize(75,30);
		submit.setSize(175,30);
		
		userId.setLocation(250,125);
		pwd.setLocation(350,125);
		submit.setLocation(250,154);
		
		CPanel.validate();
		CPanel.repaint();
		
	}


//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	

	public static void main(String [] Args) throws SQLException
	{
	
		Main app = new Main("Hotel Management System");
		app.LogIn();
			
				
	}

}

//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++

