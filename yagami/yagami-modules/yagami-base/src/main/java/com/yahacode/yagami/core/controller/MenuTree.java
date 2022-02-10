package com.yahacode.yagami.core.controller;

import com.yahacode.yagami.core.model.Menu;

import java.util.List;

public class MenuTree extends Menu {

    private List<MenuTree> children;

    public List<MenuTree> getChildren() {
        return children;
    }

    public void setChildren(List<MenuTree> children) {
        this.children = children;
    }
}
