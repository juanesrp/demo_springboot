package com.jrp.service;

import com.jrp.model.entity.Client;
import com.jrp.model.entity.ClientDto;

import java.util.List;

public interface IClientService {

    List<Client> listAll();

    Client save(ClientDto client);

    Client findById(Integer id);

    void delete(Client client);

    boolean existsById(Integer id);
}
