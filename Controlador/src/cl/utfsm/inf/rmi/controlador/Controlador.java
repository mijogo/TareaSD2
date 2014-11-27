package cl.utfsm.inf.rmi.controlador;

import cl.utfsm.inf.rmi.intefaces.*;

import java.rmi.registry.*;
import java.io.*;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Controlador 
{
	private static Token TokenU;
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
        		comp[i].start();
        	}
			comp[0].passToken(TokenU);
        } catch (Exception e) {
            System.err.println(" " + e.getMessage());
            e.printStackTrace();
        }
	}
}
