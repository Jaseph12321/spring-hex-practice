package com.example.springhexpractice.entity;


import com.example.springhexpractice.controller.dto.request.CreateStops;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.utility.RandomString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalTime;

@Getter
@Setter
@Table(name="train_stop")
@Entity
@NoArgsConstructor
@AllArgsConstructor
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

    public TrainStop(CreateStops stops, int seq, Train train){
        this.uuid = RandomString.make(32);
        this.trainUuid = train.getUuid();
        this.seq = seq;
        this.name = stops.getStop_name();
        this.time = stops.getStop_time();
        this.deleteFlag = "N";

    }
}
