package io.github.glynch.owcs.sso;

public interface TicketEncryptor {

    String encrypt(String baseUrl, String ticket) throws SSOException;
}
