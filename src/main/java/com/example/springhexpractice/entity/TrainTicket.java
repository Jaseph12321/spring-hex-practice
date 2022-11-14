package com.example.springhexpractice.entity;


import com.example.springhexpractice.controller.dto.request.CreateTicket;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.utility.RandomString;

import javax.persistence.*;
import java.time.LocalDate;


@Getter
@Setter
@Table(name="train_ticket")
@Entity
@NoArgsConstructor
public class TrainTicket {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "ticket_no")
    private String ticketNo;

    @Column(name = "train_uuid")
    private String trainUuid;

    @Column(name = "from_stop")
    private String fromStop;

    @Column(name = "to_stop")
    private String toStop;

    @Column(name = "take_date")
    private LocalDate take_date;

    @Column(name = "price")
    private Double price;

    public TrainTicket(CreateTicket createTicket, Train train, Double price){
        this.ticketNo = RandomString.make(32);
        this.trainUuid = train.getUuid();
        this.fromStop = createTicket.getFromStop();
        this.toStop = createTicket.getToStop();
        this.take_date = LocalDate.parse(createTicket.getTakeDate());
        this.price = price;
    }
}
