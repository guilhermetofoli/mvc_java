package br.edu.unicesumar.utils;

public class Utils {

    public static boolean validarCPF(String cpf){
        if(cpf.length() < 11){
            return false;
        }
        return true;
    }

}
