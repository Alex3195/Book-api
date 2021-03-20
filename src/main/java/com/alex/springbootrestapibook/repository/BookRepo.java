package com.alex.springbootrestapibook.repository;

import com.alex.springbootrestapibook.entity.Book;
import com.alex.springbootrestapibook.model.BookWithAuthorModel;
import com.alex.springbootrestapibook.model.BookWithCommentsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepo extends JpaRepository<Book, Long> {
    List<Book> findByAuthorId(Long authorId);

    List<Book> findByName(String name);

    List<Book> findByCategoryId(Long categoryId);

    List<Book> findBookByCategoryIdAndRate(Long categoryId, Double rate);

    @Query(value = "select b.id,b.name,b.authorId,b.price,b.categoryId,b.filePath,b.ISBN_10,b.description,b.languageId," +
            "c.id,c.bookId,c.content,c.time,c.userId " +
            " from Book b left join Comment c on c.bookId=b.id where b.authorId=:authorId", nativeQuery = true)
    BookWithCommentsModel getBookByIdWithComments(@Param("authorId") Long id);

    @Query("select new com.alex.springbootrestapibook.model.BookWithAuthorModel(a, b.id, b.name, b.price, b.rate, b.categoryId, b.filePath, b.ISBN_10, b.description, b.languageId) " +
            "from Book b join Author a on b.id = a.id where a.name like(CONCAT('%',:name,'%')) and b.ISBN_10=:isbn")
    List<BookWithAuthorModel> findAllByAuthorNameAndISBN_10(@Param("name") String name, @Param("isbn") String isbn_10);

    @Query("select new com.alex.springbootrestapibook.model.BookWithAuthorModel(a, b.id, b.name, b.price, b.rate, b.categoryId, b.filePath, b.ISBN_10, b.description, b.languageId) " +
            "from Book b join Author a on b.id = a.id where a.name like(CONCAT('%',:name,'%'))")
    List<BookWithAuthorModel> findAllByAuthorName(@Param("name") String author);

    @Query("select new com.alex.springbootrestapibook.model.BookWithAuthorModel(a, b.id, b.name, b.price, b.rate, b.categoryId, b.filePath, b.ISBN_10, b.description, b.languageId) " +
            "from Book b join Author a on b.id = a.id where b.ISBN_10=:isbn")
    List<BookWithAuthorModel> findAllByISBN_10(@Param("isbn") String isbn_10);
}
