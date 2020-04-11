package com.fpt.automatedtesting.practicalexams.dtos;

public class PairData <T, U> {

    private final T first;
    private final U second;

    public PairData(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() { return first; }
    public U getSecond() { return second; }
}