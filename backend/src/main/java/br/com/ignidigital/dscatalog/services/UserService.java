package br.com.ignidigital.dscatalog.services;

import br.com.ignidigital.dscatalog.domain.dto.RoleDTO;
import br.com.ignidigital.dscatalog.domain.dto.UserDTO;
import br.com.ignidigital.dscatalog.domain.dto.UserInsertDTO;
import br.com.ignidigital.dscatalog.domain.entities.Role;
import br.com.ignidigital.dscatalog.domain.entities.User;
import br.com.ignidigital.dscatalog.repositories.RoleRepository;
import br.com.ignidigital.dscatalog.repositories.UserRepository;
import br.com.ignidigital.dscatalog.services.exceptions.DatabaseException;
import br.com.ignidigital.dscatalog.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.io.Serializable;
import java.util.Optional;

@Service
public class UserService implements Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository repository;

    @Autowired
    private RoleRepository roleRepository;
    
    @Transactional(readOnly = true)
    public Page<UserDTO> findAllPaged (Pageable pageable){
        Page<User> list = repository.findAll(pageable);
        return list.map(x -> new UserDTO(x));
    }

    @Transactional(readOnly = true)
    public UserDTO findById (Long id) {
        Optional<User> obj = repository.findById(id);
        User entityUser = obj.orElseThrow(() -> new ResourceNotFoundException("Entity not found!"));
        return new UserDTO(entityUser);
    }

    @Transactional
    public UserDTO insert(UserInsertDTO dto) {
        User entityUser = new User();
        copyDtoToEntity(dto, entityUser);
        entityUser.setPassword(passwordEncoder.encode(dto.getPassword()));
        entityUser = repository.save(entityUser);
        return new UserDTO(entityUser);
    }
    @Transactional
    public UserDTO update(long id, UserDTO dto) {
        try {
            User entity = repository.getOne(id);
            copyDtoToEntity(dto, entity);
            entity = repository.save(entity);
            return new UserDTO(entity);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id not found "+id);
        }
    }

    public void delete(Long id) {
        try{
            repository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException("Id not found "+id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException ("Integrity violation!");
        }
    }

    private void copyDtoToEntity(UserDTO dto, User entityUser) {

        entityUser.setFirstName(dto.getFirstName());
        entityUser.setLastName(dto.getLastName());
        entityUser.setEmail(dto.getEmail());

        entityUser.getRoles().clear();
        for (RoleDTO roleDTO : dto.getRoles()){
            Role role = roleRepository.getOne(roleDTO.getId());
            entityUser.getRoles().add(role);
        }

    }
}
