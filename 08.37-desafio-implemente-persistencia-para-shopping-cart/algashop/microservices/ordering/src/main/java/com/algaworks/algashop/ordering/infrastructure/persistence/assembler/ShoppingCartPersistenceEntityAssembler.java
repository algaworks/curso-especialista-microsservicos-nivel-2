package com.algaworks.algashop.ordering.infrastructure.persistence.assembler;

import com.algaworks.algashop.ordering.domain.model.entity.ShoppingCart;
import com.algaworks.algashop.ordering.domain.model.entity.ShoppingCartItem;
import com.algaworks.algashop.ordering.infrastructure.persistence.entity.ShoppingCartItemPersistenceEntity;
import com.algaworks.algashop.ordering.infrastructure.persistence.entity.ShoppingCartPersistenceEntity;
import com.algaworks.algashop.ordering.infrastructure.persistence.repository.CustomerPersistenceEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ShoppingCartPersistenceEntityAssembler {

    private final CustomerPersistenceEntityRepository customerPersistenceEntityRepository;

    public ShoppingCartPersistenceEntity fromDomain(ShoppingCart shoppingCart) {
        return merge(new ShoppingCartPersistenceEntity(), shoppingCart);
    }

    public ShoppingCartPersistenceEntity merge(ShoppingCartPersistenceEntity persistenceEntity,
                                               ShoppingCart shoppingCart) {
        persistenceEntity.setId(shoppingCart.id().value());
        persistenceEntity.setCustomer(customerPersistenceEntityRepository.getReferenceById(shoppingCart.customerId().value()));
        persistenceEntity.setTotalAmount(shoppingCart.totalAmount().value());
        persistenceEntity.setTotalItems(shoppingCart.totalItems().value());
        persistenceEntity.setCreatedAt(shoppingCart.createdAt());
        Set<ShoppingCartItemPersistenceEntity> mergeItems = mergeItems(shoppingCart, persistenceEntity);
        persistenceEntity.replaceItems(mergeItems);
        return persistenceEntity;
    }

    private ShoppingCartItemPersistenceEntity fromDomain(ShoppingCartItem item) {
        return mergeItem(new ShoppingCartItemPersistenceEntity(), item);
    }

    private Set<ShoppingCartItemPersistenceEntity> mergeItems(ShoppingCart shoppingCart, ShoppingCartPersistenceEntity shoppingCartPersistenceEntity){
        Set<ShoppingCartItem> newOrUpdateItems = shoppingCart.items();
        if (newOrUpdateItems == null || shoppingCart.items().isEmpty()){
            return new HashSet<>();
        }

        Set<ShoppingCartItemPersistenceEntity> existingItems = shoppingCartPersistenceEntity.getItems();

        if(existingItems == null || existingItems.isEmpty()){
            return newOrUpdateItems.stream().map(this::fromDomain).collect(Collectors.toSet());
        }

        Map<UUID, ShoppingCartItemPersistenceEntity> existingMap = existingItems.stream()
                .collect(Collectors.toMap(ShoppingCartItemPersistenceEntity::getId, item -> item));

        return newOrUpdateItems.stream()
                .map(shoppingCartItem -> {
                    ShoppingCartItemPersistenceEntity itemPersistence = existingMap.getOrDefault(
                            shoppingCartItem.id().value(), new ShoppingCartItemPersistenceEntity()
                    );
                    return mergeItem(itemPersistence, shoppingCartItem);
                }).collect(Collectors.toSet());
    }

    private ShoppingCartItemPersistenceEntity mergeItem(ShoppingCartItemPersistenceEntity persistenceEntity, ShoppingCartItem shoppingCartItem) {
        persistenceEntity.setId(shoppingCartItem.id().value());
        persistenceEntity.setProductId(shoppingCartItem.productId().value());
        persistenceEntity.setName(shoppingCartItem.name().value());
        persistenceEntity.setPrice(shoppingCartItem.price().value());
        persistenceEntity.setQuantity(shoppingCartItem.quantity().value());
        persistenceEntity.setAvailable(shoppingCartItem.isAvailable());
        persistenceEntity.setTotalAmount(shoppingCartItem.totalAmount().value());
        return persistenceEntity;
    }

}
