package com.jrp.controller;

import com.jrp.model.entity.Client;
import com.jrp.model.entity.ClientDto;
import com.jrp.model.payload.ResponseMessage;
import com.jrp.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class ClientController   {

    @Autowired
    private IClientService clientService;

    @GetMapping("clients")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> showAll(){
        List<Client> getClients= clientService.listAll();

        if(getClients.isEmpty()){
            return new ResponseEntity<>(
                    ResponseMessage.builder()
                            .message("No records found")
                            .object(null)
                            .build(),
                    HttpStatus.OK);
        }

        return new ResponseEntity<>(
                ResponseMessage.builder()
                        .message("")
                        .object(getClients)
                        .build(),
                HttpStatus.OK);
    }


    @PostMapping("client")
    public ResponseEntity<?> create(@RequestBody ClientDto clientDto){

        try{
            Client clientSave= clientService.save(clientDto);
            return new ResponseEntity<>(ResponseMessage.builder()
                    .message("Client saved successfully.")
                    .object(ClientDto.builder()
                            .idClient(clientSave.getIdClient())
                            .name(clientSave.getName())
                            .lastName(clientSave.getLastName())
                            .email(clientSave.getEmail())
                            .registerDate(clientSave.getRegisterDate())
                            .build())
                    .build()
                    , HttpStatus.CREATED);


        }catch (DataAccessException exDt){
            return new ResponseEntity<>(
                    ResponseMessage.builder()
                            .message(exDt.getMessage())
                            .object(null)
                            .build(),
                    HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @PutMapping("client/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> update(@RequestBody ClientDto clientDto, @PathVariable Integer id){

        try {
            if (clientService.existsById(id)){
                clientDto.setIdClient(id);
            Client clientUpdate = clientService.save(clientDto);

            return new ResponseEntity<>(ResponseMessage.builder()
                    .message("Save correctly")
                    .object(ClientDto.builder()
                            .idClient(clientUpdate.getIdClient())
                            .name(clientUpdate.getName())
                            .lastName(clientUpdate.getLastName())
                            .email(clientUpdate.getEmail())
                            .registerDate(clientUpdate.getRegisterDate())
                            .build())
                    .build()
                    , HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>(
                        ResponseMessage.builder()
                                .message("The record was not found")
                                .object(null)
                                .build(),
                        HttpStatus.NOT_FOUND);
            }
        }catch (DataAccessException exDt){
            return new ResponseEntity<>(
                    ResponseMessage.builder()
                            .message(exDt.getMessage())
                            .object(null)
                            .build(),
                    HttpStatus.METHOD_NOT_ALLOWED);
        }

    }

    @DeleteMapping("client/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id){
        Map<String, Object> response = new HashMap<>();
        try{
            Client clientDelete= clientService.findById(id);
            clientService.delete(clientDelete);
            return new ResponseEntity<>(clientDelete, HttpStatus.NO_CONTENT);
        }catch (DataAccessException exDt){
            response.put("message", exDt.getMessage());
            response.put("client",null);
            return new ResponseEntity<>(
                    ResponseMessage.builder()
                            .message(exDt.getMessage())
                            .object(null)
                            .build(),
                    HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @GetMapping("client/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> showById(@PathVariable Integer id){
        Client client= clientService.findById(id);

        if(client == null){
            return new ResponseEntity<>(
                    ResponseMessage.builder()
                            .message("The record was not found.")
                            .object(null)
                            .build(),
                    HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(
                ResponseMessage.builder()
                        .message("")
                        .object(ClientDto.builder()
                                .idClient(client.getIdClient())
                                .name(client.getName())
                                .lastName(client.getLastName())
                                .email(client.getEmail())
                                .registerDate(client.getRegisterDate())
                                .build())
                        .build(),
                HttpStatus.OK);
    }
}
