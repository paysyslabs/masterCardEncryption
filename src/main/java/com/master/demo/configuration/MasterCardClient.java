package com.master.demo.configuration;

import com.mastercard.developer.encryption.FieldLevelEncryptionConfig;
import com.mastercard.developer.encryption.FieldLevelEncryptionConfigBuilder;
import com.mastercard.developer.utils.EncryptionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.PrivateKey;
import java.security.cert.Certificate;

@Configuration
@Slf4j
public class MasterCardClient {

    @Bean
    public FieldLevelEncryptionConfig mastercardConfiguration() {
        Certificate encryptionCertificate = encryptionCertificate();
        PrivateKey decryptionKey = decryptionKey();
        FieldLevelEncryptionConfig config = FieldLevelEncryptionConfigBuilder.aFieldLevelEncryptionConfig()
                .withEncryptionCertificate(encryptionCertificate)
                .withDecryptionKey(decryptionKey)
                .withEncryptionPath("$.path.to.foo", "$.path.to.encryptedFoo")
                .withDecryptionPath("$.path.to.encryptedFoo", "$.path.to.foo")
                .withOaepPaddingDigestAlgorithm("SHA-256")
                .withEncryptedValueFieldName("encryptedValue")
                .withEncryptedKeyFieldName("encryptedKey")
                .withIvFieldName("iv")
                .withFieldValueEncoding(FieldLevelEncryptionConfig.FieldValueEncoding.HEX)
                .build();
        return config;
    }

    // Loading the Encryption Certificate
    @Bean
    public Certificate encryptionCertificate() {
        Certificate encryptionCertificate = null;
        try {
            encryptionCertificate = EncryptionUtils.loadEncryptionCertificate("cert/public.pem.crt");
        } catch (Exception e) {
            log.error("exception in encryptionCertificate:{}", e.toString());
        }
        return encryptionCertificate;
    }

    // Loading the D
    @Bean
    public PrivateKey decryptionKey() {
        PrivateKey decryptionKey = null;
        try {
            decryptionKey = EncryptionUtils.loadDecryptionKey("cert/paysysPKCS.p12",
                    "paysys",
                    "paysys");
        } catch (Exception e) {
            log.error("exception in decryptionKey:{}", e.toString());
        }
        return decryptionKey;
    }
}
