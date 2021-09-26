package hh.swd20.bookstore;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import hh.swd20.bookstore.Domain.Book;
import hh.swd20.bookstore.Domain.BookRepository;
import hh.swd20.bookstore.Domain.Category;
import hh.swd20.bookstore.Domain.CategoryRepository;


@SpringBootApplication
public class BookstoreApplication {
	
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookStore(BookRepository bookRepository, CategoryRepository categoryRepository) {
		return (args) -> {
			categoryRepository.save(new Category("Fantasy"));

			log.info("save a couple of books");
			bookRepository.save(new Book("Taru sormusten herrasta", "Tolkien", 1934, "2323-45454", 23.34, categoryRepository.findByName("Fantasy").get(0)));
			
			log.info("fetch all books");
			for (Book book : bookRepository.findAll()) {
				log.info(book.toString());
			}
			
		};
	}
}
