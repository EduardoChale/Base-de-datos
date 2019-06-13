package application;

import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener;
import javafx.collections.FXCollections;

import java.util.Optional;

import application.OperacionesClientes;

public class Main extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		int tipo=0;
		
		
			
	        primaryStage.setTitle("Base de datos");
	        //Creacion de panel principal
	        BorderPane principal=new BorderPane();

	        //Creacion de TextField y Botones para la escena princpal
	        TextField id=new TextField();
	        TextField nombre=new TextField();
	        TextField apellidos=new TextField();
	        TextField direccion=new TextField();
	        RadioButton agregar=new RadioButton("Agregar");
	        RadioButton eliminar=new RadioButton("Eliminar");
	        RadioButton actualizar=new RadioButton("Actualizar");
	        RadioButton mostrar=new RadioButton("Consultar");
	        Button aceptar =new Button("Aceptar");
	        actualizar.setDisable(true);
	        //Agregar las partes del panel
	        principal.setBottom(addGridPane1(agregar,eliminar,actualizar,mostrar,aceptar));
	        principal.setCenter(addGridPane2(id,nombre,apellidos,direccion));
	        principal.setTop(addGridPane3("Clientes"));

	        //implmentar la escena en el stage
	        primaryStage.setScene(new Scene(principal, 700, 500));
	        primaryStage.show();
	        
	        //Creacion de segundo Stage
	        Stage listas=new Stage();
			listas.setTitle("Listas");
			BorderPane conPrincipal =new BorderPane();
			Button elim=new Button("Eliminar");
			
			TableView <Cliente> table =new TableView();
			TableColumn idCol = new TableColumn("Id");
	        idCol.setCellValueFactory(new PropertyValueFactory("clienteId"));
	        TableColumn nombreCol = new TableColumn("Nombre");
	        nombreCol.setCellValueFactory(new PropertyValueFactory("nombre"));
	        TableColumn apellidosCol = new TableColumn("Apellidos");
	        apellidosCol.setCellValueFactory(new PropertyValueFactory("apellidos"));
	        TableColumn direccionCol = new TableColumn("Dirección");
	        direccionCol.setCellValueFactory(new PropertyValueFactory("direccion"));	
	        table.getColumns().setAll(idCol, nombreCol,apellidosCol,direccionCol);
	        table.setPrefWidth(450);
	        table.setPrefHeight(300);
	        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
	        
	       
	        
	        conPrincipal.setTop(addGridPane3("Datos"));
	        conPrincipal.setBottom(addGridPane4(elim));
	        conPrincipal.setCenter(table);
	        
	        listas.setScene(new Scene(conPrincipal, 800, 500));
			
	        //Conexion a la base de datos
	        DBManager accesoBD = new DBManager();
	        OperacionesClientes opCliente = new OperacionesClientes(accesoBD.getConnection());



	      //Accion del radiobutton mostrar
	        mostrar.setOnAction(new EventHandler<ActionEvent>() {


 	        	//Evento del boton
	            @Override
	            public void handle(ActionEvent event) {
	            	agregar.setSelected(false);
	            	eliminar.setSelected(false);
	            	actualizar.setSelected(false);
	            	actualizar.setDisable(true);
	            	direccion.setDisable(false);
	            	nombre.clear();
	            	apellidos.clear();
	            	id.clear();
	            	direccion.clear();
	            	//Cliente regCliente = opCliente.getCliente(10);
	            	 //System.out.println("Nuevo cliente: " + regCliente);
	            }

	        });

	      //Accion del radiobutton actualizar
	        actualizar.setOnAction(new EventHandler<ActionEvent>() {


 	        	//Evento del boton
	            @Override
	            public void handle(ActionEvent event) {
	            	agregar.setSelected(false);
	            	eliminar.setSelected(false);
	            	mostrar.setSelected(false);
	            	
	            	direccion.setDisable(false);
	            	
	            	//Cliente regCliente = opCliente.getCliente(10);
	            	 //System.out.println("Nuevo cliente: " + regCliente);
	            }

	        });

	      //Accion del radiobutton eliminar
	        eliminar.setOnAction(new EventHandler<ActionEvent>() {


 	        	//Evento del boton
	            @Override
	            public void handle(ActionEvent event) {
	            	agregar.setSelected(false);
	            	mostrar.setSelected(false);
	            	actualizar.setSelected(false);
	            	actualizar.setDisable(true);
	            	direccion.setDisable(true);
	            	nombre.clear();
	            	apellidos.clear();
	            	id.clear();
	            	direccion.clear();
	            	//Cliente regCliente = opCliente.getCliente(10);
	            	 //System.out.println("Nuevo cliente: " + regCliente);
	            }

	        });

	        //Accion del radiobutton agregar
	        agregar.setOnAction(new EventHandler<ActionEvent>() {


 	        	//Evento del boton
	            @Override
	            public void handle(ActionEvent event) {
	            	mostrar.setSelected(false);
	            	eliminar.setSelected(false);
	            	actualizar.setSelected(false);
	            	actualizar.setDisable(true);
	            	direccion.setDisable(false);
	            	nombre.clear();
	            	apellidos.clear();
	            	id.clear();
	            	direccion.clear();
	            	//Cliente regCliente = opCliente.getCliente(10);
	            	 //System.out.println("Nuevo cliente: " + regCliente);
	            }

	        });


	        aceptar.setOnAction(new EventHandler<ActionEvent>() {


 	        	//Evento del boton
	            @Override
	            public void handle(ActionEvent event) {

	            	if (mostrar.isSelected()==true){
	            		
	            		 if( id.getText().length()!=0
		            				&& nombre.getText().length()==0
		            				&& apellidos.getText().length()==0
		            				&& direccion.getText().length()==0){
	            			 if(IntOpc(id)==true){

	            				 int ident=IntP(id);
	            				 if(opCliente.getCliente(ident)!=null ){
	            					 
	            					 Cliente regCliente =opCliente.getCliente(ident);
	            					 nombre.setText(String.valueOf(regCliente.getNombre()));
	            					 apellidos.setText(String.valueOf(regCliente.getApellidos()));
	            					 direccion.setText(String.valueOf(regCliente.getDireccion()));
	            					 actualizar.setDisable(false);
	            				 }else{
	            					 error("No existe un cliente con ese id");
	            				 }

	            			 }
	            		 }else{
	            			 
	            			 if(id.getText().length()!=0){
	            			 error("Solo ingrese datos en uno de los campos");
	            		 }else{
	            			 
	            			 if(apellidos.getText().length()!=0 && nombre.getText().length()==0){
	            				 
	            				 String ape=apellidos.getText();

	            				 if(opCliente.getClienteApel(ape)!=null ){
	            					 
	            					ObservableList data=opCliente.getClieCon(ape,"apellidos",1);
	            					table.setItems(data);
	            					listas.show();
	         	            		
	            					 
	            				 }else{
	            					 error("No existe un cliente con ese apellido");
	            				 }
	            			 }else{
	            				 if(nombre.getText().length()!=0 && apellidos.getText().length()==0){
	            					 
	            					 String nom=nombre.getText();

		            				 if(opCliente.getClienteNom(nom)!=null ){
		            					 
		            					 ObservableList data=opCliente.getClieCon(nom,"nombre",2);
		            					 table.setItems(data);
		         	        	        listas.show();

		            				 }else{
		            					 error("No existe un cliente con ese nombre");
		            				 }
	            					 
	            				 }else{
	            					 if(apellidos.getText().length()==0){
	            						 error("Ingresa datos en uno de los campos");
	            					 }else{
	            						 error("Solo ingrese datos en uno de los campos");
	            					 }
	            					 
	            				 }
	            				 
	            			 }
	            		 }
	            		 }

	            		
	            		
	            		
	            		
	            		
	            		
	            	}
	            	if (agregar.isSelected()==true){

	            		if(IntOpc(id)==true){

	            			if( id.getText().length()!=0
	            				&& nombre.getText().length()!=0
	            				&& apellidos.getText().length()!=0
	            				&& direccion.getText().length()!=0){

	            			int ident=IntP(id);
	            			String nom=nombre.getText();
	            			String ape=apellidos.getText();
	            			String direc=direccion.getText();

	            			if(opCliente.getCliente(ident)==null ){
            				opCliente.insertCliente(ident,nom, ape, direc);
	            			alertas("Se ingreso correctamente el nuevo cliente");

	            			}else{
            				error("Existe un cliente con ese id");

	            			}

	            			} else{
	            				alertas("Falta un dato");
	            			}

	            		}


	            	}
	            	if (eliminar.isSelected()==true){


	            		 if( id.getText().length()!=0
		            				&& nombre.getText().length()==0
		            				&& apellidos.getText().length()==0
		            				&& direccion.getText().length()==0){
	            			 if(IntOpc(id)==true){

	            				 int ident=IntP(id);
	            				 if(opCliente.getCliente(ident)!=null ){
	            					 
	            					 opCliente.deleteCliente(ident);
	            					 
	            					 
	            				 }else{
	            					 error("No existe un cliente con ese id");
	            				 }

	            			 }
	            		 }else{
	            			 
	            			 if(id.getText().length()!=0){
	            			 error("Solo ingrese datos en uno de los campos");
	            		 }else{
	            			 
	            			 if(apellidos.getText().length()!=0 && nombre.getText().length()==0){
	            				 
	            				 String ape=apellidos.getText();

	            				 if(opCliente.getClienteApel(ape)!=null ){
	            					 
	            					 
	            					 Alert selec = new Alert(Alert.AlertType.CONFIRMATION);
	            					 selec.setTitle("Advertencia");
	            					 selec.setHeaderText("Es posible que se elimine más de un cliente, "
	            					 		+ "¿Desea continuar con la  operacion?");
	            					 Optional <ButtonType> respuesta=selec.showAndWait();
	            					 
	            					 if (respuesta.get()==ButtonType.OK){
	            					 opCliente.deleteClienteApel(ape);
	            					 
	            					 }
	            					 
	            				 }else{
	            					 error("No existe un cliente con ese apellido");
	            				 }
	            			 }else{
	            				 if(nombre.getText().length()!=0 && apellidos.getText().length()==0){
	            					 
	            					 String nom=nombre.getText();

		            				 if(opCliente.getClienteNom(nom)!=null ){
		            					 Alert selec = new Alert(Alert.AlertType.CONFIRMATION);
		            					 selec.setTitle("Advertencia");
		            					 selec.setHeaderText("Es posible que se elimine más de un cliente, "
		            					 		+ "¿Desea continuar con la  operacion?");
		            					 Optional <ButtonType> respuesta=selec.showAndWait();
		            					 if (respuesta.get()==ButtonType.OK){
		            						 opCliente.deleteClienteNom(nom);
		            					 }
		            					 
		            					 
		            				 }else{
		            					 error("No existe un cliente con ese nombre");
		            				 }
	            					 
	            				 }else{
	            					 if(apellidos.getText().length()==0){
	            						 error("Ingresa datos en uno de los campos");
	            					 }else{
	            						 error("Solo ingrese datos en uno de los campos");
	            					 }
	            					 
	            				 }
	            				 
	            			 }
	            		 }
	            		 }


	            	}
	            	if (actualizar.isSelected()==true){

	            		if(IntOpc(id)==true){

	            			if( id.getText().length()!=0
	            				&& nombre.getText().length()!=0
	            				&& apellidos.getText().length()!=0
	            				&& direccion.getText().length()!=0){

	            			int ident=IntP(id);
	            			String nom=nombre.getText();
	            			String ape=apellidos.getText();
	            			String direc=direccion.getText();

	            			if(opCliente.getCliente(ident)!=null ){
            				opCliente.updateCliente(ident,nom, ape, direc);
	            			alertas("Se actualizo correctamente el cliente");

	            			}else{
            				error("No existe un cliente con ese id");

	            			}

	            			} else{
	            				alertas("Falta un dato");
	            			}

	            		}


	            	}


	            }

	        });
	        
	        elim.setOnAction(new EventHandler<ActionEvent>() {


 	        	//Evento del boton
	            @Override
	            public void handle(ActionEvent event) {
	            	Cliente orgCliente = table.getSelectionModel().getSelectedItem();
		            
		            opCliente.deleteCliente(orgCliente.getClienteId());
		            
		            ObservableList data=opCliente.getClieSin(orgCliente);
					table.setItems(data);
	            }

	        });
	        
	        
	        
	        
	}

	
	
	
	//Creacion de bottom de la escena
	public GridPane addGridPane1(RadioButton agregar,RadioButton eliminar,RadioButton actualizar,RadioButton mostrar,Button aceptar){

    	GridPane boton = new GridPane();
    	boton.setHgap(10);
	    boton.setVgap(10);
	    boton.setPadding(new Insets(20, 10, 30, 10));
	    boton.setStyle("-fx-background-color: #7fdafb;");

	    boton.add(agregar, 5, 0);
	    boton.add(eliminar, 10, 0);
	    boton.add(actualizar, 15, 0);
	    boton.add(mostrar, 20, 0);
	    boton.add(aceptar, 12, 5);
    	return boton;
    }

	//Creacion de center de la escena
	public GridPane addGridPane2(TextField id,TextField nombre,TextField apellido, TextField direccion) {
	    GridPane fields = new GridPane();
	    fields.setHgap(10);
	    fields.setVgap(10);
	    fields.setPadding(new Insets(10, 0, 10, 10));
	    fields.setStyle("-fx-background-color: #7fdafb;");




	    Text idd = new Text("ID:");
	    fields.add(idd,  16, 2);

	    fields.add(id,20,2 );


	    Text nom = new Text("Nombre:");
	    fields.add(nom,  16,6);

	    fields.add(nombre,20,6 );

	    Text ape = new Text("Apellido:");
	    fields.add(ape,  16,10);

	    fields.add(apellido,20,10 );

	    Text dir = new Text("Direccion:");
	    fields.add(dir,  16,14);
	    fields.add(direccion,20,14 );

	    return fields;
	}

	//Creacion de top de la escena
	 public GridPane addGridPane3(String opcion){

	    	GridPane top = new GridPane();
	    	top.setHgap(10);
		    top.setVgap(10);
		    top.setPadding(new Insets(0, 10, 10, 10));
		    top.setStyle("-fx-background-color: #2cc8f8;");

		    Text titulo = new Text(opcion);
		    titulo.setFont(Font.font("Arial", FontWeight.BOLD, 30));
		    top.add(titulo, 25, 2);



	    	return top;
	    }

	 //Creacion de bottom del segundo Stage
	 public GridPane addGridPane4(Button eliminar){

	    	GridPane boton = new GridPane();
	    	boton.setHgap(10);
		    boton.setVgap(10);
		    boton.setPadding(new Insets(20, 10, 30, 10));
		    boton.setStyle("-fx-background-color: #7fdafb;");

		    
		    boton.add(eliminar, 30, 0);
		    
		    
	    	return boton;
	    }
	 
	 //Creacion de Center del segundo Stage
	 public GridPane addGridPane5(TableView tv) {
		    GridPane listas = new GridPane();
		    listas.setHgap(10);
		    listas.setVgap(10);
		    listas.setPadding(new Insets(10, 0, 10, 10));
		    listas.setStyle("-fx-background-color: #7fdafb;");
		    
		    listas.add(tv, 10, 1);		    

		    return listas;
		}
	 
	 //Comprobar valor numerico de id
	 public boolean IntOpc(TextField txf){
	    	boolean resp=true;
	    	int id=0;
	    	try{
	    		id=Integer.parseInt(txf.getText());

	    	}catch(Exception ex ){
	    		resp=false;
	    		Alert alert = new Alert(AlertType.ERROR);
	    		alert.setTitle("Error");
	        	alert.setHeaderText(null);
	        	alert.setContentText("El id solo acepta numeros");

	        	alert.showAndWait();
	        	txf.clear();
	    	}

	    	return resp;
	    }

	 public void error(String opcion){
		 Alert alert = new Alert(AlertType.ERROR);
 		alert.setTitle("Error");
     	alert.setHeaderText(null);
     	alert.setContentText(opcion);

     	alert.showAndWait();
	 }

public void alertas(String opcion){

		 Alert alert = new Alert(AlertType.INFORMATION);
 		alert.setTitle("Operacion exitosa");
     	alert.setHeaderText(null);
     	alert.setContentText(opcion);

     	alert.showAndWait();
	 }

	 //Obtener valor de id
	 public int IntP(TextField txf){
	    	int resp=0;

	    	try{
	    		resp=Integer.parseInt(txf.getText());

	    	}catch(Exception ex ){

	    	}

	    	return resp;
	    }

	public static void main(String[] args) {
        launch(args);
    }
}
