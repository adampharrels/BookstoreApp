import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CustomerModel {
    private final ObservableList<Customer> customers;

    public CustomerModel() {
        customers = FXCollections.observableArrayList();
    }

    public ObservableList<Customer> getCustomers() {
        return customers;
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void updateCustomer(int index, Customer customer) {
        customers.set(index, customer);
    }

    public void removeCustomer(int index) {
        customers.remove(index);
    }
    
    public void addWishListItem(String name,int isbn){
        for (Customer customer : customers) {
            if(customer.getName().equals(name)){
                customer.addToWishlist("sd");
                return;
            }
        }
        System.out.println("Customer not found.");
    }
  

    public Customer getCustomerById(int id) {
        // Loop through the customers and return the one matching the ID
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                return customer;
            }
        }
        return null; // Return null if no customer is found
    }

    public boolean removeCustomerById(int id) {
        Customer customerToRemove = getCustomerById(id);
        if (customerToRemove != null) {
            customers.remove(customerToRemove);
            return true;
        }
        return false; // Return false if customer not found
    }


}
