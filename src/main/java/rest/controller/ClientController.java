package rest.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rest.model.Address;
import rest.model.Client;
import rest.service.AddressService;
import rest.service.ClientService;

import java.util.List;

@RestController
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    //@CrossOrigin(origins = "")
    @PostMapping("/client")
    public ResponseEntity<?> create(@RequestBody Client client){
        clientService.create(client);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //@CrossOrigin(origins = "")
    @GetMapping("/client")
    public ResponseEntity<List<Client>>readeAll(){
        final List<Client> clientList = clientService.readAll();
        return clientList != null && !clientList.isEmpty()
                ? new ResponseEntity<>(clientList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //@CrossOrigin(origins = "")
    @GetMapping("/client/{id}")
    public ResponseEntity<Client> readId(@PathVariable(name = "id") int id){
        final Client client = clientService.readById(id);
        return client != null
                ? new ResponseEntity<>(client, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //@CrossOrigin(origins = "")
    @PutMapping("/client")
    public ResponseEntity<?> update(@RequestBody Client client){
        final  boolean update = clientService.update(client);
        return update
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    //@CrossOrigin(origins = "")
    @DeleteMapping("/client/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id){
        boolean delete = clientService.delete(id);
        return delete
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    //@CrossOrigin(origins = "")
    @PatchMapping("/client")
    public ResponseEntity<?> updatePartial(@RequestBody Client client ){
        final boolean updateField = clientService.updatePartial(client);
        return updateField
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

}
