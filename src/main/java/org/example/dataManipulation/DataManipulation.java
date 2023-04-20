package org.example.dataManipulation;

import org.example.convertFiles.ConvertJson2types;
import org.example.reactorParser.ReactorParser;
import org.example.reactorParser.ReactorParserJson;
import org.example.reactorParser.ReactorParserXml;
import org.example.reactorParser.ReactorParserYaml;

import javax.swing.tree.DefaultMutableTreeNode;
import java.io.File;
import java.io.IOException;

public class DataManipulation {
    public static ReactorCollection reactors = new ReactorCollection();

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
    public String getExtension(String filePath){
        String extension = "";
        File file = new File(filePath);
        int dotIndex = file.getName().lastIndexOf(".");

        if (dotIndex > 0) {
            extension = file.getName().substring(dotIndex + 1);
        }
        return extension;
    }
    public void importData(String filePath) throws IOException {;
        ReactorParser reactorParser = null;
        switch (getExtension(filePath)){
            case "xml":{
                reactorParser = new ReactorParserXml();
                break;
            }
            case "yaml":{
                reactorParser = new ReactorParserYaml();
                break;
            }
            case "json": {
                reactorParser = new ReactorParserJson();
                break;
            }
        }
        reactors.setReactors(reactorParser.parse(filePath));
    }
    public DefaultMutableTreeNode addInfo2GUI(){
        return reactors.addInfo2GUI();

    }
}
