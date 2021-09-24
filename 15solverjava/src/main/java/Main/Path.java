package main;

public class Path {

    private State lastNode;
    private State[] states;
    private int size;
    private int indexNext;

    public Path() {
        this.size = 20;
        this.states = new State[20];
        this.indexNext = 0;
    }

    public void addState(State state) {
        if (this.indexNext == this.size) {
            State[] newStates = new State[this.size * 2];
            for (int i = 0; i < this.size; i++) {
                newStates[i] = this.states[i];
            }
            newStates[this.size + 1] = state;
            this.states = newStates;
            this.indexNext = this.size + 1;
            this.size = this.size * 2;
            lastNode = state;
        } else {
            this.states[indexNext] = state;
            this.lastNode = state;
            this.indexNext++;
        }
    }

    public State getLast() {
        State returnable = this.lastNode;
        this.lastNode = this.states[indexNext - 1];
        this.indexNext--;
        return returnable;
    }

}
