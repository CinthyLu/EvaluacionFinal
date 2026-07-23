package ec.edu.ups.icc.labevaluation.supplies.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ec.edu.ups.icc.labevaluation.supplies.dtos.CreateSupplyDto;
import ec.edu.ups.icc.labevaluation.supplies.dtos.SupplyResponseDto;
import ec.edu.ups.icc.labevaluation.supplies.dtos.UpdateSupplyQuantityDto;
import ec.edu.ups.icc.labevaluation.supplies.services.SupplyService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/supplies")
public class SupplyController {

    private final SupplyService service;

    public SupplyController(SupplyService service) {
        this.service = service;
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'COORDINATOR')")
    public ResponseEntity<SupplyResponseDto> create(@Valid @RequestBody CreateSupplyDto dto) {
        SupplyResponseDto response = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

     @GetMapping("/low-stock")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<SupplyResponseDto>> findLowStock(@RequestParam Integer maxQuantity) {
        List<SupplyResponseDto> response = service.findLowStock(maxQuantity);
        return ResponseEntity.ok(response);
    }

      @PatchMapping("/{id}/quantity")
    @PreAuthorize("hasAnyRole('ADMIN', 'COORDINATOR')")
    public ResponseEntity<SupplyResponseDto> updateQuantity(
            @PathVariable Long id,
            @Valid @RequestBody UpdateSupplyQuantityDto dto) {
        SupplyResponseDto response = service.updateQuantity(id, dto);
        return ResponseEntity.ok(response);
    }
    
    

}