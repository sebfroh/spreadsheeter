package com.github.sebfroh.spreadsheeter;

import java.util.List;

import javax.annotation.Nonnull;

public interface Sheet {

    @Nonnull
    String getName();

    @Nonnull
    List<List<Cell<?>>> getRows();

    @Nonnull
    List<List<Cell<?>>> getColumns();

}
