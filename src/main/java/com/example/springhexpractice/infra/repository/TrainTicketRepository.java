package com.example.springhexpractice.infra.repository;

import com.example.springhexpractice.domain.foo.aggregate.entity.TrainTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TrainTicketRepository extends JpaRepository<TrainTicket, Integer> {


}
