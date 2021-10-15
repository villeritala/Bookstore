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
import hh.swd20.bookstore.Domain.User;
import hh.swd20.bookstore.Domain.UserRepository;


@SpringBootApplication
public class BookstoreApplication {
	
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookStore(BookRepository bookRepository, CategoryRepository categoryRepository, UserRepository userRepository) {
		return (args) -> {
			log.info("save a couple of books");
			Category c1 = new Category("Fantasy");
			Category c2 = new Category("Kertomarjallisuus");
			categoryRepository.save(c1);
			categoryRepository.save(c2);
			bookRepository.save(new Book("Taru sormusten herrasta", "Tolkien", 1934, "2323-45454", 23.34, c1));
			bookRepository.save(new Book("Alkemisti", "Paulo Coelho", 1998, "2334343-45454", 12.35, c2));
			
			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "user@mail.com", "USER");
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C","admin@mail.com", "ADMIN");
			userRepository.save(user1);
			userRepository.save(user2);
			
			log.info("fetch all books");
			for (Book book : bookRepository.findAll()) {
				log.info(book.toString());
			}
			
		};
	}
}
