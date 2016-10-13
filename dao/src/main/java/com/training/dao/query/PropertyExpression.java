package com.training.dao.query;


public class PropertyExpression implements Expression {

    private OrderedProperty property;
    private Object value;
    private Operato operator;

    public PropertyExpression(OrderedProperty property, Object value, Operator operator) {

        this.property = property;
        this.value = value;
        this.operator = operator;
    }

    @Override
    public String render() {

        StringBuilder buf = new StringBuilder();
        buf.append(property);
        buf.append(operator);
        buf.append(value);
        return buf.toString();
    }

    ////////////// Getters //////////////

    public OrderedProperty getProperty() {

        return property;
    }

    public Object getValue() {

        return value;
    }

    public Operator getOperator() {

        return operator;
    }

}
