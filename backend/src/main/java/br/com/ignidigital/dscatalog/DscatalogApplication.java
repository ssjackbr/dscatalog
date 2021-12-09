package br.com.ignidigital.dscatalog;

import br.com.ignidigital.dscatalog.entities.Category;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DscatalogApplication {

    public static void main(String[] args) {
        SpringApplication.run(DscatalogApplication.class, args);

        Category cat = new Category();
        System.out.println( cat.getCreatedAt());

    }

}
