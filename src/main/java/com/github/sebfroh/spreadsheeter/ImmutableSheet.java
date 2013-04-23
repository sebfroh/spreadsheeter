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
public final class ImmutableSheet implements Sheet, Serializable {

    @NotThreadSafe
    public static final class Builder {

        @Nonnull
        private String name;

        @Nonnull
        private List<List<Cell<?>>> rows;

        @Nonnull
        private List<List<Cell<?>>> columns;

        public Builder() {
            // default constructor
        }

        public Builder(@Nonnull final Sheet sheet) {
            Check.notNull(sheet, "sheet");
            name = Check.notNull(sheet.getName(), "sheet.getName()");
            rows = Lists.newArrayList(Check.notNull(sheet.getRows(),
                    "sheet.getRows()"));
            columns = Lists.newArrayList(Check.notNull(sheet.getColumns(),
                    "sheet.getColumns()"));
        }

        @Nonnull
        public Builder setName(@Nonnull final String name) {
            this.name = Check.notNull(name, "name");
            return this;
        }

        @Nonnull
        public Builder setRows(@Nonnull final List<List<Cell<?>>> rows) {
            this.rows = Lists.newArrayList(Check.notNull(rows, "rows"));
            return this;
        }

        @Nonnull
        public Builder setColumns(@Nonnull final List<List<Cell<?>>> columns) {
            this.columns = Lists
                    .newArrayList(Check.notNull(columns, "columns"));
            return this;
        }

        @Nonnull
        public ImmutableSheet build() {
            return new ImmutableSheet(name, rows, columns);
        }

    }

    private static final long serialVersionUID = 1L;

    private static int buildHashCode(@Nonnull final String name,
            @Nonnull final List<List<Cell<?>>> rows,
            @Nonnull final List<List<Cell<?>>> columns) {
        return Objects.hashCode(name, rows, columns);
    }

    @Nonnull
    private final String name;

    @Nonnull
    private final List<List<Cell<?>>> rows;

    @Nonnull
    private final List<List<Cell<?>>> columns;

    private final int hash;

    public ImmutableSheet(@Nonnull final String name,
            @Nonnull final List<List<Cell<?>>> rows,
            @Nonnull final List<List<Cell<?>>> columns) {
        this.name = Check.notNull(name, "name");
        this.rows = ImmutableList.copyOf(Check.notNull(rows, "rows"));
        this.columns = ImmutableList.copyOf(Check.notNull(columns, "columns"));
        hash = buildHashCode(name, rows, columns);
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
        final ImmutableSheet other = (ImmutableSheet) obj;
        return Objects.equal(name, other.name)
                && Objects.equal(rows, other.rows)
                && Objects.equal(columns, other.columns);
    }

    @Override
    @Nonnull
    public String getName() {
        return name;
    }

    @Override
    @Nonnull
    public List<List<Cell<?>>> getRows() {
        return rows;
    }

    @Override
    @Nonnull
    public List<List<Cell<?>>> getColumns() {
        return columns;
    }

    @Override
    public int hashCode() {
        return hash;
    }

    @Override
    public String toString() {
        return "ImmutableSheet [name=" + name + ", rows=" + rows + ", columns="
                + columns + "]";
    }

}
