package antigravity.domain.entity;

import lombok.Data;
import lombok.Getter;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int price;

    @OneToMany(mappedBy = "product")
    private List<PromotionProducts> promotionProducts;
}
