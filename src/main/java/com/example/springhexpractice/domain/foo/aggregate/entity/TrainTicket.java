package com.example.springhexpractice.domain.foo.aggregate.entity;


import com.example.springhexpractice.domain.command.TicketCommand;
import com.example.springhexpractice.iface.rest.dto.request.CreateTicket;
import com.example.springhexpractice.config.exception.CheckErrorException;
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

    private TrainTicket(TicketCommand ticketCommand, Train train, Double price) throws CheckErrorException {
        this.ticketNo = RandomString.make(32);
        this.trainUuid = train.getUuid();
        this.fromStop = ticketCommand.getFromStop();
        this.toStop = ticketCommand.getToStop();
        this.take_date = LocalDate.parse(ticketCommand.getTakeDate());
        this.price = price;
    }

    public static TrainTicket create(TicketCommand ticketCommand, Train train, Double price) throws CheckErrorException {
        return new TrainTicket(ticketCommand, train, price);
    }

}
