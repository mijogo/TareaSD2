package cl.utfsm.inf.rmi.semaforo;

import java.rmi.RemoteException;
import java.util.List;

import cl.utfsm.inf.rmi.intefaces.*;

public class RTokenServiceMgr implements TokenServiceMgr {
	private List usar;
	public Token TokenP;
	public RTokenServiceMgr(List synchedList )
	{
		usar = synchedList;
	}
	@Override
	public void start() throws RemoteException {
		usar.notify();
	}

	@Override
	public void stop() throws RemoteException {
		// TODO Auto-generated method stub

	}

	@Override
	public void requestToken(int id, int sn) throws RemoteException {
	}

	@Override
	public void passToken(Token TokenU) {
		// TODO Auto-generated method stub

	}

}
