package com.example.springhexpractice.infra.repository;

import com.example.springhexpractice.domain.foo.aggregate.entity.Train;
import com.example.springhexpractice.domain.foo.aggregate.entity.TrainStop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TrainRepository extends JpaRepository<Train, Integer> {


//    @Query(value = "SELECT train_kind, train_no FROM TRAIN INNER JOIN TRAIN_STOP ON TRAIN.UUID=TRAIN_STOP.TRAIN_UUID",nativeQuery = true)
//    List<Map<String,Object>> findTrainKindAndNo();

    //問題二調整
    @Query(value = "select train_no,train_kind,name,time from train inner join train_stop on train.uuid = train_stop.train_uuid and  train.train_no =?1", nativeQuery = true)
    List<Map<String, Object>> findByTrainNoAndStopAndTime(int trainNO);

    Train findByTrainNo(int trainNo);



    @Query(value = "select train_no,train_kind from train inner join train_stop on train.uuid = train_stop.train_uuid and  train_stop.name =?1", nativeQuery = true)
    List<Map<String, Object>> findTrainNoAndTrainKind(String stop);


//    @Query(value = "select train_uuid from train_stop where name=?1 order by time asc", nativeQuery = true)
//    List<String> findUuidByStop(String stop);
//    Train findByUuid(String uuid);
}
