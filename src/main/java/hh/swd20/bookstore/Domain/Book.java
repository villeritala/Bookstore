package hh.swd20.bookstore.Domain;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class Book {
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String getBook() {
		return "index"; // --> index.html
	}
	
}
