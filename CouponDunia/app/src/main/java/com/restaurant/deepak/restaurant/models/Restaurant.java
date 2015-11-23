
package com.restaurant.deepak.restaurant.models;

import android.text.TextUtils;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.restaurant.deepak.restaurant.interfaces.IDataModel;


public class Restaurant implements IDataModel{

    @SerializedName("SubFranchiseID")
    @Expose
    private String SubFranchiseID;
    @SerializedName("OutletID")
    @Expose
    private String OutletID;
    @SerializedName("OutletName")
    @Expose
    private String OutletName;
    @SerializedName("BrandID")
    @Expose
    private String BrandID;
    @SerializedName("Address")
    @Expose
    private String Address;
    @SerializedName("NeighbourhoodID")
    @Expose
    private String NeighbourhoodID;
    @SerializedName("CityID")
    @Expose
    private String CityID;
    @SerializedName("Email")
    @Expose
    private Object Email;
    @SerializedName("Timings")
    @Expose
    private String Timings;
    @SerializedName("CityRank")
    @Expose
    private Object CityRank;
    @SerializedName("Latitude")
    @Expose
    private String Latitude;
    @SerializedName("Longitude")
    @Expose
    private String Longitude;
    @SerializedName("Pincode")
    @Expose
    private Object Pincode;
    @SerializedName("Landmark")
    @Expose
    private Object Landmark;
    @SerializedName("Streetname")
    @Expose
    private Object Streetname;
    @SerializedName("BrandName")
    @Expose
    private String BrandName;
    @SerializedName("OutletURL")
    @Expose
    private String OutletURL;
    @SerializedName("NumCoupons")
    @Expose
    private Integer NumCoupons;
    @SerializedName("NeighbourhoodName")
    @Expose
    private String NeighbourhoodName;
    @SerializedName("PhoneNumber")
    @Expose
    private String PhoneNumber;
    @SerializedName("CityName")
    @Expose
    private String CityName;
    @SerializedName("Distance")
    @Expose
    private Double Distance;
    @SerializedName("Categories")
    @Expose
    private List<Category> Categories = new ArrayList<Category>();
    @SerializedName("LogoURL")
    @Expose
    private String LogoURL;
    @SerializedName("CoverURL")
    @Expose
    private String CoverURL;
    private boolean favorite;

    /**
     * 
     * @return
     *     The SubFranchiseID
     */
    public String getSubFranchiseID() {
        return SubFranchiseID;
    }

    /**
     * 
     * @param SubFranchiseID
     *     The SubFranchiseID
     */
    public void setSubFranchiseID(String SubFranchiseID) {
        this.SubFranchiseID = SubFranchiseID;
    }

    /**
     * 
     * @return
     *     The OutletID
     */
    public String getOutletID() {
        return OutletID;
    }

    /**
     * 
     * @param OutletID
     *     The OutletID
     */
    public void setOutletID(String OutletID) {
        this.OutletID = OutletID;
    }

    /**
     * 
     * @return
     *     The OutletName
     */
    public String getOutletName() {
        return OutletName;
    }

    /**
     * 
     * @param OutletName
     *     The OutletName
     */
    public void setOutletName(String OutletName) {
        this.OutletName = OutletName;
    }

    /**
     * 
     * @return
     *     The BrandID
     */
    public String getBrandID() {
        return BrandID;
    }

    /**
     * 
     * @param BrandID
     *     The BrandID
     */
    public void setBrandID(String BrandID) {
        this.BrandID = BrandID;
    }

    /**
     * 
     * @return
     *     The Address
     */
    public String getAddress() {
        return Address;
    }

    /**
     * 
     * @param Address
     *     The Address
     */
    public void setAddress(String Address) {
        this.Address = Address;
    }

    /**
     * 
     * @return
     *     The NeighbourhoodID
     */
    public String getNeighbourhoodID() {
        return NeighbourhoodID;
    }

    /**
     * 
     * @param NeighbourhoodID
     *     The NeighbourhoodID
     */
    public void setNeighbourhoodID(String NeighbourhoodID) {
        this.NeighbourhoodID = NeighbourhoodID;
    }

    /**
     * 
     * @return
     *     The CityID
     */
    public String getCityID() {
        return CityID;
    }

    /**
     * 
     * @param CityID
     *     The CityID
     */
    public void setCityID(String CityID) {
        this.CityID = CityID;
    }

    /**
     * 
     * @return
     *     The Email
     */
    public Object getEmail() {
        return Email;
    }

    /**
     * 
     * @param Email
     *     The Email
     */
    public void setEmail(Object Email) {
        this.Email = Email;
    }

    /**
     * 
     * @return
     *     The Timings
     */
    public String getTimings() {
        return Timings;
    }

    /**
     * 
     * @param Timings
     *     The Timings
     */
    public void setTimings(String Timings) {
        this.Timings = Timings;
    }

    /**
     * 
     * @return
     *     The CityRank
     */
    public Object getCityRank() {
        return CityRank;
    }

    /**
     * 
     * @param CityRank
     *     The CityRank
     */
    public void setCityRank(Object CityRank) {
        this.CityRank = CityRank;
    }

    /**
     * 
     * @return
     *     The Latitude
     */
    public String getLatitude() {
        return Latitude;
    }

    /**
     * 
     * @param Latitude
     *     The Latitude
     */
    public void setLatitude(String Latitude) {
        this.Latitude = Latitude;
    }

    /**
     * 
     * @return
     *     The Longitude
     */
    public String getLongitude() {
        return Longitude;
    }

    /**
     * 
     * @param Longitude
     *     The Longitude
     */
    public void setLongitude(String Longitude) {
        this.Longitude = Longitude;
    }

    /**
     * 
     * @return
     *     The Pincode
     */
    public Object getPincode() {
        return Pincode;
    }

    /**
     * 
     * @param Pincode
     *     The Pincode
     */
    public void setPincode(Object Pincode) {
        this.Pincode = Pincode;
    }

    /**
     * 
     * @return
     *     The Landmark
     */
    public Object getLandmark() {
        return Landmark;
    }

    /**
     * 
     * @param Landmark
     *     The Landmark
     */
    public void setLandmark(Object Landmark) {
        this.Landmark = Landmark;
    }

    /**
     * 
     * @return
     *     The Streetname
     */
    public Object getStreetname() {
        return Streetname;
    }

    /**
     * 
     * @param Streetname
     *     The Streetname
     */
    public void setStreetname(Object Streetname) {
        this.Streetname = Streetname;
    }

    /**
     * 
     * @return
     *     The BrandName
     */
    public String getBrandName() {
        return BrandName;
    }

    /**
     * 
     * @param BrandName
     *     The BrandName
     */
    public void setBrandName(String BrandName) {
        this.BrandName = BrandName;
    }

    /**
     * 
     * @return
     *     The OutletURL
     */
    public String getOutletURL() {
        return OutletURL;
    }

    /**
     * 
     * @param OutletURL
     *     The OutletURL
     */
    public void setOutletURL(String OutletURL) {
        this.OutletURL = OutletURL;
    }

    /**
     * 
     * @return
     *     The NumCoupons
     */
    public Integer getNumCoupons() {
        return NumCoupons;
    }

    /**
     * 
     * @param NumCoupons
     *     The NumCoupons
     */
    public void setNumCoupons(Integer NumCoupons) {
        this.NumCoupons = NumCoupons;
    }

    /**
     * 
     * @return
     *     The NeighbourhoodName
     */
    public String getNeighbourhoodName() {
        return NeighbourhoodName;
    }

    /**
     * 
     * @param NeighbourhoodName
     *     The NeighbourhoodName
     */
    public void setNeighbourhoodName(String NeighbourhoodName) {
        this.NeighbourhoodName = NeighbourhoodName;
    }

    /**
     * 
     * @return
     *     The PhoneNumber
     */
    public String getPhoneNumber() {
        return PhoneNumber;
    }

    /**
     * 
     * @param PhoneNumber
     *     The PhoneNumber
     */
    public void setPhoneNumber(String PhoneNumber) {
        this.PhoneNumber = PhoneNumber;
    }

    /**
     * 
     * @return
     *     The CityName
     */
    public String getCityName() {
        return CityName;
    }

    /**
     * 
     * @param CityName
     *     The CityName
     */
    public void setCityName(String CityName) {
        this.CityName = CityName;
    }

    /**
     * 
     * @return
     *     The Distance
     */
    public Double getDistance() {
        return Distance;
    }

    /**
     * 
     * @param Distance
     *     The Distance
     */
    public void setDistance(Double Distance) {
        this.Distance = Distance;
    }

    /**
     * 
     * @return
     *     The Categories
     */
    public List<Category> getCategories() {
        return Categories;
    }

    /**
     * 
     * @param Categories
     *     The Categories
     */
    public void setCategories(List<Category> Categories) {
        this.Categories = Categories;
    }

    /**
     * 
     * @return
     *     The LogoURL
     */
    public String getLogoURL() {
        return LogoURL;
    }

    /**
     * 
     * @param LogoURL
     *     The LogoURL
     */
    public void setLogoURL(String LogoURL) {
        this.LogoURL = LogoURL;
    }

    /**
     * 
     * @return
     *     The CoverURL
     */
    public String getCoverURL() {
        return CoverURL;
    }

    /**
     * 
     * @param CoverURL
     *     The CoverURL
     */
    public void setCoverURL(String CoverURL) {
        this.CoverURL = CoverURL;
    }

    public Restaurant clone() {
        try {
            Restaurant restaurant = (Restaurant)super.clone();
            List<Category> categoryList = new ArrayList<>();
            if(null != Categories) {
                for(Category category : Categories) {
                    categoryList.add(category.clone());
                }
            }
            return restaurant;
        }catch (Exception e) {

        }
        return this;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof  Restaurant) {
            Restaurant restaurant = (Restaurant)o;
            if(TextUtils.equals(restaurant.BrandID,getBrandID()) &&(TextUtils.equals(restaurant.getOutletID(),getOutletID()))) {
                return  true;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return getBrandID().hashCode() + getOutletID().hashCode();
    }
}
