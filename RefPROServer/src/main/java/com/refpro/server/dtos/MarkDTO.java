package com.refpro.server.dtos;

public class MarkDTO {
    private double mark;

    public MarkDTO(double mark) {
        this.mark = mark;
    }

    public MarkDTO() {
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }
}
