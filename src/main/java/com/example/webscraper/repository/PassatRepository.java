package com.example.webscraper.repository;

import com.example.webscraper.model.PassatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface PassatRepository extends JpaRepository<PassatEntity, Integer> {

    List<PassatEntity> findAllByPublishDateBetween(Date startDate, Date endDate);
}
