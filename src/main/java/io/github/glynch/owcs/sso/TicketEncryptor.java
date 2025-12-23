package io.github.glynch.owcs.sso;

public interface TicketEncryptor {

    String encrypt(String ticket) throws SSOException;
}
