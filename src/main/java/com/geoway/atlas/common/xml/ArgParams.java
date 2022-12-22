package com.geoway.atlas.common.xml;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Atlas的xml中的对象
 * @author zhaotong 2022/12/22 16:33
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArgParams {

    @JacksonXmlProperty(localName = "Param")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<ArgParam> argParams;
}
