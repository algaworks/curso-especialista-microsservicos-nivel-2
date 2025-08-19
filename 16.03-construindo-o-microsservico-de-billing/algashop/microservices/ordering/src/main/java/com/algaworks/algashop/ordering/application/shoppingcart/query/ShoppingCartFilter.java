package com.algaworks.algashop.ordering.application.shoppingcart.query;

import com.algaworks.algashop.ordering.application.utility.SortablePageFilter;
import lombok.*;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCartFilter
        extends SortablePageFilter<ShoppingCartFilter.SortType> {
    private UUID customerId;
    private BigDecimal totalAmountFrom;
    private BigDecimal totalAmountTo;

    public ShoppingCartFilter(int size, int page) {
        super(size, page);
    }

    @Override
    public SortType getSortByPropertyOrDefault() {
        return getSortByProperty() == null ? SortType.CREATED_AT : getSortByProperty();
    }

    @Override
    public Sort.Direction getSortDirectionOrDefault() {
        return getSortDirection() == null ? Sort.Direction.ASC : getSortDirection();
    }

    @Getter
    @RequiredArgsConstructor
    public enum SortType {
        CREATED_AT("createdAt"),
        TOTAL_AMOUNT("totalAmount");

        private final String propertyName;
    }
}
