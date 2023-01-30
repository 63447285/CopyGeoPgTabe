package com.geoway.atlas.common.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Atlas的xml中的对象
 * @author zhaotong 2022/12/22 16:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JacksonXmlRootElement(localName = "GPInstance")
public class ArgXml {

    @JacksonXmlProperty(localName = "Parameters")
    @JacksonXmlElementWrapper(useWrapping = false)
    private ArgParams parameters;
}
