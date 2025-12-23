package io.github.glynch.owcs.sso.support;

import org.apache.commons.lang3.builder.ToStringStyle;

public class NoClassNameToStringStyle extends ToStringStyle {

    public static final ToStringStyle NO_CLASS_NAME_TO_STRING_STYLE = new NoClassNameToStringStyle();

    public NoClassNameToStringStyle() {
        super();
        setUseClassName(false);
        setUseIdentityHashCode(false);
        setNullText("");
        setContentStart("{");
        setContentEnd("}");
    }

}
