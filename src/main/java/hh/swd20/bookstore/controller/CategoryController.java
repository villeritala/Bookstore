package hh.swd20.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import hh.swd20.bookstore.Domain.Category;
import hh.swd20.bookstore.Domain.CategoryRepository;

@Controller
public class CategoryController {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@RequestMapping(value="/categorylist")
	public String getCategory(Model model) {
		model.addAttribute("categories", categoryRepository.findAll());
		return "categorylist";
	}
	@RequestMapping(value="/addcategory")
	public String addCategory(Model model) {
		model.addAttribute("category", new Category());
		return "addcategory";
	}
	@RequestMapping(value = "/savecategory", method = RequestMethod.POST)
    public String save(Category category){
        categoryRepository.save(category);
        return "redirect:categorylist";
    } 
}
