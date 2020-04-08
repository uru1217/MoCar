package com.all.car.model;

import lombok.Data;

@Data
public class Criteria {
    private int pageNum;
    private int amount;
    private int cnt;

    private String type;
    private String keyword;

    public Criteria() {
        this(1, 10);
    }

    public Criteria(int pageNum, int amount) {
        this.pageNum = pageNum;
        this.amount = amount;
    }

    public int getCnt() {
        this.cnt = (this.pageNum - 1) * amount;
        return this.cnt;
    }

    public String[] getTypeArr() {
        return type == null? new String[] {}: type.split("");
    }
}
