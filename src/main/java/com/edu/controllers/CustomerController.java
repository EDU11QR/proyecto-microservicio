package com.edu.controllers;

import com.edu.domain.Customer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class CustomerController {

    // Creamos una lista de clientes
    private List<Customer> customers = new ArrayList<>(Arrays.asList(
            new Customer(123, "Edu Quispe", "edu", "123"),
            new Customer(456,"Cristhian Rojas", "cristhian", "456"),
            new Customer(789, "Rocio Ramos", "rocio", "789"),
            new Customer(159,"Yhanis Basilio", "yhanis","159")
    ));

    @GetMapping("clientes")
    public List<Customer> getCustomers(){
        return customers;
    }

    // Enrutamiento de URL
    @GetMapping({"clientes/{username}"})
    public Customer getCliente(@PathVariable String username){
        for(Customer c : customers) {
            if (c.getUsername().equalsIgnoreCase(username)){
                return  c;
            }
        }
        return null;
    }

}








































