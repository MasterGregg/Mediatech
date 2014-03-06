package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.ResourceBundle;

public class SQLManager 
{
	final String FILE_CONFIG = "configBDD";
	private String url;
	private String usr;
	private String pwd;
	
	public SQLManager()
	{
		try 
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			ResourceBundle bundle = ResourceBundle.getBundle(FILE_CONFIG);
			this.url = bundle.getString("URL");
			this.usr = bundle.getString("USR");
			this.pwd = bundle.getString("PWD");
		} catch (InstantiationException | IllegalAccessException
				| ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private Statement cnx()
	{
		Statement st = null;
		try 
		{		
			if(usr != null && pwd != null && url != null)
			{
				Connection cnx = (Connection) DriverManager.getConnection(url, usr, pwd);
				st = (Statement) cnx.createStatement();	
			}	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return st;
	}
	
	public int userID(String login, String pwd)
	{
		int r = -1;
		Statement st = null;
		ResultSet result = null;
		
		try 
		{
			st = cnx();
			if(st == null) return -1;
			result = (ResultSet) st.executeQuery("SELECT id FROM t_users WHERE (name='"+login+"' AND password='"+pwd+"')");
			if(result.last() && result.getRow() == 1) r = result.getInt("id");
			result.close();
			st.close();
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}		
		
		return r;
	}
	
	public boolean userCanLaunch(int userID, String actionName)
	{
		Statement st = null;
		ResultSet result = null;
		int idAction = -1;
		boolean ok = false;
		String req = "";
		
		try 
		{
			st = cnx();
			if(st == null) return false;
			
			// RECUPERATION ID ACTION
			req = "SELECT id FROM t_actions WHERE (name='"+actionName+"')";
			result = (ResultSet) st.executeQuery(req);
			if(result.last() && result.getRow() == 1) 
			{
				idAction = result.getInt("id");
				
				// ON REGARDE SI L'USER PEUT LANCER L'ACTION
				req = "SELECT * FROM t_rights WHERE (id_user='"+userID+"' AND id_action='"+idAction+"')";
				result = (ResultSet) st.executeQuery(req);
				if(result.last() && result.getRow() == 1) ok = true;
			}
			result.close();
			st.close();
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}		
		return ok;
	}
	
	public boolean addMedia(Media m)
	{
		FileInputStream fis = null;
	    PreparedStatement ps = null;
		Statement st = null;
		String req = "INSERT INTO t_medias(name, binaryBlob) values (?, ?)";
		Connection con = null;
		
		try 
		{
			st = cnx();
			if(st == null) return false;
			con = st.getConnection();
			
			con.setAutoCommit(false);
			File file = m.getLocalFile();  
			fis = new FileInputStream(file);
			  
			ps = con.prepareStatement(req);
			ps.setString(1, m.getName());
			System.out.println(m.getName());
			ps.setBinaryStream(2, fis, (int) file.length());
			ps.executeUpdate();
			con.commit();
			ps.close();
			fis.close();
			st.close();
			
			/* MAJ ID MEDIA */
			int id = getIDLastMedia();
			if(id != -1)
			{
				m.setID(id);
				return true;
			}
		} catch (SQLException | IOException e) 
		{
			e.printStackTrace();
		}		
		
		return false;
	}
	
	private int getIDLastMedia()
	{
		Statement st = null;
		ResultSet result = null;
		String req = "";
		int id = -1;
		
		try 
		{
			st = cnx();
			if(st == null) return -1;
			
			// RECUPERATION ID 
			req = "SELECT MAX(id) AS max FROM t_medias";
			result = (ResultSet) st.executeQuery(req);
			if(result.last() && result.getRow() == 1)  id = result.getInt("max");
			result.close();
			st.close();
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}				
		return id;
	}
	
	public HashMap<Integer, String> listMedias()
	{
		HashMap<Integer, String> medias = new HashMap<Integer, String>();
		Statement st = null;
		ResultSet result = null;
		String req = "";
		
		try 
		{
			st = cnx();
			if(st == null) return medias;
			
			// RECUPERATION ID ACTION
			req = "SELECT id, name FROM t_medias";
			result = (ResultSet) st.executeQuery(req);
			while(result.next()) 
			{
				int id = result.getInt("id");
				String name = result.getString("name");
				medias.put(id, name);
			}
			result.close();
			st.close();
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}				
		return medias;
	}
	
	public File getRessourceFile(int id)
	{
		String req;
		Statement st = null;
		ResultSet result;
		File f = null;
		
		try 
		{
			req = "SELECT * FROM t_medias WHERE id='"+id+"'";
			st = cnx();
			if(st == null) return null;
			
			result = st.executeQuery(req);
		    if(result.last() && result.getRow() == 1) 
		    {
			      String name = result.getString("name");
			      f = new File(System.getProperty("java.io.tmpdir")+File.separator+"name");
			      FileOutputStream fos = new FileOutputStream(f);

			      byte[] buffer = new byte[1];
			      InputStream is = result.getBinaryStream("binaryBlob");
			      while (is.read(buffer) > 0) 
			      {
			        fos.write(buffer);
			      }
			      fos.close();
		    }  
		    result.close();
		    st.close();
		} catch (SQLException | IOException e) 
		{
			e.printStackTrace();
		}
		return f;
	}

	public boolean removeMedia(Media media) 
	{
		String req;
		Statement st = null;
		
		try 
		{
			st = cnx();
			if(st == null) return false;	
			req = "DELETE FROM t_medias WHERE id="+media.getID();
			st.executeUpdate(req);
			st.close();
			return true;
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}				
		return false;
	}
}
