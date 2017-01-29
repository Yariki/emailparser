package com.fruitfactory.controllers;

import com.fruitfactory.models.response.OFEmailResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Yariki on 1/29/2017.
 */
@Controller
@RequestMapping(value = "/email")
public class OFEmailController {


    @RequestMapping(value = "/items",method = RequestMethod.POST)
    public OFEmailResponse putEmailContainer(@RequestBody String body){
        OFEmailResponse response = new OFEmailResponse(false,"");
        try {
            response.setStatus(true);
        }catch(Exception ex){
            response.setMessage(ex.getMessage());
        }

        return response;
    }

}
