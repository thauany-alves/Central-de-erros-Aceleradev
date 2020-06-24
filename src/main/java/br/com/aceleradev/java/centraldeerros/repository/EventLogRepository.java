package br.com.aceleradev.java.centraldeerros.repository;

import br.com.aceleradev.java.centraldeerros.model.EventLog;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import org.springframework.data.repository.CrudRepository;



public interface EventLogRepository extends CrudRepository<EventLog, Long> {


    Page<EventLog> findAll(Specification<EventLog> specification, Pageable pageable);




}
