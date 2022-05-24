package prj3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class EsquiFx extends Application {

    private static ConnexioBD conn = new ConnexioBD();
    static Connection ct;
    
    private static ArrayList Clients = new ArrayList<Client>();
    private static ArrayList Colectius = new ArrayList<CursColectiu>();
    private static ArrayList Competitius = new ArrayList<CursCompetitiu>();
    private static ArrayList Individuals = new ArrayList<CursIndividual>();
    
    
    TextField txtDni = new TextField();
    TextField txtNom = new TextField();
    TextField txtCognom = new TextField();
    TextField txtCognom2 = new TextField();
    TextField txtData_naix = new TextField();
    TextField txtUsuari = new TextField();
    TextField txtContrasenya = new TextField();
    TextField txtSexe = new TextField();
    TextField txtMail = new TextField();
    TextField txtMaxParticipantsCol  = new TextField();
    TextField txtPreuCol  = new TextField();
    
    TextField txtIdCurs = new TextField();
    TextField txtNomCurs = new TextField();
    TextField txtData = new TextField();
    TextField txtIdMonitor = new TextField();
    
    public static void main(String[] args) {
        conn.connexio();
        launch();
    }
    
    @Override
    public void start(Stage escenari) throws SQLException {
        // Provem diferents contenidors o layouts de JavaFX
        
        //VBox contenedor = new VBox();// es `pot crear adalt o abaix
        //HBox contenedor = new HBox();
        // hi han varios contenedors: Pane, StackPane, HBox, VBox, FlowPane.
        
        /*contenedor.getChildren().addAll(btn1, btn2, btn3);
        contenedor.setAlignment(Pos.CENTER);
        contenedor.setSpacing(10);*/
        
        BorderPane bp = new BorderPane();
        bp.setTop(partSuperior());
        
        
        
        bp.setLeft(lateralEsquerra());
        
        bp.setRight(lateralDret());
        
        bp.setCenter(formulariCentral());
        
        Scene escena = new Scene(bp);
        
        Pane inferior = partInferior();
        bp.setBottom(inferior);
        BorderPane.setMargin(inferior, new Insets(20, 20, 20, 20));
        
        escenari.setScene(escena);
        escenari.setMinHeight(720);
        escenari.setMinWidth(1280);
        escenari.show();
    } 
    
    private GridPane formulariCentral(){
        GridPane gp = new GridPane();
        gp.setAlignment(Pos.CENTER);
        
        Label lblDni = new Label ("DNI");
        Label lblNom= new Label("Nom");
        Label lblCognom= new Label("Cognom");
        Label lblCognom2 = new Label ("Cognom2");
        Label lblData= new Label("Data naixement");
        Label lblUsuari= new Label("Usuari");
        Label lblContrasenya = new Label ("Contrasenya");
        Label lblSexe = new Label("Sexe");
        Label lblMail= new Label("Mail");
        
        txtDni = new TextField();
        txtNom = new TextField();
        txtCognom = new TextField();
        txtCognom2 = new TextField();
        txtData_naix = new TextField();
        txtUsuari = new TextField();
        txtContrasenya = new TextField();
        txtSexe = new TextField();
        txtMail = new TextField();
        
        gp.add(lblDni, 0, 0);
        gp.add(txtDni, 1, 0);
        gp.add(lblNom, 0, 1);
        gp.add(txtNom, 1, 1);
        gp.add(lblCognom, 0, 2);
        gp.add(txtCognom, 1, 2);
        gp.add(lblCognom2, 0, 3);
        gp.add(txtCognom2, 1, 3);
        gp.add(lblData, 0, 4);
        gp.add(txtData, 1, 4);
        gp.add(lblUsuari, 0, 5);
        gp.add(txtUsuari, 1, 5);        
        gp.add(lblContrasenya, 0, 6);
        gp.add(txtContrasenya, 1, 6);        
        gp.add(lblSexe, 0, 7);
        gp.add(txtSexe, 1, 7);        
        gp.add(lblMail, 0, 8);
        gp.add(txtMail, 1, 8);        
        
        return gp;
    }
    
    private Pane partSuperior(){
        HBox hb = new HBox();
        hb.getChildren().add(new Label ("Llogar cursos d'esquí"));
        hb.setAlignment(Pos.CENTER);
        
        return hb;
    }
    
    private Pane partInferior(){
        Button btn1 = new Button("Consultar");
        Button btn2 = new Button("Llogar");
        Button btn3 = new Button("Desinscriure");
        
        HBox hbinf = new HBox();
        hbinf.setSpacing(10);
        hbinf.getSpacing();
        hbinf.getChildren().addAll(btn1, btn2, btn3);
        hbinf.setAlignment(Pos.CENTER);
        
        return hbinf;
    }
    
    private Pane lateralEsquerra() throws SQLException{
        VBox vb = new VBox();
        vb.getChildren().add(new Label("Navegador"));
        vb.setAlignment(Pos.CENTER);
        
        TableView<Client> tblVehicles = new TableView<>();
        TableColumn<Client, String> colDni =  new TableColumn<>("DNI");
        TableColumn<Client, String> colNom =  new TableColumn<>("Nom");
        TableColumn<Client, String> colCognom =  new TableColumn<>("Cognom");
        TableColumn<Client, String> colCognom2 =  new TableColumn<>("Cognom2");
        TableColumn<Client, LocalDate> dataNaix =  new TableColumn<>("Data naixement");
        TableColumn<Client, String> usuari =  new TableColumn<>("Usuari");
        TableColumn<Client, String> pass =  new TableColumn<>("Contrasenya");
        TableColumn<Client, String> sex =  new TableColumn<>("Sexe");
        TableColumn<Client, String> mail =  new TableColumn<>("Mail");
        
        tblVehicles.getColumns().addAll(colDni, colNom, colCognom);
        
        colDni.setCellValueFactory(new PropertyValueFactory<>("dni"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colCognom.setCellValueFactory(new PropertyValueFactory<>("cognom"));
        colCognom2.setCellValueFactory(new PropertyValueFactory<>("cognom2"));
        dataNaix.setCellValueFactory(new PropertyValueFactory<>("datanaixemnt"));
        usuari.setCellValueFactory(new PropertyValueFactory<>("usuari"));
        pass.setCellValueFactory(new PropertyValueFactory<>("contrasenya"));
        sex.setCellValueFactory(new PropertyValueFactory<>("sexe"));
        mail.setCellValueFactory(new PropertyValueFactory<>("email"));
        
        this.getClient();
        
        tblVehicles.getItems().addAll(Clients);
        
        vb.getChildren().add(tblVehicles);
        
        tblVehicles.getSelectionModel().selectedItemProperty().addListener(new ChangeListener(){
        @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue){
            Client client = (Client) newValue;
      
                if (client != null){
                // movem a la pantalla les dades del client seleccionat
                    txtDni.setText(client.getDni()); //txtMarca es un text field
                    txtNom.setText(client.getNom());
                    txtCognom.setText(client.getCognom());
                    txtCognom2.setText(client.getCognom2());
                    txtData.setText(client.getDatanaixemnt().toString());
                    txtUsuari.setText(client.getUsuari());
                    txtContrasenya.setText(client.getContrasenya());
                    txtSexe.setText(client.getSexe());
                    txtMail.setText(client.getEmail());
                }
            }
        });
        
        return vb;
    }
    
    /**
     * @throws SQLException
     */
    public static void getClient() throws SQLException {

        ct = conn.getConnexioBD();

        String SQL = "SELECT * FROM client;";

        PreparedStatement ps = ct.prepareStatement(SQL);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Client cl = new Client();
            
            cl.setDni(rs.getString("dni_usuari"));
            cl.setNom(rs.getString("nom"));
            cl.setCognom(rs.getString("cognom"));
            cl.setCognom2(rs.getString("cognom2"));
            cl.setDatanaixemnt(rs.getDate("data_naix").toLocalDate());
            cl.setUsuari(rs.getString("usuari"));
            cl.setContrasenya(rs.getString("contrasenya"));
            cl.setSexe(rs.getString("sexe"));
            cl.setEmail(rs.getString("email"));
   
            Clients.add(cl);
        }
    }    
    
    private VBox lateralDret() throws SQLException {
        VBox vb = new VBox();

        Label lbl = new Label("Lateral dret");

        // vb.getChildren().addAll(lbl);
        vb.setAlignment(Pos.CENTER);

        TabPane tp = new TabPane();

        Tab tab1 = new Tab("Colectiu", cursosColectius());
        Tab tab2 = new Tab("Competicio", cursosCompeticio());
        Tab tab3 = new Tab("Individual", cursosIndividuals());

        tp.getTabs().addAll(tab1, tab2, tab3);

        vb.getChildren().addAll(lbl, tp);

        return vb;
    }
    
    private Pane cursosColectius() throws SQLException{
        
        VBox vb = new VBox();
        
        TableView<CursColectiu> tblCursosCol = new TableView<>();
        TableColumn<CursColectiu, String> colIdCurs =  new TableColumn<>("ID");
        TableColumn<CursColectiu, String> colNom =  new TableColumn<>("Nom");
        TableColumn<CursColectiu, String> colData =  new TableColumn<>("Data Inici");
        TableColumn<CursColectiu, String> colIdMonitor = new TableColumn<>("Número Monitor");
        TableColumn<CursColectiu, String> colMaxPart = new TableColumn<>("Participants");
        TableColumn<CursColectiu, String> colPreuCol = new TableColumn<>("Preu");
        
        tblCursosCol.getColumns().addAll(colIdCurs, colNom, colData, colIdMonitor, colMaxPart, colPreuCol);
        
        colIdCurs.setCellValueFactory(new PropertyValueFactory<>("id_curs"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom_curs"));
        colData.setCellValueFactory(new PropertyValueFactory<>("data_inici"));
        colIdMonitor.setCellValueFactory(new PropertyValueFactory<>("dni_monitor"));
        colMaxPart.setCellValueFactory(new PropertyValueFactory<>("aforament"));
        colPreuCol.setCellValueFactory(new PropertyValueFactory<>("preuColectiu"));
        
         this.getCursColectiu();
        tblCursosCol.getItems().addAll(Colectius);
        System.out.println(Colectius.toString());
        vb.getChildren().add(tblCursosCol);
        
        tblCursosCol.getSelectionModel().selectedItemProperty().addListener(new ChangeListener(){
        @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue){
            CursColectiu crsCol = (CursColectiu) newValue;
      
                if (crsCol != null){
                    txtIdCurs.setText(Integer.toString(crsCol.getId_curs()));
                    txtNomCurs.setText(crsCol.getNom_curs());
                    txtData.setText(String.valueOf(crsCol.getData_inici()));
                    txtIdMonitor.setText(String.valueOf(crsCol.getDni_monitor()));
                    txtMaxParticipantsCol.setText(String.valueOf(crsCol.getAforament()));
                    txtPreuCol.setText(String.valueOf(crsCol.getPreuColectiu()));
                }
            }
        });
        
        return vb;
    }
    
        /**
     * @throws SQLException
     */
    public static void getCursColectiu() throws SQLException {

        

        String SQL = "SELECT cl.id_curs as idCl, cl.aforament as aforament, cl.preu as preu, cu.id_curs as idCu, cu.dni_monitor as dniCu, cu.nom as nom,"
                + " cu.descripcio as descripcio, cu.data_inici as dataIni, cu.preu_hora as preuHora"
                + " FROM curs_colectiu as cl"
                + " INNER JOIN curs as cu ON cl.id_curs = cu.id_curs ;";

        PreparedStatement ps = ct.prepareStatement(SQL);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            CursColectiu cl = new CursColectiu();
            
            cl.setId_curs(rs.getInt("idCl"));
            cl.setAforament(rs.getInt("aforament"));
            cl.setPreuColectiu(rs.getInt("preu"));
            cl.setDni_monitor(rs.getString("dniCu"));
            cl.setNom_curs(rs.getString("nom"));
            cl.setDescripcio(rs.getString("descripcio"));
            cl.setData_inici(rs.getDate("dataIni").toLocalDate());
            cl.setPreu_hora(rs.getInt("preuHora"));
            
            Colectius.add(cl);
            System.out.println(cl.toString());
        }
    }  
    
        /**
     * @throws SQLException
     */
    public static void getCursCompetitiu() throws SQLException {

        

        String SQL = "SELECT cl.id_curs as idCl, cl.nivell as nivell, cl.preu_comp as preu, cu.id_curs as idCu, cu.dni_monitor as dniCu, cu.nom as nom,"
                + " cu.descripcio as descripcio, cu.data_inici as dataIni, cu.preu_hora as preuHora"
                + " FROM curs_competitiu as cl"
                + " INNER JOIN curs as cu ON cl.id_curs = cu.id_curs ;";

        PreparedStatement ps = ct.prepareStatement(SQL);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            CursCompetitiu cl = new CursCompetitiu();
            
            cl.setId_curs(rs.getInt("idCl"));
            cl.setNivell(rs.getString("nivell"));
            cl.setPreuCompeticio(rs.getInt("preu"));
            cl.setDni_monitor(rs.getString("dniCu"));
            cl.setNom_curs(rs.getString("nom"));
            cl.setDescripcio(rs.getString("descripcio"));
            cl.setData_inici(rs.getDate("dataIni").toLocalDate());
            cl.setPreu_hora(rs.getInt("preuHora"));
            
            Competitius.add(cl);
            System.out.println(cl.toString());
        }
    }      

    public static void getCursIndividual() throws SQLException {

        

        String SQL = "SELECT cl.id_curs as idCl, cu.id_curs as idCu, cu.dni_monitor as dniCu, cu.nom as nom,"
                + " cu.descripcio as descripcio, cu.data_inici as dataIni, cu.preu_hora as preuHora"
                + " FROM curs_individual as cl"
                + " INNER JOIN curs as cu ON cl.id_curs = cu.id_curs ;";

        PreparedStatement ps = ct.prepareStatement(SQL);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            CursIndividual cl = new CursIndividual();
            
            cl.setId_curs(rs.getInt("idCl"));
            cl.setDni_monitor(rs.getString("dniCu"));
            cl.setNom_curs(rs.getString("nom"));
            cl.setDescripcio(rs.getString("descripcio"));
            cl.setData_inici(rs.getDate("dataIni").toLocalDate());
            cl.setPreu_hora(rs.getInt("preuHora"));
            
            Individuals.add(cl);
            System.out.println(cl.toString());
        }
    }         
    
    private Pane cursosCompeticio() throws SQLException{
        
        VBox vb = new VBox();
        
        TableView<CursColectiu> tblCursosCol = new TableView<>();
        TableColumn<CursColectiu, String> colIdCurs =  new TableColumn<>("ID");
        TableColumn<CursColectiu, String> colNom =  new TableColumn<>("Nom");
        TableColumn<CursColectiu, String> colData =  new TableColumn<>("Data Inici");
        TableColumn<CursColectiu, String> colIdMonitor = new TableColumn<>("Número Monitor");
        TableColumn<CursColectiu, String> colMaxPart = new TableColumn<>("Nivell");
        TableColumn<CursColectiu, String> colPreuCol = new TableColumn<>("Preu");
        
        tblCursosCol.getColumns().addAll(colIdCurs, colNom, colData, colIdMonitor, colMaxPart, colPreuCol);
        
        colIdCurs.setCellValueFactory(new PropertyValueFactory<>("id_curs"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom_curs"));
        colData.setCellValueFactory(new PropertyValueFactory<>("data_inici"));
        colIdMonitor.setCellValueFactory(new PropertyValueFactory<>("dni_monitor"));
        colMaxPart.setCellValueFactory(new PropertyValueFactory<>("nivell"));
        colPreuCol.setCellValueFactory(new PropertyValueFactory<>("preuCompeticio"));
        
         this.getCursCompetitiu();
        tblCursosCol.getItems().addAll(Competitius);
        System.out.println(Competitius.toString());
        vb.getChildren().add(tblCursosCol);
        
        tblCursosCol.getSelectionModel().selectedItemProperty().addListener(new ChangeListener(){
        @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue){
            CursCompetitiu crsCol = (CursCompetitiu) newValue;
      
                if (crsCol != null){
                    txtIdCurs.setText(Integer.toString(crsCol.getId_curs()));
                    txtNomCurs.setText(crsCol.getNom_curs());
                    txtData.setText(String.valueOf(crsCol.getData_inici()));
                    txtIdMonitor.setText(String.valueOf(crsCol.getDni_monitor()));
                    txtMaxParticipantsCol.setText(String.valueOf(crsCol.getNivell()));
                    txtPreuCol.setText(String.valueOf(crsCol.getPreuCompeticio()));
                }
            }
        });
        
        return vb;
    }
        
    
    private Pane cursosIndividuals() throws SQLException{
      VBox vb = new VBox();
        
        TableView<CursColectiu> tblCursosCol = new TableView<>();
        TableColumn<CursColectiu, String> colIdCurs =  new TableColumn<>("ID");
        TableColumn<CursColectiu, String> colNom =  new TableColumn<>("Nom");
        TableColumn<CursColectiu, String> colData =  new TableColumn<>("Data Inici");
        TableColumn<CursColectiu, String> colIdMonitor = new TableColumn<>("Número Monitor");
        TableColumn<CursColectiu, String> colMaxPart = new TableColumn<>("Preu");
        
        
        tblCursosCol.getColumns().addAll(colIdCurs, colNom, colData, colIdMonitor, colMaxPart);
        
        colIdCurs.setCellValueFactory(new PropertyValueFactory<>("id_curs"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom_curs"));
        colData.setCellValueFactory(new PropertyValueFactory<>("data_inici"));
        colIdMonitor.setCellValueFactory(new PropertyValueFactory<>("dni_monitor"));
        colMaxPart.setCellValueFactory(new PropertyValueFactory<>("preu_hora"));
        
         this.getCursIndividual();
        tblCursosCol.getItems().addAll(Individuals);
        System.out.println(Individuals.toString());
        vb.getChildren().add(tblCursosCol);
        
        tblCursosCol.getSelectionModel().selectedItemProperty().addListener(new ChangeListener(){
        @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue){
            CursIndividual crsCol = (CursIndividual) newValue;
      
                if (crsCol != null){
                    txtIdCurs.setText(Integer.toString(crsCol.getId_curs()));
                    txtNomCurs.setText(crsCol.getNom_curs());
                    txtData.setText(String.valueOf(crsCol.getData_inici()));
                    txtIdMonitor.setText(String.valueOf(crsCol.getDni_monitor()));
                    txtMaxParticipantsCol.setText(String.valueOf(crsCol.getPreu_hora()));

                }
            }
        });
        
        return vb;
    }
}