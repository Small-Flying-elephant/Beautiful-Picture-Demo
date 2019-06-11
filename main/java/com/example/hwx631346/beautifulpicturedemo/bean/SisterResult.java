package com.example.hwx631346.beautifulpicturedemo.bean;

import java.util.ArrayList;

public class SisterResult {

    private Boolean error;

    public ArrayList<SisterBean> result;

    public SisterResult(){

    }

    public SisterResult(boolean error,ArrayList<SisterBean> results){
        this.error = error;
        this.result = results;
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }


    public ArrayList<SisterBean> getResult(){
        return result;
    }

    public void setResult(ArrayList<SisterBean> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "GankResult{" +
                "error=" + error +
                ", results=" + result.toString() +
                '}';
    }
}
