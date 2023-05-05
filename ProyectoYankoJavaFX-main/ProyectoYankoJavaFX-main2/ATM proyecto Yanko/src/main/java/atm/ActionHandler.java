package atm;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
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
	retirar, depositar , depositar2
};

public class ActionHandler implements Initializable {
@FXML
private Rectangle movimientos;
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
	private int balance;
	private int number;
	private int pin;
	State state;
	public void initialize(URL location, ResourceBundle resources) {
		setVisibleAmountComponents(false);
		initializeCardInformation();
		labelError.setVisible(false);
		int balance = (int) getBalanceFromFile();


	}
	public void logout(ActionEvent event) {
		controlador controlador = new controlador();
		controlador.switchToInsertScene(event);
	}

	public void trans(ActionEvent event) {
		controlador controlador = new controlador();
		controlador.trans(event);
	}

	public void withdraw(ActionEvent event) {
		state = State.retirar;
		setVisibleAmountComponents(true);
		clear(event);
	}
        

	
	public void deposit(ActionEvent event) {
		state = State.depositar;
		setVisibleAmountComponents(true);
		clear(event);
                
	}


	public void cancel(ActionEvent event) {
		setVisibleAmountComponents(false);
		labelError.setVisible(false);
		clear(event);
	}
	public void enter(ActionEvent event) {
		int amount = Integer.parseInt(labelWrittenAmount.getText());

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
private int getBalanceFromFile() {
    try {
        Scanner reader = new Scanner(CardController.currentCard);

        while (reader.hasNextLine()) {
            String data = reader.nextLine();

            if (data.substring(0, 9).equals("dinero: ")) {
                double balance = Double.parseDouble(data.substring(9, data.length()));

                // Revisar que el número sea entero y termine en 0 o 5
                if (balance % 1 == 0 && (balance % 10 == 0 || balance % 10 == 5)) {
                    return (int) balance;
                } else {
                    return -1;
                }
            }
        }
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
    return -1;
}



private boolean setBalance(int value) {
    if (state == State.depositar) {
        balance += value;
        labelBalance.setText("dinero: " + balance);

        return true;
    } else {
        if (value % 5 != 0) {
            labelError.setText("Lo siento, transacción denegada");
            labelError.setVisible(true);

            return false;
        } else if (balance - value < 0) {
            labelError.setText("Lo siento, parece que tus fondos actuales no son suficientes para completar esta transacción.");
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
	private void generateReceipt(int amount) {
		Date date = new Date();

		labelReceipt.setText("La transaccion se completa. \n" + DateFormat.getDateTimeInstance().format(date)
				+ ".\nMonto: " + amount  + "\ndinero: " + balance);
	}
      
}
