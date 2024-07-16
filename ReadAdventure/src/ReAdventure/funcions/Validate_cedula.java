package ReAdventure.funcions;

public class Validate_cedula {

    public boolean isValidCedula(String cedula) {
        // La cédula debe tener exactamente 10 dígitos.
        if (cedula.length() != 10 || !cedula.matches("\\d+")) {
            return false;
        }

        int alternador = 0;
        int suma = 0;

        // Iterar sobre los primeros 9 dígitos de la cédula.
        for (int i = 0; i < 9; i++) {
            int valor = Character.getNumericValue(cedula.charAt(i));
            // Multiplicar por 2 si alternador es 0, de lo contrario por 1.
            valor *= (alternador == 0) ? 2 : 1;
            alternador = 1 - alternador;

            // Si el valor resultante es mayor o igual a 10, restar 9.
            if (valor >= 10) {
                valor -= 9;
            }

            suma += valor;
        }

        // Obtener el dígito verificador (el décimo dígito de la cédula).
        int digitoVerificador = Character.getNumericValue(cedula.charAt(9));

        // Calcular el complemento de 10 del residuo de la suma.
        int comp = suma % 10;
        int com_1 = (comp == 0) ? 0 : 10 - comp;

        // Comparar el dígito verificador con el complemento calculado.
        return digitoVerificador == com_1;
    }
}
