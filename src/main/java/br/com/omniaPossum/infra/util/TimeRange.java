package br.com.omniaPossum.infra.util;

import org.apache.commons.lang3.Range;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.Temporal;
import java.util.Comparator;
import java.util.Objects;

public class TimeRange<T extends Temporal> {

    private final Range<T> range;

    private TimeRange(T start, T end, Comparator<T> comparator) {
        if (comparator.compare(start, end) > 0) {
            throw new IllegalArgumentException("'start' must be before or equal to 'end'");
        }

        this.range = Range.between(start, end, comparator);
    }

    public static TimeRange<LocalDate> between(LocalDate start, LocalDate end) {
        return new TimeRange<>(start, end, LocalDate::compareTo);
    }

    public static TimeRange<LocalDateTime> between(LocalDateTime start, LocalDateTime end) {
        return new TimeRange<>(start, end, LocalDateTime::compareTo);
    }

    public T start() {
        return range.getMinimum();
    }

    public T end() {
        return range.getMaximum();
    }

    public boolean contains(T element) {
        return range.contains(element);
    }

    public boolean isAfter(T element) {
        return range.isAfter(element);
    }

    public boolean isStartedBy(T element) {
        return range.isStartedBy(element);
    }

    public boolean isEndedBy(T element) {
        return range.isEndedBy(element);
    }

    public boolean isBefore(T element) {
        return range.isBefore(element);
    }

    public boolean containsRange(Range<T> otherRange) {
        return range.containsRange(otherRange);
    }

    public boolean isAfterRange(Range<T> otherRange) {
        return range.isAfterRange(otherRange);
    }

    public boolean isOverlappedBy(Range<T> otherRange) {
        return range.isOverlappedBy(otherRange);
    }

    public boolean isBeforeRange(Range<T> otherRange) {
        return range.isBeforeRange(otherRange);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TimeRange<T> timeRange = (TimeRange<T>) o;
        return range.equals(timeRange.range);
    }

    @Override
    public int hashCode() {
        return Objects.hash(range);
    }

    @Override
    public String toString() {
        return "DateRange{" +
            "range=" + range +
            '}';
    }

}
