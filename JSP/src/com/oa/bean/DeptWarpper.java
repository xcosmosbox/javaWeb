package com.oa.bean;

import java.util.Objects;

public class DeptWarpper {
    private String deptno;
    private String dname;
    private String loc;

    public DeptWarpper(String deptno, String dname, String loc) {
        this.deptno = deptno;
        this.dname = dname;
        this.loc = loc;
    }

    public String getDeptno() {
        return deptno;
    }

    public void setDeptno(String deptno) {
        this.deptno = deptno;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeptWarpper that = (DeptWarpper) o;
        return deptno.equals(that.deptno) && dname.equals(that.dname) && loc.equals(that.loc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deptno, dname, loc);
    }

    @Override
    public String toString() {
        return "DeptWarpper{" +
                "deptno='" + deptno + '\'' +
                ", dname='" + dname + '\'' +
                ", loc='" + loc + '\'' +
                '}';
    }
}
