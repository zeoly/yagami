package com.yahacode.yagami.chat.scrollboard.service.impl;

import com.yahacode.yagami.base.impl.BaseServiceImpl;
import com.yahacode.yagami.chat.scrollboard.model.Scrollboard;
import com.yahacode.yagami.chat.scrollboard.repository.ScrollboardRepository;
import com.yahacode.yagami.chat.scrollboard.service.ScrollboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * @author zengyongli 2018-05-07
 */
@Service
public class ScrollboardServiceImpl extends BaseServiceImpl<Scrollboard> implements ScrollboardService {

    private ScrollboardRepository scrollboardRepository;

    @Override
    public JpaRepository<Scrollboard, String> getBaseRepository() {
        return scrollboardRepository;
    }

    @Autowired
    public void setScrollboardRepository(ScrollboardRepository scrollboardRepository) {
        this.scrollboardRepository = scrollboardRepository;
    }
}
