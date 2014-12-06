package cl.utfsm.inf.rmi.intefaces;

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
	public RToken(int n)
	{LN = new int[n];}
	@Override
	public void finishRequest(int id, int[] RN, int n) {
		for (int i = 0; i < n; i++) {
			if (RN[i]==LN[i]+1) {
				LN[i]=RN[i];				
				Q.add(i);
			}
		}
	}
	
	@Override
	public boolean isEmptyQueue() {
		return Q.isEmpty();
	}
	
	@Override
	public int getFirstQueue() {
		return Q.remove(0);
	}
}
