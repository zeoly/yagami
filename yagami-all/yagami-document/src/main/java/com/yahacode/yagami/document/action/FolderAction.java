package com.yahacode.yagami.document.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yahacode.yagami.auth.model.Role;
import com.yahacode.yagami.auth.service.RoleService;
import com.yahacode.yagami.base.BaseAction;
import com.yahacode.yagami.base.BizfwServiceException;
import com.yahacode.yagami.document.model.Document;
import com.yahacode.yagami.document.service.FolderService;
import com.yahacode.yagami.pd.model.People;

@Controller
@RequestMapping("/folderAction")
public class FolderAction extends BaseAction {

	@Autowired
	private FolderService folderService;

	@Autowired
	private RoleService roleService;

	@ResponseBody
	@RequestMapping("/addFolder.do")
	public String addFolder(HttpServletRequest request, Document document) throws BizfwServiceException {
		People people = getLoginPeople(request);
		document.init(people.getCode());
		folderService.addFolder(document);
		return SUCCESS;
	}

	@ResponseBody
	@RequestMapping("/modifyFolder.do")
	public String modifyFolder(HttpServletRequest request, Document document) throws BizfwServiceException {
		People people = getLoginPeople(request);
		document.update(people.getCode());
		folderService.modifyFolder(document);
		return SUCCESS;
	}

	@ResponseBody
	@RequestMapping("/deleteFolder.do")
	public String deleteFolder(HttpServletRequest request, String documentId) throws BizfwServiceException {
		People people = getLoginPeople(request);
		folderService.deleteFolder(documentId, people);
		return SUCCESS;
	}

	@ResponseBody
	@RequestMapping("/setRoleOfFolder.do")
	public String setRoleOfFolder(HttpServletRequest request, String folderId,
			@RequestParam(value = "roleIdList[]") List<String> roleIdList) throws BizfwServiceException {
		People people = getLoginPeople(request);
		Document folder = folderService.queryById(folderId);
		folder.update(people.getCode());
		folderService.setRoleOfFolder(folder, roleIdList);
		return SUCCESS;
	}

	@ResponseBody
	@RequestMapping("/getAllFolderTree.do")
	public Document getAllFolderTree() throws BizfwServiceException {
		Document rootFolder = folderService.getAllFolderTree();
		return rootFolder;
	}

	@ResponseBody
	@RequestMapping("/getAuthFolderTree.do")
	public Document getAuthFolderTree() throws BizfwServiceException {
		return null;
	}

	@ResponseBody
	@RequestMapping("/getFolderOfRole.do")
	public List<Document> getFolderOfRole(String roleId) throws BizfwServiceException {
		Role role = roleService.queryById(roleId);
		List<Document> folderList = folderService.getFolderOfRole(role);
		return folderList;
	}

	@ResponseBody
	@RequestMapping("/getRoleOfFolder.do")
	public List<Role> getRoleOfFolder(String folderId) throws BizfwServiceException {
		Document folder = folderService.queryById(folderId);
		List<Role> roleList = folderService.getRoleOfFolder(folder);
		return roleList;
	}
}
