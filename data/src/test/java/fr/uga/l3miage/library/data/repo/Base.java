package fr.uga.l3miage.library.data.repo;

import fr.uga.l3miage.library.TestApp;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = TestApp.class, webEnvironment = SpringBootTest.WebEnvironment.NONE,
        properties = "spring.jpa.show-sql=true")
@Transactional
abstract class Base {

    @Autowired
    EntityManager entityManager;

}
