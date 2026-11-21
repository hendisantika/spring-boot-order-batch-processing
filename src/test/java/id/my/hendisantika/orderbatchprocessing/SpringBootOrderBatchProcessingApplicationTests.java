package id.my.hendisantika.orderbatchprocessing;

import id.my.hendisantika.orderbatchprocessing.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        properties = {
                "spring.datasource.url=jdbc:tc:mysql:9.1.0:///product_db",
                "spring.kafka.admin.auto-create=false"
        },
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class SpringBootOrderBatchProcessingApplicationTests {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void contextLoads() {
        assertThat(productRepository).isNotNull();
    }

    @Test
    void flywayMigrationSeedsProducts() {
        assertThat(productRepository.count()).isPositive();
    }

}
