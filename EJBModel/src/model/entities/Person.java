package model.entities;

import java.io.Serializable;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({ @NamedQuery(name = "Person.findAll", query = "select o from Person o"),
                @NamedQuery(name = "Person.findById", query = "select o from Person o where o.id=:id")
    })
public class Person implements Serializable {
    private static final long serialVersionUID = -3931802554027982556L;
    private BigDecimal age;
    @Id
    @Column(nullable = false)
    private BigDecimal id;
    @Column(length = 20)
    private String name;

    public Person() {
    }

    public Person(BigDecimal age, BigDecimal id, String name) {
        this.age = age;
        this.id = id;
        this.name = name;
    }

    public BigDecimal getAge() {
        return age;
    }

    public void setAge(BigDecimal age) {
        this.age = age;
    }

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
