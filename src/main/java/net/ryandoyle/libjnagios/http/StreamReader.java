package net.ryandoyle.libjnagios.http;

import java.io.InputStream;
import java.util.Scanner;

public class StreamReader {

    private final InputStream inputSteam;

    public StreamReader(InputStream inputStream){
        this.inputSteam = inputStream;
    }

    public String read(){
        Scanner s = new Scanner(inputSteam).useDelimiter("\\A");
        return s.hasNext() ? s.next() : "";
    }

}
