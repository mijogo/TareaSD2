package cl.utfsm.inf.rmi.semaforo;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import cl.utfsm.inf.rmi.intefaces.*;

public class Semaforo {
	//private static String Uproceso = "Uproceso";
	//private static String Tproceso = "Tproceso";
	private static String NToken = "UToken";
	
	public static void main(String[] args) 
	{
		if (System.getSecurityManager() == null) 
		{
            System.setSecurityManager(new SecurityManager());
        }
		try
		{
            
            TokenServiceProxy UTokenServiceProxy = new RTokenServiceProxy(args[0],args[1],args[2]);
        	while(true)
        	{
        		//procesamiento
        		 Thread.sleep(1000);
        		 UTokenServiceProxy.getToken();
        		 //seccion critica
        		 UTokenServiceProxy.freeToken();
        	}
            
		} catch (Exception e) {
            System.err.println(" " + e.getMessage());
            e.printStackTrace();
        }
	}
}
