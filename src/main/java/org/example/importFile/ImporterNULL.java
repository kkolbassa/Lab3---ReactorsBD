package org.example.importFile;

import org.example.reactors.Reactor;

import java.util.ArrayList;

public class ImporterNULL extends Importer{
    @Override
    public ArrayList<Reactor> readFile(String path) {
        return null;
    }
}
