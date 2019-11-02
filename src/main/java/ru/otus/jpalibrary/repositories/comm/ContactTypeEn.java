package ru.otus.jpalibrary.repositories.comm;

// TODO: Куда лучше помещать такие классы (считаем что они нужны) : путь.<имя пакета>?
public enum ContactTypeEn {
    EMAIL("email"),
    PHONE("phone");

    private String contactName;

    ContactTypeEn(String contactName) {
        this.contactName = contactName;
    }

    public String value() {
        return contactName;
    }

    public static ContactTypeEn getContactTypeEn(String s) {
        for(ContactTypeEn ce: ContactTypeEn.values())
            if( ce.value().equals(s))
                return ce;
        return null;
    }


}
