package com.training.dao.query;
import static com.training.dao.query.Restriction.*;


public class LikeExpression implements Expression {

    private QueryProperty property;
    private Object value;

    public LikeExpression(QueryProperty property, Object value) {

        this.property = property;
        this.value = value;
    }

    @Override
    public String render() {

        StringBuilder buf = new StringBuilder();
        buf.append(property);
        buf.append(Operator.LIKE);
        buf.append(value);
        return buf.toString();
    }

}
