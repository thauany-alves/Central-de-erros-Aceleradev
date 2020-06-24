package br.com.aceleradev.java.centraldeerros.mapper;

import br.com.aceleradev.java.centraldeerros.dto.EventLogDTO;
import br.com.aceleradev.java.centraldeerros.model.EventLog;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EventLogMapper {

    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "description", target = "description"),
            @Mapping(source = "origin", target = "origin"),
            @Mapping(source = "level", target = "level"),
            @Mapping(source = "createAt", target = "createAt", dateFormat = "yyyy-MM-dd HH:mm"),
            @Mapping(source = "quantity", target = "quantity")
    })

    EventLogDTO map(EventLog eventLog);

    List<EventLogDTO> map(List<EventLog> eventLogs);

}
