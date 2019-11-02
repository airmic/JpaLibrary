package ru.otus.jpalibrary.repositories.comm;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactTypeEnTest {

    @Test
    void value() {
        assertEquals(ContactTypeEn.PHONE.value(),"phone");
    }

    @Test
    void fromValue() {
        assertEquals(ContactTypeEn.getContactTypeEn("email"), ContactTypeEn.EMAIL);
    }
}