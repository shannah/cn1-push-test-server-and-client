package ca.weblite.pushtest.controllers;

import ca.weblite.pushtest.collections.Devices;
import ca.weblite.pushtest.services.PushService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.io.IOException;

@RestController
@RequestMapping("/push")
public class PushController {

    private Logger logger = LoggerFactory.getLogger(PushController.class);
    private final PushService pushService;
    private static final String DEVICE_ID_IPAD = "cn1-ios-98d2aa4518f11fec846f72f21b59f3620dfcedd397a5cec61d25cac8863070ea";
    private static final String DEVICE_ID_IPHONEX = "cn1-ios-63b7c509ae05ec6e2f3276941471510c47a13fa4bf1cf11b003915c29e3ba344";

    public PushController(PushService pushService) {
        this.pushService = pushService;
    }

    @PostMapping("/hello")
    public ResponseEntity hello() {
        try {
            pushService.sendPush(1, "Hello", Devices.withIds(DEVICE_ID_IPAD));
            return new ResponseEntity(HttpStatus.OK);
        } catch (IOException ex) {
            logger.error("Failed to send push", ex);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/hello3")
    public ResponseEntity hello3() {
        try {
            pushService.sendPush(3, "Hello visible;Hello Hidden", Devices.withIds(DEVICE_ID_IPAD, DEVICE_ID_IPHONEX));
            return new ResponseEntity(HttpStatus.OK);
        } catch (IOException ex) {
            logger.error("Failed to send push", ex);
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
