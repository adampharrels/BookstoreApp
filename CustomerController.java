public class CustomerController {
    private final CustomerModel model;

    public CustomerController(CustomerModel model) {
        this.model = model;
    }

    public void addCustomer(Customer customer) {
        model.addCustomer(customer);
    }

    public void updateCustomer(int index, Customer customer) {
        model.updateCustomer(index, customer);
    }

    public void removeCustomer(int index) {
        model.removeCustomer(index);
    }
    public void addWishListItem(String name, int isbn){
        model.addWishListItem(name, isbn);
    }

}
