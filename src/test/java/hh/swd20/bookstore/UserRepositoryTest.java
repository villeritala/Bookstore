package hh.swd20.bookstore;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import hh.swd20.bookstore.Domain.User;
import hh.swd20.bookstore.Domain.UserRepository;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTest {
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	public void findByUsername() {
		User user = userRepository.findByUsername("user");
		assertThat(user.getEmail()).isEqualTo("user@mail.com");
	}
	@Test
	public void createNewUser() {
		User user = new User("test", "password", "test@mail.com", "USER");
		userRepository.save(user);
		assertThat(user.getId()).isNotNull();
	}
	@Test
	public void deleteUser() {
		User user = userRepository.findByUsername("user");
		userRepository.save(user);
		userRepository.deleteById(user.getId());
		assertThat(userRepository.count()).isEqualTo(1);
	}
}
