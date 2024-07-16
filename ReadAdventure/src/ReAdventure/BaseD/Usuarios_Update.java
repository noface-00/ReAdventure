package ReAdventure.BaseD;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author mkevi
 */
public class Usuarios_Update {

    /**
     * @return the Cedula
     */
    public String getCedula() {
        return Cedula;
    }

    /**
     * @param Cedula the Cedula to set
     */
    public void setCedula(String Cedula) {
        this.Cedula = Cedula;
    }

    /**
     * @return the Contrasena
     */
    public String getContrasena() {
        return Contrasena;
    }

    /**
     * @param Contrasena the Contrasena to set
     */
    public void setContrasena(String Contrasena) {
        this.Contrasena = Contrasena;
    }

    /**
     * @return the Verf_Contrasena
     */
    public String getVerf_Contrasena() {
        return Verf_Contrasena;
    }

    /**
     * @param Verf_Contrasena the Verf_Contrasena to set
     */
    public void setVerf_Contrasena(String Verf_Contrasena) {
        this.Verf_Contrasena = Verf_Contrasena;
    }
 
    public Usuarios_Update(String Cedula, String Contrasena, String Verf_Contrasena) {
        this.Cedula = Cedula;
        this.Contrasena = Contrasena;
        this.Verf_Contrasena = Verf_Contrasena;
    }

    public Usuarios_Update() {
    }
    
    private String Cedula;
    private String Contrasena;
    private String Verf_Contrasena;
}
