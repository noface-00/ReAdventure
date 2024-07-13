
package ReAdventure.BaseD;

public class Usuarios_L {

    /**
     * @return the User
     */
    public String getUser() {
        return User;
    }

    /**
     * @param User the User to set
     */
    public void setUser(String User) {
        this.User = User;
    }

    /**
     * @return the Clave
     */
    public String getClave() {
        return Clave;
    }

    /**
     * @param Clave the Clave to set
     */
    public void setClave(String Clave) {
        this.Clave = Clave;
    }

    public Usuarios_L(String User, String Clave) {
        this.User = User;
        this.Clave = Clave;
    }
    
    public Usuarios_L() {
    }
    
    
    private String User;
    private String Clave;
    
}
