package com.edu.controllers;

import com.edu.domain.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @RequestMapping(method = RequestMethod.GET) //ambas maneras son correctas de trabajar
    // @GetMapping //("/clientes")
    public ResponseEntity <List<Customer>> getCustomers(){

        return ResponseEntity.ok(customers);
    }

    // Enrutamiento de URL
    @RequestMapping(value = "/{username}", method = RequestMethod.GET)
    // @GetMapping({"/{username}"})
    public ResponseEntity<?> getCliente(@PathVariable String username){
        for(Customer c : customers) {
            if (c.getUsername().equalsIgnoreCase(username)){

                return ResponseEntity.ok(c);
            }
        }
        //Colocamos Not_Found == error 404 y mandamos un mensaje de error
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado con username: " + username);
    }

    //Metodo Post para crear un nuevo Cliente
    @RequestMapping(method = RequestMethod.POST)
    // @PostMapping
    public ResponseEntity<?> postCliente(@RequestBody Customer customer){
        customers.add(customer);

        //Colocamos CREATED == 201
        return ResponseEntity.status(HttpStatus.CREATED).body("Cliente creado Exitosamente: " + customer.getUsername());
    }

    //Metodo Put para Actualizar/Modificar un cliente
    @RequestMapping(method = RequestMethod.PUT)
    // @PutMapping
    public ResponseEntity<?> putCliente(@RequestBody Customer customer){
        for(Customer c : customers){
            if (c.getID() == customer.getID()){
                c.setName(customer.getName());
                c.setUsername(customer.getUsername());
                c.setPassword(customer.getPassword());

                // Colocamos ok == 200
                return ResponseEntity.ok("Cliente modificado satisfactoriamente: " + customer.getID());
            }
        }
        // colocamos NOT_FOUND == 404
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado: " + customer.getID());
    }

    //Metodo Delete para Eliminar un cliente
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    // @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCliente(@PathVariable int id){
        for (Customer c : customers){
            if(c.getID() == id){
                customers.remove(c);

                // Codigo HTTP NO_CONTENT == 204 (NO REGRESA NINGUN MENSAJE)
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Cliente Eliminado exitosamente con el ID: " + c.getID());
            }
        }
        // colocamos NOT_FOUND == 404
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no Encontrado con el ID: " + id);
    }

    // Metodo Patch se usa para actualizar parcialmente un recurso
    // no todo el objeto a diferencia del put
    @RequestMapping(method = RequestMethod.PATCH)
    // @PatchMapping
    public ResponseEntity<?> patchCliente(@RequestBody Customer customer){
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

                // Colocamos ok == 200
                return ResponseEntity.ok("Cliente modificado parcialmente con el ID: " + customer.getID());
            }
        }
        // Colocamos NOT_FOUND == 404
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado con el ID: " + customer.getID());
    }


}// fin del Controlador