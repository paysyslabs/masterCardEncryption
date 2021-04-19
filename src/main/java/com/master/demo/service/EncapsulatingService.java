package com.master.demo.service;

import com.master.demo.json.DecryptionRequest;
import com.master.demo.json.DecryptionResponse;
import com.master.demo.json.EncryptionRequest;
import com.master.demo.json.EncryptionResponse;
import com.mastercard.developer.encryption.EncryptionException;
import java.io.IOException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

public interface EncapsulatingService {

    public EncryptionResponse encrypt(EncryptionRequest encryptionRequest) throws EncryptionException, CertificateException, IOException, NoSuchProviderException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException;
    public DecryptionResponse decrypt(DecryptionRequest decryptionRequest) throws EncryptionException, CertificateException, IOException, NoSuchProviderException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException;
}
