package edu.eci.arep.service1;

import edu.eci.arep.URLReader;

import static spark.Spark.*;

public class HelloWorldSecure1 {
    public static void main(String[] args) {
        //API: secure(keystoreFilePath, keystorePassword, truststoreFilePath, truststorePassword);
        secure(getKeyStore(), getPwdStore(), null, null);

        port(getPort());

        get("/secure-local", (req, res) -> "Hello World Secure 1");
        get("/secure-remote", (req, res) -> URLReader.secureUrl("https://ec2-44-201-131-31.compute-1.amazonaws.com:5002/secure-local", "target/keystores/ssl2/ecikeystoreRemote2.p12", "123456"));
    }

    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 5001; //returns default port if heroku-port isn't set (i.e. on localhost)
    }

    static String getKeyStore() {
        if (System.getenv("KEYSTORE") != null) {
            return System.getenv("KEYSTORE");
        }
        return "target/keystores/ssl1/ecikeystoreRemote.p12";
    }

    static String getPwdStore() {
        if (System.getenv("KEYSTOREPWD") != null) {
            return System.getenv("KEYSTOREPWD");
        }
        return "123456";
    }
}