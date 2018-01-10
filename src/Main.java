import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
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
	private static final int SPLASH_WIDTH = 676;
	private static final int SPLASH_HEIGHT = 227;
	
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

	    // layout the scene.
	    BorderPane root = new BorderPane();
	    Scene scene = new Scene(root, 1000, 600);
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
