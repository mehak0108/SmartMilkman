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

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CustomerConsole{

	static Connection con;
	PreparedStatement pst;
	
	void doConnect() throws SQLException
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
	Text ttitle,tnm,tmob,tadd,tloc,ttype,ttime,tdate,tvalmob;
	TextField fmob,floc,tbm;
	TextField tcm =new TextField();
	TextArea taadd;
	HBox h1,h2,h3,h4,h5;
	VBox v;
	Button btnsch,btnew,btnsv,btnup,btndel;
	ComboBox<String> fuid=new ComboBox<String>();
	ImageView img1,img2,img3,img4,img5;
	RadioButton btnmon,btnevg;
	CheckBox cm,bm;
	String dos;
	java.sql.Date sqld;
	DateFormat format;
	Date dosObj;
	Calendar cal;
	DatePicker dp;
	String s="";
	String s1="";
	Stage stage;
	
	
	CustomerConsole()
	{
		
		try {
			doConnect();
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
		stage=new Stage();
		doFillUids();
		fuid.getItems().add(0,"-Select-");
		fuid.getSelectionModel().select(0);
		fuid.setEditable(true);
		fuid.setVisibleRowCount(5);
		GridPane.setConstraints(fuid, 1, 1, 1, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(0,0,0,0));
		
		
		grid=new GridPane();
		grid.setGridLinesVisible(false);
		grid.setPadding(new Insets(50,0,0,80));
		grid.setVgap(15);
		grid.setHgap(10);
		
//--------------------------label----------------------------------
		
		ttitle=new Text("Customer Console");
		tnm=new Text("Name:");
		tmob=new Text("Mobile:");
		tadd=new Text("Address:");
		tloc=new Text("City/Location:");
		ttype=new Text("Milk Type:");
		ttime=new Text("Timing:");
		tdate=new Text("Start Date:");
		
		
		GridPane.setConstraints(ttitle, 0, 0, 3, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(0,0,0,0));
		GridPane.setConstraints(tnm, 0, 1, 1, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(0,0,0,0));
		GridPane.setConstraints(tmob, 0, 2, 1, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(0,0,0,0));
		GridPane.setConstraints(tadd, 0, 3, 1, 1, HPos.LEFT, VPos.TOP, null, null, new Insets(0,0,0,0));
		GridPane.setConstraints(tloc, 0, 4, 1, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(0,11,0,0));
		GridPane.setConstraints(ttype, 0, 5, 1, 1, HPos.LEFT, VPos.TOP, null, null, new Insets(0,0,0,0));
		GridPane.setConstraints(ttime, 0, 6, 1, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(0,0,0,0));
		GridPane.setConstraints(tdate, 0, 7, 1, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(0,0,0,0));
		
		
		ttitle.setFont(Font.font("Verdana",FontWeight.BOLD, 40));
		tnm.setFont(Font.font("Arial",FontWeight.MEDIUM, 18));
		tmob.setFont(Font.font("Arial",FontWeight.MEDIUM, 18));
		tadd.setFont(Font.font("Arial",FontWeight.MEDIUM, 18));
		tloc.setFont(Font.font("Arial",FontWeight.MEDIUM, 18));
		ttype.setFont(Font.font("Arial",FontWeight.MEDIUM, 18));
		ttime.setFont(Font.font("Arial",FontWeight.MEDIUM, 18));
		tdate.setFont(Font.font("Arial",FontWeight.MEDIUM, 18));
		
		
		
		fmob=new TextField();
		fmob.setPromptText("Enter the mobile number");
		floc=new TextField();
		floc.setPromptText("Enter the location");
		
		
		
		
//-----------------------------address------------------------------------------------
		
		taadd=new TextArea();
		taadd.setPrefRowCount(3);
	    taadd.setPrefColumnCount(12);
	    taadd.setWrapText(true);
	    
		
	    
//---------------button icons-------------------------------------------		
	  	img1=new ImageView(new Image(getClass().getResourceAsStream("img/search1.png")));
	  	img1.setFitHeight(15);
		img1.setFitWidth(15);
		
		img2=new ImageView(new Image(getClass().getResourceAsStream("img/save.png")));
	  	img2.setFitHeight(20);
		img2.setFitWidth(20);
	  		
	  	img3=new ImageView(new Image(getClass().getResourceAsStream("img/add.png")));
	  	img3.setFitHeight(20);
	  	img3.setFitWidth(20);
	  		
	  	img4=new ImageView(new Image(getClass().getResourceAsStream("img/delete.png")));
	  	img4.setFitHeight(20);
	  	img4.setFitWidth(20);
	  		
	  	img5=new ImageView(new Image(getClass().getResourceAsStream("img/update.png")));
	  	img5.setFitHeight(20);
	  	img5.setFitWidth(20);  
	
		
//-----------------------buttons-------------------------------------------------------
		
	    h1=new HBox();
	    h2=new HBox();
	    h3=new HBox();
	    
		btnsch=new Button("SEARCH",img1);
		btnew=new Button("NEW",img3);
		btnsv=new Button("SAVE",img2);
		btnup=new Button("UPDATE",img5);
		btndel=new Button("DELETE",img4);
		
		btnsch.setPrefSize(100, 20);
		btnew.setPrefSize(100, 20);
		btnsv.setPrefSize(100, 20);
		btnup.setPrefSize(100, 20);
		btndel.setPrefSize(100, 20);
		
		h1.getChildren().add(btnsch);
		h2.getChildren().addAll(btnew,btnsv,btnup,btndel);
		GridPane.setConstraints(h2, 0, 8, 4, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10,0,0,0));
		h2.setSpacing(40);
		
		btnsch.setStyle("-fx-background-color:#5246a0");
		btnew.setStyle("-fx-background-color:#ff8f66");
		btnsch.setTextFill(Color.WHITE);
		btnsv.setStyle("-fx-background-color:#ff8f66");
		btnup.setStyle("-fx-background-color:#ff8f66");
		btndel.setStyle("-fx-background-color:#ff8f66");
		
		btnmon=new RadioButton("Morning");
		btnevg= new RadioButton("Evening");
		btnmon.setFont(Font.font("Arial",FontWeight.MEDIUM, 14));
		btnevg.setFont(Font.font("Arial",FontWeight.MEDIUM, 14));
		
		h3.getChildren().addAll(btnmon,btnevg);
		h3.setSpacing(40);
		
//---------------------------checkbox--------------------------------------------------
		
		h4=new HBox();
		h5=new HBox();
	    
	    
		tbm= new TextField();
		tcm= new TextField();
	    
		tbm.setPromptText("Quantity in kgs");
		tbm.setPrefWidth(80);
		tcm.setPromptText("Quantity in kgs");		    
		tcm.setPrefWidth(80);
		tcm.setText("0");
		tbm.setText("0");
		
		
		cm=new CheckBox("Cow Milk");
		bm= new CheckBox("Buffalo Milk");
		v=new VBox();
		
		h4.getChildren().addAll(cm,tcm);
		h5.getChildren().addAll(bm,tbm);
		h4.setSpacing(25);
		h5.setSpacing(8);
		
		v.getChildren().addAll(h4,h5);
		v.setSpacing(10);
		GridPane.setConstraints(v, 1, 5, 1, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(0,0,0,0));
		
		
		
//---------------------------------datePicker------------------------
		
	     cal=Calendar.getInstance();
		//show current date
	//	 dp=new DatePicker(LocalDate.of(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH)+1,cal.get(Calendar.DATE)));
	     dp=new DatePicker();
		
		
		
//----------------------------------functions-------------------------
		
		btnew.setOnAction(e->donew());
		btnsv.setOnAction(e->dosave());
		btnup.setOnAction(e->doup());
		btndel.setOnAction(e->dodel());
		btnsch.setOnAction(e->dosearch());
		
		
//---------------------------adding----------------------------------------------------		
		
		grid.getChildren().addAll(ttitle,tnm,tmob,tadd,tloc,ttype,ttime,tdate);
		grid.getChildren().add(fuid);
		grid.add(fmob, 1, 2);
		grid.add(floc, 1, 4);
		grid.add(taadd, 1, 3);
		grid.add(h1, 3, 1);
		grid.add(h2, 0, 8);
		grid.add(h3, 1, 6);
		grid.add(v, 1, 5);
		grid.add(dp, 1, 7);
		
		scene=new Scene(grid,800,600);
		stage.setScene(scene);
		stage.setTitle("Console");
		stage.show();
		
	}
	
	void donew()
	{
		fuid.getSelectionModel().clearSelection();
		fmob.clear();
		taadd.clear();
		floc.clear();
		cm.setSelected(false);
		bm.setSelected(false);
		tcm.clear();
		tbm.clear();
		btnmon.setSelected(false);
		btnevg.setSelected(false);
		dp.setValue(null);
		
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
	
	
	void checkTime()
	{
		if(btnmon.isSelected())
			s="Morning";
		
		if(btnevg.isSelected())
			s1="Evening";
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
		if(fmob.getText().isEmpty())
			showMsg("Please enter the mobile number.");
		if(dp.getValue()==null)
			showMsg("Select the start date.");
		if(floc.getText().isEmpty())
			showMsg("Please enter the location.");
		if(!(btnmon.isSelected() || btnevg.isSelected()))
			showMsg("Please select the timings..");
	}
	
	
	
	void dosave()
	{
		
		if(fuid.getSelectionModel().getSelectedItem().isEmpty() || fmob.getText().isEmpty() || dp.getValue()==null || floc.getText().isEmpty() || !(btnmon.isSelected() || btnevg.isSelected()) )
		{
			docheck();
			
		}
		
		else{
		
		checkTime();
		try {
			sqlDate();
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
		try {
			pst=con.prepareStatement("INSERT INTO customer VALUES(?,?,?,?,?,?,?,?)");
			pst.setString(1, fuid.getSelectionModel().getSelectedItem());
			pst.setString(2, fmob.getText());
			pst.setString(3, taadd.getText());
			pst.setString(4, floc.getText());
			pst.setFloat(5, Float.parseFloat(tcm.getText()) );
			pst.setFloat(6, Float.parseFloat(tbm.getText()) );
			pst.setString(7, s+","+s1);
			pst.setDate(8, sqld);
			pst.executeUpdate();
			doFillUids();
			
			
		} 
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		showMsg("Successfully added the user.");
		}
	}
	
	void doup()
	{
		checkTime();
		try {
			sqlDate();
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
		try {
			pst=con.prepareStatement("UPDATE customer SET mobile=?,address=?,city=?,cow=?,buffalo=?,timings=?,date=? WHERE cname=?");
			pst.setString(8, fuid.getSelectionModel().getSelectedItem());
			pst.setString(1, fmob.getText());
			pst.setString(2, taadd.getText());
			pst.setString(3, floc.getText());
			pst.setFloat(4, Float.parseFloat(tcm.getText()) );
			pst.setFloat(5, Float.parseFloat(tbm.getText()) );
			pst.setString(6, s+s1);
			pst.setDate(7, sqld);
			pst.executeUpdate();
			System.out.println("ok");
		}
		catch (SQLException e) {
			
			e.printStackTrace();
		}
	}

	void dodel()
	{
		try {
			pst=con.prepareStatement("DELETE FROM customer WHERE cname=?");
			pst.setString(1, fuid.getSelectionModel().getSelectedItem());
			pst.executeUpdate();
			doFillUids();
		} 
		catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	void dosearch()
	{
		try {
			pst=con.prepareStatement("SELECT * FROM customer WHERE cname=?");
			pst.setString(1, fuid.getSelectionModel().getSelectedItem());
			ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
				String str=rs.getString("timings");
				String a[]=str.split(",");
			   java.sql.Date SqlDate1 = rs.getDate(8);
				
				fmob.setText(rs.getString("mobile"));
				taadd.setText(rs.getString("address"));
				floc.setText(rs.getString("city"));
				tcm.setText(rs.getString("cow"));
				tbm.setText(rs.getString("buffalo"));
				
				for(String s:a)
				{
					if(s.equals("Morning"))
							btnmon.setSelected(true);
					if(s.equals("Evening"))
						btnevg.setSelected(true);
						
				}
			  
			    dp.setValue(SqlDate1.toLocalDate());
			    
			    if(tcm.getText().equals("0"))
			    	cm.setSelected(false);
			    else
			    	cm.setSelected(true);
			    
			    if(tbm.getText().equals("0"))
			    	bm.setSelected(false);
			    else
			    	bm.setSelected(true);
			    

			}
			else
				System.out.println("Done search");
			rs.close();
		} 
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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
		
		
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	
}




