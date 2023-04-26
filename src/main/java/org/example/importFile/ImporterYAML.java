package org.example.importFile;

import org.example.reactorParser.ReactorParserYaml;
import org.example.reactors.Reactor;

import java.io.IOException;
import java.util.ArrayList;

public class ImporterYAML extends Importer {
    public ImporterYAML() {
        setReactorParser(new ReactorParserYaml());
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
