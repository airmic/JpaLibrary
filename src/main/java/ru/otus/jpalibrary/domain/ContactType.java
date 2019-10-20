package ru.otus.jpalibrary.domain;

import javax.persistence.*;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "contact_name"))
public class ContactType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name="contact_type_id")
    private int id;

    @Column(name = "contact_name", length = 30)
    private String name;
}
