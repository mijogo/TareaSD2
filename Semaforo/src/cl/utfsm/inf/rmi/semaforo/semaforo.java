package cl.utfsm.inf.rmi.semaforo;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import cl.utfsm.inf.rmi.intefaces.*;

public class Semaforo {
	//private static String Uproceso = "Uproceso";
	//private static String Tproceso = "Tproceso";
	//para levantar rmi rmiregistry -J-Djava.rmi.server.useCodebaseOnly=false
	private static String NToken = "UToken";
	
	public static void main(String[] args) 
	{
		try
		{	System.out.println("Iniciando Semaforo_"+ args[1]+"...");
            TokenServiceProxy UTokenServiceProxy = new RTokenServiceProxy(args[0],args[1]);
			//TokenServiceProxy UTokenServiceProxy = new RTokenServiceProxy("1","0");
        	while(true)
        	{
        		//procesamiento
        		 Thread.sleep(1000);
        		 UTokenServiceProxy.getToken();
        		 System.out.println("Semaforo_"+ args[1]+": Rojo.");
        		 //seccion critica
        		 Thread.sleep(2000);
        		 UTokenServiceProxy.freeToken();
        	}
            
		} catch (Exception e) {
            System.err.println(" " + e.getMessage());
            e.printStackTrace();
        }
	}
}
