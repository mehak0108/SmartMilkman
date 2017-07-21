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
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class DailyRecord {

	static Connection con;
	PreparedStatement pst,pst1;
	
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
	ListView<String> list;
	ArrayList<String> ary,ary2,ary3,ary4;
	Text ttitle;
	Button btnpe,btnnd,btnpost;
	VBox v;
	Text tcm,tbm;
	TextField fcm,fbm;
	Stage stage;
	
	
	DailyRecord() {
		
		try {
			doConnect();
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
		
		stage=new Stage();
		grid=new GridPane();
		grid.setGridLinesVisible(false);
		grid.setPadding(new Insets(100,0,0,200));
		grid.setHgap(20);
		grid.setVgap(20);
		
//--------------------------Title----------------------------------
		
		ttitle= new Text("Daily Record");
		GridPane.setConstraints(ttitle, 0, 0, 3, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(0,0,0,0));
		ttitle.setFont(Font.font("Verdana",FontWeight.BOLD, 40));
		

//-------------------------LIst--------------------------------------		
		
		try {
			doFillnames();
		} catch (SQLException e1) {
			
			e1.printStackTrace();
		}
		doselect();
		
	
	    list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
	    list.setStyle("-fx-font-size: 18px; "
				+ "-fx-font-family: 'SketchFlow Print'"
				);
		
		
//------------------------------buttons------------------------------------
		
		btnpe=new Button("Post Entry");
		btnnd= new Button("Not Delivered");
		btnpost= new Button("Post");
		btnpe.setPrefSize(100, 30);
		
		v=new VBox();
		v.getChildren().addAll(btnpe,btnnd,btnpost);
		v.setSpacing(30);
		
		
		GridPane.setConstraints(list, 0, 1, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(20,0,20,20));
		GridPane.setConstraints(v, 1, 1, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(20,0,20,20));
		
		
//----------------------------------------------------------------------------
		
		btnpe.setOnAction(e->dopost());
		btnnd.setOnAction(e->doND());
		btnpost.setOnAction(e->dopt());
		
		
		grid.getChildren().add(ttitle);
		grid.getChildren().add(list);
		grid.getChildren().add(v);
		
		
		
		
		scene=new Scene(grid,800,600);
		stage.setScene(scene);
		stage.setTitle("Record");
		stage.show();
		
		
		
		
	}
	
	void doFillnames() throws SQLException
	{
		list=new ListView<String>();
		ary=new ArrayList<String>();
		
		
		try {
			pst=con.prepareStatement("SELECT cname FROM customer");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ResultSet rs=pst.executeQuery();
		while(rs.next())
		{
			ary.add(rs.getString(1));
		}
		
		for(String s:ary)
			list.getItems().add(s);
		list.setPrefSize(150, 230);
		
	}
	
	void doSave(ResultSet rs)
	{
		try {
			
			rs.next();
	
			pst=con.prepareStatement("INSERT INTO dailyRecord VALUES(?,?,?,CURDATE())");
			pst.setString(1, rs.getString(1));
			pst.setFloat(2, rs.getFloat(5));
			pst.setFloat(3, rs.getFloat(6));
			pst.executeUpdate();
			pst.close();
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("prob");
		}
		
	}
	
	void dopost()
	{
		ary2=new ArrayList<String>();
		
		for(int i=0;i<list.getItems().size();i++)
		{
			if(list.getSelectionModel().isSelected(i)==false)
			{
				ary2.add(list.getItems().get(i));
				System.out.println(list.getItems().get(i));
			}
		}
		
		for(int j=0;j<ary2.size();j++)
		{
			try {
				pst= con.prepareStatement("SELECT * FROM customer WHERE cname=?");
				pst.setString(1, ary2.get(j));
				ResultSet rs=pst.executeQuery();
				doSave(rs);
				list.getItems().removeAll(ary2);
				
			}
			
			catch (SQLException e) 
			{
				
				e.printStackTrace();
				System.out.println("error");
			}
			
		}
		
		
	}

	void doSave2(ResultSet rs)
	{
		try {
			rs.next();
			pst=con.prepareStatement("INSERT INTO dailyRecord VALUES(?,?,?,CURDATE())");
			pst.setString(1, rs.getString(1));
			pst.setFloat(2, 0f);
			pst.setFloat(3, 0f);
			pst.executeUpdate();
			pst.close();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
	
	void doND()
	{
		ary3=new ArrayList<String>();
		
		for(int i=0; i<list.getItems().size();i++)
		{
			if(list.getSelectionModel().isSelected(i) == false)
			{
				ary3.add(list.getItems().get(i));
				System.out.println(list.getItems().get(i));
			}
		}
		
		for(int j=0; j<ary3.size(); j++)
		{
			try {
				pst=con.prepareStatement("SELECT * FROM customer WHERE cname=?");
				pst.setString(1, ary3.get(j));
				ResultSet rs= pst.executeQuery();
				doSave2(rs);
				list.getItems().removeAll(ary3);
			} 
			catch (SQLException e) {
				
				e.printStackTrace();
				System.out.println("no");
			}
			
		}
		
	}
	
	
	void doselect()
	{
		
		list.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				if(event.getClickCount()==2)
				{
					tcm=new Text("Cow Milk:");
					tbm=new Text("Buffalo Milk:");
					fcm=new TextField();
					fbm=new TextField();
					fcm.setPrefSize(120, 20);
					fbm.setPrefSize(120, 20);
					fcm.setPromptText("Quantity in kgs");
					fbm.setPromptText("Quantity in kgs");
					
				    GridPane.setConstraints(tcm, 1, 1, 1, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(130,0,0,20));
				    GridPane.setConstraints(tbm, 1, 1, 1, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(200,0,0,20));
				    GridPane.setConstraints(fcm, 1, 1, 1, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(130,0,0,120));
				    GridPane.setConstraints(fbm, 1, 1, 1, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(200,0,0,120));
				    
				    grid.getChildren().add(tcm);
				    grid.getChildren().add(tbm);
				    grid.getChildren().add(fcm);
				    grid.getChildren().add(fbm);
		
				}
				
				
			}
			
		});
		
	}
	
	
	
	
	void doSave3(ResultSet rs)
	{
		
		System.out.println("prob starts");
	
			
			
			try {
				
				
				System.out.println(1);
				pst.setString(1, rs.getString("cname"));
			//	System.out.println(2);
				
			//	System.out.println(4);
				
				pst.executeUpdate();
			//	System.out.println(5);
				pst.close();
				
			} 
			catch (SQLException e) 
			{
				System.out.println("prob");
				e.printStackTrace();
			}
			
			
		
	}
	
	void dopt()
	{
		
		System.out.println("no ok");
		try {
		
			
			pst=con.prepareStatement("INSERT INTO dailyRecord VALUES(?,?,?,CURDATE())");
			
			pst.setString(1, list.getSelectionModel().getSelectedItem());
			pst.setFloat(2, Float.parseFloat(fcm.getText()));
			//	System.out.println(3);
			pst.setFloat(3, Float.parseFloat(fbm.getText()));
			
			pst.executeUpdate();
			pst.close();
			
			
			int a=list.getSelectionModel().getSelectedIndex();
		    System.out.println("problem");	
		    list.getItems().remove(a);
		
		}
		catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	
	
	
	
}
