package com.jrp.service;

import com.jrp.model.entity.Client;

public interface IClient {

    Client save(Client client);

    Client findById(Integer id);

    void delete(Client client);

}
