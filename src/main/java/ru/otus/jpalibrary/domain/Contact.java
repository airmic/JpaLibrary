package ru.otus.jpalibrary.domain;

import javax.persistence.*;

@Entity
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "contact_type_id")
    private ContactType type;

    @Column(name = "contact_value", length = 100)
    private String value;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;
}