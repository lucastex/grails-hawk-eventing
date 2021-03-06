package grails.plugins.hawkeventing

import grails.plugin.spock.UnitSpec
import spock.lang.Unroll

/**
 * 
 * @author Kim A. Betti <kim.betti@gmail.com>
 */
class EventNameDecoderSpec extends UnitSpec {

    @Unroll("Decoding event name #fullEventName into #expectedEvents")
    def "decoding"() {
        given:
        def decoder = new EventNameDecoder(fullEventName)
        def generatedEventNames = []
        while (decoder.hasNext()) {
            generatedEventNames << decoder.next()
        }
        
        expect:
        generatedEventNames.size() == expectedEvents.size()
        expectedEvents.each { expectedEventName ->
            assert generatedEventNames.contains(expectedEventName), "expected to find " + expectedEventName
        }

        where:
        fullEventName   | expectedEvents
        'a'             | ['a']
        'a.b.c'         | ['a', 'a.b', 'a.b.c']
    }
}
