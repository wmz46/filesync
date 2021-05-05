package com.iceolive.filesync;

import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.HiddenFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.TimeUnit;

/**
 * @author wangmianzhe
 */
@SpringBootApplication
public class Application implements CommandLineRunner {
    @Value("${filesync.rootDir}")
    private String rootDir;

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Application.class);
        application.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        register();
    }

    private void register() throws Exception {
        // 轮询间隔 1 秒
        long interval = TimeUnit.SECONDS.toMillis(1);
        //不使用过滤器
        FileAlterationObserver observer = new FileAlterationObserver(new File(rootDir));
        observer.addListener(new FileListener(new File(rootDir)));
        //创建文件变化监听器
        FileAlterationMonitor monitor = new FileAlterationMonitor(interval, observer);
        // 开始监控
        monitor.start();
    }
}
