package ca.sheridancollege.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.Bean.DogBean;
import ca.sheridancollege.Bean.HandlerBean;
import ca.sheridancollege.Bean.OnlyDogs;
import ca.sheridancollege.Controller.Enums;

@Repository
public class DAO {
	
	@Autowired
	protected JdbcTemplate jdbc;
	
	public static void addDog(DogBean b) 
	{
		int entry_number = 0;
		ArrayList<DogBean> list = new  ArrayList<DogBean>();
		
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = null;
        
            conn = DriverManager.getConnection("jdbc:mysql://localhost/dogshow", "root", "malav");
            
            
        	 String Query = "insert into dog_details (entry_number,dog_name,owner_name,owneremail,breed,dog_group,gender,ranking,dateofevent)  values (?,?,?,?,?,?,?,?,?)";
        	 
        	  PreparedStatement ps = conn.prepareStatement(Query);
              ps.setInt(1, b.getEntryNumber());
               ps.setString(2, b.getDogName());
               ps.setString(3, b.getOwnerName());
               ps.setString(4, b.getEmail());
               ps.setString(5, b.getBreed());
               ps.setString(6, b.getGroup());
               ps.setString(7, b.getGender());
               ps.setString(8, b.getRanking());
               ps.setString(9, b.getDate());
               
                 ps.executeUpdate();
          
                System.out.println("Connection Success");
                conn.close();
              
         }
		
  		catch (Exception e) {
  			System.out.println("Connection Failed");
              System.out.println(e);
  		}
		  }
	
	
	public static String randomDogs() {
		
		try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
	           Connection conn = null;
	       
	           conn = DriverManager.getConnection("jdbc:mysql://localhost/dogshow", "root", "malav");
	          
	           String Query = "insert into dog_details (entry_number,dog_name,owner_name,owneremail,breed,dog_group,gender,ranking,dateofevent) values (?,?,?,?,?,?,?,?,?)";
	           
	           for(int i = 0; i < 10; i++) {
	   			String dogname = Enums.name.getRandomName().toString();
	   			String owner = Enums.Owner.getRandomOwner().toString();
	   		    String breed = Enums.Breeds.getRandomBreed().toString();
	   		    String doggroup = Enums.group.getRandomGroup().toString();
	   		    String gender = Enums.gender.getRandomGender().toString();
	   		    String ranking = Enums.ranking.getRandomGender().toString();
	   		    String days = Enums.day.getRandomGroup().toString();
	   		    
	   		 String email = ("'"+owner+'"'+"@gmail.com");
	   		    
	   		    Random random = new Random();
	   		    int rand = 10000;
	   		    while (true){
	   		        rand = random.nextInt(1000000);
	   		        if(rand !=10000) break;
	   		    }
	   		    System.out.println("..."+rand+"...");
	   			
	   		    System.out.print(dogname + "," + owner+ "," +breed+ "," +doggroup+ "," +gender+ "," +ranking);

	   		 PreparedStatement ps = conn.prepareStatement(Query);
             ps.setInt(1, rand);
              ps.setString(2, dogname);
              ps.setString(3,owner);
              ps.setString(4, email);
              ps.setString(5, breed);
              ps.setString(6, doggroup);
              ps.setString(7, gender);
              ps.setString(8, ranking);
              ps.setString(9, days);
                ps.executeUpdate();
		           }
	           System.out.println("Connection Success");
               conn.close();
		}
		
		catch (Exception e) {
			System.out.println("Connection Failed");
	       System.out.println(e);
			
		}
		return null;
	}
		
	
	
	public static ArrayList<DogBean> showDogs() {
	ArrayList<DogBean>	doglist = new ArrayList<DogBean>();
	try {
		 Class.forName("com.mysql.cj.jdbc.Driver");
           Connection conn = null;
       
           conn = DriverManager.getConnection("jdbc:mysql://localhost/dogshow", "root", "malav");
          
           String Query = "select * from dog_details";
           Statement st = conn.createStatement();
           ResultSet rs = st.executeQuery(Query);
           ResultSetMetaData rsmd = rs.getMetaData();
           int columnCount = rsmd.getColumnCount();	  
           while(rs.next()) {
           	
         String eNum =   	rs.getString(1);
         int enumi = Integer.parseInt(eNum);
         String dName =   	rs.getString(2);
          String oName = 	rs.getString(3);
          String email = rs.getString(4);
           String bre =	rs.getString(5);
           String dGroup = rs.getString(6);
           String gender = rs.getString(7);
           String ran = rs.getString(8);
           String date = rs.getString(9);
           
           
           DogBean ds = new DogBean(enumi,dName,oName,email,bre,dGroup,gender,ran,date);
           doglist.add(ds);
           }
           conn.close();     
	}
	catch (Exception e) {
		System.out.println("Connection Failed");
       System.out.println(e);
		
	}
	return doglist;
	
}
	
	
	public static ArrayList<DogBean> showAddDogs(String name) {
		ArrayList<DogBean>	doglist = new ArrayList<DogBean>();
		try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
	           Connection conn = null;
	       
	           conn = DriverManager.getConnection("jdbc:mysql://localhost/dogshow", "root", "malav");
	          
	           String Query = "select * from dog_details";
	           Statement st = conn.createStatement();
	           ResultSet rs = st.executeQuery(Query);
	           ResultSetMetaData rsmd = rs.getMetaData();
	           int columnCount = rsmd.getColumnCount();	  
	           while(rs.next()) {
	           	
	         String eNum =   	rs.getString(1);
	         int enumi = Integer.parseInt(eNum);
	         String dName =   	rs.getString(2);
	          String oName = 	rs.getString(3);
	          String email = rs.getString(4);
	           String bre =	rs.getString(5);
	           String dGroup = rs.getString(6);
	           String gender = rs.getString(7);
	           String ran = rs.getString(8);
	           String date = rs.getString(9);
	           
	           
	           DogBean ds = new DogBean(enumi,dName,oName,email,bre,dGroup,gender,ran,date);
	           doglist.add(ds);
	           }
	           conn.close();     
		}
		catch (Exception e) {
			System.out.println("Connection Failed");
	       System.out.println(e);
			
		}
		return doglist;
		
	}
	
	public static void deleteDog(int entryNumber) {
		// TODO Auto-generated method stub
		
		 try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection conn = null;
	            
	            conn = DriverManager.getConnection
	            		("jdbc:mysql://localhost/dogshow", "root", "malav");
	            String q = "delete from dog_details where entry_number='" + entryNumber +"'";
	            Statement st = conn.createStatement();
	            st.executeUpdate(q);
	
	            conn.close();
	        } catch (Exception e) {
	            System.out.println("Connection Failed");
	            System.out.println(e);
	        }
		
	}
	public static DogBean getDogByEntryNumber(int entryNumber) {
		ArrayList<DogBean> dogs = new ArrayList<DogBean>();
		 try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection conn = null;
	            conn = DriverManager.getConnection
	            		("jdbc:mysql://localhost/dogshow", "root", "malav");
	            String Query = "Select * From dog_details where entry_number=" + entryNumber;
	           
	            Statement st = conn.createStatement();
	          //  st.executeQuery() --> Get a table
	        //  st.executeUpdate()  --> Modify a table
	            
	           
	            ResultSet rs = st.executeQuery(Query);
	            ResultSetMetaData rsmd  = rs.getMetaData();
	            int columnCount = rsmd.getColumnCount();
	            
	            while(rs.next()) {
	            	
	            	int EntryNumber = Integer.parseInt(rs.getString(1));
	            	String dogName = rs.getString(2);
	            	String ownerName = rs.getString(3);
	            	String email = rs.getString(4);
	            	String breed = rs.getString(5);
	            	String group = rs.getString(6);
	            	String gender = rs.getString(7);
	            	String ranking = rs.getString(8); 
	            	String date = rs.getString(9);
	 
	            	
	            	DogBean d = new DogBean(EntryNumber, dogName,ownerName,email, breed, group, gender, ranking, date);
	            	System.out.println(d);
	            	dogs.add(d);
		            }
	            
	            
	            
	            conn.close();
	        } catch (Exception e) {
	            System.out.println("Connection Failed");
	            System.out.println(e);
	        }
		
		 if(dogs.size()>0) {
			 
			 return dogs.get(0);
			 }
			 return null;
		 
	}
	

	
	
	
	public static void modifyDog(DogBean d) {
		// TODO Auto-generated method stub
		
		 try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            Connection conn = null;
	            conn = DriverManager.getConnection
	            		("jdbc:mysql://localhost/dogshow", "root", "malav");
	         
	            String Query = "update dog_details set dog_name=?, owner_name=?, owneremail=?, breed=?, dog_group=?, gender=?, ranking=? where entry_number=?";
	            
	            PreparedStatement ps = conn.prepareStatement(Query);
	            ps.setString(1,d.getDogName());
	            ps.setString(2,d.getOwnerName());
	            ps.setString(3,d.getEmail());
	            ps.setString(4, d.getBreed());
	            ps.setString(5,d.getGroup());
	            ps.setString(6, d.getGender());  
	            ps.setString(7,d.getRanking());
	            ps.setInt(8,d.getEntryNumber()); 
	            ps.executeUpdate();
	            
	
	            conn.close();
	        } catch (Exception e) {
	            System.out.println("Connection Failed");
	            System.out.println(e);
	        }
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static ArrayList<DogBean> searchByNumber(DogBean b) {
		ArrayList<DogBean>	doglist = new ArrayList<DogBean>();
		String Query = null;
		try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
	           Connection conn = null;
	       
	           conn = DriverManager.getConnection("jdbc:mysql://localhost/dogshow", "root", "malav");
	            	 Query = "select * from dog_details where entry_number="+b.getEntryNumber();
	    
	           Statement st = conn.createStatement();
	           ResultSet rs = st.executeQuery(Query);
	           ResultSetMetaData rsmd = rs.getMetaData();
	           int columnCount = rsmd.getColumnCount();	  
	           while(rs.next()) {
	           	
	         String eNum =   	rs.getString(1);
	         int enumi = Integer.parseInt(eNum);
	         String dName =   	rs.getString(2);
	          String oName = 	rs.getString(3);
	           String bre =	rs.getString(4);
	           String dGroup = rs.getString(5);
	           String gender = rs.getString(6);
	           String ran = rs.getString(7);
	           String date = rs.getString(8);
	           
	           
	           DogBean ds = new DogBean(enumi,dName,oName,bre,dGroup,gender,ran,date);
	           doglist.add(ds);
	           }
	           conn.close();     
		}
		catch (Exception e) {
			System.out.println("Connection Failed");
	       System.out.println(e);
			
		}
		return doglist;
		
	}
	
	public static ArrayList<DogBean> searchBy(DogBean b) {
		ArrayList<DogBean>	doglist = new ArrayList<DogBean>();
		String Query = null;
			try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
	           Connection conn = null;

	           conn = DriverManager.getConnection("jdbc:mysql://localhost/dogshow", "root", "malav");
	           
                if(!b.getGroup().isEmpty()) {
	        	  
	        	  Query = "select * from dog_details where dog_group='"+b.getGroup()+"'";
	          }
	    
	           Statement st = conn.createStatement();
	           ResultSet rs = st.executeQuery(Query);
	           ResultSetMetaData rsmd = rs.getMetaData();
	           int columnCount = rsmd.getColumnCount();	  
	           while(rs.next()) {
	           	
	         String eNum =   	rs.getString(1);
	         int enumi = Integer.parseInt(eNum);
	         String dName =   	rs.getString(2);
	          String oName = 	rs.getString(3);
	           String bre =	rs.getString(4);
	           String dGroup = rs.getString(5);
	           String gender = rs.getString(6);
	           String ran = rs.getString(7);
	           String date = rs.getString(8);
	           
	           
	           
	           DogBean ds = new DogBean(enumi,dName,oName,bre,dGroup,gender,ran,date);
	          
	           doglist.add(ds);
	           }
	           conn.close();     
		}
		catch (Exception e) {
			System.out.println("Connection Failed");
	       System.out.println(e);
			
		}
		return doglist;
		
	}
	
	public static ArrayList<DogBean> searchByName(String dname) {
		ArrayList<DogBean>	dlist = new ArrayList<DogBean>();
		 Connection conn = null;
		
		try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
	          
	           conn = DriverManager.getConnection("jdbc:mysql://localhost/dogshow", "root", "malav");
          	String Query = "select * from dog_details where dog_name='"+dname+"'";
	           
          	Statement st = conn.createStatement();
	           ResultSet rs = st.executeQuery(Query);
	           ResultSetMetaData rsmd = rs.getMetaData();
	           int columnCount = rsmd.getColumnCount();	  
	           while(rs.next()) {
	           	
	         String eNum =   	rs.getString(1);
	         int enumi = Integer.parseInt(eNum);
	         String dName =   	rs.getString(2);
	          String oName = 	rs.getString(3);
	           String bre =	rs.getString(4);
	           String dGroup = rs.getString(5);
	           String gender = rs.getString(6);
	           String ran = rs.getString(7);
	          
	           String date = rs.getString(8);
	           
	           DogBean ds = new DogBean(enumi,dName,oName,bre,dGroup,gender,ran,date);
	           dlist.add(ds);
	           }
	           conn.close();   
		}
        
	catch (Exception e) {
		System.out.println("Connection Failed");
    System.out.println(e);
		
	}
          	
		return dlist;
	}
	
	
	
	public static ArrayList<DogBean> searchByOwner(String dname) {
		ArrayList<DogBean>	dlist = new ArrayList<DogBean>();
		 Connection conn = null;
		
		try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
	          
	           conn = DriverManager.getConnection("jdbc:mysql://localhost/dogshow", "root", "malav");
          	String Query = "select * from dog_details where owner_name='"+dname+"'";
	           
          	Statement st = conn.createStatement();
	           ResultSet rs = st.executeQuery(Query);
	           ResultSetMetaData rsmd = rs.getMetaData();
	           int columnCount = rsmd.getColumnCount();	  
	           while(rs.next()) {
	           	
	         String eNum =   	rs.getString(1);
	         int enumi = Integer.parseInt(eNum);
	         String dName =   	rs.getString(2);
	          String oName = 	rs.getString(3);
	           String bre =	rs.getString(4);
	           String dGroup = rs.getString(5);
	           String gender = rs.getString(6);
	           String ran = rs.getString(7);
	        
	           String date = rs.getString(8);
	           
	           DogBean ds = new DogBean(enumi,dName,oName,bre,dGroup,gender,ran,date);
	           dlist.add(ds);
	           }
	           conn.close();   
		}
        
	catch (Exception e) {
		System.out.println("Connection Failed");
    System.out.println(e);
		
	} 	
		return dlist;
		
	}
	
	
	public static ArrayList<DogBean> searchByBread(String dname) {
		ArrayList<DogBean>	dlist = new ArrayList<DogBean>();
		 Connection conn = null;
		
		try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
	          
	           conn = DriverManager.getConnection("jdbc:mysql://localhost/dogshow", "root", "malav");
          	String Query = "select * from dog_details where breed='"+dname+"'";
	           
          	Statement st = conn.createStatement();
	           ResultSet rs = st.executeQuery(Query);
	           ResultSetMetaData rsmd = rs.getMetaData();
	           int columnCount = rsmd.getColumnCount();	  
	           while(rs.next()) {
	           	
	         String eNum =   	rs.getString(1);
	         int enumi = Integer.parseInt(eNum);
	         String dName =   	rs.getString(2);
	          String oName = 	rs.getString(3);
	           String bre =	rs.getString(4);
	           String dGroup = rs.getString(5);
	           String gender = rs.getString(6);
	           String ran = rs.getString(7);
	         
	           String date = rs.getString(8);
	           
	        
	           DogBean ds = new DogBean(enumi,dName,oName,bre,dGroup,gender,ran,date);
	           dlist.add(ds);
	           }
	           conn.close();   
		}
        
	catch (Exception e) {
		System.out.println("Connection Failed");
    System.out.println(e);	
	}   	
		return dlist;
		
	}
	
	
	public static ArrayList<DogBean> showList(String dname) {
		ArrayList<DogBean>	dlist = new ArrayList<DogBean>();
		 Connection conn = null;
		
		try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
	          
	           conn = DriverManager.getConnection("jdbc:mysql://localhost/dogshow", "root", "malav");
	           
	           
          	//String Query = "select * from dog_details where dog_name='"+dname+"'";	
   String Query = "SELECT COUNT(BREED) AS count\r\n" + 
   		",BREED\r\n" + 
   		",count(case when gender = 'Male' then gender end) as male \r\n" + 
   		",count(case when Gender = 'Female' then gender end) as female \r\n" + 
   		",count(case when gender = 'male' and ranking= 'SPECIALTY' then gender end) as SM \r\n" + 
   		",count(case when gender = 'female' and ranking= 'SPECIALTY' then gender end) as SF \r\n" + 
   		"FROM dog_details WHERE dog_group='"+dname+"' GROUP BY breed";
   
          	Statement st = conn.createStatement();
	           ResultSet rs = st.executeQuery(Query);
	           ResultSetMetaData rsmd = rs.getMetaData();
	           int columnCount = rsmd.getColumnCount();	  
	           while(rs.next()) {
	        	   
	  
	         String count =   	rs.getString("count");
	         String breed =   	rs.getString("BREED");
	         String male = rs.getString("male");
	         String female = rs.getString("female");
	         String sm = rs.getString("SM");
	         String fm = rs.getString("SF");
	        
	         
	           DogBean ds = new DogBean(count,breed,male,female,sm,fm);
	           dlist.add(ds);
	           }
	           conn.close();   
		}
        
	catch (Exception e) {
		System.out.println("Connection Failed");
    System.out.println(e);
		
	}
          	
		return dlist;
	}
	
	
	public static void addHandler(HandlerBean hb, String encryptedPassword) {
		
		
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = null;
        
            conn = DriverManager.getConnection("jdbc:mysql://localhost/dogshow", "root", "malav");
             
            
            String query = "INSERT INTO handler_details (hu, hp, hph, hemail, entry_number,dog_name, owner_name, breed, dog_group, gender, ranking ) VALUES" + 
            		"(?,?,?,?,?," + 
            		"(SELECT dog_name from dog_details WHERE entry_number='"+hb.getEntry_Number()+"')," + 
            		"(SELECT owner_name from dog_details WHERE entry_number='"+hb.getEntry_Number()+"')," + 
            		"(SELECT breed from dog_details WHERE entry_number='"+hb.getEntry_Number()+"')," + 
            		"(SELECT dog_group from dog_details WHERE entry_number='"+hb.getEntry_Number()+"')," + 
            		"(SELECT gender from dog_details WHERE entry_number='"+hb.getEntry_Number()+"')," + 
            		"(SELECT ranking from dog_details WHERE entry_number='"+hb.getEntry_Number()+"'))";
            
            

      	  PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, hb.getHu());
             ps.setString(2, encryptedPassword);
             ps.setString(3, hb.getHph());
             ps.setString(4, hb.getHemail());
             ps.setString(5, hb.getEntry_Number());
            
               ps.executeUpdate();
        
              System.out.println("Connection Success");
              conn.close();
            
            
		}
		
		catch (Exception e) {
			System.out.println("Connection Failed");
	    System.out.println(e);
			
		}
		
	}
	
	
	
public static void addDogHandler(HandlerBean hb) {
		
		
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = null;
        
            conn = DriverManager.getConnection("jdbc:mysql://localhost/dogshow", "root", "malav");
             
            
            String query = "INSERT INTO handler_details ( entry_number,dog_name, owner_name, breed, dog_group, gender, ranking ) VALUES" + 
            		"(?," + 
            		"(SELECT dog_name from dog_details WHERE entry_number='"+hb.getEntry_Number()+"')," + 
            		"(SELECT owner_name from dog_details WHERE entry_number='"+hb.getEntry_Number()+"')," + 
            		"(SELECT breed from dog_details WHERE entry_number='"+hb.getEntry_Number()+"')," + 
            		"(SELECT dog_group from dog_dtails WHERE entry_number='"+hb.getEntry_Number()+"')," + 
            		"(SELECT gender from dog_details WHERE entry_number='"+hb.getEntry_Number()+"')," + 
            		"(SELECT ranking from dog_details WHERE entry_number='"+hb.getEntry_Number()+"'))" +
            		"where hu='"+hb.getHu()+"'";
            
            

      	  PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, hb.getEntry_Number());

            
               ps.executeUpdate();
        
              System.out.println("Connection Success");
              conn.close();
            
            
		}
		
		catch (Exception e) {
			System.out.println("Connection Failed");
	    System.out.println(e);
			
		}
		
	}
	
	
	public static HandlerBean findUserAccount(String name) {
		
		HandlerBean handler = null;
		
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = null;
        
            conn = DriverManager.getConnection("jdbc:mysql://localhost/dogshow", "root", "malav");
            
            String Query = "select * from handler_details where hu='"+name+"'";
            
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(Query);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();	  
            while(rs.next()) {
            	
            	handler = new HandlerBean(rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6));
		}
            conn.close();
		}
		catch (Exception e) {
			System.out.println("Connection Failed");
            System.out.println(e);
		}
		return handler;
	}
	
	
	public static  ArrayList<String>  getRoleName() {
		
		ArrayList<String> roles = new ArrayList<String> ();
		
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = null;
        
            conn = DriverManager.getConnection("jdbc:mysql://localhost/dogshow", "root", "malav");
            
            String Query = "select * from roles";
            
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(Query);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();	  
            while(rs.next()) {
            
         roles.add( rs.getString(1));
		}
            conn.close();
		}
		catch (Exception e) {
			System.out.println("Connection Failed");
            System.out.println(e);
		}
		return roles;
	}
	
	
	public static ArrayList<OnlyDogs> dogsHandleByHandler(String s){
		
		ArrayList<OnlyDogs> list = new ArrayList<OnlyDogs>();
		
		try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = null;
        
            conn = DriverManager.getConnection("jdbc:mysql://localhost/dogshow", "root", "malav");
            
            String Query = "select * from handler_details where hu='"+s+"'";
            
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(Query);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();	  
            while(rs.next()) {
            
         String EntryNum = rs.getString(6);
         String dog_name = rs.getString(7);
         String owner_name = rs.getString(8);
         String breed = rs.getString(9);
         String dog_group = rs.getString(10);
         String gender = rs.getString(11);
         String ranking = rs.getString(12);
         
         
         OnlyDogs bs = new OnlyDogs(EntryNum,dog_name,owner_name,breed,dog_group,gender,ranking);
         System.out.println(bs);
        list.add(bs);
         
         
		}
            conn.close();
		}
		catch (Exception e) {
			System.out.println("Connection Failed");
            System.out.println(e);
		}
		
		
		return list;
	}
	
	public static ArrayList<DogBean> showShows(String date) {
		ArrayList<DogBean>	dlist = new ArrayList<DogBean>();
		 Connection conn = null;
		
		try {
			 Class.forName("com.mysql.cj.jdbc.Driver");
	          
	           conn = DriverManager.getConnection("jdbc:mysql://localhost/dogshow", "root", "malav");
	           
	           
          	//String Query = "select * from dog_details where dog_name='"+dname+"'";	
   String Query = "SELECT dateofevent, COUNT(BREED) AS count\r\n" + 
   		",BREED\r\n" + 
   		",count(case when gender = 'Male' then gender end) as male \r\n" + 
   		",count(case when Gender = 'Female' then gender end) as female \r\n" + 
   		",count(case when gender = 'male' and ranking= 'SPECIALTY' then gender end) as SM \r\n" + 
   		",count(case when gender = 'female' and ranking= 'SPECIALTY' then gender end) as SF \r\n" + 
   		"FROM dog_details WHERE dateofevent='"+date+"' GROUP BY breed";
   
          	Statement st = conn.createStatement();
	           ResultSet rs = st.executeQuery(Query);
	           ResultSetMetaData rsmd = rs.getMetaData();
	           int columnCount = rsmd.getColumnCount();	  
	           while(rs.next()) {
	           	
	        String doe = rs.getString("dateofevent");	   
	         String count =   	rs.getString("count");
	         String breed =   	rs.getString("BREED");
	         String male = rs.getString("male");
	         String female = rs.getString("female");
	         String sm = rs.getString("SM");
	         String fm = rs.getString("SF");
	         
	           DogBean ds = new DogBean(doe,count,breed,male,female,sm,fm);
	           dlist.add(ds);
	           }
	           conn.close();   
		}
        
	catch (Exception e) {
		System.out.println("Connection Failed");
    System.out.println(e);
		
	}
          	
		return dlist;
	}
	}
	
		
	

	

