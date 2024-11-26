//package com.zpi.fryzland;
//
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.zpi.fryzland.controller.SalonController;
//import com.zpi.fryzland.dto.LoginDTO;
//import com.zpi.fryzland.dto.SalonDTO;
//import com.zpi.fryzland.mapper.SalonMapper;
//import com.zpi.fryzland.model.SalonModel;
//import com.zpi.fryzland.service.SalonService;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.MethodOrderer;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestMethodOrder;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.anyInt;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class SalonControllerTests {
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private SalonService salonService;
//
//    @MockBean
//    private SalonMapper salonMapper;
//
//    @Disabled
//    @Test
//    public void testGetSalonByIdWithEmployeeLogin() throws Exception {
//
//        LoginDTO loginDTO = new LoginDTO("john.doe@example.com", "aSecurePasswordHashHere");
//
//        MvcResult loginResult = mockMvc.perform(post("/api/auth/employee/login")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(loginDTO)))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        String responseContent = loginResult.getResponse().getContentAsString();
//        JsonNode jsonNode = objectMapper.readTree(responseContent);
//        String token = jsonNode.get("tokenValue").asText();
//
//        SalonModel testSalonModel = new SalonModel(1, "Salon ABC", "+48 123456789", "Warszawa", "Marszałkowska", "10", "00-001", 32.0, 32.0);
//        SalonDTO testSalonDTO = new SalonDTO(1, "Salon ABC", "+48 123456789", "Warszawa", "Marszałkowska", "10", "00-001", 32.0, 32.0);
//
//        Mockito.when(salonService.getSalonById(anyInt())).thenReturn(Optional.of(testSalonModel));
//        Mockito.when(salonMapper.toDTO(testSalonModel)).thenReturn(testSalonDTO);
//
//        mockMvc.perform(get("/api/crud/salons/1")
//                        .header("Authorization", "Bearer " + token)
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().json("{\"salonID\":1,\"salonName\":\"Salon ABC\",\"salonDialNumber\":\"+48 123456789\",\"salonCity\":\"Warszawa\",\"salonStreet\":\"Marszałkowska\",\"salonBuildingNumber\":\"10\",\"salonPostalCode\":\"00-001\"}"));
//
//    }
//
//
//    @Test
//    public void testGetSalonById() throws Exception {
//
//        SalonModel testSalonModel = new SalonModel(1, "Salon ABC", "+48 123456789", "Warszawa", "Marszałkowska", "10", "00-001", 32.0, 32.0);
//        SalonDTO testSalonDTO = new SalonDTO(1, "Salon ABC", "+48 123456789", "Warszawa", "Marszałkowska", "10", "00-001", 32.0, 32.0);
//
//        Mockito.when(salonService.getSalonById(anyInt())).thenReturn(Optional.of(testSalonModel));
//        Mockito.when(salonMapper.toDTO(testSalonModel)).thenReturn(testSalonDTO);
//
//        mockMvc.perform(get("/api/crud/salons/1")
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isOk())
//                .andExpect(content().json("{\"salonID\":1,\"salonName\":\"Salon ABC\",\"salonDialNumber\":\"+48 123456789\",\"salonCity\":\"Warszawa\",\"salonStreet\":\"Marszałkowska\",\"salonBuildingNumber\":\"10\",\"salonPostalCode\":\"00-001\"}"));
//
//    }
//}
