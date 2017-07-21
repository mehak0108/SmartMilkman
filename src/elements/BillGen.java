package elements;

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
import javafx.event.ActionEvent;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BillGen{

	static Connection con;
	PreparedStatement pst;
	
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
	
	
	Scene scene;
	GridPane grid;
	Text ttitle,tnm,tmob,ttot,tloc,ttype,ttype1,tcamt,tbamt,tstart;
	TextField fcm,fbm,fmob,fcqty,fbqty,ctot,btot,tot;
	Text ocp,obp;
    HBox h1,h2,h3,h4,h5;
	Button btntot,btnbill,btnsms,btnew,btnprint;
	ComboBox<String> fuid=new ComboBox<String>();
	String s1,s2;
	float a,b,c,d,e,f;
	
	String dos,dos1;
	java.sql.Date sqld,sqld1;
	DateFormat format;
	Date dosObj;
	Calendar cal;
	DatePicker dp,dp1;
	Stage stage=new Stage();
	//private final Label jobStatus = new Label();
	
	BillGen() {
		
		try {
			doConnect();
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
		doFillUids();
		fuid.getItems().add(0,"-Select-");
		fuid.getSelectionModel().select(0);
		fuid.setEditable(false);
		fuid.setVisibleRowCount(5);
		GridPane.setConstraints(fuid, 1, 3, 1, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(0,0,0,0));
		
		
		grid=new GridPane();
		grid.setGridLinesVisible(false);
		grid.setPadding(new Insets(50,0,0,80));
		grid.setVgap(15);
		grid.setHgap(10);
		
//--------------------------label----------------------------------
		
		ttitle=new Text("Bill Generation");
		tnm=new Text("Name:");
		tmob=new Text("Mobile:");
		tloc=new Text("End Date:");
		tstart=new Text("Start Date:");
		
		
		GridPane.setConstraints(ttitle, 0, 0, 3, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(0,0,0,0));
		GridPane.setConstraints(tnm, 0, 3, 1, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(0,0,0,0));
		GridPane.setConstraints(tstart, 0, 1, 1, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(0,0,0,0));
		GridPane.setConstraints(tmob, 0, 4, 1, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(0,0,0,0));
		GridPane.setConstraints(tloc, 0, 2, 1, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(0,11,0,0));
		
		
		ttitle.setFont(Font.font("Verdana",FontWeight.BOLD, 40));
		tnm.setFont(Font.font("Arial",FontWeight.MEDIUM, 18));
		tstart.setFont(Font.font("Arial",FontWeight.MEDIUM, 18));
		tmob.setFont(Font.font("Arial",FontWeight.MEDIUM, 18));
		tloc.setFont(Font.font("Arial",FontWeight.MEDIUM, 18));
		
		fmob= new TextField();
		fmob.setPromptText("Enter the mobile no.");
		


		
//-----------------------buttonTotal-------------------------------------------------------
		
	    h1=new HBox();

		btntot=new Button("TOTAL");
		
		btntot.setPrefSize(80, 20);

		h1.getChildren().add(btntot);
	
		GridPane.setConstraints(h1, 1, 5, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(3,0,0,0));

		btntot.setStyle("-fx-background-color:#ff8f66");
		
		
		
		

		
		
//---------------------------------datePicker------------------------
		
	     cal=Calendar.getInstance();
		//show current date
		// dp=new DatePicker(LocalDate.of(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH)+1,cal.get(Calendar.DATE)));
	   
	     dp=new DatePicker();
		 dp1=new DatePicker();
		
		
		
		
//----------------------------------functions-------------------------
		
		
		btntot.setOnAction(e->doTotal());
		
		
		
		
//---------------------------adding----------------------------------------------------		
		
		grid.getChildren().addAll(ttitle,tstart,tnm,tmob,tloc);
		grid.getChildren().add(fuid);
		grid.add(dp1, 1, 2);
		grid.add(h1, 1, 5);
		grid.add(dp, 1, 1);
		grid.add(fmob, 1, 4);
		
		scene=new Scene(grid,800,600);
		stage.setScene(scene);
		stage.setTitle("Bill");
		stage.show();
		
	}
	
	void doFillUids()
	{
		fuid.getItems().clear();
		try {
			pst=con.prepareStatement("SELECT DISTINCT cname FROM customer");
			ResultSet rs= pst.executeQuery();
			ArrayList<String>lst=new ArrayList<String>();
			while(rs.next())
			{
				String p=rs.getString("cname");
				lst.add(p);
			}
			fuid.getItems().addAll(lst);
		    fuid.getSelectionModel().selectedIndexProperty().addListener((property,oldValue,newValue)->{
		    	fillMob();
		    });
		
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	void fillMob()
	{
		try {
			pst=con.prepareStatement("SELECT * FROM customer WHERE cname=?");
			pst.setString(1, fuid.getSelectionModel().getSelectedItem());
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				String mob=rs.getString(2);
				fmob.setText(mob);
				
			}
			
			
		}
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
		
	}
	
	void showMsg(String msg)
	{
		Alert al=new Alert(AlertType.INFORMATION);
		al.setHeaderText("Message");
		al.setContentText(msg);
		al.showAndWait();
	}
	
	void docheck()
	{
		if(fuid.getSelectionModel().getSelectedItem().isEmpty())
			showMsg("Please fill the name.");
		if(dp.getValue()==null)
			showMsg("Please select the start date.");
		if(dp1.getValue()==null)
			showMsg("Please select the end date.");
		
			
	}
	
	void doTotal()
	{
		int a=0;
		if(dp.getValue()==null || dp1.getValue()== null || fuid.getSelectionModel().getSelectedItem().isEmpty())
			docheck();
		else
		{
		ttype=new Text("Cow Milk(kg):");
		ttype1=new Text("Buffalo Milk(kg):");
		ocp=new Text("CowMilk Price:");
		obp=new Text("BuffaloMilk Price:");
		
		fcm=new TextField();
		fbm=new TextField();
		fcqty= new TextField();
		fbqty=new TextField();
		
		fcm.setPrefWidth(20);
		fcqty.setPrefWidth(20);
		fbm.setPrefWidth(55);
		fbqty.setPrefWidth(55);
		
		GridPane.setConstraints(ttype, 0, 6, 1, 1, HPos.LEFT, VPos.TOP, null, null, new Insets(0,0,0,0));
		GridPane.setConstraints(ttype1, 1, 6, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(0,0,0,70));
		GridPane.setConstraints(ocp, 0, 7, 1, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(0,0,0,0));
		GridPane.setConstraints(obp, 1, 7, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(0,0,0,80));
		GridPane.setConstraints(fcm, 1, 7, 1, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(0,180,0,0));
		GridPane.setConstraints(fbm, 2, 7, 1, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(0,0,0,0));
		GridPane.setConstraints(fcqty, 1, 6, 1, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(0,180,0,0));
		GridPane.setConstraints(fbqty, 2, 6, 1, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(0,0,0,0));
		
		ttype.setFont(Font.font("Arial",FontWeight.MEDIUM, 18));
		ttype1.setFont(Font.font("Arial",FontWeight.MEDIUM, 18));
		ocp.setFont(Font.font("Arial",FontWeight.MEDIUM, 18));
		obp.setFont(Font.font("Arial",FontWeight.MEDIUM, 18));
		
		h2=new HBox();
		btnbill=new Button("BILL");
		btnbill.setPrefSize(80, 20);
		h2.getChildren().add(btnbill);
		GridPane.setConstraints(h2, 1, 8, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(3,0,0,0));
		btnbill.setStyle("-fx-background-color:#ff8f66");
		
		
		
		btnbill.setOnAction(e->doBill());
		grid.getChildren().addAll(ttype,ttype1,ocp,obp,fcm,fbm,fcqty,fbqty);
		grid.add(h2, 1, 8);
		doSum();
		}
	}
	
	void doBill()
	{
		tbamt=new Text("BfMilk Total:");
		tcamt=new Text("CMilk Total:");
		ttot=new Text("Total Amount:");
		
		ctot=new TextField();
		btot=new TextField();
		tot=new TextField();
		
		ctot.setPrefWidth(20);
		btot.setPrefWidth(55);
		tot.setPrefWidth(60);
		
		GridPane.setConstraints(ttot, 0, 10, 1, 1, HPos.LEFT, VPos.TOP, null, null, new Insets(0,0,0,0));
		GridPane.setConstraints(tcamt, 0, 9, 1, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(0,0,0,0));
		GridPane.setConstraints(tbamt, 1, 9, 1, 1, HPos.RIGHT, VPos.CENTER, null, null, new Insets(0,0,0,0));
		GridPane.setConstraints(ctot, 1, 9, 1, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(0,180,0,0));
		GridPane.setConstraints(btot, 2, 9, 1, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(0,0,0,0));
		GridPane.setConstraints(tot, 1, 10, 1, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(0,180,0,0));
		
		
		ttot.setFont(Font.font("Arial",FontWeight.MEDIUM, 18));
		tbamt.setFont(Font.font("Arial",FontWeight.MEDIUM, 18));
		tcamt.setFont(Font.font("Arial",FontWeight.MEDIUM, 18));
		
		h3=new HBox();
		btnsms=new Button("SEND SMS");
		btnew=new Button("NEW");
		btnprint= new Button("PRINT");
		btnprint.setPrefSize(100, 20);
		btnsms.setPrefSize(100, 20);
		btnew.setPrefSize(80, 20);
		h3.getChildren().addAll(btnsms,btnew);
		h3.setSpacing(40);
		GridPane.setConstraints(h3, 0, 11, 2, 1, HPos.RIGHT, VPos.CENTER, null, null, new Insets(20,0,0,60));
		btnsms.setStyle("-fx-background-color:#ff8f66");
		btnew.setStyle("-fx-background-color:#ff8f66");
		
		btnsms.setOnAction(e->sendSMS());
	/*	btnprint.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				pageSetup(grid, stage);
				
			}
		});*/
		
	//	btnprint.setOnAction(e->doprint(stage));
		
		btnew.setOnAction(e->doNew());
		grid.getChildren().addAll(ttot,tcamt,tbamt,ctot,btot,tot);
		grid.add(h3, 0, 11);
		
		doCalculate();
		
		
	}
	
/*	void doprint(Stage stage)
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
	}*/	
	
	private Object sendSMS() {
		
		String m="Done";
		String resp=SST_SMS.bceSunSoftSend("8437145781", m);
		//System.out.println(resp);
		if(resp.indexOf("Exception")!=-1)
		System.out.println("Check Internet Connection");
		else
		if(resp.indexOf("successfully")!=-1)
		System.out.println("Sent");
		else
		System.out.println( "Invalid Number");
		return null;
	}

	void sqlDate()
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
	
	void sqlDate1()
	{
		try {
			format=new SimpleDateFormat("dd/MM/yyyy");
			dos1=((TextField)dp1.getEditor()).getText();
			System.out.println(dos1);
			dosObj=format.parse(dos1);
			sqld1=new java.sql.Date(dosObj.getTime());
				
			} 
			catch (ParseException e) {
				e.printStackTrace();
			}
	}
	
	
	void doSum()
	{
		sqlDate();
		sqlDate1();
		try {
			pst=con.prepareStatement("SELECT * FROM dailyRecord WHERE cname=? AND DOD>=? AND DOD<=?");
            pst.setString(1, fuid.getSelectionModel().getSelectedItem());
            pst.setDate(2, sqld);
            pst.setDate(3, sqld1);
            
            float cc=0,bc=0;
            ResultSet rs=pst.executeQuery();
            
            while(rs.next())
            {
            	float a=rs.getFloat(2);
            	cc=cc+a;
            	float b=rs.getFloat(3);
            	bc=bc+b;
            }
            
            fcqty.setText(String.valueOf(cc));
            fbqty.setText(String.valueOf(bc));
            
            pst=con.prepareStatement("INSERT INTO billgen(StartDate,EndDate,cname,mobile,cQtyTot,bQtyTot) VALUES(?,?,?,?,?,?)");
            pst.setDate(1, sqld);
            pst.setDate(2, sqld1);
            pst.setString(3, fuid.getSelectionModel().getSelectedItem());
            pst.setString(4, fmob.getText());
            pst.setFloat(5, cc);
            pst.setFloat(6, bc);
            pst.executeUpdate();
            pst.close();


		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	
	
	void doCalculate()
	{
		try {
		
			float a=Float.parseFloat(fcm.getText());
			float b=Float.parseFloat(fbm.getText());
				
			float c=Float.parseFloat(fcqty.getText());
			float d=Float.parseFloat(fbqty.getText());
			
			
			
			float e=a*c;
			float f=b*d;
			
			
			ctot.setText(String.valueOf(e));
			btot.setText(String.valueOf(f));
			tot.setText(String.valueOf(e+f));
			
			pst=con.prepareStatement("UPDATE billgen SET Camt=?, Bamt=?, Total=?, cstatus=? WHERE cname=?" );
			//pst=con.prepareStatement("INSERT INTO billgen(Camt,Bamt,Total,cstatus) VALUES(?,?,?,?) WHERE cname=?");
			pst.setString(5, fuid.getSelectionModel().getSelectedItem());
			pst.setFloat(1, e);
			pst.setFloat(2, f);
			pst.setFloat(3, e+f);
			pst.setInt(4, 0);
			pst.executeUpdate();
			pst.close();
			
		} 
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
		
	}
	
	void doNew()
	{
		ttype.setVisible(false);
		ttype1.setVisible(false);
		ocp.setVisible(false);
		obp.setVisible(false);
		tbamt.setVisible(false);
		tcamt.setVisible(false);
		ttot.setVisible(false);
		fcm.setVisible(false);
		fbm.setVisible(false);
		fcqty.setVisible(false);
		fbqty.setVisible(false);
		ctot.setVisible(false);
		btot.setVisible(false);
		tot.setVisible(false);
		
		fmob.clear();
		fuid.getSelectionModel().clearSelection();
		dp.setValue(null);
		dp1.setValue(null);
		
		btnbill.setVisible(false);
		btnsms.setVisible(false);
		btnew.setVisible(false);
	}
}
