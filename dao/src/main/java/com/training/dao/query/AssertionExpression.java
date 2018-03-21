package com.training.dao.query;


public class AssertionExpression implements Expression {

    public static final String IS_NULL = " IS NULL";
    public static final String IS_NOT_NULL = "IS NOT NULL";

    private QueryProperty property;
    private String assertion;

    public AssertionExpression(QueryProperty property, String assertion) {

        this.property = property;
        this.assertion = assertion;
    }

    @Override
    public String render() {

        StringBuilder buf = new StringBuilder();
        buf.append(property);
        buf.append(assertion);
        return buf.toString();
    }

    public QueryProperty getProperty() {
        return property;
    }

    public String getAssertion() {
        return assertion;
    }

}
