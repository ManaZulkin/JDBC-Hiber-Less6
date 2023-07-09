package homework;


import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

import jakarta.persistence.criteria.Root;
import homework.entity.Author2;
import jakarta.persistence.criteria.Selection;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;


import java.util.List;

/**
 * Created by Asus on 01.11.2017.
 */
public class AuthorHelper {

    private SessionFactory sessionFactory;

    public AuthorHelper() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<Author2> getAuthorList(){
        // открыть сессию - для манипуляции с персист. объектами
        Session session = sessionFactory.openSession();


        // этап подготовки запроса

        // объект-конструктор запросов для Criteria API
        CriteriaBuilder cb = session.getCriteriaBuilder();// не использовать session.createCriteria, т.к. deprecated

        CriteriaQuery<Author2> cq = cb.createQuery(Author2.class);

        Root<Author2> root = cq.from(Author2.class);// первостепенный, корневой entity (в sql запросе - from)


        cq.select(root);// необязательный оператор, если просто нужно получить все значения


         //этап выполнения запроса
        Query<Author2> query = session.createQuery(cq);

        List<Author2> author2List = query.getResultList();

        session.close();

        return author2List;
    }

    public Author2 getAuthorById(long id) {
        Session session = sessionFactory.openSession();
        Author2 author2 = session.get(Author2.class, id); // получение объекта по id
        session.close();
        return author2;
    }

    public void addAuthor(Author2 author2){

        Session session = sessionFactory.openSession();

//        Author2 author1 = session.get(Author2.class, 38L);
//        author1.setName("Gogol4444");
//        author1.setLastName("Mikola");

        session.beginTransaction();

//        session.save(author1); // сгенерит ID и вставит в объект

        session.persist(author2);
//
//        for (int i = 0; i < 200; i++){
//            if(i % 20 == 0){
//                session.flush();
//            }
//        }

        session.getTransaction().commit();

        session.close();


    }

    public void addManyAuthors(Author2 author2){
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            for (int i = 1; i <= 200; i++) {
                session.persist(new Author2(author2.getName(), author2.getLastName()));
                if (i % 20 == 0) {
                    session.flush();
                }
                String name = author2.getName().substring(0, 5);
                author2.setName(name + i);
            }
            session.getTransaction().commit();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void changeNameById(int id, String newName){
        try (Session session = sessionFactory.openSession()){
            Author2 author2 = session.get(Author2.class, id);
            author2.setName(newName);
            session.beginTransaction();
            session.persist(author2);
            session.getTransaction().commit();
        }
    }
    public void nameUpdate(){
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Author2> criteriaQuery = criteriaBuilder.createQuery(Author2.class);
        Root<Author2> root = criteriaQuery.from(Author2.class);
        criteriaQuery.select(root);
        Query<Author2> query = session.createQuery(criteriaQuery);
        List<Author2> list = query.list();

        for (Author2 a: list) {
            if (a.getLastName().length() > 7){
                a.setName("1");
                session.flush();
            }
        }
        session.getTransaction().commit();
        session.close();
    }

    public void selectWhere(String where){
        Session session = sessionFactory.openSession();
        session.getTransaction().begin();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Author2.class);
        Root<Author2> root = criteriaQuery.from(Author2.class);
        Selection[] selections = {root.get("id"), root.get("Name")};
        criteriaQuery.select(criteriaBuilder.construct(Author2.class, selections)).
                where(criteriaBuilder.like(root.<String>get("name"),where));
        Query query = session.createQuery(criteriaQuery);
        List<Author2> list = query.list();
        for (Author2 a: list) {
            System.out.println(a.getId() + " " + a.getName() + " " + a.getLastName());
        }
        session.close();
    }
}
