package com.example.fatemeh_d.stack;


import java.util.List;


public class MyJsonResponse {
    boolean  has_more;
   int quota_max ;
   int quota_remaining;
    List<ITEMS> items ;

    public boolean isHas_more() {
        return has_more;
    }

    public void setHas_more(boolean has_more) {
        this.has_more = has_more;
    }

    public int getQuota_max() {
        return quota_max;
    }

    public void setQuota_max(int quota_max) {
        this.quota_max = quota_max;
    }

    public int getQuota_remaining() {
        return quota_remaining;
    }

    public void setQuota_remaining(int quota_remaining) {
        this.quota_remaining = quota_remaining;
    }

    public List<ITEMS> getItems() {
        return items;
    }

    public void setItems(List<ITEMS> items) {
        this.items = items;
    }
}


/* Retrofit 2.0 */

