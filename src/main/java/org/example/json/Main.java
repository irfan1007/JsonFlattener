package org.example.json;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {

        String json = "";
        //read JSON from program args
        if(args != null && args.length >=1 ){
            json = args[0];
        }

        // read JSON from command line stdin
        else{
            InputStreamReader isReader = new InputStreamReader(System.in);
            BufferedReader bufReader = new BufferedReader(isReader);
            String line;
            try {
                while (bufReader.ready() && (line = bufReader.readLine()) != null) {
                    if (line != null) {
                        json = json.concat(line);
                    } else {
                        break;
                    }
                }
            }
                catch (Exception e) {
                    System.out.println("Error while reading input from stdin "+e);
                }
            }

        if(json == null || json.length() == 0){
            throw new IllegalArgumentException("Received invalid JSON input");
        }
        String flattenedJson = JsonFlattener.flattenJson(json);
        System.out.println("========= Input JSON ===========");
        System.out.println(json);
        System.out.println("\n========= Flattened JSON ===========");
        System.out.println(flattenedJson);
    }
}
