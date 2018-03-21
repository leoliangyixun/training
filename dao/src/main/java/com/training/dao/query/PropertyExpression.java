package com.training.dao.query;
import static com.training.dao.query.Restriction.*;

public class PropertyExpression implements Expression {

    private QueryProperty property;
    private Object value;
    private QueryOperator operator;

    public PropertyExpression(QueryProperty property, Object value, QueryOperator operator) {

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


    public QueryProperty getProperty() {

        return property;
    }

    public Object getValue() {

        return value;
    }

    public QueryOperator getOperator() {

        return operator;
    }

}
