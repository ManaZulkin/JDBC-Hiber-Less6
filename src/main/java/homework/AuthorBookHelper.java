package homework;

import homework.entity.AuthorBook;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class AuthorBookHelper {
    private SessionFactory sessionFactory;

    public AuthorBookHelper() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public void addAuthorBook(int author, int book){
        Session session = sessionFactory.openSession();
        AuthorBook authorBook = new AuthorBook();
        session.getTransaction().begin();
        authorBook.setAuthor(author);
        authorBook.setBook(book);
        session.persist(authorBook);
        session.getTransaction().commit();
        session.close();
    }
}
