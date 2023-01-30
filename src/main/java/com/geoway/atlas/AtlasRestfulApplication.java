package com.geoway.atlas;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.geoway.atlas.common.xml.ArgParam;
import com.geoway.atlas.common.xml.ArgXml;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.request.RequestContextListener;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

@SpringBootApplication
public class AtlasRestfulApplication {

	public static final String SERVER_ADDRESS = "server.address";
	public static final String SERVER_PORT = "server.port";

	public static final String SERVER_CPU = "server.cpu";

	public static final String SERVER_MEMERYSIZE = "server.memorySize";

	public static final String SERVER_SCALE = "server.scale";

	public static final String SERVER_TOTALPERCENTAGE = "server.totalPercentage";

	public static final String SERVER_USEDPERCENTAGE = "server.usedPercentage";

	public static final String SERVER_USABLEPERCENTAGE = "server.usablePercentage";

	public static void main(String[] args) {
		String[] arguments = getXmlArguments(args);
		SpringApplication.run(AtlasRestfulApplication.class, arguments);
	}

	/**
	 * 从atlas传递的xml文件中获取参数
	 * @param arguments 输入参数
	 * @return 返回完整参数
	 */
	public static String[] getXmlArguments(String[] arguments){

		// option类型的args保留
		String[] optionArgs =
				Arrays.stream(arguments).filter(a -> StringUtils.startsWith(a, "--")).toArray(String[]::new);

		// 非option的参数
		String[] commonArgs =
				Arrays.stream(arguments).filter(a -> !StringUtils.startsWith(a, "--")).toArray(String[]::new);
		// 参数必须等于1，即xml路径
		if(commonArgs.length == 1){
			String arg = commonArgs[0];
			XmlMapper xmlMapper =
					(XmlMapper) new XmlMapper()
							.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
							.enable(SerializationFeature.INDENT_OUTPUT);
			ArgXml argXml;
			try {
				argXml = xmlMapper.readValue(new File(arg), ArgXml.class);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}

			String[] nArgs = new String[8];
			for(ArgParam argParam:argXml.getParameters().getArgParams()){
				if(StringUtils.equals(argParam.getName(), "serverPort")){
					nArgs[0] = "--server.port=" + argParam.getValue();
				}
				if(StringUtils.equals(argParam.getName(), "zkUri")){
					nArgs[1] = "--server.center=" + argParam.getValue();
				}
				if(StringUtils.equals(argParam.getName(), "serverCpu")){
					nArgs[2] = "--server.cpu=" + argParam.getValue();
				}
				if(StringUtils.equals(argParam.getName(), "serverScale")){
					nArgs[3] = "--server.scale=" + argParam.getValue();
				}
				if(StringUtils.equals(argParam.getName(), "serverMemorySize")){
					nArgs[4] = "--server.memorySize=" + argParam.getValue();
				}
				if(StringUtils.equals(argParam.getName(), "serverTotalPercentage")){
					nArgs[5] = "--server.totalPercentage=" + argParam.getValue();
				}
				if(StringUtils.equals(argParam.getName(), "serverUsedPercentage")){
					nArgs[6] = "--server.usedPercentage=" + argParam.getValue();
				}
				if(StringUtils.equals(argParam.getName(), "serverUsablePercentage")){
					nArgs[7] = "--server.usablePercentage=" + argParam.getValue();
				}

			}
			return ArrayUtils.addAll(nArgs, optionArgs);

		}else {
			throw new RuntimeException("参数数量不符合!");
		}
	}

	@Bean
	public RequestContextListener requestContextListener() {
		return new RequestContextListener();
	}

}
