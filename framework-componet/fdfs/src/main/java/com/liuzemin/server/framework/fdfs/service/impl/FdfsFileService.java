package com.liuzemin.server.framework.fdfs.service.impl;

import com.liuzemin.server.framework.fdfs.model.FileModel;
import com.liuzemin.server.framework.fdfs.service.IFdfsFileService;
import com.liuzemin.server.framework.model.helper.ResultMapHelper;
import com.liuzemin.server.framework.model.model.UploadFile;
import org.csource.common.MyException;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.TrackerGroup;
import org.csource.fastdfs.TrackerServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Service
public class FdfsFileService implements IFdfsFileService {

    public static final Logger log = LoggerFactory.getLogger(FdfsFileService.class);

    @Autowired
    private TrackerGroup trackerGroup;

    @Value("${file.service.url}")
    private String fileServiceUrl;

    @Override
    public Map<String, Object> upload(UploadFile file) {
        Map<String, Object> map = new HashMap<>(16);
        StorageClient storageClient = null;
        TrackerServer trackerServer = null;
        try {
            trackerServer = trackerGroup.getConnection();
            storageClient = new StorageClient(trackerServer, null);

            String[] ary = storageClient.upload_file(file.getContent(), file.getExt(), null);
            String groupName = ary[0];
            String remoteName = ary[1];
            map.put("data", fileServiceUrl + groupName + "/" + remoteName + "?fileName=" + file.getFileName());
        } catch (IOException e) {
            log.error("upload file to [fastdfs] system error", e);
            return ResultMapHelper.getResultMap("2", "上传失败");
        } catch (MyException e) {
            log.error("upload file to [fastdfs] system error", e);
            return ResultMapHelper.getResultMap("2", "上传失败");
        } finally {
            if (null != trackerServer) {
                try {
                    trackerServer.close();
                } catch (IOException e) {
                    log.error("close tracker server error", e);
                }
            }
        }
        map.put("code", "0");
        map.put("msg", "ok");
        return map;
    }

    @Override
    public byte[] download(String groupName, String remoteFile) {
        StorageClient storageClient = null;
        TrackerServer trackerServer = null;
        byte[] datas = null;
        try {
            trackerServer = trackerGroup.getConnection();
            storageClient = new StorageClient(trackerServer, null);
            datas = storageClient.download_file(groupName, remoteFile);
        } catch (IOException e) {
            log.error("download file from [fastdfs] system error", e);
            return datas;
        } catch (MyException e) {
            log.error("download file from [fastdfs] system error", e);
            return datas;
        } finally {
            if (null != trackerServer) {
                try {
                    trackerServer.close();
                } catch (IOException e) {
                    log.error("close tracker server error", e);
                }
            }
        }
        return datas;
    }

    @Override
    public List<FileModel> findList(FileModel fileModel) {
        String filePath = "/home/work/fastdfs/data";
        File f = new File(filePath);
        if (!f.exists()) {
            return Collections.EMPTY_LIST;
        }
        List<FileModel> list = new ArrayList<>();
        File[] files = f.listFiles();
        for (File file : files) {
            //如果目录下面还是目录不递归
            getFile(file, list, 1, "");
        }
        return list;
    }

    private void getFile(File f, List<FileModel> list, int index, String parentPath) {
        if (f.isFile()) {
            FileModel lm = new FileModel();
            lm.setFileName(f.getName());
            lm.setType(2);
            lm.setFilePath(parentPath + "/" + f.getName());
            lm.setSize(f.length()/1024);
           // lm.setUrl("/system/log/v1/download?fileName=" + lm.getFileName() + "&filePath=" + lm.getFilePath());
            lm.setUrl(fileServiceUrl + "group1" + "/" + lm.getFilePath() + "?fileName=" + lm.getFileName());
            list.add(lm);
        } else {
            FileModel lm = new FileModel();
            lm.setFileName(f.getName());
            lm.setType(1);
            lm.setFilePath(parentPath + "/" + f.getName());
            list.add(lm);
            List<FileModel> children = new ArrayList<>();
            lm.setChildren(children);
            File[] files = f.listFiles();
            index++;
            for (File file : files) {
                //如果目录下面还是目录不递归
                if (index >= 2 && file.isDirectory()) {
                    continue;
                }
                getFile(file, children, index, lm.getFilePath());
            }
        }
    }
}
