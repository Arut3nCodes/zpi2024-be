package com.zpi.fryzland.security.authentication;
import com.zpi.fryzland.model.CustomerModel;
import com.zpi.fryzland.model.EmployeeModel;
import com.zpi.fryzland.repository.CustomerRepository;
import com.zpi.fryzland.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private CustomerRepository customerRepository;
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public UserDetails loadUserByUsernameWithRole(String username, String role) throws UsernameNotFoundException, UnsupportedOperationException {
        if(role.equals("ROLE_USER_EMPLOYEE")){
            System.out.println(username);
            System.out.println(role);
            EmployeeModel employeeModel = employeeRepository.findByEmployeeEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Pracownik nie został odnaleziony w BD"));
            return new User(username, employeeModel.getEncryptedEmployeePassword(), new ArrayList<>(Collections.singleton(new SimpleGrantedAuthority(role))));
        }
        else{
            CustomerModel customerModel = customerRepository.findByCustomerEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Klient nie został odnaleziony w BD"));
            return new User(username, customerModel.getEncryptedCustomerPassword(), new ArrayList<>(Collections.singleton(new SimpleGrantedAuthority(role))));
        }
    }
}
