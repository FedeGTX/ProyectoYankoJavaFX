package atm;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class controlador {

	private Stage stage;
	private Scene scene;
	private Parent root;

	public void switchToManagementScene(ActionEvent event) {
		try {
		
			root = FXMLLoader.load(getClass().getResource("/cajero.fxml"));
			stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			scene = new Scene(root);
			stage.setScene(scene);

			scene.getStylesheets().add(this.getClass().getResource("/Styles.css").toExternalForm());

			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void switchToInsertScene(ActionEvent event) {
		try {
			
			root = FXMLLoader.load(getClass().getResource("/inicio.fxml"));
	stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	scene = new Scene(root);
	stage.setScene(scene);
	scene.getStylesheets().add(this.getClass().getResource("/Styles.css").toExternalForm());
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public void switchToPinScene(ActionEvent event) {
		try {
			root = FXMLLoader.load(getClass().getResource("/pin.fxml"));
	stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	scene = new Scene(root);
	stage.setScene(scene);
	scene.getStylesheets().add(this.getClass().getResource("/Styles.css").toExternalForm());
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
