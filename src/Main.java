import Tabs.ManageMembersTab;
import Tabs.StatisticsTab;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class Main extends Application {

	public static void main(String[] args) {
		Application.launch(args);

	}

	//global declarations
	private Pane splashLayout;
	private Stage mainStage;
	TabPane tabPane;
	private static final int SPLASH_WIDTH = 676;
	private static final int SPLASH_HEIGHT = 227;
	private static Scene scene;
	private static Scene creditsScene;
	Button backButton;
	
	
	/**
	 * setting the layout of the splash screen
	 */
	@Override public void init() {
	    ImageView splash = new ImageView(new Image("file:images/MGL_Club_logo.png"));
	    splashLayout = new VBox();
	    splashLayout.getChildren().add(splash);
	    splashLayout.setStyle("-fx-padding: 5; -fx-background-color: white; -fx-border-width:5; -fx-border-color: black;");
	    splashLayout.setEffect(new DropShadow());
	}
	
	/**
	 * main routine that is handling stage show/hide
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		if (primaryStage.isShowing()) {
	          
	          mainStage.setIconified(false);
	          primaryStage.toFront();
	          FadeTransition fadeSplash = new FadeTransition(Duration.seconds(1.2), splashLayout);
	          fadeSplash.setFromValue(1.0);
	          fadeSplash.setToValue(0.0);
	          fadeSplash.setOnFinished(new EventHandler<ActionEvent>() {
	            @Override public void handle(ActionEvent actionEvent) {
	              primaryStage.hide();
	            }
	          });
	          fadeSplash.play();
	    }
		showSplash(primaryStage);
	}

	/**
	 * routine to handle showing mainStage
	 */
	private void showMainStage() {
	    mainStage = new Stage(StageStyle.DECORATED);
	    mainStage.setTitle("MGL Club");
	    mainStage.setIconified(false);
	    
	    //Build a menu bar
		MenuBar menu = new MenuBar();
		Menu fileMenu = new Menu("File");
		Menu credits = new Menu("Credits");
		
		fileMenu.getStyleClass().add("menuFontStyle");
		fileMenu.getStyleClass().add("menuFontStyle");
		
  		//Build all menu items
  		MenuItem exit = new MenuItem("Exit");
  		exit.setOnAction(e->{
//  			Database db = Database.getInstance();
//  			db.close();
  			System.exit(0);
  		});
  		
  		backButton = new Button("Back");
  		backButton.getStyleClass().add("buttonStyles");
		backButton.setOnAction(e->{
			mainStage.setScene(scene);
		});
			
  		MenuItem about = new MenuItem("About");
  		about.setOnAction(e->{
  			
  			StackPane pane = new StackPane();
  			
  			Label crd = new Label("This Application is designed by");
  			crd.getStyleClass().add("crdStyle");
  			Label crd2 = new Label("Machael Ellakkis");
  			crd2.getStyleClass().add("crdStyle");
  			
  			VBox vbox = new VBox();
  			vbox.setPadding(new Insets(250,10,10,250));
  			vbox.getChildren().addAll(crd, crd2, backButton);
  			StackPane.setAlignment(vbox, Pos.CENTER);
  			pane.getChildren().add(vbox);
  			
  			creditsScene = new Scene(pane, 1024, 768);
  			creditsScene.getStylesheets().add("borderPane.css");
  			mainStage.setScene(creditsScene);
  		});
  		
  		fileMenu.getItems().add(exit);
  		credits.getItems().add(about);
  		//Add the menu items to the menu bar
  		menu.getMenus().addAll(fileMenu, credits);
  		
  		//Create a TabPane
  		tabPane = new TabPane();
  		tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);
  		
  		//Create the tabs
  		ManageMembersTab manageMembersTab = ManageMembersTab.getInstance();
  		manageMembersTab.setClosable(false);
  		
  		StatisticsTab statisticsTab = StatisticsTab.getInstance();
  		statisticsTab.setClosable(false);
  		//Add the tabs to the TabPane
  		tabPane.getTabs().addAll(manageMembersTab, statisticsTab);
  		
  		//Create a BorderPane
  		BorderPane pane = new BorderPane();
  		pane.getStylesheets().add("borderPane.css");
  		
  		//set the top of the BorderPane to the MenuBar
  		pane.setTop(menu);
  		//Set the center of the BorderPane to the TabPane
  		pane.setCenter(tabPane);
  		//Create the scene
  		scene = new Scene(pane, 1024, 768);
  		//Add the scene to the stage
  		scene.getStylesheets().add("DarkTheme.css");
	    mainStage.setScene(scene);
	    mainStage.show();
	  }

	/**
	 * routine to handle showing splashScreen and if a click event triggered on the 
	 * splashScreen then the main screen will show up
	 * @param primaryStage
	 */
	  private void showSplash(Stage primaryStage) {
	    Scene splashScene = new Scene(splashLayout);
	    primaryStage.initStyle(StageStyle.UNDECORATED);
	    primaryStage.setScene(splashScene);
	    
	    final Rectangle2D primScreenBounds = Screen.getPrimary().getBounds();
	    primaryStage.setX((primScreenBounds.getWidth() - SPLASH_WIDTH) / 3);
	    primaryStage.setY((primScreenBounds.getHeight() - SPLASH_HEIGHT) / 3);
	    primaryStage.show();
	    
	    splashScene.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent mouseEvent) {
		        primaryStage.hide();
		        showMainStage();
		    }
		});
	  }

	  
	  
}
