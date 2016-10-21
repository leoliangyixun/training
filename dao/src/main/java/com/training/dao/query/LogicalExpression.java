package com.training.dao.query;


import static com.training.dao.query.Restriction.*;

public class LogicalExpression implements Expression {

    private Expression leftExpression;
    private Expression rightExpression;
    private QueryOperator operator;

    public LogicalExpression(Expression leftExpression, Expression rightExpression,
            QueryOperator operator) {

        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
        this.operator = operator;
    }

    @Override
    public String render() {

        StringBuilder buf = new StringBuilder();
        buf.append("(");
        buf.append(leftExpression.render());
        buf.append(getOperator());
        buf.append(rightExpression.render());
        buf.append(")");
        return buf.toString();
    }

    //////////// Getters /////////////

    public Expression getLeftExpression() {

        return leftExpression;
    }

    public Expression getRightExpression() {

        return rightExpression;
    }

    public QueryOperator getOperator() {

        return operator;
    }

}
