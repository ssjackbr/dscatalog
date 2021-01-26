package br.com.ignidigital.dscatalog.services;

import br.com.ignidigital.dscatalog.dto.CategoryDTO;
import br.com.ignidigital.dscatalog.entities.Category;
import br.com.ignidigital.dscatalog.repositories.CategoryRepository;
import br.com.ignidigital.dscatalog.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    private CategoryRepository repository;
    
    @Transactional(readOnly = true)
    public List<CategoryDTO> findAll (){
        List<Category> list = repository.findAll();

        /*
            Possibilidade de melhoria de código utilizando expressão lambda para redução do código abaixo que contem a estrutura for;
            List<CategoryDTO> listDto = list.stream().map(x -> new CategoryDTO(x)).collect(Collectors.toList());
         */

        List<CategoryDTO> listDto = new ArrayList<>();
        for (Category cat : list) {
            listDto.add(new CategoryDTO(cat));
        }
        return listDto;
    }

    @Transactional(readOnly = true)
    public CategoryDTO findById (Long id) {
        Optional<Category> obj = repository.findById(id);
        Category entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found!"));
        return new CategoryDTO(entity);
    }

    @Transactional
    public CategoryDTO insert(CategoryDTO dto) {
        Category entity = new Category();
        entity.setName(dto.getName());
        entity = repository.save(entity);
        return new CategoryDTO(entity);
    }
    @Transactional
    public CategoryDTO update(long id, CategoryDTO dto) {
        try {
            Category entity = repository.getOne(id);
            entity.setName(dto.getName());
            entity = repository.save(entity);
            return new CategoryDTO(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found "+id);
        }
    }
}
