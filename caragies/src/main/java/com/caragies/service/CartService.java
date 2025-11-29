package com.caragies.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caragies.entitydto.CartItemDto;
import com.caragies.entitymodel.Accessory;
import com.caragies.entitymodel.CartItem;
import com.caragies.repositories.AccessoryRepository;
import com.caragies.repositories.CartRepository;

@Service
public class CartService {
	@Autowired
    private  CartRepository cartRepo;
	
	@Autowired
	private AccessoryRepository accessoryRepo;

    public CartItem addToCart(CartItemDto dto) {

        CartItem existing = cartRepo.findByUserIdAndAccessoryId(
                dto.getUserId(),
                dto.getAccessoryId()
        );

        if (existing != null) {
            existing.setQuantity(existing.getQuantity() + dto.getQuantity());
            existing.setTotalPrice(existing.getQuantity() * existing.getPrice());
            return cartRepo.save(existing);
        }
        
        Accessory accessory=accessoryRepo.findById(dto.getAccessoryId()).get();
        
        CartItem item = CartItem.builder()
                .accessoryId(dto.getAccessoryId())
                .quantity(dto.getQuantity())
                .price(accessory.getPrice())
                .totalPrice(accessory.getPrice() * dto.getQuantity())
                .userId(dto.getUserId())
                .build();

        return cartRepo.save(item);

}
}