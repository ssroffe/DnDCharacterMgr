package CharacterMgrv2;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;


public class Main extends Application {
	private String fileName;
	private Character c;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			Stage startupStage = new Stage();
			startupStage.setTitle("CharacterMgr -- Welcome");
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("StartpageViewer.fxml"));
			
			AnchorPane rootLayout = (AnchorPane) loader.load();
			Scene startScene = new Scene(rootLayout);
			StartpageController spc = loader.getController();
			spc.setStage(startupStage);
			startScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			startupStage.setScene(startScene);
			startupStage.showAndWait();
			
			this.c = spc.getCharacter();
			this.fileName = spc.getFileName();
			mainPage();
			
		} catch(Exception e) {
		}
	}
	
	public void mainPage() {
		try {
			Stage stage = new Stage();
			stage.setTitle("CharacterMgr");
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("MainPageViewer.fxml"));
			
			MainpageController mpc = new MainpageController(c,fileName,stage);
			
			loader.setController(mpc);
			
			AnchorPane root = (AnchorPane) loader.load();
			Scene scene = new Scene(root);

			stage.setScene(scene);
					
			stage.show();
		}
		catch (Exception e) {
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	

}
