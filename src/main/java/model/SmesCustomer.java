package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author 1st R. Pörtzgen
 * @author 2nd E. Koo
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SmesCustomer {
    private int code;
    private String rekeningNr;

    public SmesCustomer(int code, String rekeningNr) {
        this.code = code;
        this.rekeningNr = rekeningNr;
    }

    public SmesCustomer() {
    }

    public SmesCustomer(String rekeningNr) {
        this.rekeningNr = rekeningNr;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getRekeningNr() {
        return rekeningNr;
    }

    public void setRekeningNr(String rekeningNr) {
        this.rekeningNr = rekeningNr;
    }
}