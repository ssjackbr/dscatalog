package br.com.ignidigital.dscatalog.repositories;

import br.com.ignidigital.dscatalog.domain.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
