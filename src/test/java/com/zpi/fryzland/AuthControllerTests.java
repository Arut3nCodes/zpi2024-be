package com.zpi.fryzland;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zpi.fryzland.dto.CustomerDTO;
import com.zpi.fryzland.dto.EmployeeDTO;
import com.zpi.fryzland.dto.LoginDTO;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AuthControllerTests {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @Order(1)
    @Test
    public void testCustomerRegisterSuccess() throws Exception{
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerName("John");
        customerDTO.setCustomerSurname("Doe");
        customerDTO.setCustomerDialNumber("+48 456456456");
        customerDTO.setEncryptedCustomerPassword("testPassword");
        customerDTO.setCustomerEmail("john.doe@test1.com");
        customerDTO.setServiceCategoryID(2);

        mockMvc.perform(post("/api/auth/customer/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testCustomerRegisterCustomerAlreadyExists() throws Exception{
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerName("John");
        customerDTO.setCustomerSurname("Doe");
        customerDTO.setCustomerDialNumber("+48 456456456");
        customerDTO.setEncryptedCustomerPassword("testPassword");
        customerDTO.setCustomerEmail("john.doe@test1.com");
        customerDTO.setServiceCategoryID(2);

        mockMvc.perform(post("/api/auth/customer/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerDTO)))
                .andExpect(status().isConflict());
    }

    @Test
    public void testCustomerRegisterWrongPhoneFormat() throws Exception{
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerName("John");
        customerDTO.setCustomerSurname("Doe");
        customerDTO.setCustomerDialNumber("456456456");
        customerDTO.setEncryptedCustomerPassword("testPassword");
        customerDTO.setCustomerEmail("john.doe@test11.com");
        customerDTO.setServiceCategoryID(2);

        mockMvc.perform(post("/api/auth/customer/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCustomerLoginSuccess() throws Exception{
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail("john.doe@test1.com");
        loginDTO.setPassword("encryptedPasswordHere");

        mockMvc.perform(post("/api/auth/customer/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDTO)))
                .andExpect(status().isOk());
    }

    @Test
    public void testCustomerLoginBadCredentials() throws Exception{
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail("john.doe@test1.com");
        loginDTO.setPassword("wrongPassword");

        mockMvc.perform(post("/api/auth/customer/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDTO)))
                .andExpect(status().isBadRequest());
    }

    @Order(2)
    @Test
    public void testEmployeeRegisterSuccess() throws Exception{
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmployeeName("John");
        employeeDTO.setEmployeeSurname("Doe");
        employeeDTO.setEmployeeDialNumber("+48 444444444");
        employeeDTO.setEncryptedEmployeePassword("aSecurePasswordHashHere");
        employeeDTO.setEmployeeEmail("john.doe@test2.com");
        employeeDTO.setEmployeeBirthdayDate(LocalDate.parse("1990-05-15"));
        employeeDTO.setEmployeeEmploymentDate(LocalDate.parse("2020-01-10"));
        employeeDTO.setEmployeeMonthlyPay(3500f);
        employeeDTO.setEmployeeCity("Warsaw");
        employeeDTO.setEmployeeStreet("Main Street");
        employeeDTO.setEmployeeBuildingNumber("5A");
        employeeDTO.setEmployeeApartmentNumber("12");
        employeeDTO.setEmployeePostalCode("00-001");


        mockMvc.perform(post("/api/auth/employee/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employeeDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testEmployeeLoginSuccess() throws Exception {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail("john.doe@test2.com");
        loginDTO.setPassword("aSecurePasswordHashHere");

        mockMvc.perform(post("/api/auth/employee/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loginDTO)))
                .andExpect(status().isOk());

    }


}
