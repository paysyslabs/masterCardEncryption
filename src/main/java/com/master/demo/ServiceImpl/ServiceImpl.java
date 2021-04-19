package com.master.demo.ServiceImpl;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.master.demo.feing.Decrypt;
import com.master.demo.json.DecryptionRequest;
import com.master.demo.json.DecryptionResponse;
import com.master.demo.json.EncryptionRequest;
import com.master.demo.json.EncryptionResponse;
import com.master.demo.service.EncapsulatingService;
import com.mastercard.developer.encryption.EncryptionException;
import com.mastercard.developer.encryption.FieldLevelEncryption;
import com.mastercard.developer.encryption.FieldLevelEncryptionConfig;
import com.mastercard.developer.utils.EncryptionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;


@Slf4j
@Service
public class ServiceImpl implements EncapsulatingService {

    @Autowired
    private FieldLevelEncryptionConfig fieldLevelEncryptionConfig;

    @Autowired
    private Decrypt feingClient;


    @Override
    public EncryptionResponse encrypt(EncryptionRequest encryptionRequest) throws EncryptionException, CertificateException, IOException, NoSuchProviderException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException {
        String encryptedPayload = FieldLevelEncryption.encryptPayload(encryptionRequest.getPlainText(), fieldLevelEncryptionConfig);
        String encryptedString=new GsonBuilder().setPrettyPrinting().create().toJson(new JsonParser().parse(encryptedPayload));
        log.info("Encrypted data:{}",encryptedString);
        EncryptionResponse encryptionResponse = EncryptionResponse.builder().encryptText(encryptedString).build();
        return encryptionResponse;
    }

    @Override
    public DecryptionResponse decrypt(DecryptionRequest decryptionRequest) throws EncryptionException, CertificateException, IOException, NoSuchProviderException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException {
        Certificate encryptionCertificate = EncryptionUtils.loadEncryptionCertificate("cert/public.pem.crt");
        String payload1 = FieldLevelEncryption.decryptPayload(decryptionRequest.getEncryptedText(), fieldLevelEncryptionConfig);
        String decryptedString= new GsonBuilder().setPrettyPrinting().create().toJson(new JsonParser().parse(payload1));
        log.info("Decrypted data:{}",decryptedString);
       DecryptionResponse decryptionResponse1=feingClient.decrypt(decryptionRequest);
       log.info("===>from qadri Decrypted data:{}",decryptionResponse1.toString());
        DecryptionResponse decryptionResponse = DecryptionResponse.builder().plainText(decryptedString).build();
        return decryptionResponse;
    }
}
