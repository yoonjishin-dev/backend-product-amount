package antigravity.domain.entity;

import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
public class PromotionProducts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "promotion_id")
    private Promotion promotion;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
