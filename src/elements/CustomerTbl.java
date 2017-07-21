package elements;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CustomerTbl {

	static Connection con;
	PreparedStatement pst;
	ResultSet rs;
	
	
	public static void doConnect() throws SQLException
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Creamery","root","mehak");
			System.out.println("ok");
		} 
		catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
	}
	

	GridPane grid;
	Scene scene;
	TableView<TCustomers> tbl;
	Text ttitle,tcity,tsch;
	TextField fsch;
	ComboBox<String> fcity=new ComboBox<String>();
	Button btnfch,btnall,btnadv,btnfind;
	HBox h1,h2,h3;
	Button btnprint,btnexp;
	ObservableList<TCustomers> ary;
	Stage stage=new Stage();
	private final Label jobStatus = new Label();
	
	CustomerTbl()
	{
		
		try {
			doConnect();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		doFillCity();
		fcity.getItems().add(0,"-Select-");
		fcity.getSelectionModel().select(0);
		fcity.setEditable(false);
		fcity.setVisibleRowCount(5);
		GridPane.setConstraints(fcity, 1, 1, 1, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(0,0,0,0));
		
		grid=new GridPane();
		grid.setGridLinesVisible(false);
		grid.setHgap(10);
		grid.setVgap(20);
		grid.setPadding(new Insets(50,0,0,50));
		
		ttitle = new Text("Customers");
		GridPane.setConstraints(ttitle, 0, 0, 3, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(0,0,0,0));
		ttitle.setFont(Font.font("Verdana",FontWeight.BOLD, 40));
		
		tcity = new Text("Location:");
		GridPane.setConstraints(tcity, 0, 1, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(0,0,0,0));
		tcity.setFont(Font.font("Verdana",FontWeight.MEDIUM, 17));
		
		
		btnfch=new Button("FETCH");
		btnall=new Button("SHOW ALL");
		//btnadv=new Button("ADVANCED SEARCH");
		btnfind=new Button("FIND");
		
		
		//btnadv.setPrefSize(80, 20);
		btnall.setPrefSize(90, 20);
		btnfch.setPrefSize(70, 20);
		btnfind.setPrefSize(70, 20);
		
		h1=new HBox();
		h2=new HBox();
		
		h1.getChildren().addAll(btnfch,btnall);
		h1.setSpacing(70);
		GridPane.setConstraints(h1, 2, 1, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(0,0,0,0));
		
		
		tsch=new Text("Search:");
		GridPane.setConstraints(tsch, 0, 2, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(0,0,0,0));
		tsch.setFont(Font.font("Verdana",FontWeight.MEDIUM, 17));
		
		fsch=new TextField();
		fsch.setPromptText("Enter the name");
		fsch.setPrefWidth(200);
		h2.getChildren().addAll(tsch,fsch,btnfind);
		//h2.getChildren().addAll(tsch,fsch);
		h2.setSpacing(23);
		GridPane.setConstraints(h2, 0, 2, 3, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(0,0,0,0));
		
		btnprint=new Button("PRINT");
		btnexp=new Button("EXPORT TO EXCEL");
		btnprint.setPrefSize(90, 20);
		btnexp.setPrefSize(150, 20);
		
		h3=new HBox();
		h3.setSpacing(40);
		h3.getChildren().addAll(btnprint,btnexp);
		GridPane.setConstraints(h3, 0, 4, 3, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(0,0,0,300));
		
		btnexp.setOnAction(e->{
			try {
				writeExcel();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		btnprint.setOnAction(e->doprint(stage));
		
	/*	fsch.setOnKeyReleased(new EventHandler<KeyEvent>() {

			public void handle(KeyEvent event) {
				
				tbl.setItems(acdName());
				
				
			}
		});*/
		
		tbl= new TableView<TCustomers>();
		
		TableColumn<TCustomers, String> col_name=new TableColumn<>("Customer Name");
		col_name.setMinWidth(140);
		col_name.setCellValueFactory(new PropertyValueFactory<>("cname"));
		
		TableColumn<TCustomers, String> col_mob=new TableColumn<>("Mobile");
		col_mob.setMinWidth(110);
		col_mob.setCellValueFactory(new PropertyValueFactory<>("mobile"));
		
		TableColumn<TCustomers, String> col_add=new TableColumn<>("Address");
		col_add.setMinWidth(100);
		col_add.setCellValueFactory(new PropertyValueFactory<>("address"));
		
		TableColumn<TCustomers, String> col_city=new TableColumn<>("City");
		col_city.setMinWidth(100);
		col_city.setCellValueFactory(new PropertyValueFactory<>("city"));
		
		TableColumn<TCustomers, String> col_time=new TableColumn<>("Timings");
		col_time.setMinWidth(120);
		col_time.setCellValueFactory(new PropertyValueFactory<>("timings"));
		
		TableColumn<TCustomers, String> col_date=new TableColumn<>("Date");
		col_date.setMinWidth(100);
		col_date.setCellValueFactory(new PropertyValueFactory<>("date"));
		
		TableColumn<TCustomers, Float> col_cmilk=new TableColumn<>("Cow Milk(kg)");
		col_cmilk.setMinWidth(140);
		col_cmilk.setCellValueFactory(new PropertyValueFactory<>("cow"));
		
		TableColumn<TCustomers, Float> col_bmilk=new TableColumn<>("Buffalo Milk(kg)");
		col_bmilk.setMinWidth(160);
		col_bmilk.setCellValueFactory(new PropertyValueFactory<>("buffalo"));
		
		tbl.getColumns().addAll(col_name,col_mob,col_add,col_city,col_cmilk,col_bmilk,col_time,col_date);
		GridPane.setConstraints(tbl, 0, 3, 5, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(0,0,0,0));
		
	    btnall.setOnAction(e->{tbl.setItems(getData());});
		btnfch.setOnAction(e->{tbl.setItems(acdCity());});
		btnfind.setOnAction(e->{tbl.setItems(acdName());});
		
		grid.add(tbl, 0, 3);
		grid.getChildren().addAll(ttitle,tcity,fcity,h1,h2,h3);
		
		
		scene= new Scene(grid,1100,800);
		stage.setScene(scene);
		stage.show();
		
	}
	
	void doprint(Stage stage)
	{
		pageSetup(grid,stage);
	}
	
	private void pageSetup(Node node, Stage owner) 
	{
		// Create the PrinterJob
		PrinterJob job = PrinterJob.createPrinterJob();
		
		if (job == null) 
		{
			return;
		}
		
		// Show the page setup dialog
		boolean proceed = job.showPageSetupDialog(owner);
		Optional<String> result = new TextInputDialog().showAndWait();
		if (result.isPresent()) {
			print(job, node);
		} else {
		    // cancel might have been pressed.
		}
		if (proceed) 
		{
			
		}
	}
	
	private void print(PrinterJob job, Node node) 
	{
		// Set the Job Status Message
		jobStatus.textProperty().bind(job.jobStatusProperty().asString());
		
		// Print the node
		boolean printed = job.printPage(node);
	
		if (printed) 
		{
			job.endJob();
		}
	}
	
	void showMsg(String msg)
	{
		Alert al=new Alert(AlertType.INFORMATION);
		al.setHeaderText("Message");
		al.setContentText(msg);
		al.showAndWait();
	}
	
	public void writeExcel() throws Exception {
		Writer writer = null;
		try {
		File file = new File("/home/mehak/Documents/customer.csv");
		writer = new BufferedWriter(new FileWriter(file));
		String text="Customer Name,Mobie,Address,City,Cow Qty(kg),buff Qty(kg),Date\n";
		writer.write(text);
		for (TCustomers t : ary) {
		text = t.getCname() + "," + t.getMobile() + ","+t.getAddress()+","+t.getCity()+","+t.getCow()+","+t.getBuffalo()+","+t.getDate()+"\n";
		writer.write(text);
		showMsg("Successfully Exported!!");
		}
		} catch (Exception ex) {
		ex.printStackTrace();
		}
		finally {
		writer.flush();
		writer.close();
		}
		}
	
	ObservableList<TCustomers> getData()
	{
		
		ary= FXCollections.observableArrayList();
		try {
			pst=con.prepareStatement("SELECT * FROM customer");
			rs=pst.executeQuery();
			while(rs.next())
			{
				ary.add(new TCustomers(rs.getString("cname"),rs.getString("mobile"),rs.getString("address"),rs.getString("city"),rs.getString("timings"),rs.getString("date"),rs.getFloat("cow"),rs.getFloat("buffalo")));
			}
		} 
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		return ary;
	}
	
	void doFillCity()
	{
		try {
			pst=con.prepareStatement("SELECT DISTINCT city FROM customer");
			ResultSet rs=pst.executeQuery();
			ArrayList<String>lst=new ArrayList<String>();
			while(rs.next())
			{
				String p=rs.getString("city");
				lst.add(p);
			}
			fcity.getItems().addAll(lst);
			
			
		} 
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
		
	}
	
	ObservableList<TCustomers> acdCity()
	{
		
		//ObservableList<TCustomers> ary= FXCollections.observableArrayList();
		ary= FXCollections.observableArrayList();
		try {
			pst=con.prepareStatement("SELECT * FROM customer WHERE city=?");
			pst.setString(1, fcity.getSelectionModel().getSelectedItem());
			rs=pst.executeQuery();
			//ArrayList<String>lst=new ArrayList<String>();
			
			while(rs.next())
			{
				ary.add(new TCustomers(rs.getString("cname"),rs.getString("mobile"),rs.getString("address"),rs.getString("city"),rs.getString("timings"),rs.getString("date"),rs.getFloat("cow"),rs.getFloat("buffalo")));
			}
			
		} 
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		return ary;
		
		} 
		
	ObservableList<TCustomers> acdName()
	{
		
	
		ary= FXCollections.observableArrayList();
		try {
			pst=con.prepareStatement("SELECT * FROM customer WHERE cname LIKE '%"+fsch.getText()+"%'");
		//	pst=con.prepareStatement("SELECT * FROM customer WHERE cname=?");
		//	pst.setString(1, fsch.getText() );
			rs=pst.executeQuery();
			
			while(rs.next())
			{
				ary.add(new TCustomers(rs.getString("cname"),rs.getString("mobile"),rs.getString("address"),rs.getString("city"),rs.getString("timings"),rs.getString("date"),rs.getFloat("cow"),rs.getFloat("buffalo")));
			}
			
		} 
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		return ary;
		
		} 
	

}
