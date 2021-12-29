package br.com.ignidigital.dscatalog.repositories;

import br.com.ignidigital.dscatalog.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
