package homework.entity;


import jakarta.persistence.*;

@Entity
public class AuthorBook {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    //@ManyToOne
    @JoinColumn(name = "author", nullable = false)
    private long author;


   // @ManyToOne
    @JoinColumn(name = "book", nullable = false)
    private long book;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAuthor() {
        return author;
    }

    public void setAuthor(long author) {
        this.author = author;
    }

    public long getBook() {
        return book;
    }

    public void setBook(long book) {
        this.book = book;
    }
}
