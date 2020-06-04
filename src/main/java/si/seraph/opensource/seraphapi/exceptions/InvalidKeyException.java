package si.seraph.opensource.seraphapi.exceptions;

public class InvalidKeyException extends Exception {

    public InvalidKeyException() {
        System.out.println("Hypixel API key is invalid!");
    }

}
