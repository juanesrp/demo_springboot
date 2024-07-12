package com.jrp.service.impl;

import com.jrp.model.dao.ClientDao;
import com.jrp.model.entity.Client;
import com.jrp.service.IClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientImpl implements IClient {

    @Autowired
    private ClientDao clientDao;

    @Transactional
    @Override
    public Client save(Client client) {
        return clientDao.save(client);
    }

    @Transactional(readOnly = true)
    @Override
    public Client findById(Integer id) {
        return clientDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Client client) {
    clientDao.delete(client);
    }
}
