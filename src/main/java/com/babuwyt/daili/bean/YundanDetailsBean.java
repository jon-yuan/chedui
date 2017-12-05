package com.babuwyt.daili.bean;

import com.babuwyt.daili.base.BaseBean;
import com.babuwyt.daili.entity.LoadpickEntity;
import com.babuwyt.daili.entity.MainEntity;
import com.babuwyt.daili.entity.YundanDetailsEntity;
import com.babuwyt.daili.utils.util.JsonResponseParser;

import org.w3c.dom.ProcessingInstruction;
import org.xutils.http.annotation.HttpResponse;

import java.util.ArrayList;

/**
 * Created by lenovo on 2017/8/25.
 */
@HttpResponse(parser = JsonResponseParser.class)
public class YundanDetailsBean extends BaseBean {


    private YundanDetailsEntity obj;

    public YundanDetailsEntity getObj() {
        return obj;
    }

    public void setObj(YundanDetailsEntity obj) {
        this.obj = obj;
    }
    /**
     *
     *     "fid": 99,
     "fownersendcarno": "DD201707120013",
     "fsendcarno": null,
     "fpickuptime": null,
     "fmanageid": null,
     "ftaskstate": 0,
     "fvicecard": null,
     "fplateno": null,
     "fdriverid": null,
     "fshouldpay": null,
     "fshouldpayoilcard": null,
     "fshouldreturnmoney": null,
     "ftransportno": "T2017071200005",
     "foilcardrecharge1": null,
     "foilcardrecharge2": null,
     "frechargerecordId1": null,
     "frechargerecordId2": null,
     "fstate": 1,
     "fsettlestate": 0,
     "fsettime": null,
     "fMoneyRecharge2": null,
     "fMoneyRecharge1": null,
     "fkouchu": null,
     "facceptratio": null,

     "fsprovince": "宁夏回族自治区",
     "fscity": "银川市",
     "fsdistrict": "西夏区",
     "fsdisplay": "文萃南街战略新兴材料加工区14#",
     "fuprovince": "江苏省",
     "fucity": "泰州市",
     "fudistrict": "海陵区",
     "fudisplay": "梅兰东路海陵工业园共建区18号楼",
     "ftrucktypename": "17.5米",
     "fdrivername": null,
     "forwardername": null,
     "fownersendcarid": "45",
     "fbidstate": null,
     "foilcardrechargestate1": null,
     "foilcardrechargestate2": null,
     "fdelivergoodtime": "2017-07-12 10:43:00.0",
     "farrivetime": null,
     "fvalue": "906756.60",
     *
     */



}
