package br.com.ignidigital.dscatalog.controller;

import br.com.ignidigital.dscatalog.domain.dto.UserDTO;
import br.com.ignidigital.dscatalog.domain.dto.UserInsertDTO;
import br.com.ignidigital.dscatalog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.io.Serializable;
import java.net.URI;

@RestController
@RequestMapping(value = "/users")
public class UserController implements Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    private UserService product;

    @GetMapping
    public ResponseEntity <Page<UserDTO>> findAll(Pageable pageable) {
        Page<UserDTO> list = product.findAllPaged(pageable);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping (value = "/{id}")
    public ResponseEntity findById (@PathVariable Long id){
        UserDTO dto = product.findById(id);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<UserDTO> insert (@Valid @RequestBody UserInsertDTO dto) {
        UserDTO newDto = product.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newDto.getId()).toUri();
        return ResponseEntity.created(uri).body(newDto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<UserDTO> update(@Valid @PathVariable Long id, @RequestBody UserDTO dto) {
        dto = product.update(id,dto);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<UserDTO> delete (@PathVariable Long id){
        product.delete(id);
        return ResponseEntity.noContent().build();
    }

}
