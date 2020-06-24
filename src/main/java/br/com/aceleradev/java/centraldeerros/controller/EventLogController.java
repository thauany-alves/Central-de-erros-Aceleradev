package br.com.aceleradev.java.centraldeerros.controller;

import br.com.aceleradev.java.centraldeerros.dto.EventLogDTO;
import br.com.aceleradev.java.centraldeerros.exceptions.ResourceNotFoundException;
import br.com.aceleradev.java.centraldeerros.mapper.EventLogMapper;
import br.com.aceleradev.java.centraldeerros.model.EventLog;
import br.com.aceleradev.java.centraldeerros.rules.EnumEventLogRule;
import br.com.aceleradev.java.centraldeerros.rules.StringEventLogRule;
import br.com.aceleradev.java.centraldeerros.service.EventLogService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/events")
public class EventLogController {

    @Autowired
    private EventLogService eventLogService;

    private EventLogMapper eventLogMapper = Mappers.getMapper(EventLogMapper.class);

    @PostMapping
    @ApiOperation("Cria um registro de evento")
    @ApiResponses(value = {@ApiResponse(code = 201, message= "Evento criado com sucesso")})
    public ResponseEntity<EventLog> create(@Valid @RequestBody EventLog eventLog){
        return new ResponseEntity<EventLog>(this.eventLogService.save(eventLog), HttpStatus.CREATED);
    }

    @PutMapping
    @ApiOperation("Atualiza um evento")
    public ResponseEntity<EventLog> update(@Valid @RequestBody EventLog eventLog) {
        return new ResponseEntity<EventLog>(this.eventLogService.save(eventLog), HttpStatus.ACCEPTED);
    }

    @ApiOperation("Deleta um evento")
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteEvent(@PathVariable Long id) {
        try {
            this.eventLogService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(produces = "application/json")
    @ApiOperation("Lista eventos")
    public List<EventLogDTO> findAll( @ApiParam @RequestParam(required = false) String origin,
                                      @ApiParam  @RequestParam(required =false) String level,
                                      @ApiParam  @RequestParam(required =false) String description,
                                      @ApiParam  @RequestParam(required =false) String log,
                                      @ApiParam  @RequestParam(required =false)  Long  quantity, Pageable pageable) {

        Specification<EventLog> specifications = Specification.where(new EnumEventLogRule("level", level))
                .and(new StringEventLogRule("log", log))
                .and(new StringEventLogRule("origin", origin))
                .and(new StringEventLogRule("description", description));
        return eventLogMapper.map(eventLogService.findAll(specifications,pageable));
    }

    @GetMapping(value= "/{id}", produces = "application/json")
    @ApiOperation("Mostra um evento pelo id informado")
    @ApiResponses(value = {@ApiResponse(code = 404, message = "Evento não localizado"), @ApiResponse(code = 200, message = "Evento localizado")})
    public ResponseEntity<EventLog> findById(@PathVariable("id") Long id) {
        return new ResponseEntity<EventLog>(this.eventLogService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Log de evento")), HttpStatus.OK);
    }

}
