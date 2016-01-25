package com.example.saram.myapplication.backend;

/**
 * Created by saram on 1/25/2016.
 */

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import javax.inject.Named;

/** An endpoint class we are exposing */
@Api(
        name = "myLoginApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.saram.example.com",
                ownerName = "backend.myapplication.saram.example.com",
                packagePath=""
        )
)
public class MyEndpointUser {
    /** A simple endpoint method that takes a name and says Hi back */
    @ApiMethod(name = "myLoginApi")
    public User login(@Named("name") String name) {
        User response = new User();
        response.setUsername("Hi, " + name);

        return response;
    }
}
