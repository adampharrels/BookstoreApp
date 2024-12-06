import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class EditCustomerController {
    private EditCustomerView editCustomerView;
    private EditCustomerModel editCustomerModel;
    private CustomerController customerController;


    public EditCustomerController(EditCustomerView editCustomerView, EditCustomerModel editCustomerModel, CustomerController customerController) {
        this.editCustomerView = editCustomerView;
        this.editCustomerModel = editCustomerModel;
        this.customerController = customerController;

        // Handle the action when "Edit" button is clicked
        editCustomerView.getEditButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleEditCustomer();
            }
        });

        // Handle the action when "Remove" button is clicked
        editCustomerView.getRemoveButton().setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                handleRemoveCustomer();
            }
        });
    }

    private void handleEditCustomer() {
        // Ask for the Customer ID (this can be done through a prompt or a field in the UI)
        int customerId = Integer.parseInt(editCustomerView.getCustomerIdField().getText());

        // Check if the customer exists
        Customer customer = editCustomerModel.getCustomerById(customerId);
        if (customer != null) {
            // Show an Edit form or proceed with editing
            // Example: Show customer data in text fields and allow editing
            editCustomerView.showEditForm(customer);
        } else {
            // Handle case where customer is not found (e.g., show an error message)
            editCustomerView.showCustomerNotFoundMessage();
        }
    }

    private void handleRemoveCustomer() {
        // Ask for the Customer ID (this can be done through a prompt or a field in the UI)
        int customerId = Integer.parseInt(editCustomerView.getCustomerIdField().getText());

        // Remove the customer
        boolean removed = editCustomerModel.removeCustomerById(customerId);
        if (removed) {
            // Handle successful removal (e.g., show a confirmation message)
            editCustomerView.showCustomerRemovedMessage();
        } else {
            // Handle case where customer removal failed (e.g., customer not found)
            editCustomerView.showCustomerNotFoundMessage();
        }
    }
}
