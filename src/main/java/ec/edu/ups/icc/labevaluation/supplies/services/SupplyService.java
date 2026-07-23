package ec.edu.ups.icc.labevaluation.supplies.services;

import java.util.List;

import ec.edu.ups.icc.labevaluation.supplies.dtos.CreateSupplyDto;
import ec.edu.ups.icc.labevaluation.supplies.dtos.SupplyResponseDto;

public interface SupplyService {
    SupplyResponseDto create(CreateSupplyDto dto);
      List<SupplyResponseDto> findLowStock(Integer maxQuantity);
}
