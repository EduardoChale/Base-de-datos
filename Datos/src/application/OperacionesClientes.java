package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class OperacionesClientes {

    Connection connection;
    SQLException excep=new SQLException();
    int tipo=0;
    public OperacionesClientes(Connection conn){
        this.connection = conn;
    }

    public SQLException getExcep(){
    	
    	return excep;
    }
    public Cliente getCliente(int id){
        int clienteId = 0;
        String nombre = "", apellidos = "", direccion = "";

        String query = "SELECT clienteid, nombre, apellidos, direccion " +
                "FROM cliente " +
                "WHERE clienteID = " + id;

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                clienteId = rs.getInt("clienteid");
                nombre = rs.getString("nombre");
                apellidos = rs.getString("apellidos");
                direccion = rs.getString("direccion");
            }else{
            	return null;
            }

            //System.out.println(clienteId + ", " + nombre + " " + apellidos + ", " + direccion);

            return new Cliente(clienteId, nombre, apellidos, direccion);
        }
        catch (java.sql.SQLException ex){
            ex.printStackTrace();
            System.out.println("SQLException:" + ex.getMessage());
            System.out.println("SQLState:" + ex.getSQLState());
            System.out.println("VendorError:" + ex.getErrorCode());

            return null;
        }
    }
    public Cliente getClienteApel(String apel){
        int clienteId = 0;
        String nombre = "", apellidos = apel, direccion = "";
        
        String query = "SELECT clienteid, nombre, apellidos, direccion " +
                "FROM cliente " +
                "WHERE apellidos = '" + apellidos+"'";

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                clienteId = rs.getInt("clienteid");
                nombre = rs.getString("nombre");
                apellidos = rs.getString("apellidos");
                direccion = rs.getString("direccion");
            }else{
            	return null;
            }

            //System.out.println(clienteId + ", " + nombre + " " + apellidos + ", " + direccion);

            return new Cliente(clienteId, nombre, apellidos, direccion);
        }
        catch (java.sql.SQLException ex){
            ex.printStackTrace();
            System.out.println("SQLException:" + ex.getMessage());
            System.out.println("SQLState:" + ex.getSQLState());
            System.out.println("VendorError:" + ex.getErrorCode());

            return null;
        }
    }
    
    
    public Cliente getClienteNom(String nom){
        int clienteId = 0;
        String nombre = "", apellidos="", direccion = "";
        
        String query = "SELECT clienteid, nombre, apellidos, direccion " +
                "FROM cliente " +
                "WHERE nombre = '" + nom+"'";

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                clienteId = rs.getInt("clienteid");
                nombre = rs.getString("nombre");
                apellidos = rs.getString("apellidos");
                direccion = rs.getString("direccion");
            }else{
            	return null;
            }

            //System.out.println(clienteId + ", " + nombre + " " + apellidos + ", " + direccion);

            return new Cliente(clienteId, nombre, apellidos, direccion);
        }
        catch (java.sql.SQLException ex){
            ex.printStackTrace();
            System.out.println("SQLException:" + ex.getMessage());
            System.out.println("SQLState:" + ex.getSQLState());
            System.out.println("VendorError:" + ex.getErrorCode());

            return null;
        }
    }

    public ObservableList getClieSin(Cliente orgCliente){
        int clienteId = 0;
        String nombre = "", apellidos="", direccion = "";
        ArrayList list= new ArrayList();
        Cliente cliente=orgCliente;
        
        String query="";
        if(this.tipo==1){
        	String dato=cliente.getApellidos();
        	query = "SELECT clienteid, nombre, apellidos, direccion " +
                    "FROM cliente " +
                    "WHERE apellidos = '" + dato+"'";
        }else{
        	String dato=cliente.getNombre();
        	 query = "SELECT clienteid, nombre, apellidos, direccion " +
                "FROM cliente " +
                "WHERE nombre = '" + dato+"'";
        }
        
        

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            
           
            	while(rs.next()){
                clienteId = rs.getInt("clienteid");
                nombre = rs.getString("nombre");
                apellidos = rs.getString("apellidos");
                direccion = rs.getString("direccion");
                list.add(new Cliente(clienteId,nombre,apellidos,direccion));
                
            	}
                

            //System.out.println(clienteId + ", " + nombre + " " + apellidos + ", " + direccion);
            ObservableList data = FXCollections.observableList(list);
            return data;
        }
        catch (java.sql.SQLException ex){
            ex.printStackTrace();
            System.out.println("SQLException:" + ex.getMessage());
            System.out.println("SQLState:" + ex.getSQLState());
            System.out.println("VendorError:" + ex.getErrorCode());

            return null;
        }
    }
    
    public ObservableList getClieCon(String ape,String tipo,int opcion){
        int clienteId = 0;
        String nombre = "", apellidos="", direccion = "";
        ArrayList list= new ArrayList();
        String query="";
        this.tipo=opcion;
        if(opcion==1){
        	query = "SELECT clienteid, nombre, apellidos, direccion " +
                "FROM cliente " +
                "WHERE "+tipo+"= '" + ape+"'";
        }else{
        	query= "SELECT clienteid, nombre, apellidos, direccion " +
                    "FROM cliente " +
                    "WHERE "+tipo+"= '" + ape+"'";
        }
        

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            
            	while(rs.next()){
            		clienteId = rs.getInt("clienteid");
                nombre = rs.getString("nombre");
                apellidos = rs.getString("apellidos");
                direccion = rs.getString("direccion");
                list.add(new Cliente(clienteId,nombre,apellidos,direccion));
            	}
            

            //System.out.println(clienteId + ", " + nombre + " " + apellidos + ", " + direccion);
            ObservableList data = FXCollections.observableList(list);
            return data;
        }
        catch (java.sql.SQLException ex){
            ex.printStackTrace();
            System.out.println("SQLException:" + ex.getMessage());
            System.out.println("SQLState:" + ex.getSQLState());
            System.out.println("VendorError:" + ex.getErrorCode());

            return null;
        }
    }
    
    
    
    public int deleteCliente(int id){
        int clienteId;
        String nombre, apellidos, direccion;

        String query = "delete from cliente where clienteID = " + id;

        int numRegs = 0;
        try {
            Statement stmt = connection.createStatement();
            numRegs = stmt.executeUpdate(query);

            System.out.println("Cantidad de registros afectados: " + numRegs);
            excep=null;
            alertas("Se borro exitosamente el cliente");
        }
        catch (java.sql.SQLException ex){
            ex.printStackTrace();
            System.out.println("SQLException:" + ex.getMessage());
            System.out.println("SQLState:" + ex.getSQLState());
            System.out.println("VendorError:" + ex.getErrorCode());
            excep=ex;
            
            error(ex);
        }

        return numRegs;
    }

    public int deleteClienteApel(String apel){
        int clienteId;
        String nombre, apellidos=apel, direccion;

        String query = "delete from cliente where apellidos ='" + apel+"'";

        int numRegs = 0;
        try {
            Statement stmt = connection.createStatement();
            numRegs = stmt.executeUpdate(query);

            System.out.println("Cantidad de registros afectados: " + numRegs);
            excep=null;
            alertas("Se borro exitosamente el cliente");
        }
        catch (java.sql.SQLException ex){
            ex.printStackTrace();
            System.out.println("SQLException:" + ex.getMessage());
            System.out.println("SQLState:" + ex.getSQLState());
            System.out.println("VendorError:" + ex.getErrorCode());
            excep=ex;
            error(ex);
        }

        return numRegs;
    }
    
    public int deleteClienteNom(String nom){
        int clienteId;
        String nombre, apellidos, direccion;

        String query = "delete from cliente where nombre ='" + nom+"'";

        int numRegs = 0;
        try {
            Statement stmt = connection.createStatement();
            numRegs = stmt.executeUpdate(query);

            System.out.println("Cantidad de registros afectados: " + numRegs);
            excep=null;
            alertas("Se borro exitosamente el cliente");
        }
        catch (java.sql.SQLException ex){
            ex.printStackTrace();
            System.out.println("SQLException:" + ex.getMessage());
            System.out.println("SQLState:" + ex.getSQLState());
            System.out.println("VendorError:" + ex.getErrorCode());
            excep=ex;
            error(ex);
        }

        return numRegs;
    }
    
    public int updateCliente(int id,String nombre,String apellidos,String direccion){
        

        String query = "update cliente "+
        			"set nombre='"+nombre+
        			"' , apellidos='"+apellidos+
        			"' , direccion='"+direccion+
        			"' where clienteID="+id;
        

        int numRegs = 0;
        try {
            Statement stmt = connection.createStatement();
            numRegs = stmt.executeUpdate(query);

            System.out.println("Cantidad de registros afectados: " + numRegs);
        }
        catch (java.sql.SQLException ex){
            ex.printStackTrace();
            System.out.println("SQLException:" + ex.getMessage());
            System.out.println("SQLState:" + ex.getSQLState());
            System.out.println("VendorError:" + ex.getErrorCode());
        }

        return numRegs;
    }
    
    public int insertCliente(String nombre, String apellidos, String direccion){

        String query = "insert into cliente(nombre, apellidos, direccion) " +
                "values ('" + nombre + "', '" + apellidos + "', '" + direccion + "')";

      /*  insert into cliente(nombre, apellidos, direccion
                values ('Jorge', 'Estrada', 'Lázaro Cárdenas 123')  */

        int numRegs = 0;
        try {
            Statement stmt = connection.createStatement();
            numRegs = stmt.executeUpdate(query);

            System.out.println("Cantidad de registros afectados: " + numRegs);

        }
        catch (java.sql.SQLException ex){
            ex.printStackTrace();
            System.out.println("SQLException:" + ex.getMessage());
            System.out.println("SQLState:" + ex.getSQLState());
            System.out.println("VendorError:" + ex.getErrorCode());
        }

        return numRegs;
    }
    
    public int insertCliente(int id, String nombre, String apellidos, String direccion){
    	
        String query = "insert into cliente(clienteID, nombre, apellidos, direccion) " +
                "values ("+id+", '" + nombre + "', '" + apellidos + "', '" + direccion + "')";

      /*  insert into cliente(nombre, apellidos, direccion
                values ('Jorge', 'Estrada', 'Lázaro Cárdenas 123')  */

        int numRegs = 0;
        try {
            Statement stmt = connection.createStatement();
            numRegs = stmt.executeUpdate(query);

            System.out.println("Cantidad de registros afectados: " + numRegs);

        }
        catch (java.sql.SQLException ex){
            ex.printStackTrace();
            System.out.println("SQLException:" + ex.getMessage());
            System.out.println("SQLState:" + ex.getSQLState());
            System.out.println("VendorError:" + ex.getErrorCode());
        }

        return numRegs;
    }
    public void alertas(String opcion){

		 Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Operacion exitosa");
    	alert.setHeaderText(null);
    	alert.setContentText(opcion);

    	alert.showAndWait();
	 }
    public void error(SQLException excep){
		 Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
    	alert.setHeaderText(null);
    	alert.setContentText("SQLException:" + excep.getMessage()+"SQLState:" + excep.getSQLState()+"VendorError:" + excep.getErrorCode());

    	alert.showAndWait();
	 }
}
