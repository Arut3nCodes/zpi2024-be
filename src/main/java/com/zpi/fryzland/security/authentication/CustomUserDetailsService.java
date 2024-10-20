package com.zpi.fryzland.security.authentication;
import com.zpi.fryzland.model.CustomerModel;
import com.zpi.fryzland.repository.CustomerRepository;
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
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private CustomerRepository customerRepository;

    //@Autowired
    //private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public UserDetails loadUserByUsernameWithRole(String username, String role) throws UsernameNotFoundException, UnsupportedOperationException {
        if(role.equals("USER_EMPLOYEE")){
            throw new UnsupportedOperationException("Unsupported operation");
        }
        else{
            CustomerModel customerModel = customerRepository.findByCustomerEmail(username)
                    .orElseThrow(() -> new UsernameNotFoundException("Użytkownik nie został odnaleziony w BD"));
            System.out.println("Happy happy happy");
            System.out.println(customerModel);
            return new User(username, customerModel.getEncryptedCustomerPassword(), new ArrayList<>(Collections.singleton(new SimpleGrantedAuthority(role))));
        }
    }
}
