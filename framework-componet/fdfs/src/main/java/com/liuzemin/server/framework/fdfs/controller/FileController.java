package com.liuzemin.server.framework.fdfs.controller;

import com.liuzemin.server.framework.fdfs.client.SystemClient;
import com.liuzemin.server.framework.fdfs.init.FileExtInit;
import com.liuzemin.server.framework.fdfs.model.FileModel;
import com.liuzemin.server.framework.fdfs.service.IFdfsFileService;
import com.liuzemin.server.framework.fdfs.util.ImageWatermarkUtils;
import com.liuzemin.server.framework.fdfs.util.ResizeImageUtil;
import com.liuzemin.server.framework.model.helper.RHelper;
import com.liuzemin.server.framework.model.helper.ResultMapHelper;
import com.liuzemin.server.framework.model.model.R;
import com.liuzemin.server.framework.model.model.UploadFile;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

@Controller
@RequestMapping("/file/v1")
public class FileController {

    private static final Logger log = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private IFdfsFileService fdfsFileService;
    @Autowired
    private SystemClient systemClient;

    @Autowired
    private FileExtInit fileExtInit;
    final static double targetSize = 100 * 1024;

    @RequestMapping(value = "/app/upload", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> appUpload(@RequestBody UploadFile uploadFile) {
        String fileName = uploadFile.getFileName();
        if (validateUeExt(fileName)) {
            return ResultMapHelper.getResultMap("2", "文件格式错误");
        }
        return fdfsFileService.upload(uploadFile);
    }

    private boolean validateUeExt(String fileName) {
        if (StringUtils.isEmpty(fileName) || fileName.lastIndexOf('.') < 0) {
            return true;
        }
        String[] aa = {".png", ".jpg", ".jpeg", ".gif", ".bmp",
                ".flv", ".swf", ".mkv", ".avi", ".rm", ".rmvb", ".mpeg", ".mpg",
                ".ogg", ".ogv", ".mov", ".wmv", ".mp4", ".webm", ".mp3", ".wav", ".mid",
                ".rar", ".zip", ".tar", ".gz", ".7z", ".bz2", ".cab", ".iso",
                ".doc", ".docx", ".rtf", ".xls", ".xlsx", ".ppt", ".csv",
                ".pptx", ".pdf", ".txt", ".md", ".xml"};
        List<String> list = Arrays.asList(aa);
        String ext = fileName.substring(fileName.lastIndexOf('.'), fileName.length());
        ext = ext.toLowerCase();
        if (list.contains(ext)) {
            return false;
        }
        return true;
    }

    @RequestMapping(value = "/web/uploadImage", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> uploadImage(HttpServletRequest req) {
        List<MultipartFile> fileList = new ArrayList<>();
        if (req instanceof MultipartHttpServletRequest) {
            fileList = ((MultipartHttpServletRequest) req).getFiles("file");
        }
        Map<String, Object> resultMap = new HashMap<>(16);
        try {
            List<Map<String, Object>> returnList = new ArrayList<>();
            for (MultipartFile file : fileList) {
                UploadFile uploadFile = new UploadFile();
                String fileName = file.getOriginalFilename();
                if (validateExt(fileName, fileExtInit.getImageExtList())) {
                    return ResultMapHelper.getResultMap("2", "图片格式错误");
                }
                uploadFile.setFileName(fileName);

                uploadFile.setContent(file.getBytes());
                if (!StringUtils.isEmpty(file.getOriginalFilename())) {
                    int pos = file.getOriginalFilename().lastIndexOf(".");
                    if (pos > -1 && pos + 1 < file.getOriginalFilename().length()) {
                        uploadFile.setExt(file.getOriginalFilename().substring(pos + 1));
                    }
                }
                Map<String, Object> map = fdfsFileService.upload(uploadFile);
                if ("0".equals(map.get("code"))) {
                    Map f = new HashMap(16);
                    f.put("fileName", file.getOriginalFilename());
                    f.put("url", map.get("data"));
                    returnList.add(f);
                }
            }
            resultMap.put("datas", returnList);
        } catch (IOException e) {
            log.error("upload file error", e);
        }
        if (!resultMap.isEmpty()) {
            resultMap.put("code", "0");
            resultMap.put("msg", "ok");
            return resultMap;
        }
        return ResultMapHelper.getResultMap("2", "参数错误");
    }


    private boolean validateExt(String fileName, List<String> list) {
        if (StringUtils.isEmpty(fileName) || fileName.lastIndexOf('.') < 0) {
            return true;
        }

        String ext = fileName.substring(fileName.lastIndexOf('.'), fileName.length());
        ext = ext.toLowerCase();
        if (list.contains(ext)) {
            return false;
        }

        return true;
    }

    @RequestMapping(value = "/web/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> webUpload(HttpServletRequest req) {
        List<MultipartFile> fileList = new ArrayList<>();
        if (req instanceof MultipartHttpServletRequest) {
            fileList = ((MultipartHttpServletRequest) req).getFiles("file");
        }
        Map<String, Object> resultMap = new HashMap<>(16);
        try {
            List<Map<String, Object>> returnList = new ArrayList<>();
            for (MultipartFile file : fileList) {
                UploadFile uploadFile = new UploadFile();

                String fileName = file.getOriginalFilename();
                if (validateExt(fileName, fileExtInit.getFileExtList())) {
                    return ResultMapHelper.getResultMap("2", "文件格式错误");
                }
                uploadFile.setFileName(fileName);

                uploadFile.setContent(file.getBytes());
                if (!StringUtils.isEmpty(file.getOriginalFilename())) {
                    int pos = file.getOriginalFilename().lastIndexOf(".");
                    if (pos > -1 && pos + 1 < file.getOriginalFilename().length()) {
                        uploadFile.setExt(file.getOriginalFilename().substring(pos + 1));
                    }
                }
                Map<String, Object> map = fdfsFileService.upload(uploadFile);
                if ("0".equals(map.get("code"))) {
                    Map f = new HashMap(16);
                    f.put("fileName", file.getOriginalFilename());
                    f.put("url", map.get("data"));
                    returnList.add(f);
                }
            }
            resultMap.put("datas", returnList);
        } catch (IOException e) {
            log.error("upload file error", e);
        }
        if (!resultMap.isEmpty()) {
            resultMap.put("code", "0");
            resultMap.put("msg", "ok");
            return resultMap;
        }
        return ResultMapHelper.getResultMap("2", "参数错误");
    }

    @RequestMapping(value = "/download/{groupName}/**", method = RequestMethod.GET)
    public void downLoadFile(HttpServletRequest req, HttpServletResponse res, @PathVariable String groupName,
                             @RequestParam(required = false) String fileName) {
        try {
            String uri = req.getRequestURI().toString();
            int position = uri.indexOf(groupName);
            position = position + groupName.length() + 1;
            String remoteName = "";
            if (position > -1 && position < uri.length()) {
                remoteName = uri.substring(position);
            }
            res.setContentType("multipart/form-data");
            if (!StringUtils.isEmpty(fileName)) {
                res.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName, "utf-8"));
            } else {
                res.setHeader("Content-Disposition", "attachment;fileName=");
            }
            //groupName=group1,remoteName=M00/00/11/rBAKZGIfMSyAfv2hAAA_gMHjhuI129.jpg
            byte[] datas = fdfsFileService.download(groupName, remoteName);
//            if (!validateExt(fileName, fileExtInit.getImageExtList())) {
//                datas = ImageWatermarkUtils.markImageByText(datas);
//            }
            res.getOutputStream().write(datas);
        } catch (UnsupportedEncodingException e) {

            log.error("download file error", e);
        } catch (IOException e) {

            log.error("download file error", e);
        }
    }


    @RequestMapping(value = "/mark", method = RequestMethod.POST)
    @ApiOperation("返回base64带水印图片")
    @ResponseBody
    public R<byte[]> waterMarkFile(@RequestParam("filePath") String filePath) {
        int i = filePath.indexOf("?fileName=");
        int group = filePath.indexOf("group");
        if (-1 == group || -1 == i) {
            return RHelper.ok();
        }
        String substring = filePath.substring(group, i);
        int i1 = substring.indexOf("/");
        String groupName = substring.substring(0, i1);
        String remoteName = substring.substring(i1 + 1);
        if (StringUtils.isAnyBlank(groupName, remoteName)) {
            return null;
        }
        byte[] datas = fdfsFileService.download(groupName, remoteName);
        // 压缩到小于指定文件大小100k
        byte[] bytes = ImageWatermarkUtils.markImageByText(datas);
        if (null != datas && null != bytes) {
            try {
                while (bytes.length > targetSize) {
                    bytes = ResizeImageUtil.resizeImage(bytes);
                }
            } catch (IOException e) {
            }
            return RHelper.getSuccessR(bytes);
        }
        return RHelper.getSuccessR(datas);
    }

    @PostMapping(value = "/download")
    @ResponseBody
    public R<byte[]> download(@RequestParam("filePath") String filePath) {
        // String filePath="http://localhost:18080/group1/M00/00/11/rBAKZGIfMSyAfv2hAAA_gMHjhuI129.jpg?fileName=下载2.jpg";
        int i = filePath.indexOf("?fileName=");
        int group = filePath.indexOf("group");
        String substring = filePath.substring(group, i);
        int i1 = substring.indexOf("/");
        String groupName = substring.substring(0, i1);
        String remoteName = substring.substring(i1 + 1);
        if (StringUtils.isAnyBlank(groupName, remoteName)) {
            return RHelper.failed();
        }
        byte[] datas = fdfsFileService.download(groupName, remoteName);
        return RHelper.getSuccessR(datas);
    }

    @RequestMapping(value = "/demandAttachment/uploadFile", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> demandAttachment(HttpServletRequest req) {
        List<MultipartFile> fileList = new ArrayList<>();
        if (req instanceof MultipartHttpServletRequest) {
            fileList = ((MultipartHttpServletRequest) req).getFiles("file");
        }
        R<Map<String, String>> dictMapResult = systemClient.getAllDictByCode();
        if (!RHelper.isSuccessR(dictMapResult)) {
            return ResultMapHelper.getResultMap(dictMapResult.getCode(), dictMapResult.getMsg());
        }
        String value = dictMapResult.getDatas().get("demandAttachmentSize");
        Long dictValue = Long.valueOf(value);
//        if (dictValue == null) {
//            return ResultMapHelper.getResultMap("2", "没有设置上传文件限制大小，请设置后上传");
//        }
        Map<String, Object> resultMap = new HashMap<>(16);
        try {
            List<Map<String, Object>> returnList = new ArrayList<>();
            for (MultipartFile file : fileList) {
                UploadFile uploadFile = new UploadFile();
//                Long size = file.getSize();
//                if (size.compareTo(dictValue) == 1) {
//                    return ResultMapHelper.getResultMap("2", "上传文件大小不能超过" + dictValue / 1024 + "M");
//                }
                String fileName = file.getOriginalFilename();
                if (validateUeExt(fileName)) {
                    return ResultMapHelper.getResultMap("2", "文件格式错误");
                }
                uploadFile.setFileName(fileName);

                uploadFile.setContent(file.getBytes());
                if (!StringUtils.isEmpty(file.getOriginalFilename())) {
                    int pos = file.getOriginalFilename().lastIndexOf(".");
                    if (pos > -1 && pos + 1 < file.getOriginalFilename().length()) {
                        uploadFile.setExt(file.getOriginalFilename().substring(pos + 1));
                    }
                }
                Map<String, Object> map = fdfsFileService.upload(uploadFile);
                if ("0".equals(map.get("code"))) {
                    Map f = new HashMap(16);
                    f.put("fileName", file.getOriginalFilename());
                    f.put("url", map.get("data"));
                    returnList.add(f);
                }
            }
            resultMap.put("datas", returnList);
        } catch (IOException e) {
            log.error("upload file error", e);
        }
        if (!resultMap.isEmpty()) {
            resultMap.put("code", "0");
            resultMap.put("msg", "ok");
            return resultMap;
        }
        return ResultMapHelper.getResultMap("2", "参数错误");
    }


    @RequestMapping(value = "/findList", method = RequestMethod.GET)
    @ResponseBody
//    @Operation(value="find",desc="查询")
    public List<FileModel> findList() {

        return fdfsFileService.findList(new FileModel());
    }
}
