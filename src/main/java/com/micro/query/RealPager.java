package com.micro.query;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @param <T>
 * @author radioend
 * @ClassName: RealPager
 * @Description: 分页基础参数
 * @date 2014-3-14 下午4:26:47
 */
public class RealPager<T> {

    private int sumLine = 0; //总数

    private int pageSize = 10;//每页条数

    private List<T> rows = null; //分页结果集

    private int pageNumber = 1; //页数

    private int sumPage = 0; //总页数

    public RealPager() {
        super();
    }

    public RealPager(int pageNumber) {
        super();
        this.pageNumber = pageNumber;
    }

    public RealPager(int pageSize, int pageNumber) {
        super();
        this.pageSize = pageSize;
        this.pageNumber = pageNumber;
    }

    public RealPager(List<T> list) {
        super();
        PageInfo<T> pageInfo = new PageInfo<>(list);
        this.pageSize = pageInfo.getPageSize();
        this.pageNumber = pageInfo.getPageNum();
        this.sumLine = (int) pageInfo.getTotal();
        this.rows = pageInfo.getList();
    }

    public RealPager(PageInfo<T> pageInfo) {
        super();
        this.pageSize = pageInfo.getPageSize();
        this.pageNumber = pageInfo.getPageNum();
        this.sumLine = (int) pageInfo.getTotal();
        this.rows = pageInfo.getList();
    }

    public int getSumLine() {
        return sumLine;
    }

    public void setSumLine(int sumLine) {
        this.sumLine = sumLine;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getSumPage() {
        sumPage = pageSize == 0 ? 0 : ((sumLine + pageSize - 1) / pageSize);
        return sumPage;
    }

    public void setSumPage(int sumPage) {
        this.sumPage = sumPage;
    }


}
