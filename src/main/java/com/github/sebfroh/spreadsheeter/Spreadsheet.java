package com.github.sebfroh.spreadsheeter;

import java.util.List;

import javax.annotation.Nonnull;

public interface Spreadsheet {

    @Nonnull
    List<Sheet> getSheets();

}
