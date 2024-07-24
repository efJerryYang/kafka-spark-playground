Notice:

1. `mvn spring-boot:run` to start the application.
2. make sure you have kafka and mongodb running.

Dependencies:

1. `Spring Boot` 3.x would use `jakarta` instead of `javax`, however, only `Spark` 4.0+ will support this, so the `Spark` package is preview version.
2. The `Spring Boot` 3.x would require `Servlet API` 6.0+, causing the `Spark` crash due to the removal of `jakarta.servlet.SingleThreadModel` in `Servlet API` from 6.0+ after 20 years of deprecation.
3. Workaround: add a 5.0.0 version of `jakarta.servlet-api` explicitly while making it `provided` scope to avoid conflicts with `Spring Boot`'s dependencies.
