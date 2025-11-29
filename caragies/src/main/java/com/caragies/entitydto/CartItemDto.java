package com.caragies.entitydto;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemDto {
	    private Long accessoryId;
	    private Integer quantity;
	    private String userId;
	}


