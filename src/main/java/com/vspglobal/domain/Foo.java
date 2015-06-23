package com.vspglobal.domain;

public class Foo {

    private final long id;
    private final String content;

    public Foo(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

}