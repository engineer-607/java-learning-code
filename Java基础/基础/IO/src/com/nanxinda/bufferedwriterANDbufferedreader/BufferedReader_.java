package com.nanxinda.bufferedwriterANDbufferedreader;

public class BufferedReader_ extends Reader_{
    Reader_ reader ;

    public BufferedReader_(Reader_ reader) {
        this.reader = reader;
    }

    @Override
    public void read() {
        if(reader instanceof FileReader_){
            reader.read();
        }else if(reader instanceof StringReader_){
            reader.read();
        }
    }
    public void readFiles(int num){
        for (int i = 0; i < num; i++) {
            reader.read();
        }
    }
    public  void readStrings(int num){
        for (int i = 0; i < num; i++) {
            reader.read();
        }
    }
}
