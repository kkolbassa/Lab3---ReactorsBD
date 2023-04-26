package org.example.importFile;

import org.example.reactorParser.ReactorParser;
import org.example.reactors.Reactor;

import java.util.ArrayList;

public abstract class Importer {
    private Importer neighbour;
    private ReactorParser reactorParser;

    public ReactorParser getReactorParser() {
        return reactorParser;
    }

    public void setReactorParser(ReactorParser reactorParser) {
        this.reactorParser = reactorParser;
    }

    public Importer getNeighbour() {
        return neighbour;
    }

    public void setNeighbour(Importer neighbour) {
        this.neighbour = neighbour;
    }

    public abstract ArrayList<Reactor> readFile(String path);
}
