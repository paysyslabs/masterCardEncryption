package com.master.demo.controller;

import com.master.demo.json.DecryptionRequest;
import com.master.demo.json.DecryptionResponse;
import com.master.demo.json.EncryptionRequest;
import com.master.demo.json.EncryptionResponse;
import com.master.demo.service.EncapsulatingService;
import com.mastercard.developer.encryption.EncryptionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

@RestController
@RequestMapping("/encapsulate")
public class EncapsulateController {

    @Autowired
    EncapsulatingService service;

    @PostMapping(value = "/encrypt")
    public EncryptionResponse getEncryptedData(@RequestBody EncryptionRequest encryptionRequest) throws EncryptionException, CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, IOException, KeyStoreException, NoSuchProviderException {
        return service.encrypt(encryptionRequest);
    }

    @PostMapping(value = "/decrypt")
    public DecryptionResponse getDecryptedData(@RequestBody DecryptionRequest decryptionRequest) throws EncryptionException, CertificateException, UnrecoverableKeyException, NoSuchAlgorithmException, IOException, KeyStoreException, NoSuchProviderException {
        return service.decrypt(decryptionRequest);
    }
}
