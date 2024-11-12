package com.zpi.fryzland;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zpi.fryzland.dto.ServiceDTO;
import com.zpi.fryzland.mapper.ServiceMapper;
import com.zpi.fryzland.model.ServiceModel;
import com.zpi.fryzland.service.ServiceService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ServiceControllerTests {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServiceMapper serviceMapper;

    @MockBean
    private ServiceService serviceService;


    @Test
    public void testGetServiceByIdSuccess() throws Exception {
        int validId = 1;
        ServiceModel serviceModel = new ServiceModel();
        ServiceDTO serviceDTO = new ServiceDTO();

        Mockito.when(serviceService.getServiceById(validId)).thenReturn(Optional.of(serviceModel));
        Mockito.when(serviceMapper.toDTO(serviceModel)).thenReturn(serviceDTO);

        mockMvc.perform(get("/api/crud/service/{serviceID}", validId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(serviceDTO)));
    }

    @Test
    public void testGetServiceByIdNotFound() throws Exception {
        int invalidId = 999;

        Mockito.when(serviceService.getServiceById(invalidId)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/crud/service/{serviceID}", invalidId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetServiceIdFailure() throws Exception {

        Mockito.when(serviceService.getServiceById(anyInt())).thenThrow(new RuntimeException());

        mockMvc.perform(get("/api/crud/service/{serviceId}", anyInt())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}


