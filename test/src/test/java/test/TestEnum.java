package test;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;

/**
 * Created by yangkai on 2017/6/1.
 */
public class TestEnum {

    @Test
    public void test() {

/*
        Type type = Type.C;
        Type[] types = new Type[]{Type.A,Type.B, Type.D};

        System.out.println(ArrayUtils.contains(types, type));
*/
        System.out.println(Status.OK);

    }

    public enum Type {
        A(1),B(1),C(1),D(1);

        private int status;

        Type(int status) {
            this.status = status;
        }

        public int status() {
            return this.status;
        }
    }

    public enum Status {
        OK(1, "成功"),NONE(1, "失败");

        private int status;
        private String message;
        Status(int code, String message) {
            this.status = code;
            this.message = message;
        }

        public int status() {
            return this.status;
        }

        public String message() {
            return this.message;
        }

        @Override
        public String toString() {
           return "{\"status\": " + this.status() + ", \"message\": " +this.message + "}";
        }
    }
}
