package com.liuzemin.server.framework.fdfs.init;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class FileExtInit implements InitializingBean {

    private static final Logger log = LoggerFactory.getLogger(FileExtInit.class);

    @Value("${file.image.ext}")
    private String imageExt;

    @Value("${file.file.ext}")
    private String fileExt;

    private List<String> imageExtList;

    private List<String> fileExtList;

    @Override
    public void afterPropertiesSet() throws Exception {

        if(!StringUtils.isEmpty(imageExt)){
            imageExtList = new ArrayList<>();
            String[] tempList = imageExt.split(",");
            for(String s : tempList){
                imageExtList.add(s.trim());
            }
        }else{
            imageExtList = new ArrayList<>();
        }

        if(!StringUtils.isEmpty(fileExt)){
            fileExtList = new ArrayList<>();
            String[] tempList = fileExt.split(",");
            for(String s : tempList){
                fileExtList.add(s.trim());
            }
        }else{
            fileExtList = new ArrayList<>();
        }
    }

    public List<String> getImageExtList() {

        return imageExtList;
    }

    public List<String> getFileExtList() {
        return fileExtList;
    }
}
