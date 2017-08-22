## Summary
Application is developed using Spring Boot, Spring Data (JPA) technologies, with embedded H2 db with liquibase 
database management tool. At the first launch liquibase creates schema and inserts default rooms into db (from 101 - 104).
H2 saves data to file, so it is possible to save dat between docker containers restart, recreate using volume.  

## Build
Tested with gradle 3.5.1

Install propper gradle version, run `gradle clean build` after successful build artifact is accessible at `build/lib/marryat-<version>.jar`

## Create Docker image 
Run command  `docker build -t <docker_login>/marryat:0.1.0 .`

## Run docker container
Public image is accessible at [Docker hub.](https://hub.docker.com/r/torkuchyn/marryat/)
Run command 
```
docker pull torkuchyn/marryat:0.1.0
docker run --name marryat -p 9626:9626 -p 8080:8080 -v marryat-db-vol:/root/db
 -e JAVA_OPTS="-Dspring.profiles.active=dev" -d torkuchyn/marryat:0.1.0 
```

### Developer mode
Application can be started in dev or prod (default). Developer mode provides access to management endpoints and h2 web-console.
Add `-e JAVA_OPTS="-Dspring.profiles.active=dev"` to activate developer mode. 
 
If you want to have access to Spring Boot managers ports - use `-p 9626:9626` and all the related information will be 
accessible by [http://localhost:9626](http://localhost:9626/*), for more information refer to [official documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-endpoints.html).

To save db state between docker containers recreate use `-v marryat-db-vol:/root/db`, this will create named docker volume 
which will be stored at host file system. To have full access to db use docker mount `-v <path-to-host-folder>:/root/db` and
all the h2 data will be accessible by `<path-to-host-folder>` path. Use [http://localhost:8080/h2-console/](http://localhost:8080/h2-console/) 
as web-client. Accessible only in developer mode. 

## Endpoints
Add reservation 
POST /reservations
```
{
  "first_name": "Victor",
  "last_name": "Kuchyn",
  "room": "101",
  "start_date": "20170826",
  "end_date": "20170827"
}
```
Response 
```
{ "id": 1}
```

GET /reservations?from=20170822&to=20170830&page=1&size=10
`page` and `size` are optinal parameters, `from`, `to` - mandatory.
Response
```
[
 {
   "id": 1,
   "first_name": "Victor",
   "last_name": "Kuchyn",
   "room": "101",
   "start_date": "20170826",
   "end_date": "20170827"
 }
]
``` 

PUT /reservations/{id}
```
{
  "id": 1,
  "first_name": "Victor",
  "last_name": "Kuchyn",
  "room": "101",
  "start_date": "20170826",
  "end_date": "20170827"
}
```
Updates reservation

DELETE /reservations/{id}
Response deleted entity
```
{
  "id": 1,
  "first_name": "Victor",
  "last_name": "Kuchyn",
  "room": "101",
  "start_date": "20170826",
  "end_date": "20170827"
}
```

Also basic validation provided from common sense.  