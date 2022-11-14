package com.example.springhexpractice;

import com.example.springhexpractice.controller.dto.response.StopsResponse;
import com.example.springhexpractice.repository.TrainRepository;
import com.example.springhexpractice.repository.TrainStopRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@SpringBootTest
class SpringHexPracticeApplicationTests {


    @Autowired
    TrainRepository trainRepository;
    @Autowired
    TrainStopRepository trainStopRepository;

    @Test
    void contextLoads() {

    }


//    @Test
//    void test3() {
//        List<StopsResponse> r = this.trainStopRepository.findNameAndTime("20470FDD9ABA4F76949B15504CB85904");
//
//        for (StopsResponse s : r) {
//            System.out.println(s.getStop_name());
//            System.out.println(s.getStop_time());
//        }
//    }

    @Test
    void test4() {
        List<Map<String, Object>> map = this.trainRepository.findByTrainNoAndStopAndTime(1002);

        for (Map<String, Object> m : map) {
            System.out.println(m.get("NAME"));
            System.out.println(m.get("TIME"));
        }

    }

}
