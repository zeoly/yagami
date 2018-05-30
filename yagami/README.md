# Structure

- yagami-modules
  - yagami-base: base module including people/department/auth
  - yagami-document: file and folder and so on
  - yagami-claim: test biz module
- yagami-application
  - yagami-app-facade: application controller
  - yagami-app-bootstrap: spring boot package
- yagami-services
  - yagami-service-client: Client server for test
  - yagami-service-client2: Client server for client to invoke
  - yagami-service-config: Centralized config server
  - yagami-service-eureka: HA Eureka server(s)
  - yagami-service-bpm: Activiti BPM server
  - yagami-ribbon: test server

# port

端口|用途
-|-
9981,9982|eureka
8090|ribbon client
8070|ribbon target consumer
8081|yagami app
9991|client server
9992|client server 2
9000|config server

