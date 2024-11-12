package com.zpi.fryzland;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.zpi.fryzland.dto.SaveVisitDTO;
import com.zpi.fryzland.dto.TimeSlotDTO;
import com.zpi.fryzland.dto.employeeDisplay.SalonServiceIdsDTO;
import com.zpi.fryzland.dto.serviceDisplay.CategoryWithServicesDTO;
import com.zpi.fryzland.model.*;
import com.zpi.fryzland.service.VisitAppointmentService;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class VisitAppointmentControllerTests {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VisitAppointmentService visitAppointmentService;


    @Test
    public void testGetAllServicesInCategorySuccess() throws Exception{
        int validID = 1;
        CategoryWithServicesDTO mockCategoryWithServicesDTO = new CategoryWithServicesDTO();

        Mockito.when(visitAppointmentService.getAllCategoriesWithServicesInTheTimespan(validID)).thenReturn(mockCategoryWithServicesDTO);

        mockMvc.perform(get("/api/crud/appointment-making/services-and-categories/{id}", validID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


    @Test
    public void testGetAllServicesInCategoryBadRequest() throws Exception{
        int invalidID = 999;
        Mockito.when(visitAppointmentService.getAllCategoriesWithServicesInTheTimespan(invalidID)).thenThrow(new IllegalArgumentException());

        mockMvc.perform(get("/api/crud/appointment-making/services-and-categories/{id}", invalidID)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetAllEmployeesThatCanServeServiceValidValues() throws Exception{
        SalonServiceIdsDTO validDTO = new SalonServiceIdsDTO();
        validDTO.setSalonID(1);
        validDTO.setServiceIds(Arrays.asList(1,2));

        List<EmployeeModel> employees = Arrays.asList(new EmployeeModel(1, "John", "Doe", "+48 123456789", "testpassword", "testmail@test.com", LocalDate.of(1990,1,1), LocalDate.of(2024,1,1), 10f, "Warszawa", "Glowna", "1", "1", "00-001"));

        Mockito.when(visitAppointmentService.getAllEmployeesThatProvideChosenServices(1, Arrays.asList(1, 2)))
                .thenReturn(employees);
        mockMvc.perform(post("/api/crud/appointment-making/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(validDTO)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(employees)));
    }

    @Test
    public void testGetAllEmployeesThatCanServeServiceMissingFields() throws Exception{
        SalonServiceIdsDTO missingFieldsDTO = new SalonServiceIdsDTO();
        missingFieldsDTO.setSalonID(null);
        missingFieldsDTO.setServiceIds(null);

        mockMvc.perform(post("/api/crud/appointment-making/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(missingFieldsDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetAllEmployeesThatCanServeServiceEmptyServicesList() throws Exception{
        SalonServiceIdsDTO emptyServicesDTO = new SalonServiceIdsDTO();
        emptyServicesDTO.setSalonID(1);
        emptyServicesDTO.setServiceIds(Collections.emptyList());

        mockMvc.perform(post("/api/crud/appointment-making/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(emptyServicesDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testGetAllEmployeesThatCanServeServiceTooManyServices() throws Exception{
        SalonServiceIdsDTO tooManyServicesDTO = new SalonServiceIdsDTO();
        tooManyServicesDTO.setSalonID(1);
        tooManyServicesDTO.setServiceIds(Arrays.asList(1, 2, 3, 4));

        mockMvc.perform(post("/api/crud/appointment-making/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tooManyServicesDTO)))
                .andExpect(status().isBadRequest());
    }
    
    @Test
    public void testGetAllOpeningHoursForSalonValidID() throws Exception{
        int validSalonID = 1;
        SalonModel testSalonModel = new SalonModel();
        List<OpeningHoursModel> mockHoursModelList = Arrays.asList(
                new OpeningHoursModel(1, 1, Time.valueOf("10:00:00"), Time.valueOf("18:00:00"), testSalonModel),
                new OpeningHoursModel(2, 2,  Time.valueOf("10:00:00"), Time.valueOf("18:00:00"), testSalonModel)
        );

        Mockito.when(visitAppointmentService.getAllOpeningHoursForSalon(validSalonID)).thenReturn(mockHoursModelList);

        mockMvc.perform(get("/api/crud/appointment-making/opening-hours/{salonID}", validSalonID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("["
                        + "{'openingHoursID':1,'weekday':1,'openingHour':'10:00:00','closingHour':'18:00:00'},"
                        + "{'openingHoursID':2,'weekday':2,'openingHour':'10:00:00','closingHour':'18:00:00'}"
                        + "]"));

    }

    @Test
    public void testGetAllOpeningHoursForSalonInvalidID() throws Exception{
        int invalidSalonID = 999;

        List<OpeningHoursModel> mockOpeningHoursList = List.of();
        System.out.println(mockOpeningHoursList);

        Mockito.when(visitAppointmentService.getAllOpeningHoursForSalon(invalidSalonID)).thenReturn(mockOpeningHoursList);

        mockMvc.perform(get("/api/appointment-making/opening-hours/{salonID}", invalidSalonID)
                .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isBadRequest()); //todo: zmienic kod odpowiedzi
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    public void testGetAllTimeSlotsForAnEmployeeValidID() throws Exception{
        int validID = 1;
        List<TimeSlotDTO> mockTimeSlotModelList = Arrays.asList(
                new TimeSlotDTO(LocalDate.of(2024, 11, 6), LocalTime.NOON, validID),
                new TimeSlotDTO(LocalDate.of(2024, 11, 7), LocalTime.NOON, validID)
        );

        Mockito.when(visitAppointmentService.getAllTimeSlotsForEmployeeBeforeDate(validID, LocalDate.now().plusDays(14))).thenReturn(mockTimeSlotModelList);

        mockMvc.perform(get("/api/crud/appointment-making/time-slots/{employeeID}", validID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("["
                        + "{'timeSlotDate':'2024-11-06','timeSlotTime':'12:00:00'},"
                        + "{'timeSlotDate':'2024-11-07','timeSlotTime':'12:00:00'}"
                        + "]"));

    }

    @Test
    public void testGetAllTimeSLotsForAnEmployeeInvalidID() throws Exception{
        int invalidID = 999;

        Mockito.when(visitAppointmentService.getAllTimeSlotsForEmployeeBeforeDate(invalidID, LocalDate.now().plusDays(14))).thenThrow(new IllegalArgumentException());

        mockMvc.perform(get("/api/appointment-making/time-slots/{salonID}", invalidID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()); //todo: tu tez zmienic jak wyzej

    }

    @Test
    public void testGetAllAvailabilityDatesForEmployeeValidIDs() throws Exception{
        int validEmployeeID = 1;
        int validSalonID = 1;

        List<LocalDate> mockAvailabilityList = Arrays.asList(
                LocalDate.of(2024, 11, 7),
                LocalDate.of(2024, 11, 8),
                LocalDate.of(2024, 11, 9)
        );

        Mockito.when(visitAppointmentService.getAllDatesEmployeesAreAvailableOn(validEmployeeID, validSalonID)).thenReturn(mockAvailabilityList);

        mockMvc.perform(get("/api/crud/appointment-making/availability-dates/{salonID}/{employeeID}", validSalonID, validEmployeeID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json("["
                        + "'2024-11-07',"
                        + "'2024-11-08',"
                        + "'2024-11-09'"
                        + "]"));
    }

    @Test
    public void testGetAllAvailabilityDatesForEmployeeInvalidIDs() throws Exception{
        int invalidEmployeeID = 999;
        int invalidSalonID = 999;

        Mockito.when(visitAppointmentService.getAllDatesEmployeesAreAvailableOn(invalidEmployeeID, invalidSalonID)).thenThrow(new IllegalArgumentException());

        mockMvc.perform(get("/api/appointment-making/availability-dates/{salonID}/{employeeID", invalidSalonID, invalidEmployeeID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()); //todo: tu tez zmienic jak wyzej

    }

    @Test
    public void testSaveVisitInDatabaseSuccess() throws Exception{
        SaveVisitDTO validSaveVisitDTO = new SaveVisitDTO(
                1, 1, 1, Arrays.asList(1, 2),
                LocalDate.of(2024, 11, 6),
                LocalTime.of(14, 0)
        );
        VisitModel mockVisitModel = new VisitModel();

        Mockito.when(visitAppointmentService.makeAnAppointment(any(SaveVisitDTO.class))).thenReturn(mockVisitModel);

        mockMvc.perform(post("/api/crud/appointment-making/save-visit")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(validSaveVisitDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testSaveVisitInDatabaseFailure() throws Exception{
        SaveVisitDTO invalidSaveVisitDTO = new SaveVisitDTO(
                999, 999, 999, Arrays.asList(1, 2),
                LocalDate.of(2019, 11, 22),
                LocalTime.of(23, 0)
        );

        Mockito.when(visitAppointmentService.makeAnAppointment(any(SaveVisitDTO.class))).thenReturn(null);

        mockMvc.perform(post("/api/crud/appointment-making/save-visit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidSaveVisitDTO)))
                .andExpect(status().isBadRequest());
    }

}
