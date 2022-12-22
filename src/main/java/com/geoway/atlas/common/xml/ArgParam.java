package com.geoway.atlas.common.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Atlas的xml中的对象
 * @author zhaotong 2022/12/22 16:23
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArgParam {

    @JacksonXmlProperty(localName = "Name")
    private String name;

    @JacksonXmlText
    private String value;
}
