package com.Piggy.PiggyBox.AdapterItmes;



public class ListViewItem {

    private String dateStr,savedInt, totalInt;

    public void setDate(String date){
        dateStr = date;
    }
    public void setSaved(String save){
        savedInt = save;
    }
    public void setTotal(String total) {
        totalInt = total;
    }

    public String getDate(){
        return this.dateStr;
    }
    public String getSaved(){
        return  this.savedInt;
    }
    public String getTotal(){
        return  this.totalInt;
    }
}
