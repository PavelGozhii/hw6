package com.company;

public class MailMessage implements Sendable<String> {

    private String from;
    private String to;
    private String content;

    public MailMessage(String from, String to, String content) {
        this.from = from;
        this.to = to;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }


    @Override
    public String getKeyForMailService() {
        return getTo();
    }

    @Override
    public String getValueForMailService() {
        return getContent();
    }
}
