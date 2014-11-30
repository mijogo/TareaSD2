package cl.utfsm.inf.rmi.semaforo;

import cl.utfsm.inf.rmi.intefaces.*;
import java.util.List;
import java.util.ArrayList;

public class RToken implements Token
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1522577255399452944L;
	private int[] LN;
	private List<Integer> Q = new ArrayList<Integer>();
	
	@Override
	public void finishRequest(int id, int[] RN, int n) {
		LN = RN;
		Q.add(id);
	}
	
	@Override
	public boolean isEmptyQueue() {
		return Q.isEmpty();
	}
	
	@Override
	public int getFirstQueue() {
		return Q.remove(getFirstQueue());
	}
}
