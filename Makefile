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

#Roda apenas as imagens do app e postgres

up-simple:
	mvn clean install
	docker-compose -f docker/docker-compose-simple.yaml build
	docker-compose -f docker/docker-compose-simple.yaml up

stop-simple:
	docker-compose -f docker/docker-compose-simple.yaml stop

clean-simple:
	docker-compose -f docker/docker-compose-simple.yaml down
	mvn clean