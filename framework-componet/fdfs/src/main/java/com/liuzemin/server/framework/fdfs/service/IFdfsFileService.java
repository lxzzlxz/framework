package com.liuzemin.server.framework.fdfs.service;

import com.liuzemin.server.framework.fdfs.model.FileModel;
import com.liuzemin.server.framework.model.model.UploadFile;

import java.util.List;
import java.util.Map;

public interface IFdfsFileService {

    /**
     * 上传
     * @param file
     * @return
     */
    Map<String, Object> upload(UploadFile file);

    /**
     * 下载
     * @param remoteFile
     * @param groupName
     * @return
     */
    byte[] download(String groupName, String remoteFile);

    List<FileModel> findList(FileModel fileModel);
}
