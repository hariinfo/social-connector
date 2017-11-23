module social.emitter {
    requires spring.webflux;
    requires spring.context;
    requires spring.web;
    requires reactor.core;
    requires spring.beans;
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires javafx.base;
    exports com.stepan.twitter;
}