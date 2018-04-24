/**
 * Copyright 2013 Bruno Oliveira, and individual contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.abstractj.kalium.crypto;

import static org.abstractj.kalium.SodiumConstants.SHA256BYTES;
import static org.abstractj.kalium.SodiumConstants.SHA512BYTES;
import static org.abstractj.kalium.SodiumConstants.BLAKE2B_OUTBYTES;

import org.abstractj.kalium.Sodium;
import org.abstractj.kalium.encoders.Encoder;

public class Hash {

    private static byte[] buffer;

    public byte[] sha256(byte[] message) {
        buffer = new byte[SHA256BYTES];
        Sodium.crypto_hash_sha256(buffer, message, message.length);
        return buffer;
    }

    public byte[] sha512(byte[] message) {
        buffer = new byte[SHA512BYTES];
        Sodium.crypto_hash_sha512(buffer, message, message.length);
        return buffer;
    }

    public String sha256(String message, Encoder encoder) {
        byte[] hash = sha256(message.getBytes());
        return encoder.encode(hash);
    }

    public String sha512(String message, Encoder encoder) {
        byte[] hash = sha512(message.getBytes());
        return encoder.encode(hash);
    }

    public byte[] blake2(byte[] message) {
        buffer = new byte[BLAKE2B_OUTBYTES];
        Sodium.crypto_generichash_blake2b(
                buffer, BLAKE2B_OUTBYTES, message, message.length, null, 0);
        return buffer;
    }

    public String blake2(String message, Encoder encoder) {
        byte[] hash = blake2(message.getBytes());
        return encoder.encode(hash);
    }
}
