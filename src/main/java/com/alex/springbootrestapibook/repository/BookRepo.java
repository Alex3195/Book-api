package com.alex.springbootrestapibook.repository;

import java.util.List;

import com.alex.springbootrestapibook.entity.Book;
import com.alex.springbootrestapibook.model.BookWithAuthor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends JpaRepository<Book, Long> {
    @Query("select new BookWithAuthor(a, b.id, b.name, b.price, b.rate, b.categoryId, b.filePath, b.ISBN_10, b.description, b.languageId)"
            + "from Book b left join Author a on a.id=b.authorId where b.id=:id")
    BookWithAuthor getBookWithAuthor(@Param("id") Long id);

    @Query(value = "select * from Book b limit :limit offset:offset", nativeQuery = true)
    List<Book> findBookWithLimit(@Param("limit") Long limit, @Param("offset") long offset);

    List<Book> findByAuthorId(Long authorId);

    @Query("select new BookWithAuthor(a, b.id, b.name, b.price, b.rate, b.categoryId, b.filePath, b.ISBN_10, b.description, b.languageId) "
            + "from Book b join Author a on b.id = a.id where a.name like(CONCAT('%',:name,'%'))")
    List<BookWithAuthor> findByAuthorName(@Param("name") String name);

    @Query("select new BookWithAuthor(a, b.id, b.name, b.price, b.rate, b.categoryId, b.filePath, b.ISBN_10, b.description, b.languageId) "
            + "from Book b join Author a on b.id = a.id where b.ISBN_10=:isbn")
    List<BookWithAuthor> findByISBN_10(@Param("isbn") String isbn);

    @Query("select new BookWithAuthor(a, b.id, b.name, b.price, b.rate, b.categoryId, b.filePath, b.ISBN_10, b.description, b.languageId) "
            + "from Book b join Author a on b.id = a.id where a.name like(CONCAT('%',:name,'%')) and b.ISBN_10=:isbn")
    List<BookWithAuthor> findByAuthorNameAndISBN_10(@Param("name") String name, @Param("isbn") String isbn_10);
}
