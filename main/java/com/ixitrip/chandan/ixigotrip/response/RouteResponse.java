package com.ixitrip.chandan.ixigotrip.response;

import com.ixitrip.chandan.ixigotrip.background.RouteList;
import com.ixitrip.chandan.ixigotrip.background.SearchList;

import java.util.ArrayList;

/**
 * Created by chandan on 4/8/2017.
 */

public class RouteResponse {
   public RouteList allocateLists;


    public RouteResponse(RouteList allocateLists) {
        this.allocateLists = allocateLists;

    }
}
