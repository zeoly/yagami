package com.yahacode.yagami.document.action;

import com.yahacode.yagami.auth.model.Role;
import com.yahacode.yagami.base.BaseController;
import com.yahacode.yagami.base.BizfwServiceException;
import com.yahacode.yagami.document.model.Folder;
import com.yahacode.yagami.document.service.FolderService;
import com.yahacode.yagami.pd.model.Person;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/folder")
public class FolderAction extends BaseController {

    private FolderService folderService;

    @ApiOperation(value = "新增文件夹")
    @ApiImplicitParam(name = "folder", value = "文件夹模型", required = true, dataTypeClass = Folder.class)
    @PostMapping
    public void addFolder(@RequestBody Folder folder) throws BizfwServiceException {
        Person people = getLoginPeople();
        folder.init(people.getCode());
        folderService.addFolder(folder);
    }

    @ApiOperation(value = "修改文件夹")
    @ApiImplicitParams({@ApiImplicitParam(name = "folderId", value = "文件夹id", required = true, dataTypeClass = String
            .class), @ApiImplicitParam(name = "folder", value = "文件夹模型", required = true, dataTypeClass = Folder
            .class)})
    @PatchMapping("/{folderId}")
    public void modifyFolder(@PathVariable("folderId") String folderId, @RequestBody Folder folder) throws
            BizfwServiceException {
        Person people = getLoginPeople();
        folder.update(people.getCode());
        folder.setIdBfFolder(folderId);
        folderService.modifyFolder(folder);
    }

    @ApiOperation(value = "删除文件夹")
    @ApiImplicitParam(name = "folderId", value = "文件夹id", required = true, dataTypeClass = String.class)
    @DeleteMapping("/{folderId}")
    public void deleteFolder(@PathVariable("folderId") String folderId) throws BizfwServiceException {
        folderService.deleteFolder(folderId);
    }

    @ApiOperation(value = "获取所有文件夹树结构")
    @GetMapping
    public Folder getAllFolderTree() throws BizfwServiceException {
        return folderService.getAllFolderTree();
    }

    @ApiOperation(value = "获取文件夹下内容")
    @ApiImplicitParam(name = "folderId", value = "文件夹id", required = true, dataTypeClass = String.class)
    @GetMapping("{folderId}")
    public Folder getContentOfFolder(@PathVariable("folderId") String folderId) throws BizfwServiceException {
        Person people = getLoginPeople();
        Folder folder = folderService.queryById(folderId);
        folder.update(people.getCode());
        return folderService.getContentOfFolder(folder);
    }

    @ApiOperation(value = "获取文件夹权限")
    @ApiImplicitParam(name = "folderId", value = "文件夹id", required = true, dataTypeClass = String.class)
    @GetMapping("/{folderId}/authority")
    public List<Role> getFolderAuthority(@PathVariable("folderId") String folderId) throws BizfwServiceException {
        return folderService.getFolderAuthority(folderId);
    }

    @ApiOperation(value = "设置文件夹权限")
    @ApiImplicitParams({@ApiImplicitParam(name = "folderId", value = "文件夹id", required = true, dataTypeClass = String
            .class), @ApiImplicitParam(name = "roleIdList", value = "角色id列表", required = true, dataTypeClass = List
            .class)})
    @PostMapping("/{folderId}/authority")
    public void setFolderAuthority(@PathVariable("folderId") String folderId, @RequestBody List<String> roleIdList)
            throws BizfwServiceException {
        folderService.setFolderAuthority(folderId, roleIdList);
    }

    @ApiOperation(value = "在文件夹下新增文件")
    @ApiImplicitParams({@ApiImplicitParam(name = "file", value = "文件", required = true, dataTypeClass = MultipartFile
            .class), @ApiImplicitParam(name = "folderId", value = "文件夹id", required = true, dataTypeClass = String
            .class)})
    @PostMapping("/{folderId}/document")
    public void addDocument(@RequestBody MultipartFile file, @PathVariable("folderId") String folderId) throws
            BizfwServiceException {
        folderService.addDocument(file, folderId);
    }

    @ApiOperation(value = "删除文件夹下的文件")
    @ApiImplicitParam(name = "documentId", value = "文件id", required = true, dataTypeClass = String.class)
    @DeleteMapping("/document/{documentId}")
    public void deleteDocument(@PathVariable("documentId") String documentId) throws BizfwServiceException {
        folderService.deleteDocument(documentId);
    }

    @Autowired
    public void setFolderService(FolderService folderService) {
        this.folderService = folderService;
    }
}
