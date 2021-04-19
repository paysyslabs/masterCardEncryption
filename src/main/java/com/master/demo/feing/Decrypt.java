package com.master.demo.feing;


import com.master.demo.json.DecryptionRequest;
import com.master.demo.json.DecryptionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url="http://10.0.110.42:8084/encapsulate",name = "decrypot")
public interface Decrypt {

    @PostMapping("/decrypt")
    public DecryptionResponse decrypt(
            @RequestBody DecryptionRequest decryptionRequest);
}
