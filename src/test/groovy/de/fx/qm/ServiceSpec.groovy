package de.fx.qm

import spock.lang.Specification
import spock.lang.Unroll

class ServiceSpec extends Specification {

    @Unroll
    def "Mocking"() {
        given: "Mocking Helper"
        def sut = new Service()
        sut.helper = Mock(Helper) {
            add(int1, int2) >> mockedReturnValue
        }
        expect:
        sut.makeMeStars(int1, int2) == expect

        where:
        int1 | int2 | mockedReturnValue || expect
        1    | 1    | 10                || "*" * 10
        10   | 5    | 1                 || "*"
        1    | 1    | 20                || "********"
    }
}
