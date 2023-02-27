package com.hwq.company.util;

public class PageModel {
    private int pageIndex=1;
    private int pageLimit=10;
    private int rowStart;
    private int rowCount;
    private boolean pageOn =false;

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageLimit() {
        return pageLimit;
    }

    public void setPageLimit(int pageLimit) {
        this.pageLimit = pageLimit;
    }

    public int getRowStart() {
        rowStart=(pageIndex-1)*pageLimit;
        return rowStart;
    }

    public void setRowStart(int rowStart) {
        this.rowStart = rowStart;
    }

    public int getRowCount() {
        rowCount=pageLimit;
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public boolean isPageOn() {
        return pageOn;
    }

    public void setPageOn(boolean pageOn) {
        this.pageOn = pageOn;
    }
}
