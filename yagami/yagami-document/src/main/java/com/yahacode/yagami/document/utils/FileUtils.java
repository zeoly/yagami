package com.yahacode.yagami.document.utils;

import com.yahacode.yagami.base.common.DateUtils;
import com.yahacode.yagami.document.config.DocumentConfig;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Component
public class FileUtils {

    private DocumentConfig documentConfig;

    public static String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    public String getStorageUrl(String fileName, String fileMD5) {
        String storageFileName = fileMD5 + "." + fileName;
        int hashcode = fileName.hashCode();
        int dir1 = hashcode & 0xf;
        // int dir2 = (hashcode & 0xf0) >> 4;
        String url = "/" + DateUtils.getDateStr() + "/" + dir1;
        String localFolder = getLocalStorage() + url;
        File file = new File(localFolder);
        if (!file.exists()) {
            file.mkdirs();
        }
        return url + "/" + storageFileName;
    }

    public String getLocalStorage() {
        return documentConfig.getLocalStorage();
    }

    public static String getMD5(MultipartFile file) {
        try {
            return DigestUtils.md5Hex(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    @Autowired
    public void setDocumentConfig(DocumentConfig documentConfig) {
        this.documentConfig = documentConfig;
    }
}
