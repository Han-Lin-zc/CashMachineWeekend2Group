package rocks.zipcode.atm;

/**
 * @author ZipCodeWilmington
 */
public class ActionResult<T> {

// Generics <T> and <E> takes in whatever data type and changes it to that data type
    // in the method which its called.

    private T data;
    private String errorMessage;

    private ActionResult(T data) {
        this.data = data;
    }

    private ActionResult(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public T getData() {
        return data;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public boolean isSuccess() {
        return data != null;
    }

    public static <E> ActionResult<E> success(E data) {
        return new ActionResult<E>(data);
    }

    public static <E> ActionResult<E> fail(String errorMessage) {
        return new ActionResult<E>(errorMessage);
    }
}
