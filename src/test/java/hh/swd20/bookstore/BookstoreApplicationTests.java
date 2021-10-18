package hh.swd20.bookstore;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;

import hh.swd20.bookstore.controller.BookController;
import hh.swd20.bookstore.controller.CategoryController;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class BookstoreApplicationTests {
	
	@Autowired
	private BookController bookController;
	
	@Autowired
	private CategoryController categoryController;


	@Test
	public void contextLoads() throws Exception {
		assertThat(bookController).isNotNull();
	}
	@Test
	public void contextLoads1() throws Exception {
		assertThat(categoryController).isNotNull();
	}
}
