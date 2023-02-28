package fr.uga.l3miage.library.data.repo;

import fr.uga.l3miage.library.TestApp;
import jakarta.transaction.Transactional;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;

@SpringBootTest(classes = TestApp.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@PropertySource("classpath:test-application.properties")
@Transactional
abstract class Base {


}
