package ca.weblite.pushtest.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


@ConfigurationProperties(prefix = "push")
@Component
public class PushConfig {
    private String pushServerUrl;
    private String iosProductionPushCertificate = "";
    private String iosProductionPushCertificatePassword = "";
    private String iosDevelopmentPushCertificate = "";
    private String iosDevelopmentPushCertificatePassword = "";

    private String wnsSid = "";

    private String wnsClientSecret = "";

    private String gcmServerAPIKey = "";

    private String pushToken = "";

    private Mode mode = Mode.Development;

    public String getPushServerUrl() {
        return pushServerUrl;
    }

    public void setPushServerUrl(String pushServerUrl) {
        this.pushServerUrl = pushServerUrl;
    }

    public String getIosProductionPushCertificate() {
        return iosProductionPushCertificate;
    }

    public void setIosProductionPushCertificate(String iosProductionPushCertificate) {
        this.iosProductionPushCertificate = iosProductionPushCertificate;
    }

    public String getIosProductionPushCertificatePassword() {
        return iosProductionPushCertificatePassword;
    }

    public void setIosProductionPushCertificatePassword(String iosProductionPushCertificatePassword) {
        this.iosProductionPushCertificatePassword = iosProductionPushCertificatePassword;
    }

    public String getIosDevelopmentPushCertificate() {
        return iosDevelopmentPushCertificate;
    }

    public void setIosDevelopmentPushCertificate(String iosDevelopmentPushCertificate) {
        this.iosDevelopmentPushCertificate = iosDevelopmentPushCertificate;
    }

    public String getIosDevelopmentPushCertificatePassword() {
        return iosDevelopmentPushCertificatePassword;
    }

    public void setIosDevelopmentPushCertificatePassword(String iosDevelopmentPushCertificatePassword) {
        this.iosDevelopmentPushCertificatePassword = iosDevelopmentPushCertificatePassword;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    public String getPushToken() {
        return pushToken;
    }

    public void setPushToken(String pushToken) {
        this.pushToken = pushToken;
    }

    public String getGcmServerAPIKey() {
        return gcmServerAPIKey;
    }

    public void setGcmServerAPIKey(String gcmServerAPIKey) {
        this.gcmServerAPIKey = gcmServerAPIKey;
    }

    public String getWnsSid() {
        return wnsSid;
    }

    public void setWnsSid(String wnsSid) {
        this.wnsSid = wnsSid;
    }

    public String getWnsClientSecret() {
        return wnsClientSecret;
    }

    public void setWnsClientSecret(String wnsClientSecret) {
        this.wnsClientSecret = wnsClientSecret;
    }

    public enum Mode {
        Development,
        Production
    }
}
