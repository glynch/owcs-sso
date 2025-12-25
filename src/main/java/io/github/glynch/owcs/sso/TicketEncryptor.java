package io.github.glynch.owcs.sso;

public interface TicketEncryptor {

    String encrypt(String ticket) throws TicketEncryptionException;

    static TicketEncryptor create() {
        return new DefaultTicketEncryptor();
    }
}
