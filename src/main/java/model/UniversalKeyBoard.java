package model;

import java.util.Observable;

/**
 * @author R. Pörtzgen
 */
public final class UniversalKeyBoard extends Observable{
    public static final int MAXSIZE = 10;
    private static UniversalKeyBoard INSTANCE;
    private String input = "";
    private String format = "";

    private UniversalKeyBoard() {

    }

    public static UniversalKeyBoard getINSTANCE() {
        if(INSTANCE == null){
            INSTANCE=new UniversalKeyBoard();
        }
        return INSTANCE;
    }


    public void addDigit(String digit){
        String input = this.input;
        if(input.length() < MAXSIZE){
        this.input = input+digit;}
    }

    public void resetInput(){
        setChanged();
        notifyObservers();
        this.input = "";
    }

    public String getInput() {
        return input;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
}
