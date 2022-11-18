package com.example.springhexpractice.domain.foo.aggregate.entity;

import com.example.springhexpractice.domain.command.TrainCommand;
import com.example.springhexpractice.iface.rest.dto.request.CreateStops;
import com.example.springhexpractice.iface.rest.dto.request.CreateTrain;
import com.example.springhexpractice.config.exception.CheckErrorException;
import com.example.springhexpractice.infra.utils.ErrorInfo;
import com.example.springhexpractice.infra.utils.NameAndKind;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Table(name = "train")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Train {
    @Id
    @Column(name = "uuid")
    private String uuid;

    @Column(name = "train_no")
    private int trainNo;

    @Column(name = "train_kind")
    private String trainKind;

//    targetEntity = TrainStop.class,
    @OneToMany(targetEntity = TrainStop.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinColumn(name = "train_uuid")
    private List<TrainStop> trainStops;



    public Train(TrainCommand trainCommand,String uuid,List<TrainStop> trainStopList) throws CheckErrorException {
        this.uuid = uuid;
        this.trainNo = trainCommand.getTrainNo();

        this.trainKind = NameAndKind.getKind(trainCommand.getTrainKind());
        this.trainStops = trainStopList;
    }


    public static Train create(TrainCommand trainCommand, String uuid, List<TrainStop> trainStopList) throws CheckErrorException {
        checkTrainKind(trainCommand.getTrainKind());
        return new Train(trainCommand,uuid,trainStopList);
    }



    private static void checkTrainKind(String kind) throws CheckErrorException {
        List<String> errorList = new ArrayList<String>();
        List<ErrorInfo> errorInfo = new ArrayList<>();
        if(!"諾亞方舟號".equals(kind) && !"霍格華茲號".equals(kind)){
            errorList.add("Train kind not right");
            errorInfo.add(ErrorInfo.TrainKindInvalid);
            throw new CheckErrorException(errorInfo);
        }

    }

}