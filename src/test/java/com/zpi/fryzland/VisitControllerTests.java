package com.zpi.fryzland;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zpi.fryzland.controller.VisitController;
import com.zpi.fryzland.dto.VisitDTO;
import com.zpi.fryzland.mapper.VisitMapper;
import com.zpi.fryzland.model.VisitModel;
import com.zpi.fryzland.service.VisitService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class VisitControllerTests {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VisitMapper visitMapper;

    @MockBean
    private VisitService visitService;

    @Test
    public void testGetSingleVisitSuccess() throws Exception {
        int validID = 1;
        VisitModel visitModel = new VisitModel();
        VisitDTO visitDTO = new VisitDTO();

        Mockito.when(visitService.getVisitById(validID)).thenReturn(Optional.of(visitModel));
        Mockito.when(visitMapper.toDTO(visitModel)).thenReturn(visitDTO);

        mockMvc.perform(get("/api/crud/visit/{visitId}", validID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(visitDTO)));
    }

    @Test
    public void testGetVisitNotFound() throws Exception {
        int invalidID = 999;

        Mockito.when(visitService.getVisitById(invalidID)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/crud/visit/{visitId}", invalidID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetVisitFailure() throws Exception {
        int invalidID = 999;

        Mockito.when(visitService.getVisitById(invalidID)).thenThrow(new RuntimeException());

        mockMvc.perform(get("/api/crud/visit/{visitId}", invalidID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetAllVisitsByCustomerIDSuccess() throws Exception {
        int validID = 1;
        VisitModel visitModel = new VisitModel();
        VisitDTO visitDTO = new VisitDTO();
        List<VisitModel> visitModelList = List.of(visitModel);
        List<VisitDTO> visitDTOList = List.of(visitDTO);

        Mockito.when(visitService.getAllVisitsByCustomerID(validID)).thenReturn(visitModelList);
        Mockito.when(visitMapper.toDTO(visitModel)).thenReturn(visitDTO);

        mockMvc.perform(get("/api/crud/visit/forCustomer/{customerID}", validID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(visitDTOList)));
    }

    @Test
    public void testGetAllVisitsByCustomerIDFailure() throws Exception {
        int invalidID = 1;

        Mockito.when(visitService.getAllVisitsByCustomerID(invalidID)).thenThrow(new RuntimeException());

        mockMvc.perform(get("/api/crud/visit/forCustomer/{customerID}", invalidID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetAllVisitByEmployeeIDSuccess() throws Exception {
        int validID = 1;
        VisitModel visitModel = new VisitModel();
        VisitDTO visitDTO = new VisitDTO();
        List<VisitModel> visitModelList = List.of(visitModel);
        List<VisitDTO> visitDTOList = List.of(visitDTO);

        Mockito.when(visitService.getAllVisitsByEmployeeID(validID)).thenReturn(visitModelList);
        Mockito.when(visitMapper.toDTO(visitModel)).thenReturn(visitDTO);

        mockMvc.perform(get("/api/crud/visit/forEmployee/{employeeID}", validID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(visitDTOList)));
    }

    @Test
    public void testGetAllVisitByEmployeeIDFailure() throws Exception {
        int validID = 1;

        Mockito.when(visitService.getAllVisitsByEmployeeID(validID)).thenThrow(new RuntimeException());

        mockMvc.perform(get("/api/crud/visit/forEmployee/{employeeID}", validID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
