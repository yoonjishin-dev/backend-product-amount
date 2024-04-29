package antigravity.domain.entity;

import antigravity.domain.entity.enumeration.DiscountType;
import lombok.Getter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Entity
public class Promotion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(value = EnumType.STRING)
    private DiscountType promotion_type; //쿠폰 타입 (쿠폰, 코드)

    private String name;

    private String discount_type; // WON : 금액 할인, PERCENT : %할인

    private int discount_value; // 할인 금액 or 할인 %

    private Date use_started_at; // 쿠폰 사용가능 시작 기간

    private Date use_ended_at; // 쿠폰 사용가능 종료 기간

    @OneToMany(mappedBy = "promotion")
    private List<PromotionProducts> promotionProducts;
}
