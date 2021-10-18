package hh.swd20.bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.swd20.bookstore.Domain.Book;
import hh.swd20.bookstore.Domain.BookRepository;
import hh.swd20.bookstore.Domain.Category;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class BookRepositoryTest {
	
	@Autowired
	private BookRepository repository;
	
	@Test
	public void findByTitleShouldReturnBook() {
		List<Book> books = repository.findByTitle("Taru sormusten herrasta");
		assertThat(books).hasSize(1);
		assertThat(books.get(0).getAuthor()).isEqualTo("Tolkien");
	}
	@Test
	public void createNewBook() {
		Book book = new Book("Pudistus", "Sofi Oksanen", 2008, "235774-3445", 19.85, new Category("Historical"));
		repository.save(book);
		assertThat(book.getId()).isNotNull();
	}
	@Test
	public void deleteBook() {
		Book book = new Book("Pudistus", "Sofi Oksanen", 2008, "235774-3445", 19.85, new Category("Historical"));
		repository.save(book);
		repository.deleteById(book.getId());
		assertThat(repository.count()).isEqualTo(2);
	}
}
