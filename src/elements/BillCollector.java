package elements;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class BillCollector{

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
	
	

	ComboBox<String> fuid=new ComboBox<String>();
	ListView<String> lst;
	ListView<Date>lst1;
	ArrayList<String> ary;
	ArrayList<Date> ary1;
	Text ttitle,tnm,tstart,tamt;
	GridPane grid;
	Scene scene;
	Button btnup,btnew;
	HBox h;
	Stage stage=new Stage();
	
	BillCollector(){
		
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
		fuid.setPrefWidth(150);
		GridPane.setConstraints(fuid, 1, 1, 1, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(0,0,0,0));
		
		grid=new GridPane();
		grid.setGridLinesVisible(false);
		grid.setHgap(10);
		grid.setVgap(20);
		grid.setPadding(new Insets(100,0,0,120));
		
		ttitle=new Text("Bill Collector");
		tnm=new Text("Name:");
		
		GridPane.setConstraints(ttitle, 0, 0, 3, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(0,0,0,0));
		GridPane.setConstraints(tnm, 0, 1, 1, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(0,0,0,0));
		
		
		ttitle.setFont(Font.font("Verdana",FontWeight.BOLD, 40));
		tnm.setFont(Font.font("Arial",FontWeight.MEDIUM, 18));
		
		fuid.getSelectionModel().selectedIndexProperty().addListener((property,oldValue,newValue)->{
			doFillDate();
		
		});
		
		btnup=new Button("UPDATE");
		btnew=new Button("NEW");
		btnup.setPrefSize(100, 30);
		btnew.setPrefSize(100, 30);
		
		h=new HBox();
		h.getChildren().addAll(btnup,btnew);
		h.setSpacing(40);
		
		GridPane.setConstraints(h, 0, 7, 2, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(0,0,0,20));
		
		btnup.setOnAction(e->doup());
		btnew.setOnAction(e->doNew());
		
		grid.getChildren().addAll(fuid,ttitle,tnm,h);
		
		scene=new Scene(grid,600,600);
		stage.setScene(scene);
		stage.setTitle("bill");
		stage.show();
		
	}
	
	void doFillUids()
	{
		fuid.getItems().clear();
		try {
			pst=con.prepareStatement("SELECT DISTINCT cname FROM billgen WHERE cstatus=? ");
			pst.setInt(1, 0);
			
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
	
	void doFillDate()
	{
		tstart=new Text("Start Date");
		tamt=new Text("Total Amount");
		
		GridPane.setConstraints(tstart, 0, 2, 1, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(0,0,0,0));
		GridPane.setConstraints(tamt, 1, 2, 1, 1, HPos.LEFT, VPos.CENTER, null, null, new Insets(0,0,0,0));
		
		tstart.setFont(Font.font("Arial",FontWeight.MEDIUM, 18));
		tamt.setFont(Font.font("Arial",FontWeight.MEDIUM, 18));
		
		tstart.setUnderline(true);
		tamt.setUnderline(true);
		
		lst=new ListView<String>();
		lst1=new ListView<Date>();
		ary=new ArrayList<String>();
		ary1=new ArrayList<Date>();
		
		try {
			pst=con.prepareStatement("SELECT StartDate,Total FROM billgen WHERE cname=? AND cstatus=?");
			pst.setString(1, fuid.getSelectionModel().getSelectedItem());
			pst.setInt(2, 0);
			ResultSet rs=pst.executeQuery();
			while(rs.next())
			{
				ary1.add(rs.getDate(1));
				ary.add(String.valueOf(rs.getFloat(2)));
				
			}
			
			for(String s:ary)
				lst.getItems().add(s);
			for(Date d:ary1)
				lst1.getItems().add(d);
			
			lst1.setPrefSize(120, 140);
			lst.setPrefSize(80, 80);
			
			lst.setMouseTransparent(true);
			
			GridPane.setConstraints(lst1, 0, 3, 1, 4, HPos.CENTER, VPos.CENTER, null, null, new Insets(0,0,0,0));
			GridPane.setConstraints(lst, 1, 3, 1, 4, HPos.CENTER, VPos.CENTER, null, null, new Insets(0,0,0,0));
			
			grid.getChildren().addAll(lst1,lst,tstart,tamt);
			
			
		} catch (SQLException e) 
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
	
	void doup() 
	{
		try {
			pst=con.prepareStatement("UPDATE billgen SET cstatus=? WHERE cname=? AND StartDate=?");
			pst.setInt(1, 1);
			pst.setString(2, fuid.getSelectionModel().getSelectedItem());
			pst.setDate(3, lst1.getSelectionModel().getSelectedItem());
			
			pst.executeUpdate();
			
			int a=lst1.getSelectionModel().getSelectedIndex();
			lst1.getItems().remove(a);
			lst.getItems().remove(a);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		showMsg("Record successfully updated.");
		
	}
	
	void doNew()
	{
		fuid.getSelectionModel().clearSelection();
		tstart.setVisible(false);
		tstart.setUnderline(false);
		tamt.setUnderline(false);
		tamt.setVisible(false);
	}

	
}
