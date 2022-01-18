package ru.sas7.congratulator.backendspringboot.entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@Setter
@EqualsAndHashCode
public class Contact {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;

    public Integer getId() {
        return id;
    }

    @Basic
    @Column(name = "first_name")
    private String firstName;

    public String getFirstName() {
        return firstName;
    }

    @Basic
    @Column(name = "last_name")
    private String lastName;

    public String getLastName() {
        return lastName;
    }

    @Basic
    @Column(name = "middle_name")
    private String middleName;

    public String getMiddleName() {
        return middleName;
    }

    @Basic
    @Column(name = "birthday", nullable = false)
    private Date birthday;

    public Date getBirthday() {
        return birthday;
    }

    @Basic
    @Column(name = "photo")
    private String photo;

    public String getPhoto() {
        return photo;
    }

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;

    public Category getCategory() {
        return category;
    }
}
