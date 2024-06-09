package mainPakage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataBaseConnector {
	
	
	int id;
	int level;
	int section;
	
	public DataBaseConnector()
	{
		
	}
	
	public void createSave()
	{
		
	}
	
	public boolean getSave()
	{
		boolean loginLogrado = false;
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con = DriverManager.getConnection("jdbc:mysql://sql.freedb.tech:3306/freedb_Usuarios","freedb_KevinAdmin","*heaPNr7BX7vaZr");  
			Statement stmt=con.createStatement();  
			ResultSet rs= stmt.executeQuery("SELECT * FROM saves");
			while(rs.next())  
			{
				System.out.println(rs.getString(1)+"  "+rs.getString(2)+rs.getString(3));  
				
			}
			con.close();  
			}catch(Exception e){ System.out.println(e);
			}  
		
		if(loginLogrado)
		{
			return true;
		}
		System.out.println("fallido");
		return false;
	}  
	
	
	public void altaScore(int score,String nombre)
	{
		
		
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getSection() {
		return section;
	}

	public void setSection(int section) {
		this.section = section;
	}
	
	
}
