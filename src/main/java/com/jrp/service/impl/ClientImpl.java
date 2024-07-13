package com.jrp.service.impl;

import com.jrp.model.dao.ClientDao;
import com.jrp.model.entity.Client;
import com.jrp.model.entity.ClientDto;
import com.jrp.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientImpl implements IClientService {

    @Autowired
    private ClientDao clientDao;


    @Transactional
    @Override
    public Client save(ClientDto clientDto) {
        Client client= Client.builder()
                .idClient(clientDto.getIdClient())
                .name(clientDto.getName())
                .lastName(clientDto.getLastName())
                .email(clientDto.getEmail())
                .registerDate(clientDto.getRegisterDate())
                .build();
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

    @Override
    public boolean existsById(Integer id) {
        return clientDao.existsById(id);
    }
}
