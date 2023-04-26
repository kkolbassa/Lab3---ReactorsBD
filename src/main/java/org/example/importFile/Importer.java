package org.example.importFile;

import org.example.collections.ReactorCollection;

public abstract class Importer {
    private Importer neighbour;

    public Importer getNeighbour() {
        return neighbour;
    }

    public void setNeighbour(Importer neighbour) {
        this.neighbour = neighbour;
    }

    public abstract ReactorCollection readFile(String path);
}
