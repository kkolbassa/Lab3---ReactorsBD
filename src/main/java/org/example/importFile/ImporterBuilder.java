package org.example.importFile;

import org.example.collections.ReactorCollection;
import org.example.reactors.Reactor;

import java.util.ArrayList;

public class ImporterBuilder {
    private Importer xml;
    private Importer yaml;
    private Importer json;
    private Importer nulI;

    public ArrayList<Reactor> getData(String path){
        setParam();
        return json.readFile(path);
    }

    private void setParam() {
        this.xml = new ImportXML();
        this.yaml = new ImporterYAML();
        this.json = new ImporterJSON();
        this.nulI = new ImporterNULL();

        json.setNeighbour(yaml);
        yaml.setNeighbour(xml);
        xml.setNeighbour(nulI);
    }


}
