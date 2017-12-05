package com.babuwyt.daili.entity;

/**
 * Created by lenovo on 2017/8/22.
 */

public class LocationEntity {

    private String LocationType;//获取当前定位结果来源，如网络定位结果，详见定位类型表
    private double Latitude;//获取纬度
    private double Longitude;//获取经度
    private float Accuracy;//获取精度信息
    private String Address;//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
    private String Country;//国家信息
    private String Province;//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
    private String City;//城市信息
     private String District;//城区信息
     private String Street;//街道信息
     private String StreetNum;//街道门牌号信息
     private String CityCode;//城市编码
     private String AdCode;//地区编码
     private String AoiName;//获取当前定位点的AOI信息
     private String BuildingId;//获取当前室内定位的建筑物Id
     private String Floor;//获取当前室内定位的楼层
     private String GpsStatus;//获取GPS的当前状态

    public String getLocationType() {
        return LocationType;
    }

    public void setLocationType(String locationType) {
        LocationType = locationType;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    public float getAccuracy() {
        return Accuracy;
    }

    public void setAccuracy(float accuracy) {
        Accuracy = accuracy;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String district) {
        District = district;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public String getStreetNum() {
        return StreetNum;
    }

    public void setStreetNum(String streetNum) {
        StreetNum = streetNum;
    }

    public String getCityCode() {
        return CityCode;
    }

    public void setCityCode(String cityCode) {
        CityCode = cityCode;
    }

    public String getAdCode() {
        return AdCode;
    }

    public void setAdCode(String adCode) {
        AdCode = adCode;
    }

    public String getAoiName() {
        return AoiName;
    }

    public void setAoiName(String aoiName) {
        AoiName = aoiName;
    }

    public String getBuildingId() {
        return BuildingId;
    }

    public void setBuildingId(String buildingId) {
        BuildingId = buildingId;
    }

    public String getFloor() {
        return Floor;
    }

    public void setFloor(String floor) {
        Floor = floor;
    }

    public String getGpsStatus() {
        return GpsStatus;
    }

    public void setGpsStatus(String gpsStatus) {
        GpsStatus = gpsStatus;
    }
/**
     * amapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
     amapLocation.getLatitude();//获取纬度
     amapLocation.getLongitude();//获取经度
     amapLocation.getAccuracy();//获取精度信息
     amapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
     amapLocation.getCountry();//国家信息
     amapLocation.getProvince();//省信息
     amapLocation.getCity();//城市信息
     amapLocation.getDistrict();//城区信息
     amapLocation.getStreet();//街道信息
     amapLocation.getStreetNum();//街道门牌号信息
     amapLocation.getCityCode();//城市编码
     amapLocation.getAdCode();//地区编码
     amapLocation.getAoiName();//获取当前定位点的AOI信息
     amapLocation.getBuildingId();//获取当前室内定位的建筑物Id
     amapLocation.getFloor();//获取当前室内定位的楼层
     amapLocation.getGpsStatus();//获取GPS的当前状态
     //获取定位时间
     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
     Date date = new Date(amapLocation.getTime());
     df.format(date);
     */
}
