package com.example.dell.mobility_v1;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by Dell on 07-04-2016.
 */
public interface FrontEndBundle {

    public String getName();
    public JSONObject getDescriptor();
    public boolean isIncluded();
    public int placeholder();

    //include thumbnail as well

}
