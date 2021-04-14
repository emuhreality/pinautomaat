package controller.keyboard;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import model.UniversalKeyBoard;

import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

public class UniversalKeyboardController implements Observer,Initializable{
    private Button button;
    @FXML
    private Text inputField;
    UniversalKeyBoard universalKeyBoard = UniversalKeyBoard.getINSTANCE();

    public UniversalKeyboardController() {
        universalKeyBoard.addObserver(this);
    }


    @FXML
    private void pressDigit(ActionEvent actionEvent) {
        button = (Button)actionEvent.getSource();
        universalKeyBoard.addDigit(button.getText());
        inputField.setText(universalKeyBoard.getInput());
    }


    /**
     * This method is called whenever the observed object is changed. An
     * application calls an {@code Observable} object's
     * {@code notifyObservers} method to have all the object's
     * observers notified of the change.
     *
     * @param o   the observable object.
     * @param arg an argument passed to the {@code notifyObservers}
     */
    @Override
    public void update(Observable o, Object arg) {
        inputField.setText(universalKeyBoard.getFormat());
    }


    /**
     * Called to initialize a controller after its root element has been
     * completely processed.
     *
     * @param location  The location used to resolve relative paths for the root object, or
     *                  {@code null} if the location is not known.
     * @param resources The resources used to localize the root object, or {@code null} if
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inputField.setText(universalKeyBoard.getFormat());
    }
}
