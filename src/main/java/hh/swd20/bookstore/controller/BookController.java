package hh.swd20.bookstore.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import hh.swd20.bookstore.Domain.Book;
import hh.swd20.bookstore.Domain.BookRepository;
import hh.swd20.bookstore.Domain.CategoryRepository;

@CrossOrigin
@Controller
public class BookController {
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@RequestMapping(value="/login")
    public String login() {	
        return "login";
    }
	
	@RequestMapping(value = "/booklist")
	public String getBook(Model model) {
		model.addAttribute("books", bookRepository.findAll());
		return "booklist";
	}
	@RequestMapping(value="/books", method=RequestMethod.GET)
	public @ResponseBody List<Book> studentListRest() {
		return (List<Book>) bookRepository.findAll();
	}
	
	@RequestMapping(value="/books/{id}", method=RequestMethod.GET)
	public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long Id) {
		return bookRepository.findById(Id);
	}
	
	@RequestMapping(value = "/add")
    public String addBook(Model model){
    	model.addAttribute("book", new Book());
    	model.addAttribute("categories", categoryRepository.findAll());
        return "addbook";
    }
	@RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Book book){
        bookRepository.save(book);
        return "redirect:booklist";
    }    
	@PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteBook(@PathVariable("id") Long id, Model model) {
    	bookRepository.deleteById(id);
        return "redirect:../booklist";
    }
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editBook(@PathVariable("id") Long id, Model model) {
    	model.addAttribute("book", bookRepository.findById(id).get());
    	model.addAttribute("categories", categoryRepository.findAll());
        return "editbook";
    }
    @RequestMapping(value="/update/{id}", method=RequestMethod.POST)
    public String saveEdit(@PathVariable("id")Long id, Book book, Model model) {
		book.setId(id);
		bookRepository.save(book);
		return "redirect:../booklist";
	}
}
