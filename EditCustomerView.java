import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class EditCustomerView {
    private TextField customerIdField;
    private Button editButton;
    private Button removeButton;
    private Label messageLabel;

    public Parent createEditCustomerUI(EditCustomerModel editCustomerModel, EditCustomerView view) {
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);

        // Customer ID input
        Label idLabel = new Label("Enter Customer ID:");
        customerIdField = new TextField();
        customerIdField.setPromptText("Customer ID");

        // Message label for feedback
        messageLabel = new Label();
        messageLabel.setStyle("-fx-text-fill: red;");

        // Buttons
        editButton = new Button("Edit Customer");
        removeButton = new Button("Remove Customer");

        // Event handlers for Edit and Remove
        editButton.setOnAction(e -> {
            try {
                int id = Integer.parseInt(customerIdField.getText());
                Customer customer = editCustomerModel.getCustomerById(id);
                if (customer != null) {
                    // Launch edit form (you can add your edit logic here)
                    messageLabel.setText("Editing customer: " + customer.getName());
                } else {
                    messageLabel.setText("Customer ID not found!");
                }
            } catch (NumberFormatException ex) {
                messageLabel.setText("Invalid ID! Please enter a valid number.");
            }
        });

        removeButton.setOnAction(e -> {
            try {
                int id = Integer.parseInt(customerIdField.getText());
                if (editCustomerModel.removeCustomerById(id)) {
                    messageLabel.setText("Customer removed successfully!");
                } else {
                    messageLabel.setText("Customer ID not found!");
                }
            } catch (NumberFormatException ex) {
                messageLabel.setText("Invalid ID! Please enter a valid number.");
            }
        });

        // Layout for buttons and input
        HBox buttonRow = new HBox(10, editButton, removeButton);
        buttonRow.setAlignment(Pos.CENTER);

        layout.getChildren().addAll(idLabel, customerIdField, buttonRow, messageLabel);
        return layout;
    }

    public Button getEditButton() {
        return editButton;
    }

    public Button getRemoveButton() {
        return removeButton;
    }

    public TextField getCustomerIdField() {
        return customerIdField;
    }

        // Methods to show messages or forms for editing/removal
        public void showEditForm(Customer customer) {
            // Example: Show customer details for editing
            // This could be a new form where the user can modify fields like Name, Wishlist, etc.
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Edit Customer");
            alert.setHeaderText("Editing Customer");
            alert.setContentText("Customer details are now available for editing.");
            alert.showAndWait();
        }
    
        public void showCustomerNotFoundMessage() {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Customer Not Found");
            alert.setHeaderText(null);
            alert.setContentText("The customer with the given ID does not exist.");
            alert.showAndWait();
        }
    
        public void showCustomerRemovedMessage() {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Customer Removed");
            alert.setHeaderText(null);
            alert.setContentText("The customer has been successfully removed.");
            alert.showAndWait();
        }
}
