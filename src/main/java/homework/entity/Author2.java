package homework.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.HashSet;
import java.util.Set;


@Entity
@DynamicUpdate
@DynamicInsert
public class Author2 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Column(name = "last_name")
    private String lastName;

    @ManyToMany(mappedBy = "authors",fetch = FetchType.EAGER)
    private Set<Book2> books = new HashSet<>();

    public Author2(String name, String lastName) {
        this.name = name;
        this.lastName = lastName;
    }

    public Author2(String name) {
        this.name = name;
    }

    public Author2() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
