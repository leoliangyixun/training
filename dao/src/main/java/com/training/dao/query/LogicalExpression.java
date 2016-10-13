package com.training.dao.query;

import com.augmentum.rotation.constant.LogicOperator;

import static com.augmentum.rotation.constant.SystemConstants.LEFT_PARENTHESES;
import static com.augmentum.rotation.constant.SystemConstants.RIGHT_PARENTHESES;

public class LogicalExpression implements Expression {

    private Expression leftExpression;
    private Expression rightExpression;
    private LogicOperator operator;

    public LogicalExpression(Expression leftExpression, Expression rightExpression,
            LogicOperator operator) {

        this.leftExpression = leftExpression;
        this.rightExpression = rightExpression;
        this.operator = operator;
    }

    @Override
    public String interpret() {

        StringBuilder buf = new StringBuilder();
        buf.append(LEFT_PARENTHESES);
        buf.append(leftExpression.interpret());
        buf.append(getOperator());
        buf.append(rightExpression.interpret());
        buf.append(RIGHT_PARENTHESES);
        return buf.toString();
    }

    //////////// Getters /////////////

    public Expression getLeftExpression() {

        return leftExpression;
    }

    public Expression getRightExpression() {

        return rightExpression;
    }

    public LogicOperator getOperator() {

        return operator;
    }

}
