package Tabs;


import ENUMS.MembershipTypes;
import Tables.MembershipsTable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;

public class StatisticsTab extends Tab {

	//Step 1: create a private static instance variable
	private static StatisticsTab tab;
	public static BorderPane pane;
	
	//Step 2: set the construct to private 
	private StatisticsTab() {
		this.setText("Statistics");
		pane = new BorderPane();
		pane.setCenter(generateChart());
		this.setContent(pane);
	}		
			
	//Step 3: create a public static getInstance() method
	public static StatisticsTab getInstance() {
		if(tab == null) {
			tab = new StatisticsTab();
		}
		return tab;
	}
	
	public static PieChart generateChart() {
		MembershipsTable table = new MembershipsTable();
	
		int general = table.getMembershipTypeCount(MembershipTypes.Regular);
		int silver = table.getMembershipTypeCount(MembershipTypes.Silver);
		int gold = table.getMembershipTypeCount(MembershipTypes.Gold);
		int premuim = table.getMembershipTypeCount(MembershipTypes.Premuim);
		
		
		
		PieChart chart = new PieChart();
		chart.setTitle("Membership Types");
		chart.setLabelsVisible(true);
		ObservableList<PieChart.Data> data = 
				FXCollections.observableArrayList(
						new PieChart.Data("General: " + general, general),
						new PieChart.Data("Silver: " + silver , silver),
						new PieChart.Data("Gold: " + gold , gold),
						new PieChart.Data("Premuim: " + premuim , premuim));
		chart.setTitle("Memberships Types Count Chart");
		chart.setLabelLineLength(10);
		chart.setData(data);
		return chart;
	}
	
}
