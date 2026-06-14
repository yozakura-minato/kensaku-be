package com.yozakura_minato.kensaku_be.annotation.normalizedString;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import tools.jackson.databind.annotation.JsonDeserialize;

import java.lang.annotation.*;

/**
 * <p>Normalization annotation for String fields of class</p>
 * Destination value: {@code null} if the plain string is null or contains only spaces, else returns {@code stripped value}.
 */
@Documented
@Target({ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER, ElementType.RECORD_COMPONENT})
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@JsonDeserialize(using = StringNormalizingDeserializer.class)
public @interface NormalizedString {}
