package atm;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.net.URL;
import java.text.DateFormat;
import java.util.Date;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;

enum State {
	WITHDRAW, DEPOSIT
};

public class ActionHandler implements Initializable {

	@FXML
	private Rectangle rectangleAmount;
	@FXML
	private Label labelAmount;
	@FXML
	private Label labelWrittenAmount;

	@FXML
	private Label labelBalance;
	@FXML
	private Label labelNumber;

	@FXML
	private Label labelError;

	@FXML
	private Label labelReceipt;
	private double balance;
	private int number;
	private int pin;
	State state;
	public void initialize(URL location, ResourceBundle resources) {
		setVisibleAmountComponents(false);
		initializeCardInformation();
		labelError.setVisible(false);
		balance = getBalanceFromFile();
	}
	public void logout(ActionEvent event) {
		controlador controlador = new controlador();
		controlador.switchToInsertScene(event);
	}

	
	public void withdraw(ActionEvent event) {
		state = State.WITHDRAW;
		setVisibleAmountComponents(true);
		clear(event);
	}

	
	public void deposit(ActionEvent event) {
		state = State.DEPOSIT;
		setVisibleAmountComponents(true);
		clear(event);
	}


	public void cancel(ActionEvent event) {
		setVisibleAmountComponents(false);
		labelError.setVisible(false);
		clear(event);
	}
	public void enter(ActionEvent event) {
		double amount = Double.parseDouble(labelWrittenAmount.getText());

		if (setBalance(amount)) {
			updateCard();

			if (amount != 0) {
				generateReceipt(amount);
			}

			labelError.setVisible(false);
			setVisibleAmountComponents(false);
			clear(event);
		}
	}

	
	public void clear(ActionEvent event) {
		labelError.setVisible(false);
		labelWrittenAmount.setText("");
	}

	
	private void addCharToAmount(String ch) {
		labelWrittenAmount.setText(labelWrittenAmount.getText() + ch);
	}

	public void handleButtonOne(ActionEvent event) {
		addCharToAmount("1");
	}

	public void handleButtonTwo(ActionEvent event) {
		addCharToAmount("2");
	}

	public void handleButtonThree(ActionEvent event) {
		addCharToAmount("3");
	}

	public void handleButtonFour(ActionEvent event) {
		addCharToAmount("4");
	}

	public void handleButtonFive(ActionEvent event) {
		addCharToAmount("5");
	}

	public void handleButtonSix(ActionEvent event) {
		addCharToAmount("6");
	}

	public void handleButtonSeven(ActionEvent event) {
		addCharToAmount("7");
	}


	public void handleButtonEight(ActionEvent event) {
		addCharToAmount("8");
	}


	public void handleButtonNine(ActionEvent event) {
		addCharToAmount("9");
	}


	public void handleButtonDot(ActionEvent event) {
		addCharToAmount(".");
	}

	public void handleButtonZero(ActionEvent event) {
		addCharToAmount("0");
	}
	private void setVisibleAmountComponents(boolean value) {
		rectangleAmount.setVisible(value);
		labelAmount.setVisible(value);
		labelWrittenAmount.setVisible(value);
	}

	private void initializeCardInformation() {
		try {
			Scanner reader = new Scanner(CardController.currentCard);

			while (reader.hasNextLine()) {		
				String data = reader.nextLine();
				if (data.substring(0, 9).equals("dinero: ")) {
					labelBalance.setText("dinero: " + data.substring(9, data.length()));
				} else if (data.substring(0, 8).equals("Numero de cuenta: ")) {
					number = Integer.parseInt(data.substring(8, data.length()));
					labelNumber.setText("Numero de cuenta: " + number);
				} else if (data.substring(0, 5).equals("pin: ")) {
					pin = Integer.parseInt(data.substring(5, data.length()));
				}
			}
			
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	private double getBalanceFromFile() {
		try {
			Scanner reader = new Scanner(CardController.currentCard);

			while (reader.hasNextLine()) {
				
				String data = reader.nextLine();

				if (data.substring(0, 9).equals("dinero: ")) {
					
					return Double.parseDouble(data.substring(9, data.length()));
				}
			}

			reader.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0.0;
	}

	private boolean setBalance(double value) {
		if (state == State.DEPOSIT) {
			balance += value;
			labelBalance.setText("dinero: " + balance);

			return true;
		} else {
			
			if (balance - value < 0) {
				labelError.setText("Lo siento, parece que tus fondos actuales no son suficientes para completar esta transacci�n.");
				labelError.setVisible(true);

				return false;
			}

			balance -= value;
			labelBalance.setText("dinero: " + balance);

			return true;
		}
	}
	private void updateCard() {
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(CardController.currentCard));
			writer.write("number: " + number + "\ndinero: " + balance + "\npin: " + pin);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void generateReceipt(double amount) {
		Date date = new Date();

		labelReceipt.setText("La transacci�n se completa. \n" + DateFormat.getDateTimeInstance().format(date)
				+ ".\nMonto: " + amount  + "\ndinero: " + balance);
	}
        private void verdinero(double amount) {
		Date date = new Date();

		labelReceipt.setText("tienes un total de . \ndinero en la cuenta"  +balance );
	}
}
