package com.example.springhexpractice.entity;

import com.example.springhexpractice.controller.dto.request.CreateTrain;
import com.example.springhexpractice.methods.NameAndKind;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.utility.RandomString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Table(name = "train")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Train {


    NameAndKind nameAndKind;
    @Id
    @Column(name = "uuid")
    private String uuid;

    @Column(name = "train_no")
    private int trainNo;

    @Column(name = "train_kind")
    private String trainKind;

    public Train(CreateTrain createTrain) {
        this.uuid = RandomString.make(32);
        this.trainNo = createTrain.getTrainNo();
        this.trainKind = nameAndKind.getKind(createTrain.getTrainKind());
    }
}