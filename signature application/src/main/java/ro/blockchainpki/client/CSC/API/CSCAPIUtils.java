package ro.blockchainpki.client.CSC.API;

import ro.blockchainpki.client.CSC.JSON_FORMATS.CredentialsInfoResponse;
import com.itextpdf.io.codec.Base64;

import java.io.ByteArrayInputStream;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

public class CSCAPIUtils {

    /*This Method was developed ONLY for POC purposes.
    * Warning: Do not use it as it is.*/
    private static Certificate GetRootCA()
    {
        String RootCA = "MIIDqDCCApCgAwIBAgIQK2bbwnU3ML1CIz5RvN1ByjANBgkqhkiG9w0BAQsFADBu" +
                "MQswCQYDVQQGEwJSTzEXMBUGA1UEChMOVHJhbnMgU3BlZCBTUkwxHzAdBgNVBAsT" +
                "FkZPUiBURVNUIFBVUlBPU0VTIE9OTFkxJTAjBgNVBAMTHFRyYW5zIFNwZWQgUm9v" +
                "dCBDQSBHMiAtIFRFU1QwHhcNMTYxMjAyMTMxNjQ4WhcNMzExMjAyMTMyNjQ3WjBu" +
                "MQswCQYDVQQGEwJSTzEXMBUGA1UEChMOVHJhbnMgU3BlZCBTUkwxHzAdBgNVBAsT" +
                "FkZPUiBURVNUIFBVUlBPU0VTIE9OTFkxJTAjBgNVBAMTHFRyYW5zIFNwZWQgUm9v" +
                "dCBDQSBHMiAtIFRFU1QwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQC7" +
                "z3iVoaZf3nuiX+cw9O86dkBPtHgkS/W2a09xKTd2WXx++tD4Ci2425IWuhUy1O5i" +
                "jQoVPfCVha8DCAbmT7ElBpPzY71HitYuMZMk2u12oJqHZmbrStlOwcTW/uKYb3/1" +
                "tK6E1cAh9Ux482sXEhkeSM6nhZKe5MBrSTx+jZOYcxWhXPenxI3oqwivWH2BBqrx" +
                "A3muct5ZeOh9UM3oJG4vhyst1tNbgT6cKfHpSVhMuNoiY4IrjkEJGcm/R5+oC7TA" +
                "KpTqoDaobkVakxJkcgHMn04vzv5ZUQuP+v7t2XuRjSJO7eBIue7+m4RMbYiVsUbi" +
                "tXGvBL22oQDrdFlmAHVlAgMBAAGjQjBAMA4GA1UdDwEB/wQEAwIBBjAPBgNVHRMB" +
                "Af8EBTADAQH/MB0GA1UdDgQWBBSY/DmCEWASgk61OQkZWx22nRYZyTANBgkqhkiG" +
                "9w0BAQsFAAOCAQEAqJBXU5l21vZbc8B2v2jMYiSsQFn2L/iLNw4dPEnlwUORHREq" +
                "vv4cVwlNyBJG4PQTWSqVzfbS4KaDOMfO2eiq2HI4QJ/v9VnKre5uwgCNhSj5fGIo" +
                "3SaTljKw7/gtjorRTOJfZPd6F0YLXkLKvah+ZScjMqjB9szWqIhcbiuBJBoi5vXB" +
                "XSalZk9ZbEpYkriyQ1iBJtvYs6WbZErGibMzwWAD+YXYrDKI2e95UC84bJloHPCW" +
                "du3LeH+LbqqVmssr3jTQfSUHIfiqGRjPTeP2rMnt2aaXTmx3G4iS7DaNxdrsQhCc" +
                "xM/c9DuEWrcQVuPrsrTRLeOV4gUPlw5wk/duJg==";

        try {


            byte []encodedCert = Base64.decode(RootCA);
            ByteArrayInputStream inputStream =  new ByteArrayInputStream(encodedCert);
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            X509Certificate cert = (X509Certificate)certificateFactory.generateCertificate(inputStream);

            return cert;
        }catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    /*This Method was developed ONLY for POC purposes.
    * Warning: Do not use it as it is.*/
    private static Certificate GetSubCA()
    {
        String SubCA = "MIIE5jCCA86gAwIBAgIKS7HdUQAAAAAACzANBgkqhkiG9w0BAQsFADBuMQswCQYD" +
                "VQQGEwJSTzEXMBUGA1UEChMOVHJhbnMgU3BlZCBTUkwxHzAdBgNVBAsTFkZPUiBU" +
                "RVNUIFBVUlBPU0VTIE9OTFkxJTAjBgNVBAMTHFRyYW5zIFNwZWQgUm9vdCBDQSBH" +
                "MiAtIFRFU1QwHhcNMTcwMTEyMTMzMTM2WhcNMjcwMTEyMTM0MTM2WjB0MQswCQYD" +
                "VQQGEwJSTzEXMBUGA1UEChMOVHJhbnMgU3BlZCBTUkwxHzAdBgNVBAsTFkZPUiBU" +
                "RVNUIFBVUlBPU0VTIE9OTFkxKzApBgNVBAMTIlRyYW5zIFNwZWQgTW9iaWxlIGVJ" +
                "REFTIFFDQSAtIFRFU1QwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQDf" +
                "DHu8uwd7qGWeC9UF2bS+tDCvQfsdO23HUikPVPbDIUClYgItXEv/t8SBjTCZUZPV" +
                "lnxIPFj3o/gXYwDq3ggpAOP1fREtWv7m83aOLT6xaFFzotzBRTEqKLSj5nFQWxq9" +
                "Emd4v8jce3IRuKi16U9MCwuH2FcoODrZ4WHlLuD25/6wE8oPBqIYt5llZqRaIur5" +
                "ZhU+Cq1Hd40k/bchWslVjzFxZQIhq9AIu63I0VlZtMjpQUIhxEfzbSBNBD5Cm6Ef" +
                "cmnnY9xN32/ikr5hUM86BXwEKXfMbgqLBc0cSkA/b12j8UTKUvYj1MTPdVom3YVQ" +
                "RlelrNzgbYeX60kRGJHlAgMBAAGjggF+MIIBejAdBgNVHQ4EFgQUCvGAR+TEUYHU" +
                "KozGlW3pi3O1BMwwHwYDVR0jBBgwFoAUmPw5ghFgEoJOtTkJGVsdtp0WGckwQAYD" +
                "VR0fBDkwNzA1oDOgMYYvaHR0cDovL3d3dy50cmFuc3NwZWQucm8vY3JsL3RzX3Jv" +
                "b3RfZzJfdGVzdC5jcmwwewYIKwYBBQUHAQEEbzBtMD8GCCsGAQUFBzAChjNodHRw" +
                "Oi8vd3d3LnRyYW5zc3BlZC5yby9jYWNlcnRzL3RzX3Jvb3RfZzJfdGVzdC5jcnQw" +
                "KgYIKwYBBQUHMAGGHmh0dHA6Ly9vY3NwLXRlc3QudHJhbnNzcGVkLnJvLzBVBgNV" +
                "HSAETjBMMAkGBwQAi+xAAQIwPwYLKwYBBAGCuB0EAQEwMDAuBggrBgEFBQcCARYi" +
                "aHR0cDovL3d3dy50cmFuc3NwZWQucm8vcmVwb3NpdG9yeTASBgNVHRMBAf8ECDAG" +
                "AQH/AgEAMA4GA1UdDwEB/wQEAwIBBjANBgkqhkiG9w0BAQsFAAOCAQEAd4VtoRcG" +
                "Q5hTjQiDTlhpTFLvjMuQgSkfYk1hKwHXPlGhkitL6J94fZ7a5eKne32kBAI900ps" +
                "wAKWJu376rtxXxaMt2yCabJc+04eY4TphjDFfVFo5YJzlsBsFoFHMMapcBz9L5ii" +
                "mro/CHkTazv6qqupK46XwR3OUImiOpLEbxJv/ohCi5LnQPxZUjeMR74pLrQwESWn" +
                "mDj5qI3WF/jZQJySvxF/fD8Y+y5eBKMwZcPMO9F54RzsjkAePlAslXEDfyL/NTSW" +
                "UpPicMv3hyWtf622weH8PRnrRK4JqDaj4adEcKeThnXw5Ct2/uHpgF0kYZcTpeWy" +
                "McSbUFjhJlWEgw==";

        try {


            byte []encodedCert = Base64.decode(SubCA);
            ByteArrayInputStream inputStream =  new ByteArrayInputStream(encodedCert);
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            X509Certificate cert = (X509Certificate)certificateFactory.generateCertificate(inputStream);

            return cert;
        }catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static Certificate[] GetCertChain(String accessToken, CredentialsInfoResponse info)
    {
        Certificate[] chain = new Certificate[] {GetSigningCertificate(accessToken,info)};

        return chain;
    }



    public static Certificate GetSigningCertificate(String accessToken, CredentialsInfoResponse info)
    {
        try {

            byte []encodedCert = Base64.decode(info.getCert().certificates[0]);
            ByteArrayInputStream inputStream =  new ByteArrayInputStream(encodedCert);
            CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
            X509Certificate cert = (X509Certificate)certificateFactory.generateCertificate(inputStream);

            return cert;
        }catch(Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

}
