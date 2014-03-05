package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
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
			result = (ResultSet) st.executeQuery("SELECT id FROM t_users WHERE (name="+login+" AND password="+pwd+")");
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
		
		try 
		{
			st = cnx();
			if(st == null) return false;
			// RECUPERATION ID ACTION
			result = (ResultSet) st.executeQuery("SELECT id FROM t_actions WHERE name="+actionName);
			if(result.last() && result.getRow() == 1) 
			{
				idAction = result.getInt("id");
				
				// ON REGARDE SI L'USER PEUT LANCER L'ACTION
				result = (ResultSet) st.executeQuery("SELECT * FROM t_rights WHERE (id_user="+userID+"+ AND id_action="+idAction+")");
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
	
	/*public ArrayList<Livre> livres()
	{
		ArrayList<Livre> l = new ArrayList<Livre>();
		String req = "SELECT * FROM t_livre";	
		Statement st = null;
		ResultSet result;		
		
		try 
		{
			st = cnx();
			if(st == null) return l;
			result = (ResultSet) st.executeQuery(req);
			while(result.next())
			{
				Livre livre = new Livre(Integer.parseInt(result.getString("idLivre")), result.getString("titre"), result.getString("auteur"), result.getString("annee") );
				l.add(livre);
			}
			result.close();
			st.close();
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}		
		
		return l;
	}*/
	
	/*private String createStringInsert(String tableName, ArrayList<String> params)
	{
		StringBuilder req = new StringBuilder("INSERT INTO "+tableName+" VALUES");	
		StringBuilder values = new StringBuilder("(");
		
		for(int i=0;i<params.size()-1;i++)
		{
			values.append("'"+params.get(i)+"', ");
		}
		values.append("'"+params.get(params.size()-1)+"'");
		values.append(")");
		req.append(values);
		
		return req.toString();
	}*/
	
	/*public boolean editLivre(Livre newLivre)
	{
		Statement st = null;
		ResultSet result;		
		StringBuilder req = new StringBuilder("UPDATE t_livre SET ");
		try 
		{
			req.append("titre='"+newLivre.getTitre()+"', ");
			req.append("auteur='"+newLivre.getAuteur()+"', ");
			req.append("annee='"+newLivre.getAnnee()+"'");
			req.append(" WHERE idLivre='"+newLivre.getIdLivre()+"'");
			st = cnx();
			//System.out.println(req.toString());
			st.executeUpdate(req.toString());
			st.close();
			return true;
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}		
		return false;
	}*/
	
	/*public boolean addLivre(Livre livre)
	{
		Statement st = null;
		ResultSet result;		
		ArrayList<String> params = new ArrayList<String>();
		
		try 
		{
			params.add(livre.getTitre());
			params.add(livre.getAuteur());
			params.add(livre.getAnnee());
			st = cnx();
			String req = createStringInsert("t_livre", params);
			st.executeUpdate(req);
			st.close();
			return true;
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}		
		return false;
	}*/
	
	/*public void printLivres()
	{
		ArrayList<Livre> livres_bdd = livres();
		for(Livre l: livres_bdd)
		{
			System.out.println(l.getTitre()+ " "+l.getAuteur()+" "+l.getAnnee());
		}
	}*/
	
	/*public static void main(String [] args)
	{
		AccesBDD acc = new AccesBDD();
		ArrayList<Livre> livres_bdd = acc.livres();
		
		acc.printLivres();
		
		Livre languageC = livres_bdd.get(0);
		languageC.setAuteur("Mr");
		acc.editLivre(languageC);
		System.out.println();
		acc.printLivres();
		
		/*System.out.println(acc.saveLivre(new Livre("Tous a poil", "Greg", "2005")));;
		
		livres_bdd = acc.livres();
		for(Livre l: livres_bdd)
		{
			System.out.println(l.getIdLivre()+" "+l.getTitre()+ " "+l.getAuteur()+" "+l.getAnnee());
		}
		*/
		
//		String url, usr, pwd, req;
//		Connection cnx;
//		
//		
//		
//		
//		url = "jdbc:mysql://localhost/db_master1";
//		usr = "root";
//		pwd = "";
//		
//		
//		try 
//		{		
//			cnx = (Connection) DriverManager.getConnection(url, usr, pwd);
//			Statement st = (Statement) cnx.createStatement();
//			
//			/* LISTER LIVRE  */
//			System.out.println("\t\tListe des livres");
//			req = "SELECT * FROM t_livre";	
//			ResultSet result = (ResultSet) st.executeQuery(req);		
//			while(result.next())
//			{
//				System.out.println(result.getString("titre"));
//			}
//					
////			/* ENREGISTRER UN LIVRE */
////			req = "INSERT INTO t_livre VALUES('3', 'Ma vie de fou', 'toto', '2001')";	
////			st.executeUpdate(req);	
//			
//			/* LISTER MOT CLES  */
//			System.out.println("\n\n\t\tMot cles du livre id 1");
//			req = "SELECT idMotcle FROM t_livre_motcle WHERE idLivre='1'";	
//			result = (ResultSet) st.executeQuery(req);		
//			while(result.next())
//			{
//				System.out.println(result.getString("idMotcle"));
//			}
//			
//			/* LISTER MOT CLE D'UN LIBRE */
//			int id = 1;
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
