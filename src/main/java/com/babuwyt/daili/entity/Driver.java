package com.babuwyt.daili.entity;


import com.babuwyt.daili.utils.util.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

/**
 * Created by lenovo on 2017/9/4.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class Driver {
    private String fsendcarno;
    private String drivername;
    private String fdrivername;
    private String fplateno;
    private String ftel;
    private String fftel;
    public String getFsendcarno() {
        return fsendcarno;
    }

    public void setFsendcarno(String fsendcarno) {
        this.fsendcarno = fsendcarno;
    }

    public String getDrivername() {
        return drivername;
    }

    public void setDrivername(String drivername) {
        this.drivername = drivername;
    }

    public String getFplateno() {
        return fplateno;
    }

    public void setFplateno(String fplateno) {
        this.fplateno = fplateno;
    }

    public String getFtel() {
        return ftel;
    }

    public void setFtel(String ftel) {
        this.ftel = ftel;
    }

    public String getFdrivername() {
        return fdrivername;
    }

    public void setFdrivername(String fdrivername) {
        this.fdrivername = fdrivername;
    }

    public String getFftel() {
        return fftel;
    }

    public void setFftel(String fftel) {
        this.fftel = fftel;
    }
    /**
     * "fsendcarno": "DD201709010001",
     "drivername": "张全蛋",
     "fplateno": "陕A9527A",
     "ftel": "18628602197"
     */
}
