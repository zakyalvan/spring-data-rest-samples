How To Use This Project
=======================

1. Clone this repo
2. Start rest server using command ```mvn clean jetty:run``` from command line with installed maven path exported as system property or ```clean jetty:run``` from eclipse`s Run Us - Maven Build... menu.
3. Open test directory (Only three class, ```.rest.TestConfiguration```, ```.rest.test.TestEmployeeRestEndpoint``` and ```.rest.test.TestProjectRestEndpoint```). Look att configuration class to know how to configure ```RestTemplate``` so that we can deal with HAL ```apliction/hal+json``` media type.