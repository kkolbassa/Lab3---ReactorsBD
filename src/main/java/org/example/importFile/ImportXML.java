package org.example.importFile;

import org.example.reactorParser.ReactorParserXml;
import org.example.reactors.Reactor;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.util.ArrayList;

public class ImportXML extends Importer{
    public ImportXML() {
        setReactorParser(new ReactorParserXml());
    }

    @Override
    public ArrayList<Reactor> readFile(String path) {
        try {
            return getReactorParser().parse(path);
        } catch (Exception e){
            return getNeighbour().readFile(path);
        }
    }
}
