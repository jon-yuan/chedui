package com.babuwyt.daili.entity;


import com.babuwyt.daili.utils.util.JsonResponseParser;

import org.xutils.http.annotation.HttpResponse;

/**
 * Created by lenovo on 2017/8/29.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class Basicinfo {

    private String fid;
    private String fsendcarno;
    /**
     *  "fid": 216,
     "fsendcarno": "DD201708090001",
     "ftrucktype": 21,
     "fboxtype": null,
     "fdelivergoodtime": 1565164800000,
     "farrivetime": null,
     "fpickupno": "640181",
     "funloadno": "610116",
     "fpiecenum": 18,
     "fweight": 500,
     "fbulk": 22,
     "fvalue": 3660000,
     "fshortflag": "N",
     "fofferingtype": "TRUCK",
     "fremark": null,
     "ftransportno": "T2017080900001",
     "typename": "9.6米"
     */

}
