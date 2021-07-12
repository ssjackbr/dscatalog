package br.com.ignidigital.dscatalog.resources;

import br.com.ignidigital.dscatalog.dto.UserDTO;
import br.com.ignidigital.dscatalog.dto.UserInsertDTO;
import br.com.ignidigital.dscatalog.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.io.Serializable;
import java.net.URI;

@RestController
@RequestMapping(value = "/users")
public class UserResource implements Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    private UserService product;

    @GetMapping
    public ResponseEntity <Page<UserDTO>> findAll(

        @RequestParam(value = "page", defaultValue = "0") Integer page,
        @RequestParam(value = "linesPerPage", defaultValue = "12") Integer linesPerPage,
        @RequestParam(value = "direction", defaultValue = "ASC") String direction,
        @RequestParam(value = "orderBy", defaultValue = "firstName") String orderBy
    )
    {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        Page<UserDTO> list = product.findAllPaged(pageRequest);
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
