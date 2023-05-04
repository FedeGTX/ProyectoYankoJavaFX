package atm;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.Random;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;


public class CardController {

	@FXML
	private Button buttonCreateCard;

	@FXML
	private TextField textFieldPin;

	public static File currentCard;

	private controlador controlador;

	public CardController() {
		controlador = new controlador();
	}

	public void insertCard(ActionEvent event) {
		try {
			FileChooser fileChooser = new FileChooser();
			fileChooser.getExtensionFilters()
.addAll(new FileChooser.ExtensionFilter("cajeroTM (.ctm)", "*.ctm"));
			File selectedCard = fileChooser.showOpenDialog(buttonCreateCard.getScene().getWindow());
			if (!selectedCard.toString().toLowerCase().endsWith(".ctm")) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText(null);
				alert.setContentText("�Este no es un archivo .ctm! Cree una tarjeta si no tiene una.");
				alert.showAndWait();
				return;
			}

			if (cardIsOk(selectedCard)) {
				currentCard = selectedCard;
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText(null);
				alert.setContentText("�Lo siento, no podemos procesar su tarjeta en este momento! "
                                        + "Por favor, seleccione otra tarjeta o comun�quese con su banco para obtener m�s informaci�n.");
				alert.showAndWait();
				return;
			}
			controlador.switchToPinScene(event);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public void createCard(ActionEvent event) {
		try {
		
			FileChooser fileChooser = new FileChooser();
			
			fileChooser.getExtensionFilters()
					.addAll(new FileChooser.ExtensionFilter("CTM targeta oficial (.ctm)", "*.ctm"));

			File newCard = fileChooser.showSaveDialog(buttonCreateCard.getScene().getWindow());

			if (!newCard.toString().toLowerCase().endsWith(".ctm")) {
				newCard = new File(newCard.getAbsolutePath() + ".ctm");
			}

			BufferedWriter writer = new BufferedWriter(new FileWriter(newCard));
			Random random = new Random();

			writer.write("Numero Cuenta: " + random.ints(1000, 9999).findFirst().getAsInt() + "\ndinero: 0" + "\npin: "
					+ random.ints(1000, 9999).findFirst().getAsInt());
			writer.close();

			currentCard = newCard;

			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("LISTO");
			alert.setHeaderText(null);
			alert.setContentText("Tu c�digo PIN es: " + getPin());
			alert.showAndWait();
			controlador.switchToPinScene(event);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private boolean cardIsOk(File selectedCard) {
		try {
			Scanner reader = new Scanner(selectedCard);

			while (reader.hasNextLine()) {
				String data = reader.nextLine();
				if (data.substring(0, 8).equals("numero de cuenta: ")) {
				} else if (data.substring(0, 9).equals("dinero: ")) {
				} else if (data.substring(0, 5).equals("pin: ")) {
				} else {
                                    return false;
				}
			}

			reader.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		
		return true;
	}
	private String getPin() {
		try {
			Scanner reader = new Scanner(currentCard);

			while (reader.hasNextLine()) {
				String data = reader.nextLine();
				if (data.substring(0, 5).equals("pin: ")) {	
					return (data.substring(5, data.length()));
				}
			}

			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public void checkPin(ActionEvent event) {
		if (getPin().equals(textFieldPin.getText())) {
			controlador.switchToManagementScene(event);
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("Lo siento, el c�digo PIN que ingresaste es incorrecto. Por favor, int�ntalo de nuevo.");
			alert.showAndWait();
			return;
		}
	}
}
