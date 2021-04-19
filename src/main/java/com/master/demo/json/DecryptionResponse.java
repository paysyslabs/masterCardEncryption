package com.master.demo.json;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class DecryptionResponse {

    private String plainText;
}
