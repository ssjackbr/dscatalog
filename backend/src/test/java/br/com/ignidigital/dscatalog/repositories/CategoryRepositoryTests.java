package br.com.ignidigital.dscatalog.repositories;

import br.com.ignidigital.dscatalog.entities.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
public class CategoryRepositoryTests {

    @Autowired CategoryRepository categoryRepository;

    @Test
    public void deleteShouldDeleteObjectWhenIdExistsInDataBase(){

        categoryRepository.deleteById(1L);
        Optional<Category> category = categoryRepository.findById(1L);
        Assertions.assertFalse(category.isPresent());
    }
}
