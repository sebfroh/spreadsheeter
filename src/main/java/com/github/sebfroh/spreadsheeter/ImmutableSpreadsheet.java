package com.github.sebfroh.spreadsheeter;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;

import net.sf.qualitycheck.Check;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

@Immutable
public final class ImmutableSpreadsheet implements Spreadsheet, Serializable {

    @NotThreadSafe
    public static final class Builder {

        @Nonnull
        private List<Sheet> sheets;

        public Builder() {
            // default constructor
        }

        public Builder(@Nonnull final Spreadsheet spreadsheet) {
            Check.notNull(spreadsheet, "spreadsheet");
            sheets = Lists.newArrayList(Check.notNull(spreadsheet.getSheets(),
                    "spreadsheet.getSheets()"));
        }

        @Nonnull
        public Builder setSheets(@Nonnull final List<Sheet> sheets) {
            this.sheets = Lists.newArrayList(Check.notNull(sheets, "sheets"));
            return this;
        }

        @Nonnull
        public ImmutableSpreadsheet build() {
            return new ImmutableSpreadsheet(sheets);
        }

    }

    private static final long serialVersionUID = 1L;

    private static int buildHashCode(@Nonnull final List<Sheet> sheets) {
        return Objects.hashCode(sheets);
    }

    @Nonnull
    private final List<Sheet> sheets;

    private final int hash;

    public ImmutableSpreadsheet(@Nonnull final List<Sheet> sheets) {
        this.sheets = ImmutableList.copyOf(Check.notNull(sheets, "sheets"));
        hash = buildHashCode(sheets);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ImmutableSpreadsheet other = (ImmutableSpreadsheet) obj;
        return Objects.equal(sheets, other.sheets);
    }

    @Override
    @Nonnull
    public List<Sheet> getSheets() {
        return sheets;
    }

    @Override
    public int hashCode() {
        return hash;
    }

    @Override
    public String toString() {
        return "ImmutableSpreadsheet [sheets=" + sheets + "]";
    }

}