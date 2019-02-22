package test;

import com.hujiang.basic.framework.core.exception.SysException;
import com.hujiang.basic.framework.core.util.DateUtil;
import com.hujiang.basic.framework.core.util.JsonUtil;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by yangkai on 2017/7/14.
 */
public class TestGuava {

    @Test
    public void test() {
        Set<Integer> ids = Sets.newHashSet(1,2,3,4,5,6,7,8,9,10,11);
        System.out.println(ids);
    }


    @Test
    public void test3() {
        Maps.newHashMap(null);
    }
    private AppDao appDao = new AppDao();

    private LoadingCache<String, App> appLocalCache = CacheBuilder.newBuilder()
            .expireAfterAccess(2, TimeUnit.HOURS).build(new CacheLoader<String, App>() {
                @Override
                public App load(String key) throws Exception {
                    //Optional.ofNullable(appDao.load(key)).orElse(null);
                    return appDao.load(key);
                }
            });


    public static class AppDao {
        public App load(String appKey) {
           return Objects.equals(appKey, "10") ? null : new App(appKey, null,  null, null);
        }
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class App {
        private String appName;
        private String appKey;
        private String appSecret;
        private Boolean status;

        @Override
        public String toString() {
            return JsonUtil.object2JSON(this);
        }
    }

    @Test
    public void testLoadingCache1() throws Exception{
        App app = appLocalCache.get(null);
        System.out.println(app);
        App app1 = appLocalCache.get("1");
        System.out.println(app1);
        App app2 = appLocalCache.get("2");
        System.out.println(app2);
        // App app10 = appLocalCache.get("10");
       // System.out.println(app10);
    }


    @Test
    public void testList() throws Exception{
        Lists.partition(null, 2);
    }


    private static final LoadingCache<Provider, String> AUTHENTICATION_TOKEN_LOCAL_CACHE = CacheBuilder
        .newBuilder().expireAfterWrite(50, TimeUnit.MINUTES)
        .build(new CacheLoader<Provider, String>() {
            @Override
            public String load(Provider provider) throws Exception {
                Date refreshAt = new Date();
                String token = JWT.getToken(provider.getKeyId(), provider.getTeamId(), provider.getToken(), refreshAt.getTime() / 1000);
                System.out.println("APNs Authentication Token refreshed at {" + DateUtil.toDateString(refreshAt, DateUtil.DEFAULT_DATETIME_PATTERN) + "}, provider: {" + provider + "}, token: {" + token + "}");
                return token;
            }
        });

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Provider {
        private String keyId;
        private String teamId;
        private String token;

        @Override
        public String toString() {
            return JsonUtil.object2JSON(this);
        }

        public static Provider create(String keyId, String teamId, String token) {
            return new Provider(keyId, teamId, token);
        }

        @Override
        public boolean equals(Object obj) {
            if (obj != null && obj.getClass() == this.getClass()) {
                Provider other = (Provider) obj;
                return Objects.equals(keyId, other.getKeyId()) && Objects.equals(teamId, other.getTeamId()) && Objects.equals(token, other.getToken());
            }
            return false;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((keyId == null) ? 0 : keyId.hashCode());
            result = prime * result + ((teamId == null) ? 0 : teamId.hashCode());
            result = prime * result + ((token == null) ? 0 : token.hashCode());
            return result;
        }

        public static void main(String[] args) {
            Provider provider1 = Provider.create("A79YXTJ7A8", "J52Z9F8XVP", "MIGTAgEAMBMGByqGSM49AgEGCCqGSM49AwEHBHkwdwIBAQQgKtfmN8kuLSyNP2i1pMd9XfhhHOZGX4HCWzrAs8kGn4mgCgYIKoZIzj0DAQehRANCAARUltM57vtC3aQAYw0yRMxvF1JTA2+SIhekk8QbYMH88J8Eysz3abZlPbsl5Am1wSWfcYbGOHqWAfMoNMj7DAPe");
            Provider provider2 = Provider.create("A79YXTJ7A8", "J52Z9F8XVP", "MIGTAgEAMBMGByqGSM49AgEGCCqGSM49AwEHBHkwdwIBAQQgKtfmN8kuLSyNP2i1pMd9XfhhHOZGX4HCWzrAs8kGn4mgCgYIKoZIzj0DAQehRANCAARUltM57vtC3aQAYw0yRMxvF1JTA2+SIhekk8QbYMH88J8Eysz3abZlPbsl5Am1wSWfcYbGOHqWAfMoNMj7DAPe");
            Set<Provider> providers =new HashSet<>();
            Long start =System.currentTimeMillis();
            providers.add(provider1);
            Long end =System.currentTimeMillis();
            System.out.println(end -start);
            start =System.currentTimeMillis();
            providers.add(provider2);
            end =System.currentTimeMillis();
            System.out.println(end -start);
            System.out.println(providers);
            System.out.println(provider1.equals(provider2));
        }

    }

    public static final class JWT {

        /**
         * Generates a JWT token as per Apple's specifications.
         *
         * @param kid  The key ID (found when generating your private key)
         * @param tid The team ID (found in the member center)
         * @param secret The private key (excluding the header and the footer)
         * @param now indicates the time at which the token was generated, in terms of the number of seconds since Epoch, in UTC
         * @return The resulting token, which will be valid for one hour
         * @throws InvalidKeySpecException  if the key is incorrect
         * @throws NoSuchAlgorithmException if the key algo failed to load
         * @throws InvalidKeyException      if the key is invalid
         * @throws SignatureException       if this signature object is not initialized properly.
         */
        public static String getToken(final String kid, final String tid, final String secret, final long now) {
            //final long now = (System.currentTimeMillis() / 1000);
            final String header = "{\"alg\":\"ES256\",\"kid\":\"" + kid + "\"}";
            final String payload = "{\"iss\":\"" + tid + "\",\"iat\":" + now + "}";

            final String part1 = Base64.getUrlEncoder().encodeToString(header.getBytes(StandardCharsets.UTF_8))
                + "."
                + Base64.getUrlEncoder().encodeToString(payload.getBytes(StandardCharsets.UTF_8));

            try {
                return part1 + "." + ES256(secret, part1);
            } catch (NoSuchAlgorithmException | InvalidKeySpecException | InvalidKeyException | SignatureException e) {

                throw new SysException(50000, "system error");
            }
        }

        /**
         * Adopted from http://stackoverflow.com/a/20322894/2274894
         *
         * @param secret The secret
         * @param data   The data to be encoded
         * @return The encoded token
         * @throws InvalidKeySpecException  if the key is incorrect
         * @throws NoSuchAlgorithmException if the key algo failed to load
         * @throws InvalidKeyException      if the key is invalid
         * @throws SignatureException       if this signature object is not initialized properly.
         */
        private static String ES256(final String secret, final String data)
            throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException {

            KeyFactory kf = KeyFactory.getInstance("EC");
            KeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(secret.getBytes()));
            PrivateKey key = kf.generatePrivate(keySpec);

            final Signature sha256withECDSA = Signature.getInstance("SHA256withECDSA");
            sha256withECDSA.initSign(key);

            sha256withECDSA.update(data.getBytes(StandardCharsets.UTF_8));

            final byte[] signed = sha256withECDSA.sign();
            return Base64.getUrlEncoder().encodeToString(signed);
        }


    }


    private ExecutorService executorService = Executors.newCachedThreadPool();

    @Test
    public void testGuavaConcurrent() throws Exception {
        for (;;){
            executorService.submit(new Runnable() {
                @Override
                public void run() {
                    Provider provider = Provider.create("A79YXTJ7A8", "J52Z9F8XVP", "MIGTAgEAMBMGByqGSM49AgEGCCqGSM49AwEHBHkwdwIBAQQgKtfmN8kuLSyNP2i1pMd9XfhhHOZGX4HCWzrAs8kGn4mgCgYIKoZIzj0DAQehRANCAARUltM57vtC3aQAYw0yRMxvF1JTA2+SIhekk8QbYMH88J8Eysz3abZlPbsl5Am1wSWfcYbGOHqWAfMoNMj7DAPe");
                    String token = null;
                    try {
                        token = AUTHENTICATION_TOKEN_LOCAL_CACHE.get(provider);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(token);
                }
            });
        }


    }

    @Test
    public void testGuavaGet() {
        Provider provider = Provider.create("A79YXTJ7A8", "J52Z9F8XVP", "MIGTAgEAMBMGByqGSM49AgEGCCqGSM49AwEHBHkwdwIBAQQgKtfmN8kuLSyNP2i1pMd9XfhhHOZGX4HCWzrAs8kGn4mgCgYIKoZIzj0DAQehRANCAARUltM57vtC3aQAYw0yRMxvF1JTA2+SIhekk8QbYMH88J8Eysz3abZlPbsl5Am1wSWfcYbGOHqWAfMoNMj7DAPe");
        String token = null;
        try {
            token = AUTHENTICATION_TOKEN_LOCAL_CACHE.get(provider);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(token);
    }


























}
