package twitterkafka;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;





public class Postgresconnection extends KafkaConnection {


	  public static void main(String[] args){
		  String sm_handle;
		  Postgresconnection postgresconnection=new Postgresconnection();
			 
		
while(true){
                               Connection c = null;
                               Statement stmt = null;
try {
		//	 Class.forName("dataSourceClassName", "org.postgresql.ds.PGSimpleDataSource");
                //c = DriverManager.getConnection("dataSource.url","jdbc:postgresql://10.0.14.249:5432/twitter?user=postgres");
       
                               Class.forName("org.postgresql.ds.PGSimpleDataSource");
	
                              c = DriverManager.getConnection("jdbc:postgresql://10.0.14.249:5432/twitter?user=postgres");
			     c = DriverManager.getConnection("jdbc:postgresql://10.0.14.249:5432/tests?user=postgres");
                               c.setAutoCommit(false);
                              // c.setAutoCommit(false);
                               stmt = c.createStatement();
ResultSet rs = stmt.executeQuery( "select  * from entity_social_media;");
//ResultSet rs = stmt.executeQuery( "select  * from social_media_types a ,entity_social_media b, persons c where a.sm_type_id=b.sm_type_id and b.entity_id=c.person_id;");
while ( rs.next() ) {
			        	 
			            sm_handle = rs.getString("sm_handle");
			            
			            System.out.println(sm_handle);
try{
			            postgresconnection.r(sm_handle);
}
catch(Exception e){
				    e.printStackTrace();
}
}
			         rs.close();
			         stmt.close();
			         c.close();
} 
catch (Exception e){
			         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
			         System.exit(0);
}
			       
}

}

	}//continues loop


