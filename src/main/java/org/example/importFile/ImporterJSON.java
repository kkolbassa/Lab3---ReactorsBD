package org.example.importFile;

import org.example.reactorParser.ReactorParserJson;
import org.example.reactors.Reactor;

import java.io.IOException;
import java.util.ArrayList;

public class ImporterJSON extends Importer{
    public ImporterJSON() {
        setReactorParser(new ReactorParserJson());
    }

    @Override
    public ArrayList<Reactor> readFile(String path) {
        try {
            return getReactorParser().parse(path);
        } catch (Exception e) {
            return getNeighbour().readFile(path);
        }
    }
}
