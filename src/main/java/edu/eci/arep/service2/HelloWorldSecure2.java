package edu.eci.arep.service2;

import edu.eci.arep.URLReader;

import static spark.Spark.*;

public class HelloWorldSecure2 {
    public static void main(String[] args) {
        //API: secure(keystoreFilePath, keystorePassword, truststoreFilePath, truststorePassword);
        secure(getKeyStore(), getPwdStore(), null, null);

        port(getPort());

        get("/secure-local", (req, res) -> "Hello World Secure 2");
        get("/secure-remote", (req, res) -> URLReader.secureUrl("https://localhost:5001/secure-local", "keystores/ssl1/ecikeystore.p12", "123456"));
    }

    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 5002; //returns default port if heroku-port isn't set (i.e. on localhost)
    }

    static String getKeyStore() {
        if (System.getenv("KEYSTORE") != null) {
            return System.getenv("KEYSTORE");
        }
        return "keystores/ssl2/ecikeystore2.p12";
    }

    static String getPwdStore() {
        if (System.getenv("KEYSTOREPWD") != null) {
            return System.getenv("KEYSTOREPWD");
        }
        return "123456";
    }
}
