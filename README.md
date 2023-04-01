# microservices-plyground
Spring Boot Microservices Playground Project

## Lanzar postgresql
```
docker run -d -p 5432:5432 -e 'POSTGRES_USER=postgres' -e 'POSTGRES_PASSWORD=postgres' -e 'POSTGRES_DB=scrum_dev' -v ~/win_home/eclipse-workspace/microservices-playground/SQL/init.sql:/docker-entrypoint-initdb.d/1.sql -v ~/win_home/eclipse-workspace/microservices-playground/SQL/data.sql:/docker-entrypoint-initdb.d/2.sql postgres
```
