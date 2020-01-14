package cn.itcast.travel.domain;

import java.util.List;

public class PageBean<T> {

    private int currPage;
    private int sumPage;

    private int countOfPrePage = 10;
    private int totalCount;

    private List<T> list;

    public int getCurrPage() {
        return currPage;
    }

    public void setCurrPage(int currPage) {
        this.currPage = currPage;
    }

    public int getSumPage() {
        return sumPage;
    }

    public void setSumPage(int sumPage) {
        this.sumPage = sumPage;
    }

    public int getCountOfPrePage() {
        return countOfPrePage;
    }

    public void setCountOfPrePage(int countOfPrePage) {
        this.countOfPrePage = countOfPrePage;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "currPage=" + currPage +
                ", sumPage=" + sumPage +
                ", countOfPrePage=" + countOfPrePage +
                ", totalCount=" + totalCount +
                ", list=" + list +
                '}';
    }
}
