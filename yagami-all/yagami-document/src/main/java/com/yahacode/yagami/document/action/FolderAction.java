package com.yahacode.yagami.document.action;

import com.yahacode.yagami.base.BaseAction;
import com.yahacode.yagami.base.BizfwServiceException;
import com.yahacode.yagami.base.consts.ErrorCode;
import com.yahacode.yagami.document.model.Document;
import com.yahacode.yagami.document.model.Folder;
import com.yahacode.yagami.document.service.FolderService;
import com.yahacode.yagami.document.utils.FileUtils;
import com.yahacode.yagami.pd.model.People;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/folder")
public class FolderAction extends BaseAction {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private FolderService folderService;

    @ApiOperation(value = "新增文件夹")
    @ApiImplicitParam(name = "folder", value = "文件夹模型", required = true, dataTypeClass = Folder.class)
    @RequestMapping(method = RequestMethod.POST)
    public void addFolder(@RequestBody Folder folder) throws BizfwServiceException {
        People people = getLoginPeople();
        folder.init(people.getCode());
        folderService.addFolder(folder);
    }

    @ApiOperation(value = "更新文件夹")
    @ApiImplicitParam(name = "folder", value = "文件夹模型", required = true, dataTypeClass = Folder.class)
    @RequestMapping(method = RequestMethod.PATCH)
    public void modifyFolder(@RequestBody Folder folder) throws BizfwServiceException {
        People people = getLoginPeople();
        folder.update(people.getCode());
        folderService.modifyFolder(folder);
    }

    @ApiOperation(value = "删除文件夹")
    @ApiImplicitParam(name = "folderId", value = "文件夹id", required = true, dataTypeClass = String.class)
    @RequestMapping(method = RequestMethod.DELETE, value = "/{folderId}")
    public void deleteFolder(@PathVariable("folderId") String folderId) throws BizfwServiceException {
        People people = getLoginPeople();
        folderService.deleteFolder(folderId, people);
    }

    @ApiOperation(value = "获取所有文件夹树结构")
    @RequestMapping(method = RequestMethod.GET)
    public Folder getAllFolderTree() throws BizfwServiceException {
        return folderService.getAllFolderTree();
    }

    @ApiOperation(value = "获取文件夹下内容")
    @ApiImplicitParam(name = "folderId", value = "文件夹id", required = true, dataTypeClass = String.class)
    @RequestMapping(method = RequestMethod.GET, value = "folderId}")
    public Folder getContentOfFolder(@PathVariable("folderId") String folderId) throws BizfwServiceException {
        People people = getLoginPeople();
        Folder folder = folderService.queryById(folderId);
        folder.update(people.getCode());
        return folderService.getContentOfFolder(folder);
    }

    @ApiOperation(value = "在文件夹下新增文件")
    @ApiImplicitParams({@ApiImplicitParam(name = "file", value = "文件", required = true, dataTypeClass = MultipartFile
            .class), @ApiImplicitParam(name = "folderId", value = "文件夹id", required = true, dataTypeClass = String
            .class)})
    @RequestMapping(method = RequestMethod.POST, value = "/{folderId}/document")
    public void addDocument(@RequestBody MultipartFile file, @PathVariable("folderId") String folderId) throws
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
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/document/{documentId}")
    public void deleteDocument(@PathVariable("documentId") String documentId) throws BizfwServiceException {

    }

    @Autowired
    public void setFolderService(FolderService folderService) {
        this.folderService = folderService;
    }
}
