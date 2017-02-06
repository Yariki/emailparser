package com.fruitfactory.controllers;

import com.fruitfactory.infrastructure.OFItemsReader;
import com.fruitfactory.interfaces.IOFItemsReader;
import com.fruitfactory.models.OFItemsContainer;
import com.fruitfactory.models.response.OFEmailResponse;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Yariki on 1/29/2017.
 */
@Controller
@RequestMapping(value = "/parse")
public class OFEmailController {

    private final IOFItemsReader reader = new OFItemsReader();
    private final Logger logger = Logger.getLogger(OFEmailController.class);

    public OFEmailController() {
    }

    @RequestMapping(value = "/items",method = RequestMethod.POST)
    public OFEmailResponse putEmailContainer(@RequestBody String body){
        OFEmailResponse response = new OFEmailResponse(false,"");
        try {
            OFItemsContainer container = reader.parseContainer(body);




            response.setStatus(true);
        }catch(Exception ex){
            response.setMessage(ex.getMessage());
            logger.error(ex.getMessage());
        }

        return response;
    }

}
