package br.com.ignidigital.dscatalog.repositories;

import br.com.ignidigital.dscatalog.domain.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
