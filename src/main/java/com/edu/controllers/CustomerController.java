package com.edu.controllers;

import com.edu.domain.Customer;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
// Eliminamos las rutas de nuestros metodos
// ya que requestMapping unifica todos
@RequestMapping("/clientes")
public class CustomerController {

    // Creamos una lista de clientes
    private List<Customer> customers = new ArrayList<>(Arrays.asList(
            new Customer(123, "Edu Quispe", "edu", "123"),
            new Customer(456,"Cristhian Rojas", "cristhian", "456"),
            new Customer(789, "Rocio Ramos", "rocio", "789"),
            new Customer(159,"Yhanis Basilio", "yhanis","159")
    ));

    //Metodo Get para listar Clientes
    @GetMapping //("/clientes")
    public List<Customer> getCustomers(){
        return customers;
    }

    // Enrutamiento de URL
    @GetMapping({"/{username}"})
    public Customer getCliente(@PathVariable String username){
        for(Customer c : customers) {
            if (c.getUsername().equalsIgnoreCase(username)){
                return  c;
            }
        }
        return null;
    }

    //Metodo Post para crear un nuevo Cliente
    @PostMapping
    public Customer postCliente(@RequestBody Customer customer){
        customers.add(customer);
        return  customer;
    }

    //Metodo Put para Actualizar/Modificar un cliente
    @PutMapping
    public  Customer putCliente(@RequestBody Customer customer){
        for(Customer c : customers){
            if (c.getID() == customer.getID()){
                c.setName(customer.getName());
                c.setUsername(customer.getUsername());
                c.setPassword(customer.getPassword());

                return c;
            }
        }
        return null;
    }

    //Metodo Delete para Eliminar un cliente
    @DeleteMapping("/{id}")
    public Customer deleteCliente(@PathVariable int id){
        for (Customer c : customers){
            if(c.getID() == id){
                customers.remove(c);

                return c;
            }
        }
        return null;
    }

    // Metodo Patch se usa para actualizar parcialmente un recurso
    // no todo el objeto a diferencia del put
    @PatchMapping
    public Customer patchCliente(@RequestBody Customer customer){
        for(Customer c : customers){
            if (c.getID() == customer.getID()){

                if (customer.getName() != null){
                    c.setName(customer.getName());
                }

                if (customer.getUsername() != null){
                    c.setUsername(customer.getUsername());
                }

                if (customer.getPassword() != null){
                    c.setPassword(customer.getPassword());
                }

                return c;
            }
        }
        return null;
    }


}// fin del Controlador