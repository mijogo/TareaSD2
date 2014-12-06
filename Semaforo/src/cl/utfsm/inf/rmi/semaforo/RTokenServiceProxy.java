package cl.utfsm.inf.rmi.semaforo;

import java.net.InetAddress;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import cl.utfsm.inf.rmi.intefaces.*;

public class RTokenServiceProxy implements TokenServiceProxy {
	private static List synchedList = Collections.synchronizedList(new LinkedList());
	private Registry registry;
    String	thisAddress;
    int	thisPort,sn=0;
	TokenServiceMgr stub;
	RTokenServiceMgr UTokenServiceMgr;
	TokenServiceMgr[] comp;
	String prog,n,id;
	private static String NToken = "Tproceso";
	public RTokenServiceProxy(String n,String id)
	{	
		try
		{
            thisAddress = (InetAddress.getLocalHost()).toString();
            thisPort=3232;
			this.n = n;
			this.id = id;
	        UTokenServiceMgr = new RTokenServiceMgr(synchedList,Integer.parseInt(n));
	        this.registry = LocateRegistry.getRegistry(thisPort);
	        try {
				this.registry.list();
			} catch (Exception e) {
				this.registry = LocateRegistry.createRegistry( thisPort );
			}
	        stub = (TokenServiceMgr) UnicastRemoteObject.exportObject(UTokenServiceMgr, 0);
	        this.registry.rebind(NToken+id,stub); 
            Thread.sleep(5000);            
            comp = new TokenServiceMgr[Integer.parseInt(n)];
            while(!UTokenServiceMgr.hasStarted()){
            	try {
    				Thread.sleep(500);
    			} catch (InterruptedException e) {
    				e.printStackTrace();
    			}
            }
        	for(int i=0;i<Integer.parseInt(n);i++)
        	{
        		if(i!=Integer.parseInt(id))
        			comp[i] = (TokenServiceMgr) registry.lookup("Tproceso"+i);
        	}
        	//this.esperar();
    		System.out.println("Semaforo_"+id+": Verde.");
		} catch (Exception e) {
            System.err.println(" " + e.getMessage());
            e.printStackTrace();
        }
	}
	@Override
	public void getToken() {
		System.out.println("Semaforo_"+id+": Amarillo.");
		if(!UTokenServiceMgr.hasToken()){
			sn++;
			for(int i=0;i<Integer.parseInt(n);i++)
			{
	        	if(i!=Integer.parseInt(id))
					try {
						comp[i].requestToken(Integer.parseInt(this.id),sn);
					} catch (NumberFormatException e) {
						//  Auto-generated catch block
						e.printStackTrace();
					} catch (RemoteException e) {
						//  Auto-generated catch block
						e.printStackTrace();
					}
			}
			//TODO Bloquear aqui hasta que llegue el token
			while(!UTokenServiceMgr.hasToken()){
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println("Semaforo_"+id+" tiene el token");
		}
	}

	@Override
	public void freeToken() 
	{
		System.out.println("Semaforo_"+id+": Verde.");
		if(!UTokenServiceMgr.tokenvacio())
		{
			try {
				int target = UTokenServiceMgr.TokenP.getFirstQueue();
				comp[target].passToken(UTokenServiceMgr.TokenP);
				System.out.println("Pasando el token a el semaforo_"+target);
				UTokenServiceMgr.quitarToken();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean hasToken() {
		return UTokenServiceMgr.hasToken();
	}

	public void esperar() throws InterruptedException 
	{
		synchronized (synchedList) 
		{
			synchedList.wait();
		}
	}
}
