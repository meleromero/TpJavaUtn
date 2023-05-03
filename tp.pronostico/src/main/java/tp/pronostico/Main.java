package tp.pronostico;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
  	    //creo el array que voy a utilizar para almacenar los datos de cómo salió el partido
  	    ArrayList<String> resultado = new ArrayList<String>();
  	  
  	    
  	  try {
  		
  		  //conecto a la db
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bddJavaTp", "root", "");
            Statement statement = conn.createStatement();
            
            //elijo la tabla de resultados de la db
           ResultSet result = statement.executeQuery("SELECT * FROM resultados");
           
           //leo los datos que voy a necesitar para comparar luego en la sentencia if
  	       while (result.next()) {
  	    	   int cantGoles1 = result.getInt("cantGoles1");
  	    	   int cantGoles2 = result.getInt("cantGoles2");
  	    	  
  	    	   //comparo cantidad de goles de cada partido para obtener el resultado del equipo 1
  	    	   if(cantGoles1 > cantGoles2) {
  	    		   resultado.add("gana 1");
  	    	   }  else if (cantGoles1 < cantGoles2) {
  	    		   resultado.add("gana 2");
  	    	   } else {
  	    		   resultado.add("empate");
  	    	   }
  	       } 
  	     
            result.close();
            
             
            
  	       
  	       
  	       
     	         //leo las columnas de la tabla pronosticos
            Statement statement1 = conn.createStatement();   
  	     ResultSet result1 = statement1.executeQuery("SELECT * FROM pronosticos");
  	   
     	        while (result1.next()) {
     	        	 
     	           String gana1 = result1.getString("gana1");
     	           String empata = result1.getString("empata");
     	           String gana2 = result1.getString("gana2");
     	           String persona = result1.getString("participante"); 
     	           int nroPartido = result1.getInt("nPartido");
     	        
     	       //acá vamos a seleccionar la tabla de participantes 
     	        
     	          Statement statement2 = conn.createStatement();
				ResultSet result2 = statement2.executeQuery("SELECT * FROM participantes");
				//creo a los personas en la tabla de participantes desde acá leyendo la tabla de pronosticos
				statement.execute("INSERT IGNORE INTO participantes (nombre, puntos) VALUES ( '" + persona + "'  , 0)");
				
     	        while (result2.next()) {
     	        	int puntos = result2.getInt("puntos");
     	        
     	         //el if para que reconozca el resultado del partido, si ganó, empató o perdió
     	         //según donde esté ubicada la x
     	        String apuesta = null;
     	        
     	         if (gana1.equals("x")) {
     	             apuesta = "gana 1";
     	             
     	         } else if (gana2.equals("x")) {
     	             apuesta = "gana 2";
     	         } else {
     	        	 apuesta = "empate";
     	         }
                 
     	         
     	       //comparo la variable apuesta con resultado de los ifs con el array de resultado
     	         //tomo nroPartido-1 para que me tome los resultados de cada partido del array
     	         if (apuesta == resultado.get(nroPartido-1)) {
     	        	 //actualizo la tabla 
     	        	statement.execute(" UPDATE participantes SET puntos = " + (puntos += 1) +
     	        			" WHERE nombre = '" + persona + "';");
     	        	 
     	        	 
     	         } else {
     	        	 continue;
     	         }
     	        
     	         
     	        
     	         } 

                result2.close();
                    
  	        }  
        result1.close();
        //hago print de los resultados
        Statement statement3 = conn.createStatement();
        ResultSet result3 = statement3.executeQuery("SELECT * FROM participantes");
    	        while (result3.next()) {
    	        	int puntos = result3.getInt("puntos");
    	        	String nombre = result3.getString("nombre");
    	         
    	        
    	       System.out.println(nombre + " tiene " + puntos + " puntos.");
    	       
    	       
               
  	  } 
      result3.close();
      statement.close();
      conn.close();
     	        }
                catch (SQLException e) {
     	          e.printStackTrace();
     	      } catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
     	 }
  	  }