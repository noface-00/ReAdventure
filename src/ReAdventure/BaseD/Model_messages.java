
package ReAdventure.BaseD;

public class Model_messages {

    /**
     * @return the success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * @param success the success to set
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    public Model_messages(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    
    public Model_messages() {
    }
    
    
    private boolean success;
    private String message;
    
    
}
