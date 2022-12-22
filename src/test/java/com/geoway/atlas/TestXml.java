package com.geoway.atlas;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.geoway.atlas.common.xml.ArgXml;

import java.io.File;
import java.io.IOException;

/**
 * @author zhaotong 2022/12/22 16:29
 */
public class TestXml {

    public static void main(String[] args) {
        String arg = "D:\\Codebase\\java\\atlas-restful\\src\\test\\resources\\e9d4ac7a-6651-4bc1-b407-aef116f4d972.xml";
        XmlMapper xmlMapper =
                (XmlMapper) new XmlMapper()
                        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                        .enable(SerializationFeature.INDENT_OUTPUT);
        ArgXml argXml = new ArgXml();
        try {
            argXml = xmlMapper.readValue(new File(arg), ArgXml.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(1);
    }
}
