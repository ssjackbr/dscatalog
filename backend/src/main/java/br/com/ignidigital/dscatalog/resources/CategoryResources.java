package br.com.ignidigital.dscatalog.resources;

import br.com.ignidigital.dscatalog.entities.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResources implements Serializable {

    @GetMapping
    public ResponseEntity <List<Category>> findAll(){
        List<Category> list = new ArrayList<>();
        list.add(new Category(1L,"Books"));
        list.add(new Category(2L,"Electronics"));

        return ResponseEntity.ok().body(list);
    }

}
