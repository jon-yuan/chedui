package com.babuwyt.daili.finals;

/**
 * 此处专门放接口名
 * Created by 吴同 on 2014/12/9.
 */
public class BaseURL {
    /**
     * 每页数量
     */
    public static final int PAGESIZE = 20;
    /**
     * 请求地址
     */
//    public static final String BASE_URL = "http://192.168.2.122:8050/";//黄鹏波
    public static final String BASE_URL = "http://www.babuwyt.com:8080/tmsapi/";//测试环境
//    public static final String BASE_URL = "http://123.206.75.242:8090/tmsapi/";//测试环境
//    public static final String BASE_URL = "http://192.168.2.71:8090/tmsapi/";//测试环境
//    public static final String BASE_URL = "http://192.168.2.224:8080/tmsapi/";//测试环境

    public static final String BASE_IMAGE_URI = "http://bbkj-1253538594.picgz.myqcloud.com/";
    /**
     * 登录
     */
    public static final String LOGIN = BASE_URL + "partner/login";
    /**
     * selectpage
     * 首页数据
     */
    public static final String SELECTPAGE = BASE_URL + "partner/selectpage";

    /**
     * 派车单详情(已派车的订单)
     */
    public static final String PSELECT_DETAIL = BASE_URL + "partner/Pselect/detail";
    /**
     * 运单详情
     */
    public static final String PSELECTBYSENDCARID = BASE_URL + "partner/Pselectbysendcarid";

    /**
     * 运单列表
     */
    public static final String FINDCAR = BASE_URL + "partner/findcar";
    /**
     * 我的司机
     */
    public static final String SELECT_AVAILABLE = BASE_URL + "partner/select/available";
    /**
     * 搜索司机
     */
    public static final String SEARCHSIJI = BASE_URL + "partner/PselectByCondition";
    /**
     * 司机详情
     */
    public static final String SIJI_DETAILS = BASE_URL + "partner/PselectByfid";
    /**
     * 我的订单
     */
    public static final String MYORDER = BASE_URL + "partner/myorder";
    /**
     * 我的订单
     */
    public static final String SEARCH_ORDER = BASE_URL + "partner/findorder";

    /**
     * 轨迹
     */
    public static final String GUIJI = BASE_URL + "PLtrucklocation/PLgetlocation/history/format/sendcarid";
    /**
     * 车辆位置
     */
    public static final String WEIZHI = BASE_URL + "operationApp/getLocationBySendCarOrder";
    /**
     * 车辆位置
     */
    public static final String SYSTEM_PARAMS = BASE_URL + "tsendcarorder/selectsysparameter";
    /**
     *
     * 作业状态
     * */
    public static final String GETWORKTRACK = BASE_URL + "appcommon/getworktrack";

    /**
     *
     * 修改密码
     * */
    public static final String CHENGEPSD = BASE_URL + "partner/change";

    /**
     *获取验证码
     * system/send
     * */
    public static final String GETAUTHCODE = BASE_URL + "system/send";
    /**
     *直接派车
     * */
    public static final String PUBLISHSENDCAR = BASE_URL + "partner/publishSendCar";
    /**
     *推广码
     * */
    public static final String QRCODE = BASE_URL + "driverBD/QRcode/make";
    /**
     * 获取订单  人员等数量
     */

    public static final String FINDCOUNT=BASE_URL+"partner/findcount";
    /**
     * 检测版本
     *
     * (1 : 司机App  2 : 现场App  3 : 合伙人App  4 : 货主App)
     */
    public static final String CHECKVERSION=BASE_URL+"appcommon/getappversion";

}

