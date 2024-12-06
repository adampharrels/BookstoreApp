import javafx.stage.Stage;

public class RegisterCustomerController {
    private RegisterCustomerView view;
    private RegisterCustomerModel model;
    private CustomerModel customerModel;

    public RegisterCustomerController(RegisterCustomerView view, RegisterCustomerModel model, CustomerModel customerModel) {
        this.view = view;
        this.model = model;
        this.customerModel = customerModel;

        // Register button action
        view.getRegisterButton().setOnAction(event -> {
            String name = view.getNameField().getText();
            String idText = view.getIdField().getText();

            // Validate the input for empty fields first
            if (name.isEmpty() || idText.isEmpty()) {
                view.getErrorMessageLabel().setText("Name and ID cannot be empty.");
                return;
            }

            int id;
            try {
                // Try parsing ID only if it's not empty
                id = Integer.parseInt(idText);

                // On successful validation, update the model and clear errors
                model.setName(name);
                model.setId(id);

                view.getErrorMessageLabel().setText(""); // Clear error message
                
                // Display success message
                view.getErrorMessageLabel().setText("Customer registered successfully!");
                view.getErrorMessageLabel().setText("Customer Registered: " + name + " (" + id + ")");
                customerModel.addCustomer(new Customer(name, id));

                // Clear the input
                view.getNameField().clear();  
                view.getIdField().clear();


            } catch (NumberFormatException ex) {
                // Handle invalid number format
                view.getErrorMessageLabel().setText("Invalid ID format. Please enter a valid number.");
            }
        });
    }

    public RegisterCustomerModel getModel() {
        return model;
    }
}
