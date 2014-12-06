package cl.utfsm.inf.rmi.controlador;

import cl.utfsm.inf.rmi.intefaces.*;

import java.rmi.registry.*;
import java.io.*;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Controlador 
{
	private static Token TokenU = new RToken();
	public static void main(String[] args) 
	{
		Registry registry;
		String serverAddress="localhost";
		String serverPort="3232";
		String text=args[0];
        try 
        {
            registry=LocateRegistry.getRegistry(serverAddress,(new Integer(serverPort)).intValue());
        	//TokenServiceMgr[] comp = new TokenServiceMgr[Integer.parseInt(args[1])];
            TokenServiceMgr[] comp = new TokenServiceMgr[1];
        	//for(int i=0;i<=Integer.parseInt(args[1]);i++)
        	for(int i=0;i<1;i++)
        	{
        		comp[i] = (TokenServiceMgr) registry.lookup("Tproceso"+i);
        		//comp[i].start();
        	}
			comp[0].passToken(TokenU);
			System.out.println("Token enviado a Tproceso:0");
        } catch (Exception e) {
            System.err.println(" " + e.getMessage());
            e.printStackTrace();
        }
	}
}
