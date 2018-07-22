package push.model.common;

public class MQ {

    public enum Namespace {

        PUSH("push"),
        MESSAGE("message");

        private String namespace;

        Namespace(String namespace) {
            this.namespace = namespace;
        }

        public String namespace() {
            return this.namespace;
        }
    }

    public enum Exchange {

        PUSH("base.notify.push.ex");

        private String exchange;

        Exchange(String exchange) {
            this.exchange = exchange;
        }

        public String exchange() {
            return this.exchange;
        }
    }

    public enum RoutingKey {
        CCTALK("base_notify_push_cctalk"),
        NORMANDY("base_notify_push_normandy"),
        CICHANG_5_TIYAN("base_notify_push_cichang5tiyan"),
        CLASS("base_notify_push_class"),
        DICTATION("base_notify_push_dictation"),
        DICT("base_notify_push_dict"),
        CICHANGE("base_notify_push_cichang"),
        TEST("base_notify_push_test");

        private String routingKey;

        RoutingKey(String routingKey) {
            this.routingKey = routingKey;
        }

        public String routingKey() {
            return this.routingKey;
        }

    }

    public enum Queue {
        CCTALK("base.notify.push.cctalk.q"),
        NORMANDY("base.notify.push.normandy.q"),
        CICHANG_5_TIYAN("base.notify.push.cichang5tiyan.q"),
        CLASS("base.notify.push.class.q"),
        DICTATION("base.notify.push.dictation.q"),
        DICT("base.notify.push.dict.q"),
        CICHANGE("base.notify.push.cichang.q"),
        TEST("base.notify.push.test.q");

        private String queue;

        Queue(String queue) {
            this.queue = queue;
        }

        public String queue() {
            return this.queue;
        }

    }

}
