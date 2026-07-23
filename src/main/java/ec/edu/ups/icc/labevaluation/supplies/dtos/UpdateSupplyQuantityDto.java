package ec.edu.ups.icc.labevaluation.supplies.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record UpdateSupplyQuantityDto(
    @NotNull(message = "Quantity is required")
    @Min(value = 0, message = "Quantity must be greater than or equal to zero")
    Integer quantity
) {}
