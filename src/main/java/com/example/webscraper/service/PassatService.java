package com.example.webscraper.service;

import com.example.webscraper.model.PassatEntity;
import com.example.webscraper.repository.PassatRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PassatService {

    private final PassatRepository passatRepository;

    public PassatService(PassatRepository passatRepository) {
        this.passatRepository = passatRepository;
    }

    public List<PassatEntity> find() {
        return passatRepository.findAll();
    }

    public List<PassatEntity> find(Date startDate, Date endDate) {
        return passatRepository.findAllByPublishDateBetween(startDate, endDate);
    }

    public void save(List<PassatEntity> passatEntityList) {
        passatRepository.saveAll(passatEntityList);
    }
}
