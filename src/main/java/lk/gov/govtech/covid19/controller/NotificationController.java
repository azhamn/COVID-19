package lk.gov.govtech.covid19.controller;

import lk.gov.govtech.covid19.dto.AlertNotificationRequest;
import lk.gov.govtech.covid19.dto.CaseNotificationRequest;
import lk.gov.govtech.covid19.service.NotificationService;
import lk.gov.govtech.covid19.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(value = Constants.NOTIFICATION_API_CONTEXT)
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @PostMapping(path = "/alert/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity addNewAlert(@RequestBody AlertNotificationRequest request){
        log.info("New alert added with title {}", request.getTitle().getEnglish());
        notificationService.addAlertNotificaiton(request);

        return ResponseEntity.accepted().build();
    }

    @PutMapping(path = "/alert/{alertId}", consumes = "application/json")
    public ResponseEntity addNewAlert(@PathVariable("alertId") String alertId, @RequestBody AlertNotificationRequest request){
        boolean success = notificationService.updateAlertNotification(alertId, request);
        if (success) {
            log.info("Update alert with id:{} title:{}", alertId, request.getTitle().getEnglish());
            return ResponseEntity.accepted().build();
        } else {
            log.info("Update alert not found id:{} title:{}", alertId, request.getTitle().getEnglish());
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping(path = "/case/add", consumes = "application/json", produces = "application/json")
    public ResponseEntity addNewCase(@RequestBody CaseNotificationRequest request){
        log.info("New case added with case number {}",request.getCaseNumber());
        notificationService.addCaseNotification(request);

        return ResponseEntity.accepted().build();
    }


}
