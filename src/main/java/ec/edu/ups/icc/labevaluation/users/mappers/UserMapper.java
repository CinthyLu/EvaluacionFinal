package ec.edu.ups.icc.labevaluation.users.mappers;
import java.util.stream.Collectors;

import ec.edu.ups.icc.labevaluation.users.dtos.UserResponseDto;
import ec.edu.ups.icc.labevaluation.users.entities.UserEntity;
public final class UserMapper {

    private UserMapper() {}
    // # R2: Mapear la entidad de usuario al nuevo DTO con sus roles mapeados a String
    public static UserResponseDto toResponse(UserEntity entity){
        if (entity == null) return null;
        return new UserResponseDto(
            entity.getId(),
            entity.getFullName(),
            entity.getEmail(),
            entity.getAge(),
            entity.isActive(),
            entity.getRoles().stream()
                .map(role -> role.getName().name())
                .collect(Collectors.toSet())
        );
    }
}