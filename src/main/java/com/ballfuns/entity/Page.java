package com.ballfuns.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Revol on 2016/1/13.
 */
public class Page {
    //�ܼ�¼��
    private int totalCount;
    //��ҳ��
    private int pageCount;
    //ÿҳ��¼��
    private int pageSize=10;
    //��ǰҳ
    private int page=1;
    //��ǰҳ֮ǰ   &֮�����ʾ��ҳ���ĸ�����
    private int num=3;
    @SuppressWarnings("unchecked")
    //��ǰҳ��¼����
    private List list=new ArrayList();
    //ǰһҳ
    private int pevpage;
    //��һҳ
    private int nextpage;
    //���һҳ
    private int lastpage;
    //��ǰҳ֮ǰ����
    private List<Integer> prevPages;
    //��ǰҳ֮�󼯺�
    private List<Integer> nextPages;

    public int getTotalCount() {
        return totalCount;
    }

    /**
     * ������ҳ��
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
     * �ж��Ƿ���ǰһҳ
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
     * �ж��Ƿ�����һҳ
     * */
    public boolean getIsNext(){
        if(page<pageCount){
            return true;
        }else{
            return false;
        }
    }

    /**
     *��ȡ��һҳ
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
     * ��ȡ���һҳ
     * */
    public int getLastpage() {
        return pageCount;
    }

    public void setLastpage(int lastpage) {
        this.lastpage = lastpage;
    }


    /**
     * ֮ǰҳ�ļ���
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
     * ֮��ҳ�ļ���
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
