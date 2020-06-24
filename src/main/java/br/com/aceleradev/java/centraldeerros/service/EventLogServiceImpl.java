package br.com.aceleradev.java.centraldeerros.service;

import br.com.aceleradev.java.centraldeerros.model.EventLog;
import br.com.aceleradev.java.centraldeerros.model.Level;
import br.com.aceleradev.java.centraldeerros.repository.EventLogRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;



import java.util.List;
import java.util.Optional;


@Service
public class EventLogServiceImpl implements EventLogService {

    @Autowired
    private EventLogRepository eventLogRepository;


    @Override
    public List<EventLog> findAll(Specification<EventLog> specification, Pageable pageable) {
        return eventLogRepository.findAll(specification, pageable).getContent();
    }

    @Override
    public Optional<EventLog> findById(Long id) {
        return eventLogRepository.findById(id);
    }

    @Override
    public EventLog save(EventLog event) {
        return eventLogRepository.save(event);
    }



    @Override
    public void deleteById(Long id) {
        eventLogRepository.deleteById(id);
    }
}
