# podotherapy_backend

## IMPORTANT NOTE
Since I developed this for a school project with no financial interest, this project is licensed under the following terms: https://creativecommons.org/publicdomain/zero/1.0/

This means:

- **Everyone in the world can freely use, modify, distribute, and build upon this project** without any restrictions or obligations.
- The project is available for both personal and commercial use at no cost.
- No copyright or patent rights are asserted over the work by the creator.

### Credits

A list of credits for the code used in this project as a base or example or any other form of inspiration.

- [Springboot 3 JWT example (Spring Security 6) Daniel Pereira Guimarães ](https://github.com/daniel-pereira-guimaraes/spring-security6-jwt/)
  Used as a example and base for jwt implementation in spring security.


### Docker setup    
If you just want to run the application, you can use the docker-compose file to setup the database and the application.
There are compiled versions in ./devbuilds folder that are regularly updated. (note: that you dont need to run them directly, docker-compose will do it for you)


**1.a** Install docker, you can download docker desktop from [here](https://www.docker.com/products/docker-desktop)

**1.b** You can also download docker as a apt/homebrew package see commands below (docker-compose is included in docker desktop)
```bash
# Ubuntu
sudo apt install docker.io docker-compose
# Mac
brew install docker docker-compose
```

**2.** Run docker-compose, first go to the root of the project (here is the compose.yml located) and then run the following command 
```bash
# Ubuntu / Mac / Docker Desktop
docker-compose up
```

**3.a** With docker desktop you can see all the running containers in the dashboard, you can also see the logs of the containers there.

**3.b** If you are using the command line you can see the running containers with the following command
```bash
docker ps
```

**4.** You can stop the containers with the following command
```bash
docker-compose down
```

**5.** The CLI offers for both desktop and the base cli (wich is included in docker desktop) the following commands
```bash
# List all containers
docker ps -a

# List all images (images are the base for containers)
docker images

# Remove a container
docker rm <container id>

# Remove a image
docker rmi <image id>

# Remove all containers
docker rm $(docker ps -a -q)

# Remove all images
docker rmi $(docker images -q)
```
