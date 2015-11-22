
package com.restaurant.deepak.restaurant.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.restaurant.deepak.restaurant.interfaces.IDataModel;

public class Status implements IDataModel {

    @SerializedName("rcode")
    @Expose
    private Integer rcode;
    @SerializedName("message")
    @Expose
    private String message;

    /**
     * 
     * @return
     *     The rcode
     */
    public Integer getRcode() {
        return rcode;
    }

    /**
     * 
     * @param rcode
     *     The rcode
     */
    public void setRcode(Integer rcode) {
        this.rcode = rcode;
    }

    /**
     * 
     * @return
     *     The message
     */
    public String getMessage() {
        return message;
    }

    /**
     * 
     * @param message
     *     The message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    public Status clone() {
        try {
            return (Status)super.clone();
        }catch (Exception e) {

        }
        return this;
    }


}
