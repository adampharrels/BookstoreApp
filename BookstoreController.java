public class BookstoreController {
    private final BookstoreModel model;

    public BookstoreController(BookstoreModel model) {
        this.model = model;
    }

    public void addBook(Book book) {
        model.addBook(book);
    }

    public void updateBook(int index, Book book) {
        model.updateBook(index, book);
    }

    public void removeBook(int index) {
        model.removeBook(index);
    }
}
