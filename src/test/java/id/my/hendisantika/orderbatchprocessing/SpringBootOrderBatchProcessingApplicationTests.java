package id.my.hendisantika.orderbatchprocessing;

import id.my.hendisantika.orderbatchprocessing.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest(
        properties = {
                "management.endpoint.health.show-details=always",
                "spring.datasource.url=jdbc:tc:mysql:9.1.0:///product_db"
        },
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class SpringBootOrderBatchProcessingApplicationTests {

    @Autowired
    private ProductRepository productRepository;

    @BeforeEach
    void deleteAll() {
        productRepository.deleteAll();
    }

}
