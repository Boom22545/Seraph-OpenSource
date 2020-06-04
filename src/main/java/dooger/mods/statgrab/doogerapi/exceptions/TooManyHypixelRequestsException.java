package dooger.mods.statgrab.doogerapi.exceptions;

public class TooManyHypixelRequestsException extends Exception {

    public TooManyHypixelRequestsException() {
        System.out.println("API Limit! Wait 1 minute");
    }

}
