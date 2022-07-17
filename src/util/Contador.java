package util;

public abstract class Contador {
    private static Integer VALOR = 0;

    public static Integer proximoId(){
        VALOR++;
        return VALOR;
    }
}