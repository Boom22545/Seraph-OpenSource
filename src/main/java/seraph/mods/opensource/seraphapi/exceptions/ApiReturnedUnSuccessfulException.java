package seraph.mods.opensource.seraphapi.exceptions;

public class ApiReturnedUnSuccessfulException extends Exception {

    public ApiReturnedUnSuccessfulException() {
        System.out.println("Failed API Request");
    }

}