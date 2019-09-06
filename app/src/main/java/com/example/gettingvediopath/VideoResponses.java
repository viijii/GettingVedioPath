package com.example.gettingvediopath;

public class VideoResponses {
    String status,message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    data[] data;

    public VideoResponses.data[] getData() {
        return data;
    }

    public class data {

        String path;

        public String getPath() {
            return path;
        }
    }
}
