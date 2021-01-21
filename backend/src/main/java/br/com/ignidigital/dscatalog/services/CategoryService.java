package br.com.ignidigital.dscatalog.services;

import br.com.ignidigital.dscatalog.dto.CategoryDTO;
import br.com.ignidigital.dscatalog.entities.Category;
import br.com.ignidigital.dscatalog.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

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
}
