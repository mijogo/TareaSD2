package cl.utfsm.inf.rmi.semaforo;

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
	TokenServiceMgr stub2;
	RTokenServiceMgr UTokenServiceMgr;
	TokenServiceMgr[] comp;
	String prog,n,id;
	public RTokenServiceProxy(String prog,String n,String id)
	{
		try
		{
			this.prog = prog;
			this.n = n;
			this.id = id;
	        this.registry = LocateRegistry.getRegistry(prog);
	        UTokenServiceMgr = new RTokenServiceMgr(synchedList);
	        stub2 = (TokenServiceMgr) UnicastRemoteObject.exportObject((Remote) UTokenServiceMgr, 0);
	        this.registry.rebind("Tproceso"+id,(Remote) stub2); 
            Thread.sleep(5000);            
            comp = new TokenServiceMgr[Integer.parseInt(n)];
        	for(int i=0;i<=Integer.parseInt(n);i++)
        	{
        		if(i!=Integer.parseInt(id))
        			comp[i] = (TokenServiceMgr) registry.lookup("Tproceso"+i);
        	}
        	this.esperar();
		} catch (Exception e) {
            System.err.println(" " + e.getMessage());
            e.printStackTrace();
        }
	}
	@Override
	public void getToken() {
		for(int i=0;i<Integer.parseInt(n);i++)
		{
			try {
        		if(i!=Integer.parseInt(id))
        			comp[i].requestToken(Integer.parseInt(this.id), Integer.parseInt(this.n));
			} catch (NumberFormatException | RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void freeToken() 
	{
		if(!UTokenServiceMgr.TokenP.isEmptyQueue())
		{
			try {
				comp[UTokenServiceMgr.TokenP.getFirstQueue()].passToken(UTokenServiceMgr.TokenP);
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

	public static void esperar() throws InterruptedException 
	{
		synchronized (synchedList) 
		{
			synchedList.wait();
		}
	}
}
