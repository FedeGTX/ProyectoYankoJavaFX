package atm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/inicio.fxml"));

			Scene scene = new Scene(root);
			
			scene.getStylesheets().add(this.getClass().getResource("/Styles.css").toExternalForm());
primaryStage.setTitle("ATM cajero automatico Yanko");
			primaryStage.setScene(scene);
			
			primaryStage.setResizable(false);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
