/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 *
 * @author vahlawat
 */
public class Parser {

    String filename;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ScriptException {
        Parser p = new Parser("C:/Users/vahlawat/Documents/NetBeansProjects/Parser/src/parser/strategy.txt");
        try {
            p.parseFile();

            // TODO code application logic here
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    Parser(String filename) {
        this.filename = filename;

    }

    public int[] parseFile() throws FileNotFoundException, IOException, ScriptException {
        int mappings[] = new int[15];
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String word;

        while ((word = br.readLine()) != null) {
            String line[] = word.split(":");

            String numbers[] = line[0].split(",");

            ScriptEngineManager mgr = new ScriptEngineManager();
            ScriptEngine engine = mgr.getEngineByName("JavaScript");

            for (String number : numbers) {
                String rule = line[1];
                String wildCard = "x";

                String finalRule = rule.replaceAll(wildCard, number);

                Double map = (Double) engine.eval(finalRule);

                int mapValue = map.intValue();


                mappings[Integer.parseInt(number)] = mapValue;

            }


        }
       
        return mappings;

      
    }
}
