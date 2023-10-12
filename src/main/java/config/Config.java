/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author AVShrez
 */
public class Config {

    private final static String CONFIGFILEPATH = "configuration/etc/configuration.properties";
    private static Properties props;

    private static void configLoader() {
        try (FileReader fileReader = new FileReader(CONFIGFILEPATH)) {
            props = new Properties();
            props.load(fileReader);
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Properties getConfig() {
        if (props == null) {
            configLoader();
        }
        return props;
    }
}
