package cl.utfsm.inf.rmi.intefaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.io.Serializable;

public interface TokenServiceProxy {
	public void getToken();			// Solicitar el token y entrar para ingresar SC
	public void freeToken();		// Liberar el token al salir de SC
    public boolean hasToken();      // verifica si se tiene el Token (�til en estado ocioso)
}
