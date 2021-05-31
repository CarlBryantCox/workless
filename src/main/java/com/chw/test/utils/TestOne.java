package com.chw.test.utils;


import com.spire.doc.Document;
import com.spire.doc.FileFormat;

import java.io.FileOutputStream;

public class TestOne {

    public static void main(String[] args) throws Exception{
        Document test = new Document();
        test.loadFromFile("output.docx");
        FileOutputStream fileOutputStream = new FileOutputStream("test.pdf");
        test.saveToStream(fileOutputStream, FileFormat.PDF);
    }
}
