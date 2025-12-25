package io.github.glynch.owcs.sso.cache;

import static io.github.glynch.owcs.sso.support.NoClassNameToStringStyle.NO_CLASS_NAME_TO_STRING_STYLE;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import io.github.glynch.owcs.sso.support.PasswordMaskingReflectionToStringBuilder;

public class TokenCacheKey implements Serializable {

    private final String username;
    private final String password;

    TokenCacheKey(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static TokenCacheKey of(String username, String password) {
        Assert.hasText(username, "username cannot be empty or null");
        Assert.hasText(password, "password cannot be empty or null");
        return new TokenCacheKey(username, password);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return new PasswordMaskingReflectionToStringBuilder(this, NO_CLASS_NAME_TO_STRING_STYLE, "password").toString();
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        TokenCacheKey other = (TokenCacheKey) obj;
        return ObjectUtils.nullSafeEquals(username, other.username)
                && ObjectUtils.nullSafeEquals(password, other.password);
    }

}
