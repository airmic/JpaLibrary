package ru.otus.jpalibrary.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "contact_types", uniqueConstraints = @UniqueConstraint(columnNames = "contact_name"))
@Data
@NoArgsConstructor
public class ContactType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    @Column(name="contact_type_id")
    private int id;

    @Column(name = "contact_name", length = 30)
    private String name;
}
