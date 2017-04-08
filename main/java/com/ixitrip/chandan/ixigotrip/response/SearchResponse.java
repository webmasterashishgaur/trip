package com.ixitrip.chandan.ixigotrip.response;

import com.ixitrip.chandan.ixigotrip.background.SearchList;

import java.util.ArrayList;

/**
 * Created by chandan on 4/8/2017.
 */

public class SearchResponse {
    public ArrayList<SearchList> allocateLists;


    public SearchResponse(ArrayList<SearchList> allocateLists) {
        this.allocateLists = allocateLists;

    }
}
