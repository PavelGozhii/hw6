package com.company;

public class Salary implements Sendable<Integer> {
    private String placeWork;
    private String to;
    private int salary;

    public Salary(String placeWork, String to, int salary) {
        this.placeWork = placeWork;
        this.to = to;
        this.salary = salary;
    }

    public String getTo() {
        return to;
    }

    public int getSalary() {
        return salary;
    }


    @Override
    public String getKeyForMailService() {
        return getTo();
    }

    @Override
    public Integer getValueForMailService() {
        return getSalary();
    }
}
