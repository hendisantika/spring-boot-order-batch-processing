package id.my.hendisantika.orderbatchprocessing.controller;

import id.my.hendisantika.orderbatchprocessing.service.ProductService;
import id.my.hendisantika.orderbatchprocessing.service.ProductServiceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-order-batch-processing
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 22/11/24
 * Time: 05.47
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    private final ProductServiceV2 productServiceV2;

    //this endpoint for testing
    @GetMapping("/ids")
    public ResponseEntity<List<Long>> getIds() {
        return ResponseEntity.ok(productService.getProductIds());
    }

    //this endpoint for data reset
    @PostMapping("/reset")
    public ResponseEntity<String> resetProductRecords() {
        String response = productService.resetRecords();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/process")
    public ResponseEntity<String> processProductIds(@RequestBody List<Long> productIds) {
        productService.processProductIds(productIds);
        return ResponseEntity.ok("Products processed and events published.");
    }
}
