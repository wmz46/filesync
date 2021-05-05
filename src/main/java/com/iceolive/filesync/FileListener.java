package com.iceolive.filesync;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;

/**
 * 文件变化监听器
 * <p>
 * 在Apache的Commons-IO中有关于文件的监控功能的代码. 文件监控的原理如下：
 * 由文件监控类FileAlterationMonitor中的线程不停的扫描文件观察器FileAlterationObserver，
 * 如果有文件的变化，则根据相关的文件比较器，判断文件时新增，还是删除，还是更改。（默认为1000毫秒执行一次扫描）
 *
 * @author wangmianzhe
 */
@Slf4j
public class FileListener extends FileAlterationListenerAdaptor {
    private File directory;
    private FtpClientUtil f;
    private FtpConfig ftpConfig;

    public FileListener(File directory, FtpConfig ftpConfig) {
        this.directory = directory;
        this.f = new FtpClientUtil(ftpConfig.getServer(), ftpConfig.getPort(), ftpConfig.getUserName(), ftpConfig.getUserPassword());

        this.ftpConfig = ftpConfig;

    }

    /**
     * 文件创建执行
     */
    @Override
    public void onFileCreate(File file) {
        log.info("[新建文件]:" + file.getAbsolutePath());
        try {
            if (f.open()) {
                String ftpDirectory = file.getAbsolutePath().substring(directory.getAbsolutePath().length());
                ftpDirectory = ftpConfig.getPath() + ftpDirectory.substring(0, ftpDirectory.lastIndexOf(File.separator));
                f.put(file.getAbsolutePath(), file.getName(), ftpDirectory);
                log.info("上传ftp文件成功:"+ftpDirectory+file.getName());
                f.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件创建修改
     */
    @Override
    public void onFileChange(File file) {
        log.info("[修改文件]:" + file.getAbsolutePath());
        try {
            if (f.open()) {
                String ftpDirectory = file.getAbsolutePath().substring(directory.getAbsolutePath().length());
                ftpDirectory = ftpConfig.getPath() + ftpDirectory.substring(0, ftpDirectory.lastIndexOf(File.separator));
                f.put(file.getAbsolutePath(), file.getName(), ftpDirectory);
                log.info("上传ftp文件成功:"+ftpDirectory+file.getName());
                f.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件删除
     */
    @Override
    public void onFileDelete(File file) {
        log.info("[删除文件]:" + file.getAbsolutePath());
        if(ftpConfig.getCanDelete()) {
            try {
                if (f.open()) {
                    String ftpDirAndFileName = file.getAbsolutePath().substring(directory.getAbsolutePath().length());
                    ftpDirAndFileName = ftpConfig.getPath() + ftpDirAndFileName;
                    f.deleteFile(ftpDirAndFileName);
                    log.info("删除ftp文件成功:" + ftpDirAndFileName);
                    f.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 目录创建
     */
    @Override
    public void onDirectoryCreate(File directory) {
        log.info("[新建目录]:" + directory.getAbsolutePath());
    }

    /**
     * 目录修改
     */
    @Override
    public void onDirectoryChange(File directory) {
        log.info("[修改目录]:" + directory.getAbsolutePath());
    }

    /**
     * 目录删除
     */
    @Override
    public void onDirectoryDelete(File directory) {
        log.info("[删除目录]:" + directory.getAbsolutePath());
    }

    @Override
    public void onStart(FileAlterationObserver observer) {
        // TODO Auto-generated method stub
        super.onStart(observer);
    }

    @Override
    public void onStop(FileAlterationObserver observer) {
        // TODO Auto-generated method stub
        super.onStop(observer);
    }

}