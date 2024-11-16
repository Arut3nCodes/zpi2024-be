package com.zpi.fryzland;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zpi.fryzland.dto.CustomerDTO;
import com.zpi.fryzland.dto.EmployeeDTO;
import com.zpi.fryzland.dto.LoginDTO;
import com.zpi.fryzland.model.CustomerModel;
import com.zpi.fryzland.model.EmployeeModel;
import com.zpi.fryzland.security.authentication.AuthService;
import com.zpi.fryzland.service.CustomerService;
import com.zpi.fryzland.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerUnitTests {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @MockBean
    private EmployeeService employeeService;

    @MockBean
    private AuthService authService;

    @Test
    public void testCustomerRegisterSuccess() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerEmail("test@test1.com");
        customerDTO.setEncryptedCustomerPassword("testpassword");
        CustomerModel customerModel = new CustomerModel();

        Mockito.when(customerService.findByEmail(Mockito.anyString())).thenReturn(Optional.empty());
        Mockito.when(customerService.addCustomer(Mockito.any(CustomerModel.class))).thenReturn(customerModel);

        mockMvc.perform(post("/api/auth/customer/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customerDTO)))
                .andExpect(status().isOk());

    }

    @Test
    public void testCustomerRegisterNullDTO() throws Exception {

        mockMvc.perform(post("/api/auth/customer/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCustomerRegisterConflict() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerEmail("existing@example.com");

        CustomerModel existingCustomer = new CustomerModel();
        Mockito.when(customerService.findByEmail(customerDTO.getCustomerEmail())).thenReturn(Optional.of(existingCustomer));

        mockMvc.perform(post("/api/auth/customer/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerDTO)))
                .andExpect(status().isConflict());
    }

    @Test
    public void testCustomerRegisterFailure() throws Exception {
        CustomerDTO customerDTO = new CustomerDTO();

        Mockito.when(customerService.findByEmail(customerDTO.getCustomerEmail())).thenThrow(new RuntimeException());

        mockMvc.perform(post("/api/auth/customer/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(customerDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testEmployeeRegisterSuccess() throws Exception {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmployeeEmail("test@test1.com");
        employeeDTO.setEncryptedEmployeePassword("testpassword");
        EmployeeModel employeeModel = new EmployeeModel();

        Mockito.when(employeeService.getByEmployeeEmail(Mockito.anyString())).thenReturn(Optional.empty());
        Mockito.when(employeeService.addEmployee(Mockito.any(EmployeeModel.class))).thenReturn(employeeModel);

        mockMvc.perform(post("/api/auth/employee/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(employeeDTO)))
                .andExpect(status().isOk());
    }

    @Test
    public void testEmployeeRegisterNullDTO() throws Exception {

        mockMvc.perform(post("/api/auth/employee/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testEmployeeRegisterConflict() throws Exception {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setEmployeeEmail("existing@example.com");

        EmployeeModel existingEmployee = new EmployeeModel();
        Mockito.when(employeeService.getByEmployeeEmail(employeeDTO.getEmployeeEmail())).thenReturn(Optional.of(existingEmployee));

        mockMvc.perform(post("/api/auth/employee/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employeeDTO)))
                .andExpect(status().isConflict());
    }

    @Test
    public void testEmployeeRegisterFailure() throws Exception {
        EmployeeDTO employeeDTO = new EmployeeDTO();

        Mockito.when(employeeService.getByEmployeeEmail(employeeDTO.getEmployeeEmail())).thenThrow(new RuntimeException());

        mockMvc.perform(post("/api/auth/employee/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(employeeDTO)))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCustomerLoginSuccess() throws Exception {
        LoginDTO loginDTO = new LoginDTO();

        Mockito.when(authService.login(loginDTO, "ROLE_CUSTOMER")).thenReturn("authenticationToken");

        mockMvc.perform(post("/api/auth/customer/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(loginDTO)))
                .andExpect(status().isOk());
    }
}
