package com.android.rahmadarifanhr.instadakwah.Bean;

import com.google.gson.annotations.Expose;

/**
 * Created by Rahmad Arifan Hr on 11/25/2016.
 */

public class User {
    @Expose
    private String name;
    @Expose
    private String photo_url;

    public String getName() {
        return name;
    }

    public String getPhoto_url() {
        return photo_url;
    }
}
