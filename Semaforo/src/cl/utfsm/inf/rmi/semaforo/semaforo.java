package cl.utfsm.inf.rmi.semaforo;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import cl.utfsm.inf.rmi.intefaces.*;

public class Semaforo {
	private static String Uproceso = "Uproceso1";
	private static String Tproceso = "Tproceso1";
	private static String NToken = "UToken";
	public static void main(String[] args) 
	{
		if (System.getSecurityManager() == null) 
		{
            System.setSecurityManager(new SecurityManager());
        }
		try
		{
            Registry registry = LocateRegistry.getRegistry(args[0]);
            
            TokenServiceProxy UTokenServiceProxy = new RTokenServiceProxy();
            TokenServiceProxy stub = (TokenServiceProxy) UnicastRemoteObject.exportObject((Remote) UTokenServiceProxy, 0);
            registry.rebind(Uproceso,(Remote) stub);
            
            TokenServiceMgr UTokenServiceMgr = new RTokenServiceMgr();
            TokenServiceMgr stub2 = (TokenServiceMgr) UnicastRemoteObject.exportObject((Remote) UTokenServiceMgr, 0);
            registry.rebind(Tproceso,(Remote) stub2);      
            
            TokenServiceProxy[] comp = new TokenServiceProxy[Integer.parseInt(args[1])];
        	for(int i=0;i<=Integer.parseInt(args[1]);i++)
        	{
        		comp[i] = (TokenServiceProxy) registry.lookup("Uproceso"+i);
        	}
            
        	Token UToken= (Token) registry.lookup(NToken);
            
		} catch (Exception e) {
            System.err.println(" " + e.getMessage());
            e.printStackTrace();
        }
	}

}
