package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class SpaceSearcher {

    private ArrayList<State> states;
    private HashSet<State> closedSet;
    private ArrayList<State> terminalStates;
    private int min;

    SpaceSearcher()
    {
        this.terminalStates = new ArrayList<State>();
        this.states = new ArrayList<State>();
        this.closedSet = new HashSet<State>();
    }


    public State BFSClosedSet(State initialState)
    {
        if(initialState.isTerminal())
        {
            return initialState;
        }
        this.closedSet.add(initialState);

        this.states.addAll(initialState.getChildrenCrossingEast(1));


        while(states.size() > 0)
        {
            State currentState = this.states.remove(0);

            currentState.print();

            if(currentState.isTerminal())
            {
                terminalStates.add(currentState);
            }

            if(!closedSet.contains(currentState))
            {
                closedSet.add(currentState);

                if (currentState.isWaa()){

                    this.states.addAll(currentState.getChildrenCrossingWest(1));

                } else {

                    this.states.addAll(currentState.getChildrenCrossingEast(1));
                }
            }
        }

        State someTerminal = this.terminalStates.remove(0);
        min = someTerminal.getTime();
        State finalTerminal = someTerminal;

        while(terminalStates.size()>0){

            if (someTerminal.getTime()<min){
                min = someTerminal.getTime();
                finalTerminal = someTerminal;
            }
            someTerminal = this.terminalStates.remove(0);

        }
        return finalTerminal;
    }
}

