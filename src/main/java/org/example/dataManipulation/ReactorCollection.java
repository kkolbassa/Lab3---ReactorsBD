package org.example.dataManipulation;

import org.example.reactors.Reactor;

import javax.swing.tree.DefaultMutableTreeNode;
import java.util.ArrayList;

public class ReactorCollection {
    private ArrayList<Reactor> reactors ;

    public ReactorCollection() {
        reactors = new ArrayList<>();
    }

    public ArrayList<Reactor> getReactors() {
        return reactors;
    }

    public void setReactors(ArrayList<Reactor> reactors) {
        this.reactors = reactors;
    }

    public DefaultMutableTreeNode addInfo2GUI() {
        DefaultMutableTreeNode main = new DefaultMutableTreeNode("Реакторы");
        for (Reactor reactor: reactors) {
            main.add(reactor.getNode());
        }
        return main;
    }
}
