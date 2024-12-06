import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Customer {
    private final StringProperty name;
    private final IntegerProperty id;
    private final ObservableList<String> wishlist;

    public Customer(String name, int id) {
        this.name = new SimpleStringProperty(name);
        this.id = new SimpleIntegerProperty(id);
        this.wishlist = FXCollections.observableArrayList();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int id) {
        this.id.set(id);
    }
    public ObservableList<String> getWishList(){
        return wishlist;
    }
    public void addToWishlist(String title){
        wishlist.add(title);
    }

    public String getWishlistAsString() {
        return String.join(", ", wishlist);
       
    }
    public void removeFromWishlist(String bookTitle) {
        wishlist.remove(bookTitle);
    }
}
