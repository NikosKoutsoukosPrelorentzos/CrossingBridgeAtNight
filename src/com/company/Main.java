package com.company;

import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args){

		State initialState = new State(true);
		initialState.print();
		System.out.println("***************");
		SpaceSearcher spaceSearcher = new SpaceSearcher();
		State terminalState = null;

		long start = System.currentTimeMillis();
		long end = System.currentTimeMillis();

		start = System.currentTimeMillis();
		terminalState = spaceSearcher.BFSClosedSet(initialState);
		end = System.currentTimeMillis();
		if(terminalState == null)
		{
			System.out.println("Could not find solution");
		}
		else
		{
			State temp = terminalState;
			ArrayList<State> path = new ArrayList<State>();
			path.add(terminalState);
			while(temp.getFather()!=null)
			{
				path.add(temp.getFather());
				temp = temp.getFather();
			}
			Collections.reverse(path);
			System.out.println("Finished in "+path.size()+" steps!");
			for(State item : path)
			{
				item.print();
			}
		}
		System.out.println("BFS with closed set search time: " + (double)(end - start) / 1000 + " sec.");
		System.out.println("***************");


    }
}
