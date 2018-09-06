package org.gt.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource(value = "classpath:config/api.properties",encoding = "utf-8")
public class ApiProperties {
    @Value("${org.gt.api.title}")
    private String title;
    @Value("${org.gt.api.description}")
    private String description;
    @Value("${org.gt.api.version}")
    private String version;
    @Value("${org.gt.api.author}")
    private String author;
    @Value("${org.gt.api.url}")
    private String url;
    @Value("${org.gt.api.license}")
    private String license;
    @Value("${org.gt.api.licenseUrl}")
    private String licenseUrl;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getVersion() {
        return version;
    }

    public String getAuthor() {
        return author;
    }

    public String getUrl() {
        return url;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLicense() {
        return license;
    }

    public String getLicenseUrl() {
        return licenseUrl;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public void setLicenseUrl(String licenseUrl) {
        this.licenseUrl = licenseUrl;
    }
}
