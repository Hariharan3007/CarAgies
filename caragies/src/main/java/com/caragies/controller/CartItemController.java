package com.caragies.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.caragies.entitydto.CartItemDto;
import com.caragies.entitymodel.CartItem;
import com.caragies.service.CartService;
@RestController
@RequestMapping("/api/cart")
public class CartItemController {
	@Autowired
	private CartService cartService;

    @PostMapping("/add")
    public CartItem addToCart(@RequestBody CartItemDto dto) {
        return cartService.addToCart(dto);
    }
}



