--file: csvs/contact_types.csv
insert into contact_types(contact_type_id,contact_name) values(1,'email'),(2,'phone');

--file: csvs/genres.csv
insert into genres(genre_id, genre_name) values (1,'Драма'), (2,'Трагедия'), (3,'Комедия');  

--file: csvs/authors.csv
insert into authors(author_id,last_name,first_name,father_name,birth_date) values 
(1,'Грибоедов','Александр','Сергеевич','1795-01-15')
,(2,'Чехов','Антон','Павлович','1860-01-29')
,(3,'Фонвизин','Денис','Иванович','1744-04-14')
,(4,'Достоевскмй','Фёдор','Михайлович','1821-11-11')
,(5,'Шекспир','Уильям','','1564-04-26');

---file: csvs/users.csv
insert into users(user_id,nick) values (1,'ivan'), (2,'vasya');  

--file: csvs/contacts.csv
insert into Contacts(contact_id,contact_type_id,contact_value,user_id) values
(1,1,'ivan@mail.ru',1)
,(2,2,'9021234567',1)
,(3,1,'vasya@mail.ru',2)
,(4,2,'9037655432',2);

--file: csvs/books.csv
insert into books(book_id,book_name,issue_year,genre_id) values 
 (1,'Горе от ума',1957,3)
,(2,'Вишневый сад',1958,3)
,(3,'Недоросль',1959,3)
,(4,'Преступление и наказание',1955,1)
,(5,'Гамлет',1954,2)  ;

--file: csvs/books_authors.csv
insert into books_authors (book_id,author_id) values (1,1)
  , (2,2), (3,3)
  , (4,4), (5,5);

--file: csvs/comments.csv
insert into comments(comment_id,book_id,user_id,text,created_dt) values
  (1,1,1,'Хорошая книга','2018-02-22')
 ,(2,1,2,'Хорошая книга2','2018-02-23')
  ,(3,1,1,'Хорошая книга3','2018-02-24')
;