package com.yahacode.yagami.document.repository;

import com.yahacode.yagami.base.BaseRepository;
import com.yahacode.yagami.document.model.RoleFolderAuthority;

import java.util.List;

/**
 * @author zengyongli 2018-04-03
 */
public interface RoleFolderAuthorityRepository extends BaseRepository<RoleFolderAuthority, String> {

    void deleteByFolderId(String folderId);

    List<RoleFolderAuthority> findAllByFolderId(String folderId);
}
