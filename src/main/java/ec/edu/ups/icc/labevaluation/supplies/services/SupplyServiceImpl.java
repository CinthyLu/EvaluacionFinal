package ec.edu.ups.icc.labevaluation.supplies.services;

import ec.edu.ups.icc.labevaluation.supplies.dtos.CreateSupplyDto;
import ec.edu.ups.icc.labevaluation.supplies.dtos.SupplyResponseDto;
import ec.edu.ups.icc.labevaluation.supplies.dtos.UpdateSupplyQuantityDto;
import ec.edu.ups.icc.labevaluation.supplies.entities.SupplyEntity;
import ec.edu.ups.icc.labevaluation.supplies.exceptions.SupplyConflictException;
import ec.edu.ups.icc.labevaluation.supplies.mappers.SupplyMapper;
import ec.edu.ups.icc.labevaluation.supplies.repositories.SupplyRepository;
import java.util.List;

import ec.edu.ups.icc.labevaluation.core.exceptions.domain.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SupplyServiceImpl implements SupplyService {

    private final SupplyRepository repository;

    public SupplyServiceImpl(SupplyRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public SupplyResponseDto create(CreateSupplyDto dto) {
        if (repository.existsByNameIgnoreCaseAndDeletedFalse(dto.name())) {
            throw new SupplyConflictException("Supply name already registered");
        }
        SupplyEntity entity = SupplyMapper.toEntity(dto);
        SupplyEntity saved = repository.save(entity);
        return SupplyMapper.toResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SupplyResponseDto> findLowStock(Integer maxQuantity) {
        return repository.findByActiveTrueAndDeletedFalseAndQuantityLessThanOrderByQuantityAsc(maxQuantity)
                .stream()
                .map(SupplyMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public SupplyResponseDto updateQuantity(Long id, UpdateSupplyQuantityDto dto) {
        SupplyEntity entity = repository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new NotFoundException("SUPPLY_NOT_FOUND", "Supply not found"));

        entity.setQuantity(dto.quantity());
        SupplyEntity saved = repository.save(entity);
        return SupplyMapper.toResponse(saved);
    }

   
    @Override
    @Transactional
    public void delete(Long id) {
      
        SupplyEntity entity = repository.findByIdAndDeletedFalse(id)
                .orElseThrow(() -> new NotFoundException("SUPPLY_NOT_FOUND", "Supply not found"));

               if (entity.getQuantity() > 0) {
            throw new SupplyConflictException("Supply cannot be deleted while quantity is greater than zero");
        }

        entity.setDeleted(true);
        entity.setActive(false);
        repository.save(entity);
    }
}