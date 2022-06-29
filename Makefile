#Roda todas as imagens do projeto

up:
	mvn clean install
	docker-compose -f docker/docker-compose.yaml build
	docker-compose -f docker/docker-compose.yaml up

stop:
	docker-compose -f docker/docker-compose.yaml stop

clean:
	docker-compose -f docker/docker-compose.yaml down
	mvn clean