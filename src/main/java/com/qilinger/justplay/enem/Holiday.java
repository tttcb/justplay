package com.qilinger.justplay.enem;

public enum Holiday {
    元旦(1, 1, false, 3),
    春节(1, 1, true, 0),
    清明节(4, 0, false, 3,true),
    劳动节(5, 1, false, 5),
    中秋节(8, 15, true, 3),
    国庆节(10, 1, false, 7),
    涂成博的生日捏(9, 19, false, 1, true);

    private Integer day;
    private Integer month;
    private Boolean IfNongLi;
    private Boolean IfSpecial;
    private int Howlong;

    Holiday(int month, int day, boolean ifNongLi, boolean ifSpecial) {
        this.month = month;
        this.day = day;
        this.IfNongLi = ifNongLi;
        this.IfSpecial = ifSpecial;
    }

    Holiday(int month, int day, boolean ifNongLi, int howlong) {
        this(month, day, ifNongLi, howlong, false);
    }

    Holiday(int month, int day, boolean ifNongLi, int howlong, boolean ifSpecial) {
        this.month = month;
        this.day = day;
        this.IfNongLi = ifNongLi;
        this.Howlong = howlong;
        this.IfSpecial = ifSpecial;
    }

    public int GetMonth() {
        return this.month;
    }

    public int GetDay() {
        return this.day;
    }

    public Boolean GetIfNongLi() {
        return this.IfNongLi;
    }

    public Boolean GetIfSpecial() {
        return this.IfSpecial;
    }

    public int GetHowlong() {
        return this.Howlong;
    }
}
