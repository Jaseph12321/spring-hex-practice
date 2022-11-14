package com.example.springhexpractice.repository;

import com.example.springhexpractice.controller.dto.response.StopsResponse;
import com.example.springhexpractice.entity.TrainStop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;
import java.util.Map;


@Repository
public interface TrainStopRepository extends JpaRepository<TrainStop, Integer> {


//    @Query("select new com.example.springhexpractice.controller.dto.response.StopsResponse(e.name,e.time) from TrainStop e where e.trainUuid=?1 ")
//    List<StopsResponse> findNameAndTime(String trainUuid);


    @Query(value = "select train_uuid from train_stop where name=?1 order by time asc", nativeQuery = true)
    List<String> findUuidByStop(String stop);

    @Query(value = "select train_no,train_kind from train inner join train_stop on train.uuid = train_stop.train_uuid and  train_stop.name =?1",nativeQuery = true)
    List<Map<String,Object>> findTrainNoAndTrainKind(String stop);

    TrainStop findByTrainUuidAndName(String uuid,String stopName);
}

