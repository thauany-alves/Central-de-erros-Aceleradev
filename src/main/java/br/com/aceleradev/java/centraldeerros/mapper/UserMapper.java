package br.com.aceleradev.java.centraldeerros.mapper;

import br.com.aceleradev.java.centraldeerros.dto.UserDTO;
import br.com.aceleradev.java.centraldeerros.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel ="spring")
public interface UserMapper {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "login", target = "login"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "createAt", target = "createAt")
    })
    UserDTO map(User user);
}
