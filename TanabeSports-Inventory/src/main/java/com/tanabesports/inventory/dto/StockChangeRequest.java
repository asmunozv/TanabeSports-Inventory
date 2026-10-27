package com.tanabesports.inventory.dto;
import jakarta.validation.constraints.NotNull;
public record StockChangeRequest(@NotNull Integer quantity) {}
