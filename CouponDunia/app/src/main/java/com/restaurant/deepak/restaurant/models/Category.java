
package com.restaurant.deepak.restaurant.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.restaurant.deepak.restaurant.interfaces.IDataModel;


public class Category implements IDataModel{

    @SerializedName("OfflineCategoryID")
    @Expose
    private String OfflineCategoryID;
    @SerializedName("Name")
    @Expose
    private String Name;
    @SerializedName("ParentCategoryID")
    @Expose
    private String ParentCategoryID;
    @SerializedName("CategoryType")
    @Expose
    private String CategoryType;

    /**
     * 
     * @return
     *     The OfflineCategoryID
     */
    public String getOfflineCategoryID() {
        return OfflineCategoryID;
    }

    /**
     * 
     * @param OfflineCategoryID
     *     The OfflineCategoryID
     */
    public void setOfflineCategoryID(String OfflineCategoryID) {
        this.OfflineCategoryID = OfflineCategoryID;
    }

    /**
     * 
     * @return
     *     The Name
     */
    public String getName() {
        return Name;
    }

    /**
     * 
     * @param Name
     *     The Name
     */
    public void setName(String Name) {
        this.Name = Name;
    }

    /**
     * 
     * @return
     *     The ParentCategoryID
     */
    public String getParentCategoryID() {
        return ParentCategoryID;
    }

    /**
     * 
     * @param ParentCategoryID
     *     The ParentCategoryID
     */
    public void setParentCategoryID(String ParentCategoryID) {
        this.ParentCategoryID = ParentCategoryID;
    }

    /**
     * 
     * @return
     *     The CategoryType
     */
    public String getCategoryType() {
        return CategoryType;
    }

    /**
     * 
     * @param CategoryType
     *     The CategoryType
     */
    public void setCategoryType(String CategoryType) {
        this.CategoryType = CategoryType;
    }

    public Category clone() {
        try {
            return (Category)super.clone();
        }catch (Exception e) {

        }
        return this;
    }

}
