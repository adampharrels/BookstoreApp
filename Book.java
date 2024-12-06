import javafx.beans.property.*;

public class Book {
    private final StringProperty title;
    private final DoubleProperty price;
    private final StringProperty author;
    private final ObjectProperty<Genre> genre;
    private final IntegerProperty isbn;

    public Book(String title, double price, String author, Genre genre, int isbn) {
        this.title = new SimpleStringProperty(title);
        this.price = new SimpleDoubleProperty(price);
        this.author = new SimpleStringProperty(author);
        this.genre = new SimpleObjectProperty<>(genre);
        this.isbn = new SimpleIntegerProperty(isbn);
    }

    public StringProperty titleProperty() {
        return title;
    }

    public DoubleProperty priceProperty() {
        return price;
    }

    public StringProperty authorProperty() {
        return author;
    }

    public ObjectProperty<Genre> genreProperty() {
        return genre;
    }

    public IntegerProperty isbnProperty() {
        return isbn;
    }

    public String getTitle() {
        return title.get();
    }

    public void setTitle(String title) {
        this.title.set(title);
    }

    public double getPrice() {
        return price.get();
    }

    public void setPrice(double price) {
        this.price.set(price);
    }

    public String getAuthor() {
        return author.get();
    }

    public void setAuthor(String author) {
        this.author.set(author);
    }

    public Genre getGenre() {
        return genre.get();
    }

    public void setGenre(Genre genre) {
        this.genre.set(genre);
    }

    public int getIsbn() {
        return isbn.get();
    }

    public void setIsbn(int isbn) {
        this.isbn.set(isbn);
    }
}
