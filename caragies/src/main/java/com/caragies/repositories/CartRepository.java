package com.caragies.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.caragies.entitymodel.CartItem;

public interface CartRepository extends JpaRepository<CartItem,Long>{
    CartItem findByUserIdAndAccessoryId(String userId, Long accessoryId);
}



