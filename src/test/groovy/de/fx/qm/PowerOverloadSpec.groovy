package de.fx.qm

import spock.lang.Specification
import spock.lang.Unroll

class PowerOverloadSpec extends Specification {

    @Unroll
    def "#number1 + #number2 = #result"() {
        expect:
        assert number1 + number2 == result: "Oh my God it is $message! The Result should be ${number1 + number2}"

        where:
        number1 | number2 || result
        1       | 1       || 2
        99      | 100     || 999
        message = number1 + number2 > result ? "too Small" : "too Big"
    }
}
