import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class BookstoreModel {
    private final ObservableList<Book> books;

    public BookstoreModel() {
        this.books = FXCollections.observableArrayList();
    }

    public ObservableList<Book> getBooks() {
        return books;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void updateBook(int index, Book book) {
        books.set(index, book);
    }

    public void removeBook(int index) {
        books.remove(index);
    }
    public Book getBookByISBN(int isbn){
       for (Book book : books){
        if (book.getIsbn() == isbn){
            return book;
        }
       }
           return null;
    }
    
    
}
