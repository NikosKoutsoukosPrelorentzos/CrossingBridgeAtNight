package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

public class SpaceSearcher {

    private ArrayList<State> states;
    private HashSet<State> closedSet;

    SpaceSearcher()
    {
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
                return currentState;
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
        return null;
    }
}
