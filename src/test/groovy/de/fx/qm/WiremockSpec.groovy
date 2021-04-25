package de.fx.qm

import com.github.tomakehurst.wiremock.junit.WireMockRule
import spock.lang.Shared
import spock.lang.Specification

import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse

import static com.github.tomakehurst.wiremock.client.WireMock.get
import static com.github.tomakehurst.wiremock.client.WireMock.ok

class WiremockSpec extends Specification {

    @Shared
    public WireMockRule wireMockRule = new WireMockRule()

    def client = HttpClient.newHttpClient()

    def setupSpec() {
        wireMockRule.start()
    }

    def cleanupSpec() {
        wireMockRule.shutdown()
    }

    def "Wiremock Test"() {
        given: "Wiremock Mock"
        wireMockRule.stubFor(get("/wiremock").willReturn(ok("Hello From Wiremock!")))
        def request = HttpRequest.newBuilder(URI.create("http://localhost:8080/wiremock")).build()
        when:
        def response = client.send(request, HttpResponse.BodyHandlers.ofString())
        then:
        response.statusCode() == 200
        and:
        response.body().toString() == "Hello From Wiremock!"
    }
}
