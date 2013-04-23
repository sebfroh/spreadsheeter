package com.github.sebfroh.spreadsheeter;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

public interface Cell<T> {

    @Nonnegative
    long getRowIndex();

    @Nonnegative
    long getColumnIndex();

    @Nonnull
    ValueContext<T> getValueContext();

}
