
package ReAdventure.BaseD;

public class Usuarios {

    /**
     * @return the ContrasenaR
     */
    public String getContrasenaR() {
        return ContrasenaR;
    }

    /**
     * @param ContrasenaR the ContrasenaR to set
     */
    public void setContrasenaR(String ContrasenaR) {
        this.ContrasenaR = ContrasenaR;
    }

    /**
     * @return the Edad
     */
    public String getEdad() {
        return Edad;
    }

    /**
     * @param Edad the Edad to set
     */
    public void setEdad(String Edad) {
        this.Edad = Edad;
    }

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
     * @return the Nombre
     */
    public String getNombre() {
        return Nombre;
    }

    /**
     * @param Nombre the Nombre to set
     */
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    /**
     * @return the Apellido
     */
    public String getApellido() {
        return Apellido;
    }

    /**
     * @param Apellido the Apellido to set
     */
    public void setApellido(String Apellido) {
        this.Apellido = Apellido;
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
     * @return the Curso
     */
    public String getCurso() {
        return Curso;
    }

    /**
     * @param Curso the Curso to set
     */
    public void setCurso(String Curso) {
        this.Curso = Curso;
    }

    /**
     * @return the Genero
     */
    public String getGenero() {
        return Genero;
    }

    /**
     * @param Genero the Genero to set
     */
    public void setGenero(String Genero) {
        this.Genero = Genero;
    }

    public Usuarios(String Edad, String Cedula, String Nombre, String Apellido, String Contrasena, String Curso, String Genero, String ContrasenaR) {
        this.Edad = Edad;
        this.Cedula = Cedula;
        this.Nombre = Nombre;
        this.Apellido = Apellido;
        this.Contrasena = Contrasena;
        this.Curso = Curso;
        this.Genero = Genero;
        this.ContrasenaR = ContrasenaR;
    }
    
    public Usuarios(String Cedula, String Contrasena) {
        this.Cedula = Cedula;
        this.Contrasena = Contrasena;
    }
    
    private String Edad;
    private String Cedula;
    private String Nombre;
    private String Apellido;
    private String Contrasena;
    private String Curso;
    private String Genero;
    private String ContrasenaR;
    
}
