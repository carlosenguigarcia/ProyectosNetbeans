/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;


import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.stage.StageStyle;
import modelo.Casa;
import modelo.Chalet;
import modelo.Duplex;
import modelo.Piso;
import  java.sql.*;

/**
 * FXML Controller class
 *
 * @author CARLOS
 */
public class InmobiliariaController implements Initializable {

    @FXML
    private TextField txtReferencia;
    @FXML
    private Button btnRegistrar;
    @FXML
    private RadioButton rdbPiso;
    @FXML
    private RadioButton rdbChalet;
    @FXML
    private RadioButton rdbDuplex;
    @FXML
    private Button btnActualizar;
    @FXML
    private TextField txtPrecio;
    @FXML
    private TextField txtSuperficie;
    @FXML
    private TextField txtHabitaciones;
    @FXML
    private TextField txtTam2;
    @FXML
    private TextField txtTam1;
    @FXML
    private TextField txtParcela;
    @FXML
    private TextField txtPlanta;
    @FXML
    private Button btnLimpiar;
    @FXML
    private ComboBox<String> Combo;
    @FXML
    private Button btnBorrarTodas;
    @FXML
    private Button btnBorrarCasa;
    @FXML
    private ToggleGroup tipo;
    @FXML
    private ListView<Casa> listview;
    @FXML
    private AnchorPane ventana_principal;

    private ObservableList<Casa> Casas;
    
   
    private Connection cn;
    private Statement st;
   
    private ResultSet rs;
      
    
   
    Piso pis;
    Chalet cha;
    Duplex dupl;
    
  
    ObservableList<String> opciones = FXCollections.observableArrayList("Subir un 5%", "Bajar un 5%");
    @FXML
    private Button btnCargar;
      
    
     /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
  
      Combo.setItems(opciones);
      
     Casas = FXCollections.observableArrayList();
        listview.setItems(Casas);   
        txtReferencia.setDisable(true);
    }    

    @FXML
    private void registrar(ActionEvent event) throws SQLException{
        try {
 
        Alert alerta;
        
        if (txtPrecio.getText().isEmpty()){
            
        
        alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Error");
        alerta.setHeaderText("");
        alerta.setContentText("El campo precio no puede estar vacío");
        alerta.initStyle(StageStyle.UTILITY);
        alerta.showAndWait();
        
        
        }else if (txtSuperficie.getText().isEmpty()){
        
        alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Error");
        alerta.setHeaderText("");
        alerta.setContentText("El campo superficie no puede estar vacío");
        alerta.initStyle(StageStyle.UTILITY);
        alerta.showAndWait();
        
        
        }else if (txtHabitaciones.getText().isEmpty()){
        
        alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Error");
        alerta.setHeaderText("");
        alerta.setContentText("El campo habitaciones no puede estar vacío");
        alerta.initStyle(StageStyle.UTILITY);
        alerta.showAndWait();
          }
        
        //*** Opciones si seleccionamos el tipCasa (radiobuutons) Piso. ***  
        
        if (rdbPiso.isSelected()){
              
              
//Si el campo está vacío.
              if (txtPlanta.getText().isEmpty()){
              
//Saltamos una alerta que diga "El campo planta no puede estar vacío".                  
              alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("Error");
        alerta.setHeaderText("");
        alerta.setContentText("El campo planta no puede estar vacío");
        alerta.initStyle(StageStyle.UTILITY);
        alerta.showAndWait();  
        
//si el campo Planta no está vacío, recogemos los valores.
              }else{
              
            int ref = 0;        
            conectar();            
            st = cn.createStatement();
            rs = st.executeQuery("SELECT MAX(referencia)+1 referencia FROM ENGUI_GARCIA_CARLOS_PROG09");
                while (rs.next()){
                    ref = rs.getInt("referencia");
                }
        desconectar();   
              int precio = Integer.parseInt(this.txtPrecio.getText());
              int superf = Integer.parseInt(this.txtSuperficie.getText());
              int habit = Integer.parseInt(this.txtHabitaciones.getText());
              int planta = Integer.parseInt(this.txtPlanta.getText());
              
//y creamos un objeto de la clase Piso con dichos parámetros.
              
           
             if(ref==0){
                ref=ref+1;                
            }
            
            conectar();
            //Creamos el objeto de tipo piso con los valores obtenidos y los añadimos a la Observablelist
            
            Piso p1 = new Piso(ref, precio, superf, habit, planta, "'P'");
            Casas.add(p1);
            
            
            
            //Insertamos el registro en la BBDD con la sentencia correspondiente.
            
            st=cn.createStatement();                               
            st.executeUpdate("INSERT INTO ENGUI_GARCIA_CARLOS_PROG09 VALUES (" + ref +
                    "," + txtPrecio.getText() + "," + txtSuperficie.getText() + "," + txtHabitaciones.getText() + "," + 
                    txtPlanta.getText() + "," + null + "," + null + "," + null + "," + "'P'" + ")");
            desconectar();
             
              }
              
        }

            //*** Opciones si seleccionamos el tipCasa (radiobuutons) Chalet.***                  
    
            if (rdbChalet.isSelected()){
              
              
//Si el campo está vacío.
              if (txtParcela.getText().isEmpty()){
              
//Saltamos una alerta que diga "El campo planta no puede estar vacío".                  
            alerta = new Alert(Alert.AlertType.WARNING);
            alerta.setTitle("Advertencia");
            alerta.setHeaderText("");
            alerta.setContentText("El campo parcela no puede estar vacío");
            alerta.initStyle(StageStyle.UTILITY);
            alerta.showAndWait();  
        
//si el campo Parcela no está vacío, recogemos los valores.
              }else{
        int ref = 0;
        
        conectar();            
        st = cn.createStatement();
        rs = st.executeQuery("SELECT MAX(referencia)+1 referencia FROM ENGUI_GARCIA_CARLOS_PROG09");
            while (rs.next()){
                ref = rs.getInt("referencia");
            }
        desconectar();   
             if(ref==0){
                ref=ref+1;                
            } 
              int precio = Integer.parseInt(this.txtPrecio.getText());
              int superf = Integer.parseInt(this.txtSuperficie.getText());
              int habit = Integer.parseInt(this.txtHabitaciones.getText());
              int parcela = Integer.parseInt(this.txtParcela.getText());
              
//y creamos un objeto de la clase Piso con dichos parámetros.
             conectar();
             cha =  new Chalet(ref, precio, superf, habit,parcela, "'C'");
             





//Lo añadimos al Observablelist.
                  Casas.add(cha);
//Insertamos el registro en la BBDD con la sentencia correspondiente.
             st=cn.createStatement(); 
             st.executeUpdate("INSERT INTO ENGUI_GARCIA_CARLOS_PROG09 VALUES ("
                + ref + ", " + txtPrecio.getText() + ", " + txtSuperficie.getText() + ", " + txtHabitaciones.getText() + ", "+ null +
                ", "+ txtParcela.getText()+ ", " + null +", "+ null + ", " + "'C'" + ");");             
             desconectar();
             }
              
            }
               
            //*** Opciones si seleccionamos el tipCasa (radiobuutons) Duplex.***
              
              if (rdbDuplex.isSelected()){
              
              
//Si el campo Tamaño parcela 1 está vacío.
              if (txtTam1.getText().isEmpty()){
              
//Saltamos una alerta que diga "El campo Tamaño Planta 1 no puede estar vacío".                  
        alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle("Advertencia");
        alerta.setHeaderText("");
        alerta.setContentText("El campo Tamaño Planta 1 no puede estar vacío");
        alerta.initStyle(StageStyle.UTILITY);
        alerta.showAndWait();  
        
//Si el campo Tamaño parcel 2 está vacío.
              }else if (txtTam2.getText().isEmpty()){
              
//Saltamos una alerta que diga "El campo Tamaño Planta 2 no puede estar vacío".             
        alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle("Advertencia");
        alerta.setHeaderText("");
        alerta.setContentText("El campo Tamaño Planta 2 no puede estar vacío");
        alerta.initStyle(StageStyle.UTILITY);
        alerta.showAndWait(); 
                  
              }else{
        int ref = 0;
        
        conectar();            
        st = cn.createStatement();
        rs = st.executeQuery("SELECT MAX(referencia)+1 referencia FROM ENGUI_GARCIA_CARLOS_PROG09");
            while (rs.next()){
                ref = rs.getInt("referencia");
            }
        desconectar();   
 //si el campo Tamañi planta 1 y Planta 2 no están vacías, recogemos los valores.
              int precio = Integer.parseInt(this.txtPrecio.getText());
              int superf = Integer.parseInt(this.txtSuperficie.getText());
              int habit = Integer.parseInt(this.txtHabitaciones.getText());
              int tam1 = Integer.parseInt(this.txtTam1.getText());
              int tam2 = Integer.parseInt(this.txtTam2.getText());
              
//y creamos un objeto de la clase Piso con dichos parámetros.
             conectar(); 

             dupl =  new Duplex(ref, precio, superf, habit,tam1, tam2, "'D'");
            //Lo añadimos al Observablelist.
            Casas.add(dupl);     
            //Insertamos el registro en la BBDD con la sentencia correspondiente.
            st=cn.createStatement(); 
            st.executeUpdate("INSERT INTO ENGUI_GARCIA_CARLOS_PROG09 VALUES ("
                        + ref + ", " + txtPrecio.getText() + ", " + txtSuperficie.getText() + ", " + txtHabitaciones.getText() +", " + null +
                         ", " + null +  ", "+ txtTam1.getText() + ", " + txtTam2.getText() + ", " + "'D'" +");");
            desconectar();

            }
    
            
        
        }
        }catch (NumberFormatException e) {
        Alert alerta;
        alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle("FORMATO INCORRECTO");
        alerta.setHeaderText("");
        alerta.setContentText("Introduzca un número válido o complete los campos");
        alerta.showAndWait();
        
        }
        
        }
        
        
    @FXML
    private void actualizar(ActionEvent event) {
        
     int x = listview.getSelectionModel().getSelectedIndex();
        Casa c1 = Casas.get(x);
        /*
        En este metodo he implementado con la setencia Update la modificación de los objetos
        también en la base de datos
        */
        
        if (x<0){
            
                Alert alerta;
                alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("ERROR en la actualización");
                alerta.setHeaderText("");
                alerta.setContentText("No ha seleccionado ninguna vivienda");
                alerta.showAndWait();
        }
        if (Combo.getSelectionModel().getSelectedIndex()<0){
                Alert alerta;
                alerta = new Alert(Alert.AlertType.ERROR);
                alerta.setTitle("ERROR en la actualización");
                alerta.setHeaderText("");
                alerta.setContentText("Seleccione un descuento a aplicar");
                alerta.showAndWait();
        }else if (Combo.getSelectionModel().getSelectedIndex()==0){
            try {
                c1.subirPrecio();
                Casas.set(x, c1);
                int y = c1.getReferencia();

                String sql = "UPDATE ENGUI_GARCIA_CARLOS_PROG09 SET precio = '" + c1.getPrecio()+"' " +"WHERE referencia = " + y +"";
                conectar();
                    st = cn.createStatement();
                    st.executeUpdate(sql);
                desconectar();
            } catch (SQLException e) {
                System.out.println(e.getErrorCode());
                System.out.println(e.getMessage());
            }
        }else if (Combo.getSelectionModel().getSelectedIndex()==1){
            try {
                c1.bajarPrecio();
                Casas.set(x, c1);
                int y = c1.getReferencia();
        
                String sql = "UPDATE ENGUI_GARCIA_CARLOS_PROG09 SET precio = '" + c1.getPrecio()+"' " +"WHERE referencia = " + y +"";
                conectar();
                st = cn.createStatement();
                st.executeUpdate(sql);
                    if(st.executeUpdate(sql)>0){
                        System.out.println("Se ha actualizado correctamente");
                    }else{
                        System.out.println("El precio de la vivienda no se ha actualidazo correctamente");
                    }
                desconectar();
            } catch (SQLException e) {
                System.out.println(e.getErrorCode());
                System.out.println(e.getMessage());
            }
        }

    }
             


         
    

    @FXML
    private void limpiar(ActionEvent event) {

//Seteamos todos los campos ""
        txtReferencia.setText("");
        txtPrecio.setText("");
        txtSuperficie.setText("");
        txtHabitaciones.setText("");
        txtPlanta.setText("");
        txtParcela.setText("");
        txtTam1.setText("");
        txtTam2.setText("");

        
    }

    @FXML
    private void borrarTodas(ActionEvent event) throws SQLException{
        
        
        Casas.clear();
        
        conectar();
                st = cn.createStatement();
                st.executeUpdate("DELETE FROM ENGUI_GARCIA_CARLOS_PROG09");     
                st.close();         
        desconectar();
        
        Alert alerta;
        alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Información");
        alerta.setHeaderText("");
        alerta.setContentText("Se han borrado todas las Casas");
        alerta.initStyle(StageStyle.UTILITY);
        alerta.showAndWait();
        
    }

    @FXML
    private void borrarCasa(ActionEvent event)  throws  SQLException{
        
        int a = listview.getSelectionModel().getSelectedIndex();
         int b = Casas.get(a).getReferencia();
        if (a != -1) {
    Casas.remove(a);
    
    conectar();
                st = cn.createStatement();
                st.executeUpdate("DELETE FROM ENGUI_GARCIA_CARLOS_PROG09 WHERE referencia = " + b + "");     
                st.close();
                desconectar();
    
    Alert alerta;
        alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Información");
        alerta.setHeaderText("");
        alerta.setContentText("Se ha borrado la Casa");
        alerta.initStyle(StageStyle.UTILITY);
        alerta.showAndWait();
        
        }else{
        
          Alert alerta;
        alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Información");
        alerta.setHeaderText("");
        alerta.setContentText("Por favor,\nSeleccione una Casa");
        alerta.initStyle(StageStyle.UTILITY);
        alerta.showAndWait();
        
        }
        
    }

    public ComboBox<String> getCombo() {
        return Combo;
    }

    public void setCombo(ComboBox<String> Combo) {
        this.Combo = Combo;
    }

    @FXML
    private void quitarPiso(ActionEvent event) {
        
        
        if (rdbPiso.isSelected()){
        txtReferencia.setDisable(true);
        txtPrecio.setDisable(false);
        txtSuperficie.setDisable(false);
        txtHabitaciones.setDisable(false);
        txtTam1.setDisable(true);
        txtTam2.setDisable(true);
        txtParcela.setDisable(true);
        txtPlanta.setDisable(false);
        }
        
    }

    @FXML
    private void quitarChalet(ActionEvent event) {
        
        if (rdbChalet.isSelected()){
        txtReferencia.setDisable(true);
        txtPrecio.setDisable(false);
        txtSuperficie.setDisable(false);
        txtHabitaciones.setDisable(false);
        txtTam1.setDisable(true);
        txtTam2.setDisable(true);
        txtParcela.setDisable(false);
        txtPlanta.setDisable(true);
        }
        
    }

    @FXML
    private void quitarDuplex(ActionEvent event) {
        
        if (rdbDuplex.isSelected()){
        txtReferencia.setDisable(true);    
        txtPrecio.setDisable(false);
        txtSuperficie.setDisable(false);
        txtHabitaciones.setDisable(false);
        txtTam1.setDisable(false);
        txtTam2.setDisable(false);
        txtParcela.setDisable(true);
        txtPlanta.setDisable(true);
        }
        
    }

    @FXML
    private void cargarBasedeDatos(ActionEvent event) throws SQLException{
        
        Casas.clear();
        try {
            
            String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://remotemysql.com:3306/qlW2AGII30";
    String user = "qlW2AGII30";
    String password = "jkAScG6UNp";
    
    int regmax=0;
            
            cn = DriverManager.getConnection(url, user, password);
            st = cn.createStatement();
            rs = st.executeQuery("SELECT MAX(referencia) referencia FROM ENGUI_GARCIA_CARLOS_PROG09");
            while (rs.next()){
            regmax = rs.getInt("referencia");
            }
            
            
         if(regmax!=0){
            
                    String tipo;                                        
                    rs= st.executeQuery("SELECT * FROM ENGUI_GARCIA_CARLOS_PROG09");
                    while(rs.next()){
                        tipo=rs.getString(9);
                        
                            switch (tipo) {
                                case "P":
                                    Piso p7 = new Piso(rs.getInt(1),rs.getDouble(2),rs.getDouble(3),rs.getInt(4),rs.getInt(5),rs.getString(9));
                                    Casas.add(p7);
                                    break;

                                case "C":
                                    Chalet c7 = new Chalet(rs.getInt(1),rs.getDouble(2),rs.getDouble(3),rs.getInt(4),rs.getDouble(6),rs.getString(9));
                                    Casas.add(c7);
                                    break;

                                case "D":
                                    Duplex d7 = new Duplex(rs.getInt(1),rs.getDouble(2),rs.getDouble(3),rs.getInt(4),rs.getDouble(7),rs.getDouble(8),rs.getString(9));
                                    Casas.add(d7);
                                    break;

                                default:
                                    break;
                            }

                    }
            
        cn.close();
        st.close();
        rs.close();
        
         }else{
                Alert alerta;
                alerta = new Alert(Alert.AlertType.INFORMATION);
                alerta.setTitle("ATENCIÓN");
                alerta.setHeaderText("");
                alerta.setContentText("No hay registros");
                alerta.showAndWait(); 
            }
                    
        } catch (SQLException ex){
            System.out.println(ex.getErrorCode());
            System.out.println(ex.getMessage());
        }
        
        
    }



public void conectar() throws SQLException{ 
    
         
        
    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://remotemysql.com:3306/qlW2AGII30";
    String user = "qlW2AGII30";
    String password = "jkAScG6UNp";
  
    cn = DriverManager.getConnection(url, user, password); 
    System.out.println("Conectado");
}

public void desconectar() throws SQLException{
       try {
           cn.close();
           st.close();
           rs.close();
           System.out.println("Te has desconectado");
           } catch (SQLException ex) {
            ex.printStackTrace();
          }   
    }
    


}



