package br.com.ignidigital.dscatalog.repositories;

import br.com.ignidigital.dscatalog.entities.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class UserRepositoryTests {

    @Autowired UserRepository userRepository;

    @Test
    public void deleteShouldDeleteObjectWhenIdExistsInDataBase(){

        userRepository.deleteById(1L);
        Optional<User> user = userRepository.findById(1L);
        Assertions.assertFalse(user.isPresent());
    }
}
