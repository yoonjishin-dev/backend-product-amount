package antigravity.repository;

import antigravity.domain.entity.Product;
import antigravity.domain.entity.PromotionProducts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PromotionProductsRepository extends JpaRepository<PromotionProducts, Integer> {

    List<PromotionProducts> findAllByProduct(Product product);
}
