package com.thepracticaldeveloper.objectmapperbasics.samplebeans;

import java.security.cert.X509Certificate;
import java.text.ParseException;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.RSASSAVerifier;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.util.X509CertUtils;
import com.nimbusds.jwt.SignedJWT;

public class Test {

    public static void main(String[] args) throws ParseException {

        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";

        String payload = Test.parseJWT(token);
        
        System.out.println(payload);

        
    }

    private static String parseJWT1(String accessToken) throws java.text.ParseException, JOSEException {
        X509Certificate cert = getCert("accessToken");
        RSAKey rsaJWK = RSAKey.parse(cert);
        JWSVerifier verifier = new RSASSAVerifier(rsaJWK);

        var payload = ""; 
        var decodedJWT = SignedJWT.parse(accessToken);
        decodedJWT.verify(verifier);

        var header = decodedJWT.getHeader().toString();
        payload = new String(decodedJWT.getPayload().toBase64URL().decode());
        
        return payload;
    }


    private static String parseJWT(String accessToken) throws java.text.ParseException {
        var payload = ""; 
        var decodedJWT = SignedJWT.parse(accessToken);
        var header = decodedJWT.getHeader().toString();
        payload = new String(decodedJWT.getPayload().toBase64URL().decode());
        
        return payload;
    }

    private static X509Certificate getCert(String encodedCert) {
        X509Certificate cert = X509CertUtils.parse(encodedCert);
        return cert;

    }
    
}
