import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CustomerView {
    private TableView<Customer> customerTableView;
    private CustomerController controller;
    private BookstoreModel bookstoreModel;

    public CustomerView(CustomerController controller, BookstoreModel bookstoreModel) {
        this.controller = controller;
        this.bookstoreModel=bookstoreModel;
    }

    public Parent createCustomerManagementUI(CustomerModel model) {
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);
    
        // Customer Table
        customerTableView = new TableView<>();
        TableColumn<Customer, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
    
        TableColumn<Customer, Number> idColumn = new TableColumn<>("Customer ID");
        idColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty());
    
        TableColumn<Customer, String> wishlistColumn = new TableColumn<>("Wishlist");
        wishlistColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getWishlistAsString()));
    
        customerTableView.getColumns().addAll(nameColumn, idColumn, wishlistColumn);
        customerTableView.setItems(model.getCustomers());


    
        // Customer Management Buttons
  
    
        Button editButton = new Button("Edit Customer");
        editButton.setOnAction(event -> {
            int selectedIndex = customerTableView.getSelectionModel().getSelectedIndex();
            if (selectedIndex != -1) {
                showEditCustomerDialog(controller, model, selectedIndex);
            } else {
                showErrorDialog("Please select a customer to edit.");
            }
        });
    
        Button addWishlistButton = new Button("Add to Wishlist");
        addWishlistButton.setOnAction(event -> {
            Customer selectedCustomer = customerTableView.getSelectionModel().getSelectedItem();
            if (selectedCustomer != null) {

                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Add to Wishlist");
                dialog.setHeaderText("Enter ISBN");
                dialog.setContentText("ISBN:");
        
                dialog.showAndWait().ifPresent(isbnInput -> {
                    try {
                        int isbn = Integer.parseInt(isbnInput); 
                        Book book = bookstoreModel.getBookByISBN(isbn); 
                        if (book != null) {
                            selectedCustomer.addToWishlist(book.getTitle());
                            customerTableView.refresh();
                        } else {
                            showErrorDialog("Book with ISBN " + isbn + " not found.");
                        }
                    } catch (NumberFormatException e) {
                        showErrorDialog("Invalid ISBN. Please enter a valid number.");
                    }
                });
            } else {
                showErrorDialog("Please select a customer to add to the wishlist.");
            }
        });
       // Add this button in your buttonRow for removing a book from the wishlist
Button removeWishlistButton = new Button("Remove from Wishlist");
removeWishlistButton.setOnAction(event -> {
    Customer selectedCustomer = customerTableView.getSelectionModel().getSelectedItem();
    if (selectedCustomer != null) {
        if (!selectedCustomer.getWishList().isEmpty()) {
            // Simple logic to remove the last item from the wishlist
            selectedCustomer.getWishList().remove(selectedCustomer.getWishList().size() - 1);
            customerTableView.refresh(); // Refresh table to show updated wishlist
        } else {
            showErrorDialog("The wishlist is already empty.");
        }
    } else {
        showErrorDialog("Please select a customer to modify their wishlist.");
    }
});


    
        Button removeButton = new Button("Remove Customer");
        removeButton.setOnAction(event -> {
            int selectedIndex = customerTableView.getSelectionModel().getSelectedIndex();
            if (selectedIndex != -1) {
                controller.removeCustomer(selectedIndex);
            } else {
                showErrorDialog("Please select a customer to remove.");
            }
        });
    
        HBox buttonRow = new HBox(10, editButton, addWishlistButton, removeWishlistButton, removeButton);
        buttonRow.setAlignment(Pos.CENTER);
    
        layout.getChildren().addAll(customerTableView, buttonRow);
        return layout;
    }

    private void showEditCustomerDialog(CustomerController controller, CustomerModel model, int index) {
        Stage stage = new Stage();
        stage.setTitle("Edit Customer");

        Customer selectedCustomer = model.getCustomers().get(index);

        TextField nameField = new TextField(selectedCustomer.getName());
        TextField idField = new TextField(String.valueOf(selectedCustomer.getId()));

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(event -> {
            String name = nameField.getText();
            try {
                int id = Integer.parseInt(idField.getText());

                if (name.trim().isEmpty()) {
                    showErrorDialog("Customer name cannot be empty.");
                    return;
                }

                controller.updateCustomer(index, new Customer(name, id));
                stage.close();
            } catch (NumberFormatException e) {
                showErrorDialog("Customer ID must be a valid number.");
            }
        });

        VBox layout = new VBox(10, new Label("Edit Customer"), nameField, idField, submitButton);
        layout.setAlignment(Pos.CENTER);
        stage.setScene(new Scene(layout, 300, 200));
        stage.show();
    }

    private void showErrorDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
