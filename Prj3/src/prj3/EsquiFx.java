package prj3;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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

    private static TabPane tp = new TabPane();
    private static ArrayList clientsCol = new ArrayList <Client>(); 
    private static ArrayList clientsCom = new ArrayList <Client>(); 
    private static ArrayList clientsIndiv = new ArrayList <Client>(); 
    private static CursColectiu crsCol = new CursColectiu(); 
    private static CursCompetitiu crsCom = new CursCompetitiu(); 
    private static CursIndividual crsIndv = new CursIndividual(); 
    private static ConnexioBD conn = new ConnexioBD();
    static Connection ct;
    static TableView<CursColectiu> tblCursosCol;
    static TableView<CursCompetitiu> tblCursosCo2;
    static TableView<CursIndividual> tblCursosCo3;
    static TableView<Client> tblVehicles;
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
    TextField txtPreu = new TextField();
    TextField txtNivell = new TextField();
    TextField txtAforament = new TextField();
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
        Label lblPreu= new Label("Preu");
        Label lblNivell= new Label("Nivell");
        Label lblAforament= new Label("Aforament");
        
        txtDni = new TextField();
        txtNom = new TextField();
        txtCognom = new TextField();
        txtCognom2 = new TextField();
        txtData_naix = new TextField();
        txtUsuari = new TextField();
        txtContrasenya = new TextField();
        txtSexe = new TextField();
        txtMail = new TextField();
        txtPreu = new TextField();
        txtNivell = new TextField();        
        txtAforament = new TextField();
        
        
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
        gp.add(lblPreu, 0, 9);
        gp.add(txtPreu, 1, 9);        
        gp.add(lblNivell, 0, 10);
        gp.add(txtNivell, 1, 10);        
        gp.add(lblAforament, 0, 11);
        gp.add(txtAforament, 1, 11);        
                        
        
        return gp;
    }
    
    private Pane partSuperior(){
        HBox hb = new HBox();
        hb.getChildren().add(new Label ("Llogar cursos d'esquí"));
        hb.setAlignment(Pos.CENTER);
        
        return hb;
    }
    
    private Pane partInferior(){
        //Button btn1 = new Button("Consultar");
        
        Button btn2 = new Button("Llogar");
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    insert();
                } catch (SQLException ex) {
                    Logger.getLogger(EsquiFx.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        Button btn3 = new Button("Desinscriure");
        btn3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                
            }
        });            
        HBox hbinf = new HBox();
        hbinf.setSpacing(10);
        hbinf.getSpacing();
        hbinf.getChildren().addAll(btn2, btn3);
        hbinf.setAlignment(Pos.CENTER);
        
        return hbinf;
    }
    
    private Pane lateralEsquerra() throws SQLException{
        VBox vb = new VBox();
        vb.getChildren().add(new Label("Navegador"));
        vb.setAlignment(Pos.CENTER);
        
        tblVehicles = new TableView<>();
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
    
    /**
     * @throws SQLException
     */
    public static void insert() throws SQLException {

        ct = conn.getConnexioBD();
        clientsCol.clear();
        clientsCom.clear();
        clientsIndiv.clear();
        String SQL = "SELECT * FROM client WHERE dni_usuari IN( SELECT dni_usuari FROM client_colectiu );";
        String SQL2 = "SELECT * FROM client WHERE dni_usuari IN( SELECT dni_usuari FROM client_individual );";
        String SQL3 = "SELECT * FROM client WHERE dni_usuari IN( SELECT dni_usuari FROM client_federat );";
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
            
            clientsCol.add(cl);
        }
        PreparedStatement ps2 = ct.prepareStatement(SQL2);
        ResultSet rs2 = ps2.executeQuery();
        while (rs2.next()) {
            Client cl = new Client();
            
            cl.setDni(rs2.getString("dni_usuari"));
            cl.setNom(rs2.getString("nom"));
            cl.setCognom(rs2.getString("cognom"));
            cl.setCognom2(rs2.getString("cognom2"));
            cl.setDatanaixemnt(rs2.getDate("data_naix").toLocalDate());
            cl.setUsuari(rs2.getString("usuari"));
            cl.setContrasenya(rs2.getString("contrasenya"));
            cl.setSexe(rs2.getString("sexe"));
            cl.setEmail(rs2.getString("email"));
   
            clientsIndiv.add(cl);
        }      
        PreparedStatement ps3 = ct.prepareStatement(SQL3);
        ResultSet rs3 = ps3.executeQuery();
        while (rs3.next()) {
            Client cl = new Client();
            
            cl.setDni(rs3.getString("dni_usuari"));
            cl.setNom(rs3.getString("nom"));
            cl.setCognom(rs3.getString("cognom"));
            cl.setCognom2(rs3.getString("cognom2"));
            cl.setDatanaixemnt(rs3.getDate("data_naix").toLocalDate());
            cl.setUsuari(rs3.getString("usuari"));
            cl.setContrasenya(rs3.getString("contrasenya"));
            cl.setSexe(rs3.getString("sexe"));
            cl.setEmail(rs3.getString("email"));
   
            clientsCom.add(cl);
        }
        System.out.println("col" + clientsCol.toString());
        
        System.out.println("com" + clientsCom.toString());
        System.out.println("indv" + clientsIndiv.toString());
        if(!tblVehicles.getSelectionModel().isEmpty() && (!tblCursosCol.getSelectionModel().isEmpty() || !tblCursosCo2.getSelectionModel().isEmpty() || !tblCursosCo3.getSelectionModel().isEmpty()) ){
            System.out.println(clientsCol.contains((Client)tblVehicles.getSelectionModel().getSelectedItem()));
            System.out.println(clientsCom.contains(tblVehicles.getSelectionModel().getSelectedItem()));
            System.out.println(clientsIndiv.contains(tblVehicles.getSelectionModel().getSelectedItem()));
            System.out.println(tblVehicles.getSelectionModel().getSelectedItem());
            
                    
            if(clientsCol.contains(tblVehicles.getSelectionModel().getSelectedItem())){
                if(!tblCursosCol.getSelectionModel().isEmpty()){
                    String sqlLloguer_col = "INSERT INTO lloguercurs_colectiu"
                        + "(id_curs,dni_usuari,data_lloguer,preu,max_clients) "
                        + "VALUES(?,?,?,?,?)";
                    try (PreparedStatement ps4 = ct.prepareStatement(sqlLloguer_col)){
                        ps4.setInt(1, tblCursosCol.getSelectionModel().getSelectedItem().getId_curs());
                        ps4.setString(2, tblVehicles.getSelectionModel().getSelectedItem().getDni());
                        ps4.setDate(3, Date.valueOf(LocalDate.now()));
                        ps4.setDouble(4, tblCursosCol.getSelectionModel().getSelectedItem().getPreuColectiu());
                        ps4.setDouble(5, tblCursosCol.getSelectionModel().getSelectedItem().getAforament());
                        ps4.executeUpdate();
                    }catch (SQLException ex){
                         Logger.getLogger(EsquiFx.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                } else if (!tblCursosCo3.getSelectionModel().isEmpty()){
                    String sqlLloguer_col = "INSERT INTO lloguercurs_individual"
                        + "(id_curs,dni_usuari,data_lloguer,preu) "
                        + "VALUES(?,?,?,?)";                    	
                    try (PreparedStatement ps4 = ct.prepareStatement(sqlLloguer_col)){
                        ps4.setInt(1, tblCursosCo3.getSelectionModel().getSelectedItem().getId_curs());
                        ps4.setString(2, tblVehicles.getSelectionModel().getSelectedItem().getDni());
                        ps4.setDate(3, Date.valueOf(LocalDate.now()));
                        ps4.setDouble(4, tblCursosCo3.getSelectionModel().getSelectedItem().getPreuHora());
                        ps4.executeUpdate();
                    }catch (SQLException ex){
                         Logger.getLogger(EsquiFx.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    alert("El client colectiu només pot anar a cursos colectius i individuals");
                }
               
                
            } else if (clientsCom.contains(tblVehicles.getSelectionModel().getSelectedItem())){
                if(!tblCursosCo2.getSelectionModel().isEmpty()){
                    String sqlLloguer_col = "INSERT INTO lloguercurs_competitiu"
                        + "(id_curs,dni_usuari,data_lloguer,preu,nivell) "
                        + "VALUES(?,?,?,?,?)";
                    
                    try (PreparedStatement ps4 = ct.prepareStatement(sqlLloguer_col)){
                        ps4.setInt(1, tblCursosCo2.getSelectionModel().getSelectedItem().getId_curs());
                        ps4.setString(2, tblVehicles.getSelectionModel().getSelectedItem().getDni());
                        ps4.setDate(3, Date.valueOf(LocalDate.now()));
                        ps4.setDouble(4, tblCursosCo2.getSelectionModel().getSelectedItem().getPreuCompeticio());
                        ps4.setString(5, tblCursosCo2.getSelectionModel().getSelectedItem().getNivell());
                        ps4.executeUpdate();
                    }catch (SQLException ex){
                         Logger.getLogger(EsquiFx.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    alert("El client competitiu/federat només pot anar a cursos competitius/federats");
                }
            } else if (clientsIndiv.contains(tblVehicles.getSelectionModel().getSelectedItem())){
                if(!tblCursosCol.getSelectionModel().isEmpty()){
                    String sqlLloguer_col = "INSERT INTO lloguercurs_colectiu"
                        + "(id_curs,dni_usuari,data_lloguer,preu,max_clients) "
                        + "VALUES(?,?,?,?,?)";
                    try (PreparedStatement ps4 = ct.prepareStatement(sqlLloguer_col)){
                        ps4.setInt(1, tblCursosCol.getSelectionModel().getSelectedItem().getId_curs());
                        ps4.setString(2, tblVehicles.getSelectionModel().getSelectedItem().getDni());
                        ps4.setDate(3, Date.valueOf(LocalDate.now()));
                        ps4.setDouble(4, tblCursosCol.getSelectionModel().getSelectedItem().getPreuColectiu());
                        ps4.setDouble(5, tblCursosCol.getSelectionModel().getSelectedItem().getAforament());
                        ps4.executeUpdate();
                    }catch (SQLException ex){
                         Logger.getLogger(EsquiFx.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                } else if (!tblCursosCo3.getSelectionModel().isEmpty()){
                    String sqlLloguer_col = "INSERT INTO lloguercurs_individual"
                        + "(id_curs,dni_usuari,data_lloguer,preu) "
                        + "VALUES(?,?,?,?)";                    	
                    try (PreparedStatement ps4 = ct.prepareStatement(sqlLloguer_col)){
                        ps4.setInt(1, tblCursosCo3.getSelectionModel().getSelectedItem().getId_curs());
                        ps4.setString(2, tblVehicles.getSelectionModel().getSelectedItem().getDni());
                        ps4.setDate(3, Date.valueOf(LocalDate.now()));
                        ps4.setDouble(4, tblCursosCo3.getSelectionModel().getSelectedItem().getPreuHora());
                        ps4.executeUpdate();
                    }catch (SQLException ex){
                         Logger.getLogger(EsquiFx.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    alert("El client individual només pot anar a cursos individuals i colectius");
                }
            }
        } else {
            alert("Has de seleccionar un client i un curs");
        }
      //  tp.getSelectionModel().getSelectedItem().getText();
      //  tblCursosCol.getSelectionModel().getSelectedItem();
      //  tblCursosCo2.getSelectionModel().getSelectedItem();
      //  tblCursosCo3.getSelectionModel().getSelectedItem();
      //  tblVehicles.getSelectionModel().getSelectedItem();
        
        
    }      
    
    private static void alert(String missatge){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("INFO");
        alert.setContentText(missatge);
        alert.showAndWait();
    }
    
    private VBox lateralDret() throws SQLException {
        VBox vb = new VBox();

        Label lbl = new Label("Lateral dret");

        // vb.getChildren().addAll(lbl);
        vb.setAlignment(Pos.CENTER);

        tp = new TabPane();
        Tab tab1 = new Tab("Colectiu", cursosColectius());
        tab1.setClosable(false);
        Tab tab2 = new Tab("Competicio", cursosCompeticio());
        tab2.setClosable(false);
        Tab tab3 = new Tab("Individual", cursosIndividuals());
        tab3.setClosable(false);

        tp.getTabs().addAll(tab1, tab2, tab3);

        vb.getChildren().addAll(lbl, tp);

        tp.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Tab>() { 
        @Override 
        public void changed(ObservableValue<? extends Tab> observable, Tab oldTab, Tab newTab) {
            if(oldTab.equals (tab1)) {            
                tblCursosCol.getSelectionModel().clearSelection();
                txtNivell.setText("");
                txtAforament.setText("");
                txtPreu.setText("");
            }
            if(oldTab.equals (tab2)) {            
                tblCursosCo2.getSelectionModel().clearSelection();
                txtAforament.setText("");
                txtNivell.setText("");
                txtPreu.setText("");
            }
            if(oldTab.equals (tab3)) {            
                tblCursosCo3.getSelectionModel().clearSelection();
                txtAforament.setText("");
                txtNivell.setText("");
                txtPreu.setText("");
            }
    }
});
        return vb;
    }
    
    private Pane cursosColectius() throws SQLException{
        
        VBox vb = new VBox();
        
        tblCursosCol = new TableView<>();
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
            crsCol = (CursColectiu) newValue;
      
                if (crsCol != null){
                    txtIdCurs.setText(Integer.toString(crsCol.getId_curs()));
                    txtNomCurs.setText(crsCol.getNom_curs());                    
                    txtIdMonitor.setText(String.valueOf(crsCol.getDni_monitor()));
                    txtAforament.setText(String.valueOf(crsCol.getAforament()));
                    txtPreu.setText(String.valueOf(crsCol.getPreuColectiu()));
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
        
        tblCursosCo2 = new TableView<>();
        TableColumn<CursCompetitiu, String> colIdCurs =  new TableColumn<>("ID");
        TableColumn<CursCompetitiu, String> colNom =  new TableColumn<>("Nom");
        TableColumn<CursCompetitiu, String> colData =  new TableColumn<>("Data Inici");
        TableColumn<CursCompetitiu, String> colIdMonitor = new TableColumn<>("Número Monitor");
        TableColumn<CursCompetitiu, String> colMaxPart = new TableColumn<>("Nivell");
        TableColumn<CursCompetitiu, String> colPreuCol = new TableColumn<>("Preu");
        
        tblCursosCo2.getColumns().addAll(colIdCurs, colNom, colData, colIdMonitor, colMaxPart, colPreuCol);
        
        colIdCurs.setCellValueFactory(new PropertyValueFactory<>("id_curs"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom_curs"));
        colData.setCellValueFactory(new PropertyValueFactory<>("data_inici"));
        colIdMonitor.setCellValueFactory(new PropertyValueFactory<>("dni_monitor"));
        colMaxPart.setCellValueFactory(new PropertyValueFactory<>("nivell"));
        colPreuCol.setCellValueFactory(new PropertyValueFactory<>("preuCompeticio"));
        
        this.getCursCompetitiu();
        tblCursosCo2.getItems().addAll(Competitius);
        System.out.println(Competitius.toString());
        vb.getChildren().add(tblCursosCo2);
        
        tblCursosCo2.getSelectionModel().selectedItemProperty().addListener(new ChangeListener(){
        @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue){
             crsCom = (CursCompetitiu) newValue;
      
                if (crsCom != null){
                    txtIdCurs.setText(Integer.toString(crsCom.getId_curs()));
                    txtNomCurs.setText(crsCom.getNom_curs());
                    txtIdMonitor.setText(String.valueOf(crsCom.getDni_monitor()));
                    txtNivell.setText(String.valueOf(crsCom.getNivell()));
                    txtPreu.setText(String.valueOf(crsCom.getPreuCompeticio()));
                }
            }
            
        });
        
        return vb;
    }
        
    
    private Pane cursosIndividuals() throws SQLException{
      VBox vb = new VBox();
        
        tblCursosCo3 = new TableView<>();
        TableColumn<CursIndividual, String> colIdCurs =  new TableColumn<>("ID");
        TableColumn<CursIndividual, String> colNom =  new TableColumn<>("Nom");
        TableColumn<CursIndividual, String> colData =  new TableColumn<>("Data Inici");
        TableColumn<CursIndividual, String> colIdMonitor = new TableColumn<>("Número Monitor");
        TableColumn<CursIndividual, String> colMaxPart = new TableColumn<>("Preu");
        
        
        tblCursosCo3.getColumns().addAll(colIdCurs, colNom, colData, colIdMonitor, colMaxPart);
        
        colIdCurs.setCellValueFactory(new PropertyValueFactory<>("id_curs"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom_curs"));
        colData.setCellValueFactory(new PropertyValueFactory<>("data_inici"));
        colIdMonitor.setCellValueFactory(new PropertyValueFactory<>("dni_monitor"));
        colMaxPart.setCellValueFactory(new PropertyValueFactory<>("preu_hora"));
        
        this.getCursIndividual();
        tblCursosCo3.getItems().addAll(Individuals);
        System.out.println(Individuals.toString());
        vb.getChildren().add(tblCursosCo3);
        
        tblCursosCo3.getSelectionModel().selectedItemProperty().addListener(new ChangeListener(){
        @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue){
            crsIndv = (CursIndividual) newValue;
      
                if (crsIndv != null){
                    txtIdCurs.setText(Integer.toString(crsIndv.getId_curs()));
                    txtNomCurs.setText(crsIndv.getNom_curs());
                    txtIdMonitor.setText(String.valueOf(crsIndv.getDni_monitor()));
                    txtPreu.setText(String.valueOf(crsIndv.getPreu_hora()));

                }
            }
        });
        return vb;
    }
}