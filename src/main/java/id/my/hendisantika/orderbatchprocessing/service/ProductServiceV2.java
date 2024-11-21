package id.my.hendisantika.orderbatchprocessing.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.my.hendisantika.orderbatchprocessing.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-order-batch-processing
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 22/11/24
 * Time: 05.43
 * To change this template use File | Settings | File Templates.
 */
@Service
@RequiredArgsConstructor
public class ProductServiceV2 {

    private final ProductRepository repository;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;
    @Value("${product.discount.update.topic}")
    private final String topicName;

    private final ExecutorService executorService = Executors.newFixedThreadPool(6);

    public String resetRecords() {
        repository.findAll()
                .forEach(product -> {
                    product.setOfferApplied(false);
                    product.setPriceAfterDiscount(product.getPrice());
                    product.setDiscountPercentage(0);
                    repository.save(product);
                });
        return "Data Reset to DB";
    }

    public void executeProductIds(List<Long> productIds) {
        List<List<Long>> batches = splitIntoBatches(productIds, 50);

        List<CompletableFuture<Void>> futures = batches
                .stream()
                .map(
                        batch -> CompletableFuture.runAsync(() -> processProductIds(batch), executorService))
                .collect(Collectors.toList());

        //wait for all future to complete
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();

    }
}
