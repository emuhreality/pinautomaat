package services;


import com.google.gson.Gson;
import model.RestResponse;
import model.SmesCustomer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author E. Koo
 */
public class LinkAtmTerminalService {

    UniversalPostRequest universalPostRequest = new UniversalPostRequest();

    /**
     * Validate terminal request
     * @param validateSmesCustomer get attributes
     * @return message
     */
    public String validateLinkTerminalRequest(SmesCustomer validateSmesCustomer) {
        Map<String, String > params = new HashMap<>();
        // map vullen
        params.put("iban", validateSmesCustomer.getRekeningNr());
        params.put("code", String.valueOf(validateSmesCustomer.getCode()));

        // uri aanvullen en params meegeven
        String returnJsonString = universalPostRequest.postRequest(params, "validate-code");

        // map de return String json naar RestResponse object
        Gson gson = new Gson();
        RestResponse response = gson.fromJson(returnJsonString, RestResponse.class);

        return response.getMessage();
    }

    public String validateCustomer(SmesCustomer validateAccount){
       Map<String, String> params = new HashMap<>();
       params.put("iban", validateAccount.getRekeningNr());

       // uri aanvullen
        String returnJsonString = universalPostRequest.postRequest(params, "/check-for-request");

        Gson gson = new Gson();
        RestResponse response = gson.fromJson(returnJsonString, RestResponse.class);

        return response.getMessage();
    }
}
