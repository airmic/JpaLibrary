package ru.otus.jpalibrary.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "contacts")
@Data
@NoArgsConstructor
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    private long id;

    @OneToOne
    @JoinColumn(name = "contact_type_id")
    private ContactType type;

    @Column(name = "contact_value", length = 100)
    private String value;

}