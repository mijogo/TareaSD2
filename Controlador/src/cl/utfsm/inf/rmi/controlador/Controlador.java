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
		Registry registry;
		String serverAddress="localhost";
		String serverPort="3232";
		int n=Integer.parseInt(args[0]);
		TokenU = new RToken(n);
        try 
        {
            registry=LocateRegistry.getRegistry(serverAddress,(new Integer(serverPort)).intValue());
        	TokenServiceMgr[] comp = new TokenServiceMgr[n];
        	for(int i=0;i<n;i++)
        	{
        		comp[i] = (TokenServiceMgr) registry.lookup("Tproceso"+i);
        		comp[i].start();
        		Thread.sleep(500)
;        	}
			comp[0].passToken(TokenU);
			System.out.println("Token enviado a Tproceso:0");
        } catch (Exception e) {
            System.err.println(" " + e.getMessage());
            e.printStackTrace();
        }
	}
}
