package com.github.sebfroh.spreadsheeter;

import java.io.Serializable;

import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.NotThreadSafe;

import net.sf.qualitycheck.Check;

import org.apache.poi.ss.formula.functions.T;

import com.google.common.base.Objects;

@Immutable
public final class ImmutableCell implements Cell, Serializable {

    @NotThreadSafe
    public static final class Builder {

        @Nonnegative
        private long rowIndex;

        @Nonnegative
        private long columnIndex;

        @Nonnull
        private ValueContext<T> valueContext;

        public Builder() {
            // default constructor
        }

        public Builder(@Nonnull final Cell cell) {
            Check.notNull(cell, "cell");
            rowIndex = Check.notNegative(cell.getRowIndex(),
                    "cell.getRowIndex()");
            columnIndex = Check.notNegative(cell.getColumnIndex(),
                    "cell.getColumnIndex()");
            valueContext = Check.notNull(cell.getValueContext(),
                    "cell.getValueContext()");
        }

        @Nonnull
        public Builder setRowIndex(@Nonnegative final long rowIndex) {
            this.rowIndex = Check.notNegative(rowIndex, "rowIndex");
            return this;
        }

        @Nonnull
        public Builder setColumnIndex(@Nonnegative final long columnIndex) {
            this.columnIndex = Check.notNegative(columnIndex, "columnIndex");
            return this;
        }

        @Nonnull
        public Builder setValueContext(
                @Nonnull final ValueContext<T> valueContext) {
            this.valueContext = Check.notNull(valueContext, "valueContext");
            return this;
        }

        @Nonnull
        public ImmutableCell build() {
            return new ImmutableCell(rowIndex, columnIndex, valueContext);
        }

    }

    private static final long serialVersionUID = 1L;

    private static int buildHashCode(@Nonnegative final long rowIndex,
            @Nonnegative final long columnIndex,
            @Nonnull final ValueContext<T> valueContext) {
        return Objects.hashCode(rowIndex, columnIndex, valueContext);
    }

    @Nonnegative
    private final long rowIndex;

    @Nonnegative
    private final long columnIndex;

    @Nonnull
    private final ValueContext<T> valueContext;

    private final int hash;

    public ImmutableCell(@Nonnegative final long rowIndex,
            @Nonnegative final long columnIndex,
            @Nonnull final ValueContext<T> valueContext) {
        this.rowIndex = Check.notNegative(rowIndex, "rowIndex");
        this.columnIndex = Check.notNegative(columnIndex, "columnIndex");
        this.valueContext = Check.notNull(valueContext, "valueContext");
        hash = buildHashCode(rowIndex, columnIndex, valueContext);
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
        final ImmutableCell other = (ImmutableCell) obj;
        return Objects.equal(rowIndex, other.rowIndex)
                && Objects.equal(columnIndex, other.columnIndex)
                && Objects.equal(valueContext, other.valueContext);
    }

    @Override
    @Nonnegative
    public long getRowIndex() {
        return rowIndex;
    }

    @Override
    @Nonnegative
    public long getColumnIndex() {
        return columnIndex;
    }

    @Override
    @Nonnull
    public ValueContext<T> getValueContext() {
        return valueContext;
    }

    @Override
    public int hashCode() {
        return hash;
    }

    @Override
    public String toString() {
        return "ImmutableCell [rowIndex=" + rowIndex + ", columnIndex="
                + columnIndex + ", valueContext=" + valueContext + "]";
    }

}