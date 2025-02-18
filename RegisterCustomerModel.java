import javafx.collections.ObservableList;

public class RegisterCustomerModel {
    private String name;
    private int id;

    // Constructor
    public RegisterCustomerModel(String name, int id) {
        this.name = name;
        this.id = id;
    }
    
    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
