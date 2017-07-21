package elements;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Login extends Application {

	static Connection con;
	PreparedStatement pst;
	
	static void doConnect() throws SQLException
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Creamery","root","mehak");
			System.out.println("ok");
		} 
		catch (ClassNotFoundException e) 
		{
			
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		
	
		try {
			doConnect();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		launch(args);

	}

	Scene scene,scene2;
	GridPane grid,grid2;
	ImageView img,img1;
	Text lbltitle,lbluid,lblpwd;
	TextField txtuid;
	PasswordField txtpwd;
	DropShadow ds;
	HBox h;
	Button log,btnsms;
	Alert msg;
	String name,pwd;
	Stage theStage;
	BackgroundImage myBI,bg;
	
	
	Text ttitle,about,t1,t2,t3,t4,t5,t6,t7,t8;
	ListView<String> lst;
	ArrayList<String> ary;
	HBox h1,h2,h3,h4,h5,h6,h7,h8;
	ImageView img2,img3,img4,img5,img6,img7,img8,img9;
	
	@Override
	public void start(Stage st) throws Exception {
		
		st.setTitle("Window");
		theStage= st;
		
		
//-------------------grid---------------------------------------------------------		
		grid=new GridPane();
		grid.setGridLinesVisible(false);
		grid.setPadding(new Insets(80,0,0,80));
		grid.setHgap(10);
		grid.setVgap(10);
		
		
//-------------------------logo----------------------------------------------------		
		img=new ImageView(new Image(Login.class.getResourceAsStream("img/login.png")));
		img.setFitHeight(60);
		img.setFitWidth(60);
		GridPane.setConstraints(img, 0, 0, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(20,20,20,30));
		
//-------------------------label---------------------------------------------------
		
		lbltitle=new Text("Login Form");
		lbluid=new Text("User Id:");
		lblpwd=new Text("Password:");
		lbltitle.setFont(Font.font("Verdana",FontWeight.BOLD,45));
		lbluid.setFont(Font.font("Arial",FontWeight.MEDIUM,18));
		lblpwd.setFont(Font.font("Arial",FontWeight.MEDIUM,18));
		
		//GridPane.setMargin(lbltitle, new Insets(20,0,20,30));
		GridPane.setConstraints(lbltitle, 1, 0, 2, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(20,0,20,80));
		GridPane.setConstraints(lbluid, 1, 1, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(15,91,3,0));
		GridPane.setConstraints(lblpwd, 1, 2, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(15,65,3,0));
		
		
//-------------------------------styling title----------------------------------------------		
		ds=new DropShadow();
		ds.setOffsetY(1.0);
		//ds.setOffsetX(3);
		ds.setColor(Color.color(0.4, 0.4, 0.4));
		lbltitle.setFill(Color.valueOf("#e91640"));
		lbltitle.setEffect(ds);
		
		lbluid.setFill(Color.valueOf("#ffd480"));
		lblpwd.setFill(Color.valueOf("#ffd480"));
		
//---------------------------------button----------------------------------------------
		h=new HBox();
		log=new Button("LOGIN");
		btnsms=new Button("SMS Pwd");
		
		log.setPrefSize(90, 30);
		btnsms.setPrefSize(90, 30);
		
		
		log.setStyle("-fx-background-color:#9696e8");
		btnsms.setStyle("-fx-background-color:#9696e8");
		h.getChildren().addAll(log,btnsms);
		h.setSpacing(30);
		GridPane.setConstraints(h, 0, 5, 3, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(40,0,0,90));
	
		log.setOnAction(e->doadd());
		//log.setOnAction(e->temp());
		btnsms.setOnAction(e->sendSMS());
		
//---------------------fields------------------------------------------------------
		txtuid=new TextField();
		txtpwd=new PasswordField();
		txtuid.setPromptText("Enter the user id");
		txtpwd.setPromptText("Enter the password");
		
		
//-----------------------add-------------------------------------------------------
		grid.add(img, 0, 0);
		grid.add(lbltitle, 1, 0);
		grid.add(lbluid, 1, 1);
		grid.add(lblpwd, 1, 3);
		grid.add(txtuid, 1, 2);
		grid.add(txtpwd, 1, 4);
		grid.add(h, 0, 5);
		
//---------------------------------scene2-------------------------------------------
		
		grid2=new GridPane();
		grid2.setGridLinesVisible(false);
		grid2.setPadding(new Insets(10,0,0,10));
		grid2.setHgap(10);
		grid2.setVgap(10);
		
		
		ttitle=new Text("Dashboard");
		ttitle.setFont(Font.font("Verdana",FontWeight.BOLD,40));
		ttitle.setFill(Color.valueOf("#ffd480"));
		grid2.setConstraints(ttitle, 1, 0, 2, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(0,0,0,0));
		
		lst=new ListView<String>();
		ary=new ArrayList<String>();
		
		lst.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		ary.add(" ");
		ary.add("About");
		ary.add("Customer Info");
		ary.add("Bill Record");
		ary.add("Tables");
		ary.add("Statistics");
		
		for(String item:ary)
		{
		lst.getItems().add(item);
		}
		lst.setPrefSize(180, 730);
	
		lst.getSelectionModel().select(1);
		lst.setStyle("-fx-font-size: 20px; "
				+ "-fx-font-family: 'SketchFlow Print';"
				+ "-fx-control-inner-background: #0d0d0d;-fx-background-insets: 0");
		grid2.setConstraints(lst, 0, 0, 1, 13, HPos.LEFT, VPos.CENTER, null, null, new Insets(0,0,0,0));

//----------------------------------about------------------------------------------------------------		
		h1=new HBox();
		about=new Text("It is a Java application which is made to help the Milk Supplier of an area.\nIt provides forms which can be used by him to maintain the records of customers.");
		about.setFont(Font.font("Verdana",FontWeight.BOLD,18));
		about.setFill(Color.valueOf("#eda891"));
		grid2.setConstraints(about, 1, 2, 2, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(0,0,0,30));
		
//-------------------------------------customers-------------------------------------------------------		
		img2=new ImageView(new Image(Login.class.getResourceAsStream("img/adduser.png")));
		img2.setFitHeight(100);
		img2.setFitWidth(100);
		t1=new Text("Customer Console\n\nThis is used to add\nnew customers.");
		t1.setFont(Font.font("Verdana",FontWeight.BOLD,18));
		t1.setFill(Color.valueOf("#eda891"));
		h1.getChildren().addAll(img2,t1);
		h1.setSpacing(12);
		GridPane.setConstraints(h1, 1, 2, 2, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(50,0,0,80));
		
		h2=new HBox();
		img3=new ImageView(new Image(Login.class.getResourceAsStream("img/dailyrec.png")));
		img3.setFitHeight(100);
		img3.setFitWidth(100);
		t2=new Text("Daily Record\n\nThis is used to note\nthe daily consumption of\ncustomers.");
		t2.setFont(Font.font("Verdana",FontWeight.BOLD,18));
		t2.setFill(Color.valueOf("#cc3600"));
		h2.getChildren().addAll(img3,t2);
		h2.setSpacing(12);
		GridPane.setConstraints(h2, 1, 5, 2, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(50,0,0,80));
		
//-----------------------------------------bill----------------------------------------------------------
		
		h3=new HBox();
		img4=new ImageView(new Image(Login.class.getResourceAsStream("img/billgen.png")));
		img4.setFitHeight(100);
		img4.setFitWidth(100);
		t3=new Text("Bill Generator\n\nThis is used to calculate\nevery customer's bill.");
		t3.setFont(Font.font("Verdana",FontWeight.BOLD,18));
		t3.setFill(Color.valueOf("#eda891"));
		h3.getChildren().addAll(img4,t3);
		h3.setSpacing(12);
		GridPane.setConstraints(h3, 1, 2, 2, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(50,0,0,80));
		
		h4=new HBox();
		img5=new ImageView(new Image(Login.class.getResourceAsStream("img/billc.png")));
		img5.setFitHeight(100);
		img5.setFitWidth(100);
		t4=new Text("Bill Collector\n\nThis is used to maintain\nthe record of the payment\nof the customers.");
		t4.setFont(Font.font("Verdana",FontWeight.BOLD,18));
		t4.setFill(Color.valueOf("#cc3600"));
		h4.getChildren().addAll(img5,t4);
		h4.setSpacing(12);
		GridPane.setConstraints(h4, 1, 5, 2, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(50,0,0,80));
		
//-------------------------------------tables--------------------------------------
		
		h5=new HBox();
		img6=new ImageView(new Image(Login.class.getResourceAsStream("img/ctbl.png")));
		img6.setFitHeight(100);
		img6.setFitWidth(100);
		t5=new Text("Customer Record\n\nGet the details regarding\nthe purchase of every customer.");
		t5.setFont(Font.font("Verdana",FontWeight.BOLD,18));
		t5.setFill(Color.valueOf("#eda891"));
		h5.getChildren().addAll(img6,t5);
		h5.setSpacing(12);
		GridPane.setConstraints(h5, 1, 2, 2, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(50,0,0,80));
		
		h6=new HBox();
		img7=new ImageView(new Image(Login.class.getResourceAsStream("img/billtbl.jpg")));
		img7.setFitHeight(100);
		img7.setFitWidth(100);
		t6=new Text("Bill Record\n\nGet the details of\nof the payment status\nof the customers.");
		t6.setFont(Font.font("Verdana",FontWeight.BOLD,18));
		t6.setFill(Color.valueOf("#cc3600"));
		h6.getChildren().addAll(img7,t6);
		h6.setSpacing(12);
		GridPane.setConstraints(h6, 1, 5, 2, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(50,0,0,80));
		
		h7=new HBox();
		img8=new ImageView(new Image(Login.class.getResourceAsStream("img/milkbowl.png")));
		img8.setFitHeight(100);
		img8.setFitWidth(100);
		t7=new Text("Milk Bowl\n\nGet the details of the\nexpenditure that has to be\ndone by the milkman.");
		t7.setFont(Font.font("Verdana",FontWeight.BOLD,18));
		t7.setFill(Color.valueOf("#eda891"));
		h7.getChildren().addAll(img8,t7);
		h7.setSpacing(12);
		h7.setSpacing(12);
		GridPane.setConstraints(h7, 1, 8, 2, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(50,0,0,80));
		
//----------------------------stats---------------------------------------------
		
		h8=new HBox();
		img9=new ImageView(new Image(Login.class.getResourceAsStream("img/stat.png")));
		img9.setFitHeight(100);
		img9.setFitWidth(100);
		t8=new Text("Analysis\n\nGet the trend\n in the expenditure that has to be\ndone by the milkman.");
		t8.setFont(Font.font("Verdana",FontWeight.BOLD,18));
		t8.setFill(Color.valueOf("#eda891"));
		h8.getChildren().addAll(img9,t8);
		h8.setSpacing(12);
		h8.setSpacing(12);
		GridPane.setConstraints(h8, 1, 2, 2, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(50,0,0,80));
		
		
		
//----------------------------fxn--------------------------------------------------
		
		about.setVisible(true);
		h1.setVisible(false);
		h2.setVisible(false);
		h3.setVisible(false);
		h4.setVisible(false);
		h5.setVisible(false);
		h6.setVisible(false);
		h7.setVisible(false);
		h8.setVisible(false);
		
		lst.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				
				if(lst.getSelectionModel().getSelectedItem().equals("About"))
				{
					about.setVisible(true);
					h1.setVisible(false);
					h2.setVisible(false);
					h3.setVisible(false);
					h4.setVisible(false);
					h5.setVisible(false);
					h6.setVisible(false);
					h7.setVisible(false);
					h8.setVisible(false);
				}
				
				if(lst.getSelectionModel().getSelectedItem().equals("Customer Info"))
				{	
					about.setVisible(false);
					h1.setVisible(true);
					h2.setVisible(true);
					h3.setVisible(false);
					h4.setVisible(false);
					h5.setVisible(false);
					h6.setVisible(false);
					h7.setVisible(false);
					h8.setVisible(false);
				}
				if(lst.getSelectionModel().getSelectedItem().equals("Bill Record"))
				{
					about.setVisible(false);
					h1.setVisible(false);
					h2.setVisible(false);
					h3.setVisible(true);
					h4.setVisible(true);
					h5.setVisible(false);
					h6.setVisible(false);
					h7.setVisible(false);
					h8.setVisible(false);
				}
				if(lst.getSelectionModel().getSelectedItem().equals("Tables"))
				{
					about.setVisible(false);
					h1.setVisible(false);
					h2.setVisible(false);
					h3.setVisible(false);
					h4.setVisible(false);
					h5.setVisible(true);
					h6.setVisible(true);
					h7.setVisible(true);
					h8.setVisible(false);
				}
				
				if(lst.getSelectionModel().getSelectedItem().equals("Statistics"))
				{
					about.setVisible(false);
					h1.setVisible(false);
					h2.setVisible(false);
					h3.setVisible(false);
					h4.setVisible(false);
					h5.setVisible(false);
					h6.setVisible(false);
					h7.setVisible(false);
					h8.setVisible(true);
					
					
				}
			}
		});
		
		
//--------------------------------------events---------------------------------------
		
		img2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				new CustomerConsole();
				
			}
		});
		
		img3.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				new DailyRecord();
				
			}
		});
		
		img4.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				new BillGen();
				
			}
		});
		
		img5.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				new BillCollector();
				
			}
		});
		
		img6.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				new CustomerTbl();
				
			}
		});
		
		img7.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				new PaymentTbl();
				
			}
		});
		
		img8.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				new MilkBowlTbl();
				
			}
		});
		
		img9.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				new Stats();
				
			}
		});
		
		
//-----------------------------add2--------------------------------------------------
		grid2.getChildren().addAll(ttitle,lst,about,h1,h2,h3,h4,h5,h6,h7,h8);
		
//-----------------------set---------------------------------------------------------	
		
		myBI= new BackgroundImage(new Image("elements/img/bground.jpg",1400,750,false,true),
		        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
		          BackgroundSize.DEFAULT);
		//then you set to your node
		grid.setBackground(new Background(myBI));
		
		bg= new BackgroundImage(new Image("elements/img/bg.jpg",1400,750,false,true),
		        BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
		          BackgroundSize.DEFAULT);
		//then you set to your node
		grid2.setBackground(new Background(bg));
		scene2=new Scene(grid2, 1400, 750);
		
		scene=new Scene(grid,1400,750);
		st.setScene(scene);
		st.show();

	}
	
private Object sendSMS() {
		
		String m="Your password is qwerty123 and UserId is Mehak Mittal ";
		String resp=SST_SMS.bceSunSoftSend("8437145781", m);
		if(resp.indexOf("Exception")!=-1)
		{
		System.out.println("Check Internet Connection");
		showMsg("Check Internet Connection");
		}
		else
		if(resp.indexOf("successfully")!=-1)
		{
		System.out.println("Sent");
		showMsg("Message Sent");
		}
		else
		
		System.out.println( "Invalid Number");
		return null;
	}

	void showMsg(String msg)
	{
		Alert al=new Alert(AlertType.INFORMATION);
		al.setHeaderText("Message");
		al.setContentText(msg);
		al.showAndWait();
	}
	
	
	
	void doadd() 
	{ 

		 try {
				
				pst = con.prepareStatement("select * from loginup where sname=?");
				pst.setString(1, "Mehak Mittal" );
				
				ResultSet rs = pst.executeQuery();
				while(rs.next())
				{
						name = rs.getString("sname");
					    pwd = rs.getString("pwd");	

				}
				rs.close();
				System.out.println(txtuid.getText());
				if(name.equals(txtuid.getText()) && pwd.equals(txtpwd.getText()))
				{
					showMsg("You have successfully logged in ");
					theStage.setScene(scene2);
					
				}
				else
						showMsg("Either Username or password is incorrect ");
			
			} catch (SQLException e) 
		    {
				e.printStackTrace();
			}
	}
	
/*	void temp()
	{
		theStage.setScene(scene2);
	}*/
}
