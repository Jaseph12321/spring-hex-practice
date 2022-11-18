package com.example.springhexpractice;

import com.example.springhexpractice.domain.foo.aggregate.entity.Train;
import com.example.springhexpractice.infra.repository.TrainRepository;
import com.example.springhexpractice.infra.utils.NameAndKind;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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

    @Test
    void test5() {
        Dog dog = new Dog("bob",21,"fda");
        Cat cat = new Cat();
        BeanUtils.copyProperties(dog,cat);
        System.out.println(cat.getName());
        System.out.println(cat.getAge());
    }

    @Test
    void test6() {
        System.out.println(NameAndKind.A.getAge());
        System.out.println(NameAndKind.B.getKind());
    }

    @Test
    void test7() {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(MyConfiguration.class);
        MyService service = ctx.getBean(MyService.class);
        service.log("Hi");
    }

    @Test
    void test8(){
        List<Train> train1 = this.trainRepository.findAll();
        System.out.println(train1.get(0).getTrainStops().size());
    }

}
