package com.training.dao.query;

import static com.training.dao.query.Restriction.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class JunctionExpression implements Expression {

    private static final String EMPTY_CONDITION = "1=1";

    private List<Expression> elements = new ArrayList<>();
    private Operator operator;

    public JunctionExpression(Operator operator) {

        this.operator = operator;
    }

    public JunctionExpression(List<Expression> elements, Operator operator) {

        this.elements = elements;
        this.operator = operator;
    }

    public JunctionExpression add(Expression element) {

        elements.add(element);
        return this;
    }

    @Override
    public String render() {

        if (elements.size() == 0) {
            return EMPTY_CONDITION;
        } else {
            Iterator<Expression> iterator = elements.iterator();
            StringBuilder buf = new StringBuilder();
            buf.append(LEFT_PARENTHESES);

            while (iterator.hasNext()) {
                buf.append(SPACE);
                buf.append(iterator.next().interpret());

                if (iterator.hasNext()) {
                    buf.append(operator);
                }
            }
            buf.append(RIGHT_PARENTHESES);
            return buf.toString();
        }
    }
}
