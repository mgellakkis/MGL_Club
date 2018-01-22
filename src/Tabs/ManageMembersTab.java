package Tabs;

import java.sql.Connection;
import java.sql.DriverManager;
import com.mysql.jdbc.Statement;
import Database.Const;
import ENUMS.Gender;
import JavaBean.Member;
import Tables.MembersTable;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class ManageMembersTab extends Tab {
	
	//Step 1: create a private static instance variable
	private static ManageMembersTab tab;
	
	//tableview to show the data from members table
	private static TableView<Member> table = new TableView<Member>();
	private static final double TABLE_MIN_WIDTH = 500;
	private static ObservableList<Member> membersData;
	String c1;
    
	//Step 2: set the construct to private 
	private ManageMembersTab() {
		this.setText("Manage Members");
		Label title = new Label("Manage Members...");
		title.getStyleClass().add("titleFontStyle");

		Button addButton = new Button("Add Member");
		Button editButton = new Button("Update Member");
		Button deleteButton = new Button("Delete Member");
		
		BorderPane pane = new BorderPane();
		pane.getStylesheets().add("borderPane.css");
		
		addButton.getStyleClass().add("buttonStyles");
		editButton.getStyleClass().add("buttonStyles");
		deleteButton.getStyleClass().add("buttonStyles");
		
		//tableview columns
		TableColumn<Member, String> colID = new TableColumn<Member, String>("Member ID");
		TableColumn<Member, String> colfName = new TableColumn<Member, String>("First Name");
		TableColumn<Member, String> collName = new TableColumn<Member, String>("Last Name");
		TableColumn colGender = new TableColumn("Gender");
		TableColumn<Member, String> colPhone = new TableColumn<Member, String>("Phone");
		
        //feed the Tableview with data from the table
        MembersTable membersTable = new MembersTable();

		//data to feed
        membersData = FXCollections.observableArrayList(membersTable.getAllMembers());
        
        //setting data to columns
        
        //member_id column
		colID.setCellValueFactory(new PropertyValueFactory<Member, String>("memberId"));
		colID.setMinWidth(120);
		colID.setCellFactory(TextFieldTableCell.forTableColumn());
		colID.setOnEditCommit(
                new EventHandler<CellEditEvent<Member, String>>() {
                    @Override
                    public void handle(CellEditEvent<Member, String> t) {
                        ((Member) t.getTableView().getItems().get(
                        		t.getTablePosition().getRow())
                        		).setId(t.getNewValue());
                    }
                }
            );
		
		//first name column
        colfName.setCellValueFactory(new PropertyValueFactory<Member, String>("fname"));
        colfName.setMinWidth(120);
        colfName.setCellFactory(TextFieldTableCell.forTableColumn());
        colfName.setOnEditCommit(
                new EventHandler<CellEditEvent<Member, String>>() {
                    @Override
                    public void handle(CellEditEvent<Member, String> t) {
                        ((Member) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).setFname(t.getNewValue());
                    }
                }
            );
        
        //last name column
        collName.setCellValueFactory(new PropertyValueFactory<Member, String>("lname"));
        collName.setMinWidth(120);
        collName.setCellFactory(TextFieldTableCell.forTableColumn());
        collName.setOnEditCommit(
                new EventHandler<CellEditEvent<Member, String>>() {
                    @Override
                    public void handle(CellEditEvent<Member, String> t) {
                        ((Member) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).setLname(t.getNewValue());
                    }
                }
            );
        
        //Gender column
        ObservableList<Gender> genderList = FXCollections.observableArrayList(Gender.values());
        colGender.setCellValueFactory(new Callback<CellDataFeatures<Member, Gender>, ObservableValue<Gender>>() {

			@Override
			public ObservableValue<Gender> call(CellDataFeatures<Member, Gender> param) {
				Member member = param.getValue();
                // F,M
                String genderCode = member.getGender();
                Gender gender = Gender.getByCode(genderCode);
                return new SimpleObjectProperty<Gender>(gender);
			}
        });
 
        colGender.setCellFactory(ComboBoxTableCell.forTableColumn(genderList));        
        colGender.setOnEditCommit(new EventHandler<CellEditEvent<Member, Gender>>() {
	            
				@Override
				public void handle(CellEditEvent<Member, Gender> event) {
					TablePosition<Member, Gender> pos = event.getTablePosition();
					 
		            Gender newGender = event.getNewValue();
		 
		            int row = pos.getRow();
		            Member member = event.getTableView().getItems().get(row);
		            member.setGender(newGender.getCode());
				}	            
        });
 
        colGender.setMinWidth(120);
        
        //phone column
        colPhone.setCellValueFactory(new PropertyValueFactory<Member, String>("phone"));
        colPhone.setMinWidth(120);
        colPhone.setCellFactory(TextFieldTableCell.forTableColumn());
        colPhone.setOnEditCommit(
                new EventHandler<CellEditEvent<Member, String>>() {
                    @Override
                    public void handle(CellEditEvent<Member, String> t) {
                        ((Member) t.getTableView().getItems().get(
                            t.getTablePosition().getRow())
                            ).setPhone(t.getNewValue());
                    }
                }
            );
        
        table.getColumns().addAll(colID, colfName, collName, colGender, colPhone);
        table.setItems(membersData);
        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        table.setMinWidth(TABLE_MIN_WIDTH);
        table.setEditable(true);
        
        
        //text fields for adding new member details
        final TextField addID = new TextField();
        addID.setPromptText("ID");
        addID.setMaxWidth(colID.getPrefWidth());
        final TextField addFirstName = new TextField();
        addFirstName.setPromptText("First Name");
        addFirstName.setMaxWidth(colfName.getPrefWidth());
        final TextField addLastName = new TextField();
        addLastName.setMaxWidth(collName.getPrefWidth());
        addLastName.setPromptText("Last Name");
        final TextField addGender = new TextField();
        addGender.setMaxWidth(collName.getPrefWidth());
        addGender.setPromptText("Gender");
        final TextField addPhone = new TextField();
        addPhone.setMaxWidth(colPhone.getPrefWidth());
        addPhone.setPromptText("Phone");
 
      //button event handlers
        addButton.setOnAction(e->{
	        	membersData.add(new Member(
	        				addID.getText(),
	                    addFirstName.getText(),
	                    addLastName.getText(),
	                    addGender.getText(),
	                    addPhone.getText()));
	            addFirstName.clear();
	            addLastName.clear();
	            addPhone.clear();
	            addID.clear();
	            addGender.clear();
        });
        
        deleteButton.setOnAction(e->{
        		table.getItems().removeAll(
                table.getSelectionModel().getSelectedItems()
            );
        });
        
      
        editButton.setOnAction(e->{
        		updataData();
        });
        
        
        pane.setTop(title);;
		pane.setCenter(table);
		
		//vbox to hold the buttons
		VBox vbox = new VBox();
		vbox.getChildren().addAll(addButton, editButton, deleteButton);
		vbox.setPadding(new Insets(10, 10, 10, 10));
		pane.setRight(vbox);
		pane.setPadding(new Insets(30, 30, 30, 30));
		this.setContent(pane);
		
		//hbox to hold text fields
		HBox hbox = new HBox();
		hbox.getChildren().addAll(addID, addFirstName, addLastName, addGender, addPhone);
		pane.setBottom(hbox);
	}		
			

	private void updataData() {
		
		Connection connection = null;
        try {
            connection = DriverManager.getConnection(Const.url+Const.DB_NAME, Const.DB_USER, Const.DB_PASS);
            Statement con = (Statement) connection.createStatement();
            //connection
            TablePosition<?, ?> pos = table.getSelectionModel().getSelectedCells().get(0);
            int row = pos.getRow();
            TableColumn<?, ?> col = pos.getTableColumn();
             String data1 = (String) col.getCellObservableValue(row).getValue();
            //cell
            Member row1 = table.getSelectionModel().getSelectedItem();
            c1 = row1.getId();
            //row
            //tableview variables
            con.execute("UPDATE Members SET  memberId = 'data1' WHERE memberId = 'c1' ");
            //Query       
        } catch (Exception ex) {
            System.err.println("Error" + ex);
        }
		
	}


	//Step 3: create a public static getInstance() method
	public static ManageMembersTab getInstance() {
		if(tab == null) {
			tab = new ManageMembersTab();
		}
		return tab;

	}
}

