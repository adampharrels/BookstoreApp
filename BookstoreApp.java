import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BookstoreApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("UTS BOOKSTORE");

        VBox scene1Root = new VBox(20);
        scene1Root.setAlignment(Pos.CENTER);

        Button startButton = new Button("Press to Log in.");
        scene1Root.getChildren().add(startButton);

        Scene scene1 = new Scene(scene1Root, 300, 250);

        VBox scene2Root = createScene2Content(primaryStage, scene1);
        Scene scene2 = new Scene(scene2Root, 800, 500);

        startButton.setOnAction(e -> primaryStage.setScene(scene2));

        primaryStage.setScene(scene1);
        primaryStage.show();
    }

private VBox createScene2Content(Stage primaryStage, Scene scene1) {
    // Shared customer model
    CustomerModel customerModel = new CustomerModel();
    CustomerController customerController = new CustomerController(customerModel);

    // Bookstore (for context)
    BookstoreModel bookModel = new BookstoreModel();
    BookstoreController bookController = new BookstoreController(bookModel);

    // Views
    CustomerView customerView = new CustomerView(customerController, bookModel);

    // Register Customer pane
    RegisterCustomerView registerPane = new RegisterCustomerView();
    RegisterCustomerModel model = new RegisterCustomerModel("", 0);
    RegisterCustomerController controller = new RegisterCustomerController(registerPane, model, customerModel);

    // Create an instance of EditCustomerModel
    EditCustomerModel editCustomerModel = new EditCustomerModel(customerModel);

    // Edit Customer pane
    EditCustomerView editCustomerView = new EditCustomerView();
    Parent editCustomerPane = editCustomerView.createEditCustomerUI(editCustomerModel, editCustomerView); // Corrected  call
    EditCustomerController editCustomerController = new EditCustomerController(editCustomerView, editCustomerModel, customerController);

    // Tabs
    TabPane tabPane = new TabPane();

    Tab bookTab = new Tab("Manage Books", new BookstoreView(bookController).createBookManagementUI(bookModel));
    Tab customerTab = new Tab("Manage Customers", customerView.createCustomerManagementUI(customerModel));
    Tab registerTab = new Tab("Register Customer", registerPane);
    Tab editCustomerTab = new Tab("Edit Customer", editCustomerPane);

    tabPane.getTabs().addAll(bookTab, customerTab, registerTab, editCustomerTab);

    // Back button to return to the login screen
    Button backButton = new Button("Sign out");
    backButton.setOnAction(e -> primaryStage.setScene(scene1));

    VBox scene2Root = new VBox(10, tabPane, backButton);
    scene2Root.setAlignment(Pos.CENTER);

    return scene2Root;
}
    

    public static void main(String[] args) {
        launch(args);
    }
}
