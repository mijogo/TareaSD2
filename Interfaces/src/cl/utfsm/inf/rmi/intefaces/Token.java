package cl.utfsm.inf.rmi.intefaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.io.Serializable;

public interface Token extends Serializable {
    public void finishRequest( int id, int RN[], int n);   // actualizar Token para solicitudes existentes
    public boolean isEmptyQueue();
    public int getFirstQueue();
}
