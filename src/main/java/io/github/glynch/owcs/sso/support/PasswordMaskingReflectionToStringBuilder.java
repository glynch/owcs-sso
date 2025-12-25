package io.github.glynch.owcs.sso.support;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class PasswordMaskingReflectionToStringBuilder extends ReflectionToStringBuilder {

    private static final String PASSWORD_MASK = "*****";
    private final List<String> passwordFields;

    public PasswordMaskingReflectionToStringBuilder(Object object, String... passwordFields) {
        super(object);
        if (ArrayUtils.isNotEmpty(passwordFields)) {
            this.passwordFields = Arrays.asList(passwordFields);
        } else {
            this.passwordFields = Collections.emptyList();
        }
    }

    public PasswordMaskingReflectionToStringBuilder(Object object, ToStringStyle style, String... passwordFields) {
        super(object, style);
        if (ArrayUtils.isNotEmpty(passwordFields)) {
            this.passwordFields = Arrays.asList(passwordFields);
        } else {
            this.passwordFields = Collections.emptyList();
        }
    }

    public PasswordMaskingReflectionToStringBuilder(Object object, ToStringStyle style, StringBuffer buffer,
            String... passwordFields) {
        super(object, style, buffer);
        if (ArrayUtils.isNotEmpty(passwordFields)) {
            this.passwordFields = Arrays.asList(passwordFields);
        } else {
            this.passwordFields = Collections.emptyList();
        }
    }

    public <T> PasswordMaskingReflectionToStringBuilder(T object, ToStringStyle style, StringBuffer buffer,
            Class<? super T> reflectUpToClass, boolean outputTransients, boolean outputStatics,
            String... passwordFields) {
        super(object, style, buffer, reflectUpToClass, outputTransients, outputStatics);
        if (ArrayUtils.isNotEmpty(passwordFields)) {
            this.passwordFields = Arrays.asList(passwordFields);
        } else {
            this.passwordFields = Collections.emptyList();
        }
    }

    @Override
    protected Object getValue(Field field) throws IllegalAccessException {
        Object value = super.getValue(field);
        return passwordFields.contains(field.getName()) ? value != null ? PASSWORD_MASK : "" : value;
    }
}
