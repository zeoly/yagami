package com.yahacode.yagami.document.action;

import com.yahacode.yagami.base.BaseAction;
import com.yahacode.yagami.base.BizfwServiceException;
import com.yahacode.yagami.base.consts.ErrorCode;
import com.yahacode.yagami.document.model.Document;
import com.yahacode.yagami.document.model.Folder;
import com.yahacode.yagami.document.service.FolderService;
import com.yahacode.yagami.document.utils.FileUtils;
import com.yahacode.yagami.pd.model.People;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/folder")
public class FolderAction extends BaseAction {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private FolderService folderService;

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public void addFolder(@RequestBody Folder folder) throws BizfwServiceException {
        People people = getLoginPeople();
        folder.init(people.getCode());
        folderService.addFolder(folder);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.PATCH)
    public void modifyFolder(@RequestBody Folder folder) throws BizfwServiceException {
        People people = getLoginPeople();
        folder.update(people.getCode());
        folderService.modifyFolder(folder);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.DELETE, value = "/{folderId}")
    public void deleteFolder(@PathVariable("folderId") String folderId) throws BizfwServiceException {
        People people = getLoginPeople();
        folderService.deleteFolder(folderId, people);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public Folder getAllFolderTree() throws BizfwServiceException {
        return folderService.getAllFolderTree();
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "folderId}")
    public Folder getContentOfFolder(@PathVariable("folderId") String folderId) throws BizfwServiceException {
        People people = getLoginPeople();
        Folder folder = folderService.queryById(folderId);
        folder.update(people.getCode());
        return folderService.getContentOfFolder(folder);
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, value = "/{folderId}/document")
    public String addDocument(@RequestBody MultipartFile file, @PathVariable("folderId") String folderId) throws
            BizfwServiceException {
        try {
            String fileName = file.getOriginalFilename();
            String url = FileUtils.getStorageUrl(fileName);
            String filePath = FileUtils.getLocalStorage() + url;
            File newFile = new File(filePath);
            file.transferTo(newFile);

            People people = getLoginPeople();
            Document document = new Document(people.getCode());
            document.setName(fileName);
            document.setUrl(url);
            document.setSize(file.getSize());
            document.setMd5(FileUtils.getMd5(filePath));

            folderService.addDocument(document, folderId);
        } catch (IllegalStateException | IOException e) {
            logger.error("文件上传失败", e);
            throw new BizfwServiceException(ErrorCode.Doc.File.ACCESS_FAIL_NO_AUTH, e);
        }
        return SUCCESS;
    }

    @Autowired
    public void setFolderService(FolderService folderService) {
        this.folderService = folderService;
    }
}
