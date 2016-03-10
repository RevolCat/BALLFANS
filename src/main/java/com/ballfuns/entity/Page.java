package com.ballfuns.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Revol on 2016/1/13.
 */
public class Page {
    //总记录数
    private int totalCount;
    //总页数
    private int pageCount;
    //每页记录数
    private int pageSize=10;
    //当前页
    private int page=1;
    //当前页之前   &之后的显示的页数的个数；
    private int num=3;
    @SuppressWarnings("unchecked")
    //当前页记录集合
    private List list=new ArrayList();
    //前一页
    private int pevpage;
    //下一页
    private int nextpage;
    //最后一页
    private int lastpage;
    //当前页之前集合
    private List<Integer> prevPages;
    //当前页之后集合
    private List<Integer> nextPages;

    public int getTotalCount() {
        return totalCount;
    }

    /**
     * 计算总页数
     * */
    public void setTotalCount(int totalCount) {
        if(totalCount>0){
            this.totalCount=totalCount;
            this.pageCount=(totalCount+pageSize-1)/pageSize;
        }
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @SuppressWarnings("unchecked")
    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }



    /**
     * 判断是否有前一页
     * */
    public boolean getIsPrev(){
        if(page>1){
            return true;
        }else{
            return false;
        }
    }
    public int getPevpage() {
        if(getIsPrev()){
            return page-1;
        }else{
            return page;
        }
    }


    public void setPevpage(int pevpage) {
        this.pevpage = pevpage;
    }


    /**
     * 判断是否有下一页
     * */
    public boolean getIsNext(){
        if(page<pageCount){
            return true;
        }else{
            return false;
        }
    }

    /**
     *获取后一页
     * */
    public int getNextpage() {
        if(getIsNext()){
            return page+1;
        }
        else{
            return getPageCount();
        }

    }

    public void setNextpage(int nextpage) {
        this.nextpage = nextpage;
    }


    /**
     * 获取最后一页
     * */
    public int getLastpage() {
        return pageCount;
    }

    public void setLastpage(int lastpage) {
        this.lastpage = lastpage;
    }


    /**
     * 之前页的集合
     * */
    public List<Integer> getPrevPages() {
        List<Integer> list = new ArrayList<Integer>();
        int _frontStart = 1;
        if (page > num) {
            _frontStart = page - num;
        } else if (page <= num) {
            _frontStart = 1;
        }
        for (int i = _frontStart; i < page; i++) {
            list.add(i);
        }
        return list;
    }

    public void setPrevPages(List<Integer> prevPages) {
        this.prevPages = prevPages;
    }


    /**
     * 之后页的集合
     * */
    public List<Integer> getNextPages() {
        List<Integer> list = new ArrayList<Integer>();
        int _endCount = num;
        if (num < pageCount && (page + num) < pageCount) {
            _endCount = page + _endCount;
        } else if ((page + num) >= pageCount) {
            _endCount = pageCount;
        }
        for (int i = page + 1; i <= _endCount; i++) {
            list.add(i);
        }
        return list;
    }

    public void setNextPages(List<Integer> nextPages) {
        this.nextPages = nextPages;
    }
}
