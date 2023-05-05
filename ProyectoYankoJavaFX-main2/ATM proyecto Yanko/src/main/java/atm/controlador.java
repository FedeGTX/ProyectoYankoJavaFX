package atm;
import java.io.IOException;

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
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
      public void switchToMovimientosScene(ActionEvent event) {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/movimientos.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
    }
}

}
