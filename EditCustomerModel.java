public class EditCustomerModel {
    private CustomerModel customerModel;
    private EditCustomerView view;
    private EditCustomerModel model;
    

    public EditCustomerModel(CustomerModel customerModel) {
        this.customerModel = customerModel;
    }

    public Customer getCustomerById(int id) {
        return customerModel.getCustomerById(id);
    }

    public boolean removeCustomerById(int id) {
        return customerModel.removeCustomerById(id);
    }
}
