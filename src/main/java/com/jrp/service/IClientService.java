package com.jrp.service;

import com.jrp.model.entity.Client;
import com.jrp.model.entity.ClientDto;

public interface IClientService {

    Client save(ClientDto client);

    Client findById(Integer id);

    void delete(Client client);

    boolean existsById(Integer id);
}
