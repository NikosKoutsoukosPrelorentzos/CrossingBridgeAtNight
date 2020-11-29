package com.company;

import java.util.ArrayList;

public class State {

    private int people = 5;
    private int sides = 2;
    private Person [][] tiles;
    private State father = null;
    private boolean waa;
    private int time=0;

    public State(){
        this.people = -1;
        this.tiles = null;
    }

    public boolean isWaa() {
        return waa;
    }

    public State(Person [][] tiles){
        this.people = tiles.length;
        this.tiles = new Person[this.people][this.sides];
        for(int i=0; i<this.people; i++)
        {
            for(int j=0; j<this.sides; j++)
            {
                this.tiles[i][j] = tiles[i][j];
            }
        }
    }

    public State (boolean fixed){
        if (fixed){
            Person p1 = new Person("Son", 1);
            Person p2 = new Person("Mom", 3);
            Person p3 = new Person("Dad", 6);
            Person p4 = new Person("Grandma", 8);
            Person p5 = new Person("Grandpa", 12);
            this.tiles =new Person [5][2];
            this.tiles[0][0]=p1;
            this.tiles[1][0]=p2;
            this.tiles[2][0]=p3;
            this.tiles[3][0]=p4;
            this.tiles[4][0]=p5;
            this.tiles[0][1]=null;
            this.tiles[1][1]=null;
            this.tiles[2][1]=null;
            this.tiles[3][1]=null;
            this.tiles[4][1]=null;
        }
    }

    public int getDimension()
    {
        return this.people;
    }

    public Person[][] getTiles()
    {
        return this.tiles;
    }

    public void setDimension(int dimension)
    {
        this.people = dimension;
    }


    public boolean crossEast(int i, int j, int k, int l){
        if(j==0 && l==0 && i!=k){
            this.tiles[i][j+1]=this.tiles[i][j];
            this.tiles[i][j]=null;
            this.tiles[k][l+1]=this.tiles[k][l];
            this.tiles[k][l]=null;
            return true;
        }
        return false;
    }

    public boolean crossWest(int i, int j){
        if(j==1){
            this.tiles[i][j-1]=this.tiles[i][j];
            this.tiles[i][j]=null;
            return true;
        }
        return false;
    }


    public ArrayList<State> getChildrenCrossingEast(int heuristic){

        ArrayList<State> children = new ArrayList<State>();
        State child = new State(this.tiles);

        for(int i=0; i<this.people-1; i++){
            for (int k=i+1; k<this.people; k++){

                if(this.tiles[i][0]!=null && this.tiles[k][0]!=null){
                    if (child.crossEast(i,0,k,0)){

                        child.setFather(this);
                        children.add(child);

                        if(this.tiles[i][0].getDelay() < this.tiles[k][0].getDelay()){
                            child.time = child.getFather().time + this.tiles[k][0].getDelay();
                        }else {
                            child.time = child.getFather().time + this.tiles[i][0].getDelay();
                        }

                    }
                    child.waa=true;
                    child = new State(this.tiles);
                }
            }
        }


        return children;
    }


    public ArrayList<State> getChildrenCrossingWest(int heuristic){

        ArrayList<State> children2 = new ArrayList<State>();
        State child2 = new State(this.tiles);

        for(int i=0; i<this.people; i++){
            if(this.tiles[i][1]!=null){

                if (child2.crossWest(i,1)){

                    child2.setFather(this);
                    children2.add(child2);
                    child2.time =child2.getFather().time + this.tiles[i][1].getDelay();
                }

                child2.waa=false;
                child2 = new State(this.tiles);
            }
        }


        return children2;
    }



    public State getFather() {
        return father;
    }

    public void setFather(State father) {
        this.father = father;
    }


    public boolean isTerminal()
    {
        for(int i=0; i<this.people; i++){
            if (this.tiles[i][0]!=null){
                return false;
            }
        }
        if (this.time < 30){
            return true;
        }
        return false;
    }

    public void print()
    {
        System.out.println("------------------------------------");
        System.out.println("Time= " + time);
        for(int i=0; i<this.people; i++)
        {
            for(int j=0; j<this.sides; j++)
            {
                if(this.tiles[i][j] == null)
                {
                    System.out.print("NULL");
                }
                else
                {
                    System.out.print(this.tiles[i][j].getName());
                }
                if(j < this.people - 1)
                {
                    System.out.print('\t');
                }
            }
            System.out.println();
        }
        System.out.println("------------------------------------");
    }

}