package com.example.springhexpractice.domain.foo.aggregate.entity;


import com.example.springhexpractice.domain.command.StopsCommand;
import com.example.springhexpractice.iface.rest.dto.request.CreateStops;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;

import javax.persistence.*;
import java.time.LocalTime;

@Getter
@Setter
@Table(name="train_stop")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
public class TrainStop {


    @Id
    @Column(name = "uuid")
    private String uuid;

    @Column(name = "train_uuid")
    private String trainUuid;

    @Column(name = "seq")
    private int seq;

    @Column(name = "name")
    private String name;

    @Column(name = "time")
    private LocalTime time;

    @Column(name = "delete_flag")
    private String deleteFlag;



    public TrainStop(StopsCommand stops, int seq, String trainUuid){
        this.uuid = RandomString.make(32);
        this.trainUuid = trainUuid;
        this.seq = seq;
        this.name = stops.getStop_name();
        this.time = stops.getStop_time();
        this.deleteFlag = "N";

    }
    public static TrainStop create(StopsCommand stops, int seq, String trainUuid){
        return new TrainStop(stops, seq, trainUuid);
    }
}
