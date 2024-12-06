import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BookstoreView {
    private TableView<Book> bookTableView;
    private BookstoreController controller;

    public BookstoreView(BookstoreController controller) {
        this.controller = controller;
    }

    public Parent createBookManagementUI(BookstoreModel model) {
        VBox layout = new VBox(10);
        layout.setAlignment(Pos.CENTER);

        // Book Table
        bookTableView = new TableView<>();
        TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        TableColumn<Book, String> authorColumn = new TableColumn<>("Author");
        authorColumn.setCellValueFactory(cellData -> cellData.getValue().authorProperty());
        TableColumn<Book, Number> isbnColumn = new TableColumn<>("ISBN");
        isbnColumn.setCellValueFactory(cellData -> cellData.getValue().isbnProperty());
        TableColumn<Book, Number> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(cellData -> cellData.getValue().priceProperty());
        TableColumn<Book, Genre> genreColumn = new TableColumn<>("Genre");
        genreColumn.setCellValueFactory(cellData -> cellData.getValue().genreProperty());

        bookTableView.getColumns().addAll(titleColumn, authorColumn, isbnColumn, priceColumn, genreColumn);
        bookTableView.setItems(model.getBooks());

        // Book Management Buttons
        Button addBookButton = new Button("Add Book");
        addBookButton.setOnAction(event -> showAddBookDialog(controller));

        Button editBookButton = new Button("Edit Book");
        editBookButton.setOnAction(event -> {
            int selectedIndex = bookTableView.getSelectionModel().getSelectedIndex();
            if (selectedIndex != -1) {
                showEditBookDialog(controller, model, selectedIndex);
            } else {
                showErrorDialog("Please select a book to edit.");
            }
        });

        Button removeBookButton = new Button("Remove Book");
        removeBookButton.setOnAction(event -> {
            int selectedIndex = bookTableView.getSelectionModel().getSelectedIndex();
            if (selectedIndex != -1) {
                controller.removeBook(selectedIndex);
            } else {
                showErrorDialog("Please select a book to remove.");
            }
        });

        HBox buttonRow = new HBox(10, addBookButton, editBookButton, removeBookButton);
        buttonRow.setAlignment(Pos.CENTER);

        layout.getChildren().addAll(bookTableView, buttonRow);
        return layout;
    }

    private void showAddBookDialog(BookstoreController controller) {
        Stage stage = new Stage();
        stage.setTitle("Add Book");

        TextField titleField = new TextField();
        titleField.setPromptText("Title");

        TextField authorField = new TextField();
        authorField.setPromptText("Author");

        TextField isbnField = new TextField();
        isbnField.setPromptText("ISBN (6 digits)");

        TextField priceField = new TextField();
        priceField.setPromptText("Price");

        ComboBox<Genre> genreComboBox = new ComboBox<>();
        genreComboBox.getItems().setAll(Genre.values());
        genreComboBox.setPromptText("Select Genre");

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(event -> {
            try {
                String title = titleField.getText();
                String author = authorField.getText();
                int isbn = Integer.parseInt(isbnField.getText());
                double price = Double.parseDouble(priceField.getText());
                Genre genre = genreComboBox.getValue();

                if (String.valueOf(isbn).length() != 6) {
                    showErrorDialog("ISBN must be a 6-digit number.");
                    return;
                }

                if (genre == null) {
                    showErrorDialog("Please select a genre.");
                    return;
                }

                controller.addBook(new Book(title, price, author, genre, isbn));
                stage.close();
            } catch (NumberFormatException e) {
                showErrorDialog("Invalid input. Please enter valid values.");
            }
        });

        VBox layout = new VBox(10, titleField, authorField, isbnField, priceField, genreComboBox, submitButton);
        layout.setAlignment(Pos.CENTER);
        stage.setScene(new javafx.scene.Scene(layout, 300, 400));
        stage.show();
    }

    private void showEditBookDialog(BookstoreController controller, BookstoreModel model, int index) {
        Stage stage = new Stage();
        stage.setTitle("Edit Book");

        Book selectedBook = model.getBooks().get(index);

        TextField titleField = new TextField(selectedBook.getTitle());
        TextField authorField = new TextField(selectedBook.getAuthor());
        TextField isbnField = new TextField(String.valueOf(selectedBook.getIsbn()));
        TextField priceField = new TextField(String.valueOf(selectedBook.getPrice()));
        ComboBox<Genre> genreComboBox = new ComboBox<>();
        genreComboBox.getItems().setAll(Genre.values());
        genreComboBox.setValue(selectedBook.getGenre());

        Button submitButton = new Button("Submit");
        submitButton.setOnAction(event -> {
            try {
                String title = titleField.getText();
                String author = authorField.getText();
                int isbn = Integer.parseInt(isbnField.getText());
                double price = Double.parseDouble(priceField.getText());
                Genre genre = genreComboBox.getValue();

                if (String.valueOf(isbn).length() != 6) {
                    showErrorDialog("ISBN must be a 6-digit number.");
                    return;
                }

                if (genre == null) {
                    showErrorDialog("Please select a genre.");
                    return;
                }

                controller.updateBook(index, new Book(title, price, author, genre, isbn));
                stage.close();
            } catch (NumberFormatException e) {
                showErrorDialog("Invalid input. Please enter valid values.");
            }
        });

        VBox layout = new VBox(10, titleField, authorField, isbnField, priceField, genreComboBox, submitButton);
        layout.setAlignment(Pos.CENTER);
        stage.setScene(new javafx.scene.Scene(layout, 300, 400));
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
