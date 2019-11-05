package com.sys.entity;

public class PageBean {

    /*
           page显示当前页数
           pageSize显示每页最大记录
     */
    private Integer page;
    private Integer pageSize=3;

    public PageBean(){

    }

    public PageBean(Integer page, Integer pageSize) {
        this.page = page;
        this.pageSize = pageSize;
    }

    public PageBean(Integer page) {
        this.page = page;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
