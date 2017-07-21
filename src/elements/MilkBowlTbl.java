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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MilkBowlTbl {

	static Connection con;
	PreparedStatement pst1,pst2;
	ResultSet rs1,rs2;
	
	
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
	
	Stage stage=new Stage();
	GridPane grid;
	Scene scene;
	TableView<TMilk> tbl;
	Text ttitle,tdate;
	Button btnprint,btnexp;
	Button btnfind,btnall;
	HBox h1,h2,h3;
	String dos;
	java.sql.Date sqld;
	DateFormat format;
	Date dosObj;
	Calendar cal;
	DatePicker dp;
	ObservableList<TMilk> ary;
	private final Label jobStatus = new Label();
	
	
	MilkBowlTbl()
	{
		
		try {
			doConnect();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		grid=new GridPane();
		grid.setGridLinesVisible(false);
		grid.setHgap(10);
		grid.setVgap(20);
		grid.setPadding(new Insets(50,0,0,50));
		
		ttitle = new Text("My Milk Bowl");
		GridPane.setConstraints(ttitle, 0, 0, 3, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(0,0,0,0));
		ttitle.setFont(Font.font("Verdana",FontWeight.BOLD, 40));
		
		tdate = new Text("Date:");
		GridPane.setConstraints(tdate, 0, 1, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(0,0,0,0));
		tdate.setFont(Font.font("Verdana",FontWeight.MEDIUM, 17));
		
		btnfind=new Button("FIND");
		btnall=new Button("SHOW ALL");
		btnfind.setPrefSize(70, 20);
		btnfind.setPrefSize(100, 20);
		
		cal=Calendar.getInstance();
	    dp=new DatePicker();
		
		h1=new HBox();
		h1.getChildren().addAll(dp,btnfind);
		h1.setSpacing(30);
		GridPane.setConstraints(h1, 1, 1, 3, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(0,0,0,0));
		GridPane.setConstraints(btnall, 4, 1, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(0,0,0,0));
		
		btnprint=new Button("PRINT");
		btnexp=new Button("EXPORT TO EXCEL");
		btnprint.setPrefSize(90, 20);
		btnexp.setPrefSize(150, 20);
		
		h3=new HBox();
		h3.setSpacing(40);
		h3.getChildren().addAll(btnprint,btnexp);
		GridPane.setConstraints(h3, 0, 4, 4, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(0,0,0,150));
		
		
		tbl= new TableView<TMilk>();
		
		TableColumn<TMilk, String> col_date=new TableColumn<>("Date");
		col_date.setMinWidth(90);
		col_date.setCellValueFactory(new PropertyValueFactory<>("sDate"));
		
		TableColumn<TMilk, Float> col_cMilkQty=new TableColumn<>("Cow Milk Qty(total)");
		col_cMilkQty.setMinWidth(160);
		col_cMilkQty.setCellValueFactory(new PropertyValueFactory<>("cMilkQty"));

		TableColumn<TMilk, Float> col_bMilkQty=new TableColumn<>("Buffalo Milk Qty(total)");
		col_bMilkQty.setMinWidth(180);
		col_bMilkQty.setCellValueFactory(new PropertyValueFactory<>("bMilkQty"));

		TableColumn<TMilk, Float> col_total=new TableColumn<>("Total Qty(kg)");
		col_total.setMinWidth(120);
		col_total.setCellValueFactory(new PropertyValueFactory<>("totalQty"));
		
		
		
		tbl.getColumns().addAll(col_date,col_cMilkQty,col_bMilkQty,col_total);
		GridPane.setConstraints(tbl, 0, 2, 5, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(0,0,0,0));
		
		tbl.setItems(getAll());
	
		btnfind.setOnAction(e->{tbl.setItems(acdDate());});
		btnall.setOnAction(e->{tbl.setItems(getAll());});
		btnexp.setOnAction(e->{
			try {
				writeExcel();
			} catch (Exception e1) {
				
				e1.printStackTrace();
			}
		});
		
		btnprint.setOnAction(e->doprint(stage));
		
		
		
		grid.add(tbl, 0, 2);
		grid.getChildren().addAll(ttitle,tdate,h1,btnall,h3);
		
		
		scene= new Scene(grid,700,700);
		stage.setScene(scene);
		stage.show();
		
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
		File file = new File("/home/mehak/Documents/milkbowl.csv");
		writer = new BufferedWriter(new FileWriter(file));
		String text="Date,Cow Milk Qty(Total),Buffalo Milk Qty(Total),Total qty\n";
		writer.write(text);
		for (TMilk t : ary) {
		text = t.getSDate() + "," + t.getCMilkQty() + ","+t.getBMilkQty()+","+t.getTotalQty()+"\n";
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
	


	void sqlDate() throws SQLException
	{
		try {
		format=new SimpleDateFormat("dd/MM/yyyy");
		dos=((TextField)dp.getEditor()).getText();
		System.out.println(dos);
		dosObj=format.parse(dos);
		sqld=new java.sql.Date(dosObj.getTime());
			
		} 
		catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
	
	ObservableList<TMilk> acdDate()
	{
		try {
			sqlDate();
		} catch (SQLException e1) 
		{
			e1.printStackTrace();
		}
		ary= FXCollections.observableArrayList();
	
		try {
			pst1=con.prepareStatement("SELECT * FROM milkBowl Where sDate=?");
			pst1.setDate(1, sqld);
			rs1=pst1.executeQuery();
			
			
			while(rs1.next())
			{

			ary.add(new TMilk(rs1.getDate("sDate").toString(),rs1.getFloat("cMilkQty"),rs1.getFloat("bMilkQty"),rs1.getFloat("totalQty")));
			
			}
			
		} 
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		return ary;
	}
	
	ObservableList<TMilk> getAll()
	{
		try {
			pst2=con.prepareStatement("DELETE FROM milkBowl");
			pst2.executeUpdate();
			System.out.println(10);
			
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
		
		ary= FXCollections.observableArrayList();
	
		try {
			pst1=con.prepareStatement("SELECT SUM(cmilkQty),SUM(bmilkQty),DOD FROM dailyRecord GROUP BY DOD");
			rs1=pst1.executeQuery();
			
			while(rs1.next())
			{
			float a= rs1.getFloat(1);
			float b= rs1.getFloat(2);
			pst2 = con.prepareStatement("INSERT INTO milkBowl VALUES(?,?,?,?)");
			
			pst2.setDate(1, rs1.getDate(3));
			pst2.setFloat(2, a);
			pst2.setFloat(3, b);
			pst2.setFloat(4, a+b);
			pst2.executeUpdate();
			
			ary.add(new TMilk(rs1.getDate(3).toString(),a,b,a+b));
			
			}
			
			System.out.println(1);
		
		} 
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		return ary;
	}
	
}
