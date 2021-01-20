package br.com.ignidigital.dscatalog.services;

import br.com.ignidigital.dscatalog.entities.Category;
import br.com.ignidigital.dscatalog.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository repository;

    public List<Category> findAll() {
        return repository.findAll();
    }
}
