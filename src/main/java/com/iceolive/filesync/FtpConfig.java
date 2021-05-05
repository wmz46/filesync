package com.iceolive.filesync;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangmianzhe
 */
@Data
@ConfigurationProperties(prefix = "filesync.ftp")
@Configuration
public class FtpConfig {
    private String server;
    private Integer port;
    private String userName;
    private String userPassword;
    private String path;
    private Boolean canDelete;
}
