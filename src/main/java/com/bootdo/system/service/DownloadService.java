package com.bootdo.system.service;

import java.io.File;

public interface DownloadService {

    /**
     * 根据简历id下载word
     * @param resumeId :简历id
     * @return
     */
    public File downloadResume(String resumeId);
}
