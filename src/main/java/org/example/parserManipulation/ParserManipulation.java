package org.example.parserManipulation;

import org.example.collections.ReactorCollection;
import org.example.convertFiles.ConvertJson2types;
import org.example.importFile.ImporterBuilder;

import java.io.File;
import java.io.IOException;


public class ParserManipulation {
    private ImporterBuilder ib = new ImporterBuilder();

    public void createFiles() throws IOException {
        if (new File("./data/Reactors_json.json").exists()) {
            ConvertJson2types converter = new ConvertJson2types();
            if (!new File("./data/Reactors_xml.xml").exists()){
                converter.convert2xml();
            }
            if (!new File("./data/Reactors_yaml.yaml").exists()){
                converter.convert2yaml();
            }
        }
    }
    public ReactorCollection importData(String filePath) {
        ReactorCollection reactorCollection = new ReactorCollection();
        reactorCollection.setReactors(ib.getData(filePath));
        return reactorCollection;
    }

}
