package Resourse;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {
    RequestSpecification specrequest;
    public RequestSpecification requestSpecification() throws IOException {
        PrintStream log=new PrintStream(new FileOutputStream("Log_report.txt"));
        specrequest=new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUri"))
                .addQueryParam("key","qaclick123")
                 .setContentType(ContentType.JSON)
                 .addFilter(RequestLoggingFilter.logRequestTo(log))
                 .addFilter(ResponseLoggingFilter.logResponseTo(log))
                 .build();
         return specrequest;
    }
    public static String getGlobalValue(String key) throws IOException {
        Properties prop=new Properties();
        FileInputStream fis=new FileInputStream("src/test/java/Resourse/global.properties");
        prop.load(fis);
        return prop.getProperty(key);

    }
}
