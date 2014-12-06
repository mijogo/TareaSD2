package cl.utfsm.inf.rmi.intefaces;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.io.Serializable;

public interface TokenServiceMgr extends Remote 
{
    public void start() throws RemoteException;
    public void stop() throws RemoteException;
	public void requestToken(int id, int sn) throws RemoteException;
	public void passToken(Token TokenU) throws RemoteException;
}
