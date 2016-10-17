package com.training.dao.query;

import static com.training.dao.query.Restriction.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class JunctionExpression implements Expression {

    private static final String EMPTY_CONDITION = "1=1";

    private List<Expression> elements = new ArrayList<>();
    private QueryOperator queryOperator;

    public JunctionExpression(QueryOperator queryOperator) {

        this.queryOperator = queryOperator;
    }

    public JunctionExpression(List<Expression> elements, QueryOperator queryOperator) {

        this.elements = elements;
        this.queryOperator = queryOperator;
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
            buf.append("(");

            while (iterator.hasNext()) {
                buf.append(" ");
                buf.append(iterator.next().render());

                if (iterator.hasNext()) {
                    buf.append(queryOperator);
                }
            }
            buf.append(")");
            return buf.toString();
        }
    }
}
