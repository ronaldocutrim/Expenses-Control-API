CONTAINER_NAME ?=  postgres-local
DB_USER ?= postgres
DB_NAME ?= expensescontrol

run:
	./mvnw spring-boot:run -Dspring.devtools.restart.enabled=true
deps:
	./mvnw dependency:resolve
db:
	docker exec -it $(CONTAINER_NAME) psql -U $(DB_USER) -d $(DB_NAME)