package hh.swd20.bookstore;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.swd20.bookstore.Domain.Category;
import hh.swd20.bookstore.Domain.CategoryRepository;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CategoryRepositoryTest {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Test
	public void findByNameShouldReturnCategory() {
		List<Category> categories = categoryRepository.findByName("Fantasy");
		assertThat(categories).hasSize(1);
	}
	@Test
	public void createNewCategory() {
		Category category = new Category("Horror");
		categoryRepository.save(category);
		assertThat(category.getCategoryId()).isNotNull();
	}
	@Test
	public void deleteCategory() {
		Category category = new Category("Horror");
		categoryRepository.save(category);
		categoryRepository.deleteById(category.getCategoryId());
		assertThat(categoryRepository.count()).isEqualTo(2);
	}
}
