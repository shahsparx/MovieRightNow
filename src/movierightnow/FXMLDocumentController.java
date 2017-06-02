/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movierightnow;

import com.jfoenix.controls.JFXTextField;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Dialog;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Pair;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author shahsparx
 */
public class FXMLDocumentController implements Initializable {
    
    String OMDB_API_KEY="";
    
    ObservableList<MRN> list = FXCollections.observableArrayList();
    ObservableList<MRN> list2 = FXCollections.observableArrayList();
    private Label label;
    @FXML
    private TableView<MRN> tv;
    @FXML
    private TableColumn<MRN, String> mncol;
    @FXML
    private TableColumn<MRN, String> rcol;
    @FXML
    private TableColumn<MRN, String> ycol;
    @FXML
    private TableColumn<MRN, String> gcol;
    @FXML
    private TableColumn<MRN, String> pcol;
    @FXML
    private TableColumn<MRN, String> dcol;
    @FXML
    private TableColumn<MRN, String> actcol;
    @FXML
    private TableColumn<MRN, String> awardcol;
    @FXML
    private JFXTextField loc;
    
    private static String[] paths;
    @FXML
    private Button locbutton;
    @FXML
    private TableView<MRN> tvman;
    @FXML
    private TableColumn<MRN, String> mnman;
    @FXML
    private TableColumn<MRN, String> rman;
    @FXML
    private TableColumn<MRN, String> yman;
    @FXML
    private TableColumn<MRN, String> gman;
    @FXML
    private TableColumn<MRN, String> pman;
    @FXML
    private TableColumn<MRN, String> dman;
    @FXML
    private TableColumn<MRN, String> acman;
    @FXML
    private TableColumn<MRN, String> awman;
    @FXML
    private Button manbutton;
    @FXML
    private JFXTextField mnmanfield;
    @FXML
    private JFXTextField ymanfield;
    @FXML
    private MenuItem close;
    @FXML
    private MenuItem shahsparx;
    
   
    
    private void initCol()
    {
        mncol.setCellValueFactory(new PropertyValueFactory<>("movieName"));
        rcol.setCellValueFactory(new PropertyValueFactory<>("rating"));
        ycol.setCellValueFactory(new PropertyValueFactory<>("year"));
        gcol.setCellValueFactory(new PropertyValueFactory<>("genre"));
        pcol.setCellValueFactory(new PropertyValueFactory<>("plot"));
        pcol.setCellFactory(new Callback<TableColumn<MRN, String>, TableCell<MRN, String>>() {

        @Override
        public TableCell<MRN, String> call(
                TableColumn<MRN, String> param) {
            TableCell<MRN, String> cell = new TableCell<>();
            Text text = new Text();
            text.setFill(Color.WHITE);
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(cell.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell ;
        }

    });
        dcol.setCellValueFactory(new PropertyValueFactory<>("director"));
        actcol.setCellValueFactory(new PropertyValueFactory<>("actor"));
        actcol.setCellFactory(new Callback<TableColumn<MRN, String>, TableCell<MRN, String>>() {

        @Override
        public TableCell<MRN, String> call(
                TableColumn<MRN, String> param) {
            TableCell<MRN, String> cell = new TableCell<>();
            Text text = new Text();
            text.setFill(Color.WHITE);
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(cell.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell ;
        }

    });
        awardcol.setCellValueFactory(new PropertyValueFactory<>("award"));
    }
    
    private void initColman()
    {
        mnman.setCellValueFactory(new PropertyValueFactory<>("movieName"));
        rman.setCellValueFactory(new PropertyValueFactory<>("rating"));
        yman.setCellValueFactory(new PropertyValueFactory<>("year"));
        gman.setCellValueFactory(new PropertyValueFactory<>("genre"));
        pman.setCellValueFactory(new PropertyValueFactory<>("plot"));
        pman.setCellFactory(new Callback<TableColumn<MRN, String>, TableCell<MRN, String>>() {

        @Override
        public TableCell<MRN, String> call(
                TableColumn<MRN, String> param) {
            TableCell<MRN, String> cell = new TableCell<>();
            Text text = new Text();
            text.setFill(Color.WHITE);
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(cell.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell ;
        }

    });
        dman.setCellValueFactory(new PropertyValueFactory<>("director"));
        acman.setCellValueFactory(new PropertyValueFactory<>("actor"));
        acman.setCellFactory(new Callback<TableColumn<MRN, String>, TableCell<MRN, String>>() {

        @Override
        public TableCell<MRN, String> call(
                TableColumn<MRN, String> param) {
            TableCell<MRN, String> cell = new TableCell<>();
            Text text = new Text();
            text.setFill(Color.WHITE);
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(cell.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell ;
        }

    });
        awman.setCellValueFactory(new PropertyValueFactory<>("award"));
    }
    
    private static String readAll(Reader rd) throws IOException 
    {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }

        return sb.toString();
    }
    
    public static JSONObject readJsonFromUrl(String url) throws IOException, ParseException {
        InputStream is=null;

        try {
            is = new URL(url).openStream();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONParser parser = new JSONParser();
            Object obj = parser.parse(jsonText);
            JSONObject json = (JSONObject) obj;
            return json;
        }
        catch(UnknownHostException uhe)
        {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("No Internet Connection !");
            alert.setHeaderText(null);
            alert.setContentText("404 No internet Found!");
            alert.showAndWait();
        }
        catch (ParseException pe) {
            System.out.println("position: " + pe.getPosition());
            System.out.println(pe);
        } finally {
            is.close();
        }
        return null;

    }
    
    
    
    private void loadData()
    {
        try
        {
            for(String m : paths)
            {
                String mnx = m;
                String encodedmn = URLEncoder.encode(m);
                String link = "http://www.omdbapi.com/?apikey="+OMDB_API_KEY+"&t="+encodedmn;
                System.out.println(link);
                JSONObject json = readJsonFromUrl(link);
                if(json.containsKey("Error"))
                {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("404 Movie Not Found !");
                    alert.setHeaderText("Please make a correction to the Movie names stored on your Drive");
                    alert.setContentText("This Movie name has an error: '"+ m+"'. "+"\n");
                    alert.showAndWait();
                    continue;
                }
                System.out.println(json);
                String rx = json.get("imdbRating").toString();
                String yx = json.get("Year").toString();
                String gx = json.get("Genre").toString();
                String px = json.get("Plot").toString();
                String dx = json.get("Director").toString();
                String acx = json.get("Actors").toString();
                String awx = json.get("Awards").toString();
                
                list.add(new MRN(mnx,rx,yx,gx,px,dx,acx,awx));
                System.out.println(list);
                
                
                
            }
        }
        catch(NullPointerException ne)
        {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Empty Request");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter a Movie Directory Location !");
            alert.showAndWait();
        }
        
        catch(Exception e)
        {
            e.printStackTrace();
        }
        tv.getItems().setAll(list);
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loc.setStyle("-fx-text-inner-color: white;");
        mnmanfield.setStyle("-fx-text-inner-color: white;");
        ymanfield.setStyle("-fx-text-inner-color: white;");
        initCol();
        
        // TODO
    }    

    @FXML
    private void locHandler(ActionEvent event) {
        File file = null;
        try
        {
            file = new File(loc.getText());
            paths = file.list();
            
            System.out.println(paths.length);
            if(paths.length==0)
            {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("No Movie Entry in the Directory !");
                alert.setHeaderText(null);
                alert.setContentText("No movies were found, please get some! :)");
                alert.showAndWait();
            }
            for(String path:paths)
            {
                System.out.println(path);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        loadData();
        tv.refresh();
        
    }

    @FXML
    private void locbuttonhandler(ActionEvent event) {
        JFileChooser chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle("Show me where all your movies are located");
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.setAcceptAllFileFilterUsed(false);
        
        if(chooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)
        {
            loc.setText(chooser.getSelectedFile().toString());
            
        }
    }

    @FXML
    private void manbuttonhandler(ActionEvent event) {
        
        initColman();
        
        try
        {
            
            String mnx = mnmanfield.getText();
            String encmnx = URLEncoder.encode(mnx);
            String yx = URLEncoder.encode(ymanfield.getText());
            String linkman = "http://www.omdbapi.com/?apikey="+OMDB_API_KEY+"&t="+encmnx+"&y="+yx;
            JSONObject json = readJsonFromUrl(linkman);
            if(json.containsKey("Error"))
                {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("404 Movie Not Found !");
                    alert.setHeaderText("Please make a correction to the Movie name/Year");
                    alert.setContentText("This Movie name/year has an error: '"+ mnx+"'. "+"\n");
                    alert.showAndWait();
                   
                }
            
            String rx = json.get("imdbRating").toString();
            String yxj  = json.get("Year").toString();
            String gx = json.get("Genre").toString();
            String px = json.get("Plot").toString();
            String dx = json.get("Director").toString();
            String acx = json.get("Actors").toString();
            String awx = json.get("Awards").toString();
                
            list2.add(new MRN(mnx,rx,yxj,gx,px,dx,acx,awx));
            
            
        }
        catch(NullPointerException ne)
        {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Empty Request");
            alert.setHeaderText(null);
            alert.setContentText("Please Enter correct Movie name/Year !");
            alert.showAndWait();
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        tvman.getItems().setAll(list2);
        
        
        
        
    }

    @FXML
    private void closebtnhandler(ActionEvent event) {
        Stage stageold = (Stage)tv.getScene().getWindow();
        stageold.close();
    }

    @FXML
    private void shahhandler(ActionEvent event) {
       
    }

    
    
    
    public static class MRN
    {
        private final SimpleStringProperty movieName;
        private final SimpleStringProperty rating;
        private final SimpleStringProperty year;
        private final SimpleStringProperty genre;
        private final SimpleStringProperty plot;
        private final SimpleStringProperty director;
        private final SimpleStringProperty actor;
        private final SimpleStringProperty award;
        
        MRN(String mn,String r,String y,String g,String p,String d,String ac,String aw)
        {
            this.movieName = new SimpleStringProperty(mn);
            this.rating = new SimpleStringProperty(r);
            this.year = new SimpleStringProperty(y);
            this.genre = new SimpleStringProperty(g);
            this.plot = new SimpleStringProperty(p);
            this.director = new SimpleStringProperty(d);
            this.actor = new SimpleStringProperty(ac);
            this.award = new SimpleStringProperty(aw);
        }

        public String getMovieName() {
            return movieName.get();
        }

        public String getRating() {
            return rating.get();
        }

        public String getYear() {
            return year.get();
        }

        public String getGenre() {
            return genre.get();
        }

        public String getPlot() {
            return plot.get();
        }

        public String getDirector() {
            return director.get();
        }

        public String getActor() {
            return actor.get();
        }

        public String getAward() {
            return award.get();
        }
        
        
        
    }
    
}
