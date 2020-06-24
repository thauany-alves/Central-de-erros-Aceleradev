package br.com.aceleradev.java.centraldeerros.dto;

import br.com.aceleradev.java.centraldeerros.model.EventLog;
import br.com.aceleradev.java.centraldeerros.model.Level;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventLogDTO {

    private Long id;
    private Level level;
    private String description;
    private String origin;
    private LocalDateTime createAt;
    private Long quantity;




}
