package com.example.trabajo.retrofit;

import java.nio.charset.Charset;
import okio.ByteString;

import static java.nio.charset.StandardCharsets.ISO_8859_1;


/** Factory for HTTP authorization credentials. */
public final class Credentials {
    private Credentials() {
    }

    /** Returns an auth credential for the Basic scheme. */
    public static String basic(String userName, String password) {
        return basic(userName, password, ISO_8859_1);
    }

    public static String basic(String userName, String password, Charset charset) {
        String usernameAndPassword = userName + ":" + password;
        String encoded = ByteString.encodeString(usernameAndPassword, charset).base64();
        return "Basic " + encoded;
    }
}
