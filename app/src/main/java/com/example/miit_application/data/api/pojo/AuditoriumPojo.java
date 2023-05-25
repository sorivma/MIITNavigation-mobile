package com.example.miit_application.data.api.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AuditoriumPojo {
    @Expose
    @SerializedName("auditoriumNumber")
    String auditoriumNumber;

    public AuditoriumPojo(String auditoriumNumber) {
        this.auditoriumNumber = auditoriumNumber;
    }

    public String getAuditoriumNumber() {
        return auditoriumNumber;
    }

    public void setAuditoriumNumber(String auditoriumNumber) {
        this.auditoriumNumber = auditoriumNumber;
    }
}
