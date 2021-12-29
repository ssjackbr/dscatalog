package br.com.ignidigital.dscatalog.repositories;

import br.com.ignidigital.dscatalog.domain.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
