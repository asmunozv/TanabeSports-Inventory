package com.tanabesports.inventory.dto;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
public record CreateProductRequest(@NotBlank String sku,@NotBlank String name,@NotNull @DecimalMin("0.0") BigDecimal price,@Min(0) int stock,@Min(0) int minimumStock) {}
