package elements;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class Stats{

	static Connection con;
	PreparedStatement pst;
	Stage stage=new Stage();
	
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
	
	
	Stats()
	{
		
		//ObservableList<XYChart.Series<Date, Number>> series = FXCollections.observableArrayList();
		try {
			doConnect();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		
		CategoryAxis xAxis = new CategoryAxis();
	    final NumberAxis yAxis = new NumberAxis();
	    xAxis.setLabel("Date");
	    yAxis.setLabel("Total Quantity(kg)");
	   final LineChart<String, Number> lineChart = new LineChart<String, Number>(xAxis, yAxis);
	 //   final BarChart<String, Number> lineChart = new BarChart<String, Number>(xAxis, yAxis);
	    
	    lineChart.setTitle("Analysis");
	    XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
	    series.setName("Daily Quantity");
	    // populating the series with data
	    
	    try {
			pst=con.prepareStatement("SELECT sDate,totalQty FROM milkBowl");
			 ResultSet rs=pst.executeQuery();
			    
			    while(rs.next())
			    {
			    	series.getData().add(new XYChart.Data(rs.getDate(1).toString(),rs.getFloat(2)));
			    }
			    
		} 
	    catch (SQLException e)
	    {
			
			e.printStackTrace();
		}
	   
	    
	    
	   

	    Scene scene = new Scene(lineChart, 800, 600);
	    lineChart.getData().add(series);

	    stage.setScene(scene);
	    stage.show();
		
	}

}
