import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class RegisterCustomerView extends StackPane {
    private TextField nameField;
    private TextField idField;
    private Button registerButton;
    private Label errorMessageLabel; // Label for error messages

    public RegisterCustomerView() {
        // Initialize form components
        nameField = new TextField();
        idField = new TextField();
        registerButton = new Button("Register");
        errorMessageLabel = new Label(""); // Initialize error message label
        errorMessageLabel.setTextFill(Color.RED); // Style error messages

        // Create layout for the registration form
        GridPane formLayout = new GridPane();
        formLayout.setVgap(10);
        formLayout.setHgap(10);
        formLayout.setAlignment(Pos.CENTER);

        formLayout.add(new Label("Name:"), 0, 0);
        formLayout.add(nameField, 1, 0);
        formLayout.add(new Label("ID:"), 0, 1);
        formLayout.add(idField, 1, 1);
        formLayout.add(registerButton, 1, 2);
        formLayout.add(errorMessageLabel, 1, 3); // Add error label below the form

        // Add layout to the StackPane
        this.setAlignment(Pos.CENTER);
        this.getChildren().add(formLayout);
    }

    public TextField getNameField() {
        return nameField;
    }

    public TextField getIdField() {
        return idField;
    }

    public Button getRegisterButton() {
        return registerButton;
    }

    public Label getErrorMessageLabel() {
        return errorMessageLabel;
    }
}
