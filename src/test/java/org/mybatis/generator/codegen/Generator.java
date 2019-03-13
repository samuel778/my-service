package org.mybatis.generator.codegen;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author radioend
 * @version v1.0
 *  Generator.java Create on 2015年10月3日 下午12:23:00
 * <p>
 *
 * </p>
 */
public class Generator {
    public static void main(String[] args) throws IOException, XMLParserException, InvalidConfigurationException,
            SQLException, InterruptedException {

        List<String> warnings = new ArrayList<String>();
        boolean overwrite = true;
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(Generator.class.getResourceAsStream("/mybatis/generator/generatorConfig.xml"));
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
        myBatisGenerator.generate(null);
        System.out.println("success");
    }
}
