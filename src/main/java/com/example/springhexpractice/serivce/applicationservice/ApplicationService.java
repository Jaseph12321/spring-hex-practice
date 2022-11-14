package com.example.springhexpractice.serivce.applicationservice;

import com.example.springhexpractice.controller.dto.request.CheckStatus;
import com.example.springhexpractice.controller.dto.request.CreateStops;
import com.example.springhexpractice.controller.dto.request.CreateTicket;
import com.example.springhexpractice.controller.dto.request.CreateTrain;
import com.example.springhexpractice.entity.Train;
import com.example.springhexpractice.entity.TrainStop;
import com.example.springhexpractice.entity.errorMessage.CheckErrorMessage;
import com.example.springhexpractice.exception.CheckErrorException;
import com.example.springhexpractice.exception.DataNotFoundException;
import com.example.springhexpractice.repository.TrainRepository;
import com.example.springhexpractice.repository.TrainStopRepository;
import com.example.springhexpractice.serivce.outboundservice.OutboundService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
@Slf4j
public class ApplicationService {
    private final String[] places = {"屏東", "高雄", "臺南", "嘉義", "彰化", "台中", "苗粟", "新竹", "桃園", "樹林",
            "板橋", "萬華", "台北", "松山", "南港", "汐止", "基隆"};

    @Autowired
    TrainRepository trainRepository;
    @Autowired
    TrainStopRepository trainStopRepository;

    @Autowired
    OutboundService outboundService;


    //check starter
    public void TrainValidation(CreateTrain createTrain) throws DataNotFoundException, CheckErrorException {
        outboundService.checkTrainStatus(createTrain);
        checkIfExists(createTrain);
    }
    public void TicketValidation(CreateTicket createTicket) throws CheckErrorException {
        checkIfValid(createTicket);
    }


    // check ifs
    private void checkIfValid(CreateTicket createTicket) throws CheckErrorException {
        List<CheckErrorMessage> checkErrorMessageList = new ArrayList<>();
        List<String> errorList = new ArrayList<>();
        if (checkDateFormat(createTicket.getTakeDate())) {
            errorList.add("create ticket date format is not right");
            checkErrorMessageList.add(new CheckErrorMessage("Pattern", "日期格式不正確 yyyy-mm-dd", "takeDate"));
        }

        if (checkTrainNotExists(createTicket.getTrainNo())) {
            errorList.add("the train number is not exist");
            checkErrorMessageList.add(new CheckErrorMessage("TrainNoNotExists", "Train No does not exists"));
        }

        if (checkTicketMakeSense(createTicket)) {
            errorList.add("create ticket stop is not invalid");
            checkErrorMessageList.add(new CheckErrorMessage("TicketStopsInvalid", "Ticket From & To is invalid"));
        }


        if (checkErrorMessageList.size() > 0)
            for (String e : errorList)
                log.error(e);
        throw new CheckErrorException("VALIDATE_FAILED", checkErrorMessageList);
    }

    private void checkIfExists(CreateTrain createTrain) throws CheckErrorException {
        List<CheckErrorMessage> checkErrorMessageList = new ArrayList<>();
        List<String> errorList = new ArrayList<>();
        if (null != trainRepository.findByTrainNo(createTrain.getTrainNo())) {
            errorList.add("Train number is already exists");
            checkErrorMessageList.add(new CheckErrorMessage("TrainNoExists", "Train No is exists"));
        }
        if (checkTrainKind(createTrain.getTrainKind())) {
            errorList.add("Train kind is not right");
            checkErrorMessageList.add(new CheckErrorMessage("TrainKindInvalid", "Train Kind is invalid"));
        }
        if (checkDuplicate(createTrain)) {
            errorList.add("Train stop is not duplicate");
            checkErrorMessageList.add(new CheckErrorMessage("TrainStopDuplicate", "Train Stops is duplicate"));
        }

        System.out.println(checkErrorMessageList.size());
        if (checkErrorMessageList.size() > 0) {
            for(String e : errorList)
                log.error(e);
            throw new CheckErrorException("VALIDATED_FAILED", checkErrorMessageList);
        } else {
            if (stopTimeNotRight(createTrain.getStops()) || stopPositionNotRight(createTrain.getStops())) {
                checkErrorMessageList.add(new CheckErrorMessage("TrainStopsNotSorted", "Train Stops is not sorted"));
                throw new CheckErrorException("VALIDATION_FAILED", checkErrorMessageList);
            }
        }

    }


    //below are methods


    private boolean checkTrainKind(String kind) {
        return !Objects.equals(kind, "諾亞方舟號") && !Objects.equals(kind, "霍格華茲號");
    }

    private boolean checkDuplicate(CreateTrain createTrain) {
        Set<String> stopNameSet = new HashSet<>();
        List<CreateStops> stopList = createTrain.getStops();
        for (CreateStops n : stopList) {
            if (stopNameSet.contains(n.getStop_name()))
                return true;

            stopNameSet.add(n.getStop_name());
        }
        return false;
    }

    private boolean stopPositionNotRight(List<CreateStops> stopsList) {
        List<String> stops = new ArrayList<>();
        for (CreateStops n : stopsList) {
            System.out.println(n.getStop_name());
            stops.add(n.getStop_name());
        }


        List<Integer> stopnumbers = new ArrayList<>();
        for (String s : stops) {
            for (int j = 0; j < places.length; j++) {
                if (s.equals(places[j])) {
                    System.out.println(j + "place");
                    stopnumbers.add(j);
                    break;
                }
            }

        }
        if (stopnumbers.get(0) < stopnumbers.get(stopnumbers.size() - 1)) {
            for (int i = 0; i < stopnumbers.size(); i++) {
                if (stopnumbers.get(i) > stopnumbers.get(i + 1))
                    return true;
            }
        } else if (stopnumbers.get(0) > stopnumbers.get(stopnumbers.size() - 1)) {
            log.info("start decline");
            for (int i = 0; i < stopnumbers.size() - 1; i++) {
                if (stopnumbers.get(i) < stopnumbers.get(i + 1))
                    return true;
            }
        }
        return false;
    }

    private boolean stopTimeNotRight(List<CreateStops> stopsList) {
        for (int i = 0; i < stopsList.size() - 1; i++) {
            if (stopsList.get(i).getStop_time().isAfter(stopsList.get(i + 1).getStop_time()))
                return true;
        }
        return false;
    }

    private boolean checkDateFormat(String dateTime) {
        try {
            SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");
            Date date = d.parse(dateTime);
            return false;
        } catch (ParseException e) {
            return true;
        }
    }

    private boolean checkTrainNotExists(int trainNo) {
        return null == this.trainRepository.findByTrainNo(trainNo);
    }

    private boolean checkTicketMakeSense(CreateTicket createTicket) {
        Train train = this.trainRepository.findByTrainNo(createTicket.getTrainNo());
        TrainStop startStop = this.trainStopRepository.findByTrainUuidAndName(train.getUuid(), createTicket.getFromStop());
        TrainStop finishStop = this.trainStopRepository.findByTrainUuidAndName(train.getUuid(), createTicket.getToStop());
        return startStop.getSeq() >= finishStop.getSeq();
    }
}
