package id.my.hendisantika.orderbatchprocessing.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-order-batch-processing
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 22/11/24
 * Time: 05.39
 * To change this template use File | Settings | File Templates.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id
    private Long id;
    private String name;
    private String category;
    private double price;
    @Column(name = "isOfferApplied")
    private boolean isOfferApplied;
    @Column(name = "discountPercentage")
    private double discountPercentage;
    @Column(name = "priceAfterDiscount")
    private double priceAfterDiscount;
}
