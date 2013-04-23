package com.github.sebfroh.spreadsheeter;

import javax.annotation.Nonnull;

public interface ValueContext<T> {

    // @Nonnull
    // <S> S getValue(Class<S> clazz);

    @Nonnull
    T getValue();

    @Nonnull
    Class<T> getType();
}
