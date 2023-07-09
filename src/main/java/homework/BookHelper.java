package homework;

import homework.entity.AuthorBook;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import homework.entity.Author2;
import homework.entity.Book2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;


import java.awt.print.Book;
import java.util.List;

public class BookHelper {
    private SessionFactory factory;
    public BookHelper(){
        factory = HibernateUtil.getSessionFactory();
    }

    public List<Book> getBookList(){
        try(Session session = factory.openSession()) {

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
            Root<Book> root = criteriaQuery.from(Book.class);
            criteriaQuery.select(root);
            Query<Book> query = session.createQuery(criteriaQuery);

            return query.list();
        }
    }

    public Book getBookById(long id){
        try(Session session = factory.openSession()){
            return session.get(Book.class, id);
        }
    }

    public void addBook(Book2 book){
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            session.persist(book);
            session.getTransaction().commit();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void changeNameById(int id, String newName){
        try (Session session = factory.openSession()){
            Book2 book2 = session.get(Book2.class, id);
            book2.setName(newName);
            session.beginTransaction();
            session.persist(book2);
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void bookInfo(int id){
        try (Session session = factory.openSession()){
            Book2 book2 = session.get(Book2.class, id);
            Author2 author2 = session.get(Author2.class, book2.getAuthorId());
            System.out.println("Book name: " + book2.getName() + "\nAuthor name: " + author2.getName() + " " + author2.getLastName());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deleteById(int book_id){
        try(Session session = factory.openSession()){
            session.getTransaction().begin();
            Book2 book2 = session.get(Book2.class, book_id);
            session.remove(book2);
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void deleteByAuthor(int author_id){
        try(Session session = factory.openSession()){
            session.getTransaction().begin();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Book2> criteriaQuery = criteriaBuilder.createQuery(Book2.class);
            Root<Book2> root = criteriaQuery.from(Book2.class);
            criteriaQuery.select(root);
            Query<Book2> query = session.createQuery(criteriaQuery);
            List<Book2> list = query.list();
            for (Book2 b: list) {
                if (author_id == b.getAuthorId()){
                    session.remove(b);
                    session.flush();
                }
            }
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void deleteByAuthor(String author_name){
        try(Session session = factory.openSession()){
            session.getTransaction().begin();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<AuthorBook> criteriaQuery = criteriaBuilder.createQuery(AuthorBook.class);
            Root<AuthorBook> root = criteriaQuery.from(AuthorBook.class);
            criteriaQuery.select(root);
            Query<AuthorBook> query = session.createQuery(criteriaQuery);
            List<AuthorBook> list = query.list();

            CriteriaBuilder criteriaBuilder1 = session.getCriteriaBuilder();
            CriteriaQuery<Author2> criteriaQuery1 = criteriaBuilder1.createQuery(Author2.class);
            Root<Author2> root1 = criteriaQuery1.from(Author2.class);
            criteriaQuery1.select(root1);
            Query<Author2> query1 = session.createQuery(criteriaQuery1);
            List<Author2> list1 = query1.list();

            for (Author2 author2: list1) {
                if (author_name.equalsIgnoreCase(author2.getName())){
                    long author_id = author2.getId();
                    for (AuthorBook  authorBook: list) {
                        if (author_id == authorBook.getAuthor()){
                            Book2 del = session.get(Book2.class, authorBook.getBook());
                            session.remove(del);
                            session.flush();
                        }

                    }
                }
            }
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
