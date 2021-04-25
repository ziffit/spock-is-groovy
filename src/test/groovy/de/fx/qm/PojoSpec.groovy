package de.fx.qm

import spock.lang.Specification
import spock.lang.Unroll

class PojoSpec extends Specification {

    def sut = new Pojo()

    @Unroll
    def "get/set #method => #value"() {
        when:
        sut.invokeMethod("set$method".toString(), value)

        then:
        sut."get${method}"() == value

        where:
        method         || value
        "StringValue"  || "Jo"
        "IntegerValue" || 1
        "BooleanValue" || true
        "Nope"         || null
    }

    def "Init a List of Pojos"() {
        given:
        def pojos = [
                new Pojo(
                        booleanValue: true,
                        stringValue: "Jep",
                        integerValue: 1,
                        map: ["some": "value", "someMore": "value+1"],
                        list: ["omg", "is", "that", "simple"]
                ),
                new Pojo(),
                new Pojo("String Constructor")
        ]
        expect:
        pojos.size() == 3

        and:
        verifyAll(pojos.first(), Pojo) {
            it.booleanValue
            it.stringValue == "Jep"
            it.integerValue == 1
            it.map == ["some": "value", "someMore": "value+1"]
            it.list == ["omg", "that", "is", "wrong"]
        }
    }
}
