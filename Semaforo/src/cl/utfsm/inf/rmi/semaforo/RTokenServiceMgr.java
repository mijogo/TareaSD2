package cl.utfsm.inf.rmi.semaforo;

import java.rmi.RemoteException;
import java.util.List;

import cl.utfsm.inf.rmi.intefaces.*;

public class RTokenServiceMgr implements TokenServiceMgr {
	private List usar;
	public Token TokenP;
	private int[] RN;
	private boolean TengoToken;
	public RTokenServiceMgr(List synchedList )
	{
		usar = synchedList;
		TengoToken=false;
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
		RN[id]++;
		TokenP.finishRequest(id, RN, sn);
	}

	@Override
	public void passToken(Token TokenU) {
		this.TokenP=TokenU;
		this.TengoToken=true;
	}
	public boolean hasToken()
	{
		return this.TengoToken;
	}
	public void quitarToken()
	{
		this.TengoToken = false;
	}
}
