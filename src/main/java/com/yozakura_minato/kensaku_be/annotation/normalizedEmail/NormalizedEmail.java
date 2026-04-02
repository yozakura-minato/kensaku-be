package com.yozakura_minato.kensaku_be.annotation.normalizedEmail;

import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import tools.jackson.databind.annotation.JsonDeserialize;

import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@JsonDeserialize(using = EmailNormalizingDeserializer.class)
public @interface NormalizedEmail {}
