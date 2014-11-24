package cl.utfsm.inf.rmi.controlador;

import cl.utfsm.inf.rmi.intefaces.*;

import java.rmi.registry.*;
import java.io.*;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Controlador 
{
	private String[] proceso;
	private static String NToken = "UToken";
	
	public static void main(String[] args) 
	{
		if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try 
        {
            Registry registry = LocateRegistry.getRegistry(args[0]);
            
        	TokenServiceMgr[] comp = new TokenServiceMgr[Integer.parseInt(args[1])];
        	for(int i=0;i<=Integer.parseInt(args[1]);i++)
        	{
        		comp[i] = (TokenServiceMgr) registry.lookup("Tproceso"+i);
        	}
        	
            Token UToken = new RToken();
			Token stub = (Token) UnicastRemoteObject.exportObject((Remote) UToken, 0);
            registry.rebind(NToken, (Remote) stub);
            
            
        } catch (Exception e) {
            System.err.println(" " + e.getMessage());
            e.printStackTrace();
        }
	}
}