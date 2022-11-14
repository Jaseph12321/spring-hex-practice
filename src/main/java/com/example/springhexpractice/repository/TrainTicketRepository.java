package com.example.springhexpractice.repository;

import com.example.springhexpractice.entity.TrainTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TrainTicketRepository extends JpaRepository<TrainTicket, Integer> {


}
