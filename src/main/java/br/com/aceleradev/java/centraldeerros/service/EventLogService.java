package br.com.aceleradev.java.centraldeerros.service;

import br.com.aceleradev.java.centraldeerros.model.EventLog;

import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface EventLogService {

    List<EventLog> findAll(Specification<EventLog> specification, Pageable pageable);

    Optional<EventLog> findById(Long id);

    EventLog save(EventLog event);

    void deleteById(Long id);





}
