# memes-microservices-demo

Demonstrative project showing my skills in Spring Cloud development area.

## Links to demo

https://www.youtube.com/watch?v=Qxw1_JhrH98

https://www.youtube.com/watch?v=liqFH__OB8g

https://www.youtube.com/watch?v=v17P2ESkB4A

https://www.youtube.com/watch?v=FPrxGhJh49A
 
## How to run project locally?

### Required tools

You won't be able to run this project unless you have installed docker and some software needed to execute maven commands.

### Instruction

After downloading and unzipping project files, you need to build jars first. To do it, execute command *mvn clean compile package* on each of nested projects. Next thing to do is to open cmd console in root directory of project and issue commands:

- *docker-compose -f docker-compose.yml build*
- *docker-compose -f docker-compose.yml up*

That's it! You're free to play.
