package utils.model;

import javax.crypto.SecretKey;

public class AESencryption {
    private SecretKey symmetricKey;

    private byte[] initializationVector;

    public AESencryption() {
    }

    public AESencryption(SecretKey symmetricKey, byte[] initializationVector) {
        this.symmetricKey = symmetricKey;
        this.initializationVector = initializationVector;
    }

    public SecretKey getSymmetricKey() {
        return symmetricKey;
    }

    public void setSymmetricKey(SecretKey symmetricKey) {
        this.symmetricKey = symmetricKey;
    }

    public byte[] getInitializationVector() {
        return initializationVector;
    }

    public void setInitializationVector(byte[] initializationVector) {
        this.initializationVector = initializationVector;
    }
}
