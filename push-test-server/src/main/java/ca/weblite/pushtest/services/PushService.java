package ca.weblite.pushtest.services;

import ca.weblite.pushtest.collections.Devices;
import ca.weblite.pushtest.config.PushConfig;
import ca.weblite.pushtest.valueobjects.Device;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

@Component
public class PushService {

    private static final Logger logger = LoggerFactory.getLogger(PushService.class);

    private final PushConfig pushConfig;

    private static final Charset charset = StandardCharsets.UTF_8;

    public PushService(PushConfig pushConfig) {
        this.pushConfig = pushConfig;
    }

    public void sendPush(int type, String message, Devices devices) throws IOException {
        HttpURLConnection connection =
                (HttpURLConnection)new URL(pushConfig.getPushServerUrl()).openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=" + charset.name());
        String cert = pushConfig.getIosDevelopmentPushCertificate();
        String pass = pushConfig.getIosDevelopmentPushCertificatePassword();
        if(pushConfig.getMode() == PushConfig.Mode.Production) {
            cert = pushConfig.getIosProductionPushCertificate();
            pass = pushConfig.getIosProductionPushCertificatePassword();
        }
        StringBuilder query = new StringBuilder("token=" + pushConfig.getPushToken());
        for (Device device : devices) {
            query.append("&device=").append(URLEncoder.encode(device.getId(), charset));
        }

        query
                .append("&type=").append(type)
                .append("&auth=").append(URLEncoder.encode(pushConfig.getGcmServerAPIKey(), charset))
                .append("&certPassword=").append(URLEncoder.encode(pass, charset))
                .append("&cert=").append(URLEncoder.encode(cert, charset))
                .append("&body=").append(URLEncoder.encode(message, charset))
                .append("&production=").append(pushConfig.getMode() == PushConfig.Mode.Production)
                .append("&sid=").append(URLEncoder.encode(pushConfig.getWnsSid(), charset))
                .append("&client_secret=").append(URLEncoder.encode(pushConfig.getWnsClientSecret(), charset));

        try (OutputStream output = connection.getOutputStream()) {
            output.write(query.toString().getBytes(charset));
        }
        int c = connection.getResponseCode();
        if (c != 200) {
            throw new IOException("Unexpected response code received from push server: "+c+". Expected 200");
        }
        StringBuilder response = new StringBuilder();
        try (InputStream input = connection.getInputStream()) {
            byte[] buf = new byte[4096];
            int len;
            while ((len = input.read(buf)) != -1) {
                response.append(new String(buf, 0, len, charset));
            }
            logger.info("Response: " + response);
        }


    }
}
