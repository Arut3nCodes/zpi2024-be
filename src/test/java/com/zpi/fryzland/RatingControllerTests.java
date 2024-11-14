package com.zpi.fryzland;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zpi.fryzland.dto.RatingAverageDTO;
import com.zpi.fryzland.dto.RatingDTO;
import com.zpi.fryzland.mapper.RatingMapper;
import com.zpi.fryzland.model.EmployeeModel;
import com.zpi.fryzland.model.RatingModel;
import com.zpi.fryzland.model.VisitModel;
import com.zpi.fryzland.service.RatingService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class RatingControllerTests {

    @MockBean
    private RatingService ratingService;

    @MockBean
    private RatingMapper ratingMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetRatingByIDSuccess() throws Exception {
        int validID = 1;
        RatingModel ratingModel = new RatingModel();
        RatingDTO ratingDTO = new RatingDTO();

        Mockito.when(ratingService.getRatingById(validID)).thenReturn(Optional.of(ratingModel));
        Mockito.when(ratingMapper.toDTO(ratingModel)).thenReturn(ratingDTO);

        mockMvc.perform(get("/api/crud/rating/{ratingID}", validID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(ratingDTO)));
    }

    @Test
    public void testGetRatingByIDNotFound() throws Exception {
        int invalidID = -1;

        Mockito.when(ratingService.getRatingById(invalidID)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/crud/rating/{ratingID}", invalidID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetRatingByIDFailure() throws Exception {

        Mockito.when(ratingService.getRatingById(anyInt())).thenThrow(new RuntimeException());

        mockMvc.perform(get("/api/crud/rating/{ratingID}", anyInt())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testAddRatingToDatabaseSuccess() throws Exception {
        RatingModel ratingModel = new RatingModel();
        RatingDTO ratingDTO = new RatingDTO();

        Mockito.when(ratingService.addRating(Mockito.any(RatingModel.class))).thenReturn(ratingModel);
        Mockito.when(ratingMapper.toDTO(ratingModel)).thenReturn(ratingDTO);
        Mockito.when(ratingMapper.toModel(ratingDTO, false)).thenReturn(ratingModel);

        mockMvc.perform(post("/api/crud/rating")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ratingDTO)))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(ratingDTO)));

    }

    @Test
    public void testAddRatingToDatabaseConflict() throws Exception {
        RatingDTO ratingDTO = new RatingDTO();

        Mockito.when(ratingService.addRating(Mockito.any(RatingModel.class))).thenReturn(null);
        Mockito.when(ratingMapper.toModel(ratingDTO, false)).thenReturn(new RatingModel());

        mockMvc.perform(post("/api/crud/rating")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ratingDTO)))
                .andExpect(status().isConflict());
    }

    @Test
    public void testAddRatingToDatabaseFailure() throws Exception {
        RatingDTO ratingDTO = new RatingDTO();

        Mockito.when(ratingService.addRating(Mockito.any(RatingModel.class))).thenThrow(new RuntimeException());
        Mockito.when(ratingMapper.toModel(ratingDTO, false)).thenReturn(new RatingModel());

        mockMvc.perform(post("/api/crud/rating")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ratingDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetAllRatingsForSalonSuccess() throws Exception {
        int validID = 1;
        RatingDTO ratingDTO = new RatingDTO();
        RatingModel ratingModel = new RatingModel();

        List<RatingDTO> listRatingDTO = List.of(ratingDTO);
        List<RatingModel> listRatingModel = List.of(ratingModel);

        Mockito.when(ratingService.getAllRatingsBySalonID(validID)).thenReturn(listRatingModel);
        Mockito.when(ratingMapper.toDTO(ratingModel)).thenReturn(ratingDTO);

        mockMvc.perform(get("/api/crud/rating/allForSalon/{salonID}", validID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(listRatingDTO)));
    }

    @Test
    public void testGetAllRatingsForSalonNotFound() throws Exception {
        int invalidID = 999;
        List<RatingModel> emptyList = List.of();

        Mockito.when(ratingService.getAllRatingsBySalonID(invalidID)).thenReturn(emptyList);

        mockMvc.perform(get("/api/crud/rating/allForSalon/{salonID}", invalidID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetAllRatingsForSalonFailure() throws Exception {

        Mockito.when(ratingService.getAllRatingsBySalonID(anyInt())).thenThrow(new RuntimeException());

        mockMvc.perform(get("/api/crud/rating/allForSalon/{salonID}", anyInt())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetAllRatingsForEmployeeSuccess() throws Exception {
        int validID = 1;
        RatingDTO ratingDTO = new RatingDTO();
        RatingModel ratingModel = new RatingModel();

        List<RatingDTO> listRatingDTO = List.of(ratingDTO);
        List<RatingModel> listRatingModel = List.of(ratingModel);

        Mockito.when(ratingService.getAllRatingsByEmployeeID(validID)).thenReturn(listRatingModel);
        Mockito.when(ratingMapper.toDTO(ratingModel)).thenReturn(ratingDTO);

        mockMvc.perform(get("/api/crud/rating/allForEmployee/{employeeID}", validID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(listRatingDTO)));
    }

    @Test
    public void testGetAllRatingsForEmployeeNotFound() throws Exception {
        int invalidID = 999;
        List<RatingModel> emptyList = List.of();

        Mockito.when(ratingService.getAllRatingsByEmployeeID(invalidID)).thenReturn(emptyList);

        mockMvc.perform(get("/api/crud/rating/allForEmployee/{employeeID}", invalidID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetAllRatingsForEmployeeFailure() throws Exception {

        Mockito.when(ratingService.getAllRatingsByEmployeeID(1)).thenThrow(new RuntimeException());

        mockMvc.perform(get("/api/crud/rating/allForEmployee/{employeeID}", 1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetAverageRatingForSalonSuccess() throws Exception {
        int validID = 1;
        float anyFloat = anyFloat();
        RatingAverageDTO ratingAverageDTO = new RatingAverageDTO(anyFloat);

        Mockito.when(ratingService.calculateAverageRatingForSalonById(validID)).thenReturn(anyFloat);

        mockMvc.perform(get("/api/crud/rating/avgForSalon/{salonID}", validID)
                    .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(ratingAverageDTO)));
    }

    @Test
    public void testGetAverageRatingForSalonNotFound() throws Exception {
        int invalidID = 999;

        Mockito.when(ratingService.calculateAverageRatingForSalonById(invalidID)).thenThrow(new NoSuchElementException());

        mockMvc.perform(get("/api/crud/rating/avgForSalon/{salonID}", invalidID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetAverageRatingForSalonFailure() throws Exception {

        Mockito.when(ratingService.calculateAverageRatingForSalonById(anyInt())).thenThrow(new RuntimeException());

        mockMvc.perform(get("/api/crud/rating/avgForSalon/{salonID}", anyInt())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetAverageRatingForEmployeeSuccess() throws Exception {
        int validID = 1;
        float anyFloat = anyFloat();
        RatingAverageDTO ratingAverageDTO = new RatingAverageDTO(anyFloat);

        Mockito.when(ratingService.calculateAverageRatingForEmployeeById(validID)).thenReturn(anyFloat);

        mockMvc.perform(get("/api/crud/rating/avgForEmployee/{employeeID}", validID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(ratingAverageDTO)));
    }

    @Test
    public void testGetAverageRatingForEmployeeNotFound() throws Exception {
        int invalidID = 999;

        Mockito.when(ratingService.calculateAverageRatingForEmployeeById(invalidID)).thenThrow(new NoSuchElementException());

        mockMvc.perform(get("/api/crud/rating/avgForEmployee/{employeeID}", invalidID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testGetAverageRatingForEmployeeFailure() throws Exception {

        Mockito.when(ratingService.calculateAverageRatingForEmployeeById(anyInt())).thenThrow(new RuntimeException());

        mockMvc.perform(get("/api/crud/rating/avgForEmployee/{employeeID}", anyInt())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}
