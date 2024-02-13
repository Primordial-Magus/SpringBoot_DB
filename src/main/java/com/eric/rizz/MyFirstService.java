package com.eric.rizz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
//@PropertySource("classpath:custom.properties")
public class MyFirstService {

    private final MyFirstClass myFirstClass;

//    private Environment environment;

    @Value("${my.custom.property}")
    private String customProperty;

//    @Value("${my.prop}")
//    private String customPropertyFromAnotherFile;

    @Value("${my.custom.property.int}")
    private Integer customPropertyInt;

    public MyFirstService(
           @Qualifier("bean1") MyFirstClass myFirstClass) {
        this.myFirstClass = myFirstClass;
    }

    public String tellAStory() {
        return "the dependency is saying: " + myFirstClass.sayHello();
    }

//    public String getJavaVersion() {
//        return environment.getProperty("java.version");
//    }
//
//    public String getOsName() {
//        return environment.getProperty("os.name");
//    }
//
//    public String readProp() {
//        return environment.getProperty("my.custom.property");
//    }
//
//
//    @Autowired
//    public void setEnvironment(Environment environment) {
//        this.environment = environment;
//    }

//    public String getCustomPropertyFromAnotherFile() {
//        return customPropertyFromAnotherFile;
//    }
//
//    public void setCustomPropertyFromAnotherFile(String customPropertyFromAnotherFile) {
//        this.customPropertyFromAnotherFile = customPropertyFromAnotherFile;
//    }

    public String getCustomProperty() {
        return customProperty;
    }

    public void setCustomProperty(String customProperty) {
        this.customProperty = customProperty;
    }

    public Integer getCustomPropertyInt() {
        return customPropertyInt;
    }

    public void setCustomPropertyInt(Integer customPropertyInt) {
        this.customPropertyInt = customPropertyInt;
    }
}
