package push2.apns;

import org.junit.Test;

public class TestJWT {

    private static final String TEAM_ID = "4S5ZWGJSPN";
    private static final String KEY_ID = "CPPMHRM7CC";
    private static final String ACCESS_SECRET = "MIGTAgEAMBMGByqGSM49AgEGCCqGSM49AwEHBHkwdwIBAQQgjZTdQgZM/8AQspuD9G7aTsQTEBqomiu4TJ0LxU5gaI+gCgYIKoZIzj0DAQehRANCAASbKaMLxR56q2N0oQ8YXcOFEhHL7cAuNcLUwkMTLplUKoaRzSY9Me85UnjtuMxjmYpFkbmYtFtrNULzedlBXk/K";

    @Test
    public void testJWT() throws Exception {
        String token = JWT.getToken(TEAM_ID, KEY_ID, ACCESS_SECRET);
        System.out.println(token);
    }
}
