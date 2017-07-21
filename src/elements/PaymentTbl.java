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
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PaymentTbl {

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
	
	Stage stage=new Stage();
	GridPane grid;
	Scene scene;
	TableView<TPayment> tbl;
	Text ttitle;
	ToggleGroup group;
	RadioButton btnall,btndone,btnbal;
	Button btnfind;
	HBox h1,h2,h3;
	Button btnprint,btnexp;
	ObservableList<TPayment> ary;
	private final Label jobStatus = new Label();
	
	PaymentTbl()
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
		
		ttitle = new Text("Payment Status");
		GridPane.setConstraints(ttitle, 0, 0, 3, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(0,0,0,0));
		ttitle.setFont(Font.font("Verdana",FontWeight.BOLD, 40));
		
		
		
		
		btnfind=new Button("SEARCH");
		btnfind.setPrefSize(80, 20);
		
		h1=new HBox();
		h1.getChildren().add(btnfind);
		
		GridPane.setConstraints(h1, 4, 1, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(0,0,0,20));
		
		btnall=new RadioButton("All");
		btndone=new RadioButton("Fully Paid");
		btnbal=new RadioButton("Balance due");
		
		group=new ToggleGroup();
		btnall.setToggleGroup(group);
		btndone.setToggleGroup(group);
		btnbal.setToggleGroup(group);
		
		
		
		h2=new HBox();

		h2.getChildren().addAll(btnall,btndone,btnbal);
		h2.setSpacing(23);
		GridPane.setConstraints(h2, 0, 1, 3, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(5,0,0,0));
		
		btnprint=new Button("PRINT");
		btnexp=new Button("EXPORT TO EXCEL");
		btnprint.setPrefSize(90, 20);
		btnexp.setPrefSize(150, 20);
		
		h3=new HBox();
		h3.setSpacing(40);
		h3.getChildren().addAll(btnprint,btnexp);
		GridPane.setConstraints(h3, 0, 4, 3, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(0,0,0,300));
		
		
		tbl= new TableView<TPayment>();
		
		TableColumn<TPayment, String> col_name=new TableColumn<>("Customer Name");
		col_name.setMinWidth(140);
		col_name.setCellValueFactory(new PropertyValueFactory<>("cname"));
		
		TableColumn<TPayment, String> col_mob=new TableColumn<>("Mobile");
		col_mob.setMinWidth(100);
		col_mob.setCellValueFactory(new PropertyValueFactory<>("mobile"));
		
		TableColumn<TPayment, String> col_sd=new TableColumn<>("Start Date");
		col_sd.setMinWidth(100);
		col_sd.setCellValueFactory(new PropertyValueFactory<>("StartDate"));
		
		TableColumn<TPayment, String> col_ed=new TableColumn<>("End Date");
		col_ed.setMinWidth(100);
		col_ed.setCellValueFactory(new PropertyValueFactory<>("EndDate"));
		
		TableColumn<TPayment, Float> col_cTot=new TableColumn<>("CMilk Total(kg)");
		col_cTot.setMinWidth(120);
		col_cTot.setCellValueFactory(new PropertyValueFactory<>("cQtyTot"));
		
		TableColumn<TPayment, Float> col_bTot=new TableColumn<>("BMilk Total(kg)");
		col_bTot.setMinWidth(120);
		col_bTot.setCellValueFactory(new PropertyValueFactory<>("bQtyTot"));
		
		TableColumn<TPayment, Float> col_cmilk=new TableColumn<>("Cow Milk(Rs)");
		col_cmilk.setMinWidth(120);
		col_cmilk.setCellValueFactory(new PropertyValueFactory<>("Camt"));
		
		TableColumn<TPayment, Float> col_bmilk=new TableColumn<>("Buffalo Milk(Rs)");
		col_bmilk.setMinWidth(130);
		col_bmilk.setCellValueFactory(new PropertyValueFactory<>("Bamt"));
		
		TableColumn<TPayment, Float> col_tot=new TableColumn<>("Total Amount");
		col_tot.setMinWidth(120);
		col_tot.setCellValueFactory(new PropertyValueFactory<>("Total"));
		
		tbl.getColumns().addAll(col_sd,col_ed,col_name,col_mob,col_cTot,col_bTot,col_cmilk,col_bmilk,col_tot);
		GridPane.setConstraints(tbl, 0, 2, 5, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(0,0,0,0));

		
		btnfind.setOnAction(e->doFill());
		btnexp.setOnAction(e->{
			try {
				writeExcel();
			} catch (Exception e1) {
				
				e1.printStackTrace();
			}
		});
		
		btnprint.setOnAction(e->doprint(stage));
		
		grid.add(tbl, 0, 2);
		grid.getChildren().addAll(ttitle,h1,h2,h3);
		
		
		scene= new Scene(grid,1200,800);
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
		File file = new File("/home/mehak/Documents/payment.csv");
		writer = new BufferedWriter(new FileWriter(file));
		String text="Start Date,End Date,Customer Name,Mobie,Cqty Total,Bqty Total,Cow Milk amt,Buffalo Milk Amt,Total amt\n";
		writer.write(text);
		for (TPayment t : ary) {
		text = t.getStartDate() + "," + t.getEndDate() + ","+t.getCname()+","+t.getMobile()+","+t.getCQtyTot()+","+t.getBQtyTot()+","+t.getCamt()+","+t.getBamt()+","+t.getTotal()+"\n";
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
	
	private Object doFill() 
	{
		if(btnall.isSelected())
		{
			tbl.setItems(showAll());
			
		}
		if(btndone.isSelected())
		{
			tbl.setItems(showDone());
			
		}
		
		if(btnbal.isSelected())
		{
			tbl.setItems(showBal());
		}
		
		return null;
	}
	

	ObservableList<TPayment> showBal()
	{
		 ary= FXCollections.observableArrayList();
		try {
			pst=con.prepareStatement("SELECT * FROM billgen WHERE cstatus=?");
			pst.setInt(1, 0);
			rs=pst.executeQuery();
			while(rs.next())
			{
				ary.add(new TPayment(rs.getString("StartDate"),rs.getString("EndDate"),rs.getString("cname"),rs.getString("mobile"),rs.getFloat("cQtyTot"),rs.getFloat("bQtyTot"),rs.getFloat("Camt"),rs.getFloat("Bamt"),rs.getFloat("Total")));
				//System.out.println(rs.getFloat("cQtyTot")+"   "+rs.getFloat("bQtyTot"));
			}
			
		} 
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
		return ary;
		
	}
	
	
	ObservableList<TPayment> showAll()
	{
		ary= FXCollections.observableArrayList();
		try {
			pst=con.prepareStatement("SELECT * FROM billgen");
			rs=pst.executeQuery();
			while(rs.next())
			{
				ary.add(new TPayment(rs.getString("StartDate"),rs.getString("EndDate"),rs.getString("cname"),rs.getString("mobile"),rs.getFloat("cQtyTot"),rs.getFloat("bQtyTot"),rs.getFloat("Camt"),rs.getFloat("Bamt"),rs.getFloat("Total")));
			}
			
		} 
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
		return ary;
		
	}
	
	
	ObservableList<TPayment> showDone()
	{
		ary= FXCollections.observableArrayList();
		try {
			pst=con.prepareStatement("SELECT * FROM billgen WHERE cstatus=?");
			pst.setInt(1, 1);
			rs=pst.executeQuery();
			while(rs.next())
			{
				ary.add(new TPayment(rs.getString("StartDate"),rs.getString("EndDate"),rs.getString("cname"),rs.getString("mobile"),rs.getFloat("cQtyTot"),rs.getFloat("bQtyTot"),rs.getFloat("Camt"),rs.getFloat("Bamt"),rs.getFloat("Total")));
			}
			
		} 
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
		return ary;
		
	}
	
}
