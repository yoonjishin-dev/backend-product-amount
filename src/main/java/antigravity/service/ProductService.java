package antigravity.service;

import antigravity.domain.entity.Product;
import antigravity.domain.entity.Promotion;
import antigravity.domain.entity.PromotionProducts;
import antigravity.model.request.ProductInfoRequest;
import antigravity.model.response.ProductAmountResponse;
import antigravity.repository.ProductRepository;
import antigravity.repository.PromotionProductsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final PromotionProductsRepository promotionProductsRepository;

    public ProductAmountResponse getProductAmount(ProductInfoRequest request) {
        System.out.println("상품 가격 추출 로직을 완성 시켜주세요.");

        if (!Objects.nonNull(request) || !Objects.nonNull(request.getCouponIds()) || !Objects.nonNull(productRepository.findById(request.getProductId()))) {
            throw new NullPointerException();
        }

        Product product = productRepository.findById(request.getProductId()).orElse(new Product());
        List<PromotionProducts> promotionProducts = promotionProductsRepository.findAllByProduct(product);

        double couponResult = 0;
        double codeResult = 0;
        for (PromotionProducts p : promotionProducts) {
            Promotion promotion = p.getPromotion();
            switch (promotion.getPromotion_type()) {
                case COUPON ->
                        couponResult += product.getPrice() - (product.getPrice() - promotion.getDiscount_value());
                case CODE ->
                        codeResult += product.getPrice() - (product.getPrice() * (1 - (promotion.getDiscount_value() / 100.0)));
            }
        }

        return ProductAmountResponse.builder()
                .name(product.getName())
                .originPrice(product.getPrice())
                .discountPrice((int) couponResult + (int) codeResult)
                .finalPrice(returnPrice(product.getPrice() - (int) couponResult - (int) codeResult))
                .build();
    }

    private int returnPrice(double result) {

        return (int) (Math.floor(result / 10000) * 10000);
    }
}
