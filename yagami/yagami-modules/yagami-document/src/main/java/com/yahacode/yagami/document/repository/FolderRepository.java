package com.yahacode.yagami.document.repository;

import com.yahacode.yagami.base.BaseRepository;
import com.yahacode.yagami.document.model.Folder;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author zengyongli 2018-04-02
 */
public interface FolderRepository extends BaseRepository<Folder, String> {

    Folder findByName(String name);

    List<Folder> findAllByParentIdOrderByName(String parentId);

    long countByParentId(String parentId);

    @Query("select f from Folder f where f.idBfFolder in (select a.folderId from RoleFolderAuthority a where a.roleId" +
            " = ?1)")
    List<Folder> findAllAuthorizedFolderByRoleId(String roleId);
}
