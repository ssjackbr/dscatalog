package br.com.ignidigital.dscatalog.resources;

import br.com.ignidigital.dscatalog.dto.CategoryDTO;
import br.com.ignidigital.dscatalog.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.Serializable;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource implements Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    private CategoryService service;

    @GetMapping
    public ResponseEntity <List<CategoryDTO>> findAll(){
        List<CategoryDTO> listDto = service.findAll();
        return ResponseEntity.ok().body(listDto);
    }

    @GetMapping (value = "/{id}")
    public ResponseEntity findById (@PathVariable Long id){
        CategoryDTO dto = service.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> insert (@RequestBody CategoryDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<CategoryDTO> update(@PathVariable Long id, @RequestBody CategoryDTO dto) {
        dto = service.update(id,dto);
        return ResponseEntity.ok().body(dto);
    }

}
