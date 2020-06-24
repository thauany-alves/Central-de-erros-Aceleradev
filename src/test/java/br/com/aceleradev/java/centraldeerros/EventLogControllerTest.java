package br.com.aceleradev.java.centraldeerros;

import br.com.aceleradev.java.centraldeerros.auth.TokenService;
import br.com.aceleradev.java.centraldeerros.controller.EventLogController;
import br.com.aceleradev.java.centraldeerros.mapper.EventLogMapper;
import br.com.aceleradev.java.centraldeerros.model.EventLog;
import br.com.aceleradev.java.centraldeerros.model.User;
import br.com.aceleradev.java.centraldeerros.repository.EventLogRepository;
import br.com.aceleradev.java.centraldeerros.service.EventLogService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static br.com.aceleradev.java.centraldeerros.EventLogMocks.asJson;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest({EventLogController.class, EventLogMapper.class})
@AutoConfigureMockMvc
@WebAppConfiguration
@Import(TokenService.class)
public class EventLogControllerTest {


    @MockBean
    private EventLogService eventLogService;

    @InjectMocks
    EventLogMocks eventLogMocks= new EventLogMocks();

    @Mock
    EventLogRepository eventLogRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }



    @Test
    @WithMockUser
    public void shouldReturnEventById() throws Exception {
        String token = createUserAndReturnToken();
        final EventLog eventLog =  eventLogMocks.create();
        when(eventLogService.findById(eventLog.getId())).thenReturn(java.util.Optional.of(eventLog));
        mockMvc.perform(get("/events/1")
                .header("Authorization",token))
                .andDo((print()))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString(asJson(eventLog))));

    }

    @Test
    @WithMockUser
    public void shouldCreateaNewEvent() throws Exception {
        String token = createUserAndReturnToken();
        final EventLog eventLog =  eventLogMocks.create();
        when(eventLogService.save(any(EventLog.class))).thenReturn(eventLog);
        mockMvc.perform(post("/events")
                .header("Authorization",token)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJson(eventLog))
                .characterEncoding("utf-8")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(content().string(containsString(asJson(eventLog))));

    }

    @Test
    @WithMockUser
    public void shouldDeleteEvent() throws Exception {
        String token = createUserAndReturnToken();
        final EventLog eventLog = eventLogMocks.create();
        mockMvc.perform(delete("/events/1")
                .header("Authorization", token )
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }


    public String createUserAndReturnToken(){
        final User user = new User("tata", "12345", "tata@tata.com");
        return "Bearer " + TokenService.create(user.getLogin());
    }

}
