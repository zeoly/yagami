package com.yahacode.yagami.document.action;

import com.yahacode.yagami.auth.model.Role;
import com.yahacode.yagami.base.BaseAction;
import com.yahacode.yagami.base.BizfwServiceException;
import com.yahacode.yagami.document.model.Folder;
import com.yahacode.yagami.document.service.FolderService;
import com.yahacode.yagami.pd.model.People;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/folder")
public class FolderAction extends BaseAction {

    private FolderService folderService;

    @ApiOperation(value = "新增文件夹")
    @ApiImplicitParam(name = "folder", value = "文件夹模型", required = true, dataTypeClass = Folder.class)
    @RequestMapping(method = RequestMethod.POST)
    public void addFolder(@RequestBody Folder folder) throws BizfwServiceException {
        People people = getLoginPeople();
        folder.init(people.getCode());
        folderService.addFolder(folder);
    }

    @ApiOperation(value = "修改文件夹")
    @ApiImplicitParams({@ApiImplicitParam(name = "folderId", value = "文件夹id", required = true, dataTypeClass = String
            .class), @ApiImplicitParam(name = "folder", value = "文件夹模型", required = true, dataTypeClass = Folder
            .class)})
    @RequestMapping(method = RequestMethod.PATCH, value = "/{folderId}")
    public void modifyFolder(@PathVariable("folderId") String folderId, @RequestBody Folder folder) throws
            BizfwServiceException {
        People people = getLoginPeople();
        folder.update(people.getCode());
        folder.setIdBfFolder(folderId);
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

    @ApiOperation(value = "获取文件夹权限")
    @ApiImplicitParam(name = "folderId", value = "文件夹id", required = true, dataTypeClass = String.class)
    @RequestMapping(method = RequestMethod.GET, value = "/{folderId}/authority")
    public List<Role> getFolderAuthority(@PathVariable("folderId") String folderId) throws BizfwServiceException {
        return folderService.getFolderAuthority(folderId);
    }

    @ApiOperation(value = "设置文件夹权限")
    @ApiImplicitParams({@ApiImplicitParam(name = "folderId", value = "文件夹id", required = true, dataTypeClass = String
            .class), @ApiImplicitParam(name = "roleIdList", value = "角色id列表", required = true, dataTypeClass = List
            .class)})
    @RequestMapping(method = RequestMethod.POST, value = "/{folderId}/authority")
    public void setFolderAuthority(@PathVariable("folderId") String folderId, @RequestBody List<String> roleIdList)
            throws BizfwServiceException {
        folderService.setFolderAuthority(folderId, roleIdList, getLoginPeople().getCode());
    }

    @ApiOperation(value = "在文件夹下新增文件")
    @ApiImplicitParams({@ApiImplicitParam(name = "file", value = "文件", required = true, dataTypeClass = MultipartFile
            .class), @ApiImplicitParam(name = "folderId", value = "文件夹id", required = true, dataTypeClass = String
            .class)})
    @RequestMapping(method = RequestMethod.POST, value = "/{folderId}/document")
    public void addDocument(@RequestBody MultipartFile file, @PathVariable("folderId") String folderId) throws
            BizfwServiceException {
        folderService.addDocument(file, folderId, getLoginPeople().getCode());
    }

    @ApiOperation(value = "删除文件夹下的文件")
    @ApiImplicitParam(name = "documentId", value = "文件id", required = true, dataTypeClass = String.class)
    @RequestMapping(method = RequestMethod.DELETE, value = "/document/{documentId}")
    public void deleteDocument(@PathVariable("documentId") String documentId) throws BizfwServiceException {
        folderService.deleteDocument(documentId, getLoginPeople());
    }

    @Autowired
    public void setFolderService(FolderService folderService) {
        this.folderService = folderService;
    }
}
