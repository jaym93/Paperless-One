package com.paperless.connections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

public class Select {
	ResultSet rs ;

	public ResultSet runCommandNoParams(String Query, Connection c)
	{
		
		PreparedStatement stmt = null;
		
		try {
			
			stmt = c.prepareStatement(Query);				
			rs = stmt.executeQuery();
		
		  
	}
		catch(Exception e){
		
		}
		
		
		return rs;
		
		
}
	
	public ResultSet selectWithParams(String Query, Object a, Connection c)
	{
		
		PreparedStatement stmt = null;
		
		try {
			
			stmt = c.prepareStatement(Query);
		if(a instanceof Date){
			stmt.setDate(1, (java.sql.Date) a);
		}else if(a instanceof Integer){
			stmt.setInt(1, (Integer)a);
			
		}else{ 
			stmt.setString(1, (String)a);
			
		}
				
		rs = stmt.executeQuery();
		
		  
	}
		catch(Exception e){
		
		}
		
		
		return rs;
		
		
}
}