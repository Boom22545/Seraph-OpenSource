package si.seraph.opensource.seraphapi.exceptions;

public class NullJSONFileException extends Exception {

    public NullJSONFileException() {
        System.out.println("Hypixel API has found a null JSON file - cause: " + getCause());
    }

}
