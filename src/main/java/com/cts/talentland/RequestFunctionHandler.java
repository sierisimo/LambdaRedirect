package com.cts.talentland;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.Map;
import java.util.logging.Logger;

public class RequestFunctionHandler implements RequestHandler<Map<String, String>, String> {
    private String VERSION = "0.0.7";

    private final Logger LOGGER = Logger.getLogger(RequestFunctionHandler.class.getName());

    @Override
    public String handleRequest(Map<String, String> input, Context context) {
        LOGGER.info("Handling request");
        String redirectURL = "http://www.cognizant.com";
        String userAgent = input.get("User-Agent");
        if(userAgent != null){
            if(userAgent.matches(".*Android.*")){
                redirectURL = "https://play.google.com/store/apps";
            }else if(userAgent.matches(".*Apple-iP[hao].*")){
                redirectURL = "https://itunes.apple.com/mx/app/feria-todos-santos-colima-2017/id1290548021?mt=8";
            }
        }

        throw new RuntimeException(new ResponseFound(redirectURL));

        //return "You invoked a lambda function->" + VERSION + ": " + sb.toString();
    }

    class ResponseFound extends Throwable {
        public ResponseFound(String uri) {
            super(uri);
        }
    }
}
