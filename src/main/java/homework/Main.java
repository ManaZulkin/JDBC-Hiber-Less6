package homework;

import homework.entity.Author2;
import homework.entity.Book2;
import org.apache.log4j.Logger;

import java.awt.print.Book;

/**
 * Created by Asus on 04.11.2017.
 */
public class Main {

    private static final Logger LOG = Logger.getLogger(AuthorHelper.class.getName());

    public static void main(String[] args) {
        AuthorHelper ah = new AuthorHelper();
        BookHelper bh = new BookHelper();
        AuthorBookHelper authorBookHelper = new AuthorBookHelper();
        Author2 author = new Author2();

        ah.nameUpdate();
        ah.selectWhere("esi");
        author.setName("Lesia");
        author.setLastName("Ukrainka");
        ah.addAuthor(author);//Добавление нового автора
//        ah.changeNameById(1, "Orak");
//        bh.addBook(new Book2("Book", 1));
//        bh.changeNameById(1, "Over");

//        authorBookHelper.addAuthorBook(1,1);
//        authorBookHelper.addAuthorBook(2,3);
//        authorBookHelper.addAuthorBook(2,2);
//        authorBookHelper.addAuthorBook(3,2);
//        authorBookHelper.addAuthorBook(3,3);
//        authorBookHelper.addAuthorBook(1,2);

//        Book2 forDelete = new Book2("elete", 1);
//        forDelete.setId(4);
//        bh.addBook(forDelete);
//        bh.deleteById(4);

//        bh.addBook(new Book2("censure", 1));
//        bh.addBook(new Book2("Dook", 2));
//        bh.addBook(new Book2("Frog", 3));

        //   bh.deleteByAuthor(1);
   //     bh.deleteByAuthor("Orak");

//
//            bh.bookInfo(2);
 //       ah.addManyAuthors(author);

//        Author2 author = ah.getAuthorById(38);
//        author.setName("Aleksandr");
//        author.setLastName("Pushkin");
//        ah.addAuthor(author);//Будет апдейт автора или добавление?
//        ah.addAuthor(null);


    }

}
