package br.com.ignidigital.dscatalog.repositories;

import br.com.ignidigital.dscatalog.domain.entities.Product;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Optional;

@DataJpaTest
public class ProductRepositoryTests {

    @Autowired ProductRepository productRepository;

    @Test
    public void deleteByIdShouldDeleteObjectWhenIdExistsInDataBase(){

        productRepository.deleteById(1L);
        Optional<Product> result = productRepository.findById(1L);
        Assertions.assertFalse(result.isPresent());
    }

    @Test
    public void deleteByIdShouldThrowEmptyResultDataAccessExceptionWhenIdDoesNotExist(){

        Long nonExistingId = 999L;
        Assertions.assertThrows(EmptyResultDataAccessException.class, (() -> {
            productRepository.deleteById(nonExistingId);
        }));
    }
}
