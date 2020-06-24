package br.com.aceleradev.java.centraldeerros;

import br.com.aceleradev.java.centraldeerros.model.EventLog;
import br.com.aceleradev.java.centraldeerros.model.Level;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class EventLogMocks {
    private Long id = 1L;
    private Level levels = Level.valueOf("ERROR");
    private String description = "descriptionteste";
    private String log = "teste";
    private String origin = "127.0.0.1";

    public EventLog create() {
        EventLog event = new EventLog();
        event.setId(id);
        event.setLevel(levels);
        event.setLog(log);
        event.setDescription(description);
        event.setOrigin(origin);
        event.setQuantity(3L);
        return event;
    }

    public static String asJson(Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
