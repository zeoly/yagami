package com.yahacode.yagami.document.repository;

import com.yahacode.yagami.base.BaseRepository;
import com.yahacode.yagami.document.model.Document;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author zengyongli 2018-04-02
 */
public interface DocumentRepository extends BaseRepository<Document, String> {

    Document findByMd5(String md5);

    @Query("select d from Document d where d.idBfDocument in (select g.documentId from DocumentGroup g where g" + "" +
            ".groupNo = ?1)")
    List<Document> findAllByGroupNo(String groupNo);
}
