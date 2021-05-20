# weather-currency-app-v2
My repo for PDRIS project in SBT


Монолитное приложение из 1 семестра разбито на три отдельных ""микросервиса"". Микросервисы должны работать в среде Spring Cloud Eureka. То есть, у вас должны быть три отдельных микросервиса вашего приложения, где находится вся бизнес-логика, а также отдельный микосервис Eureka, в котором ваши микросервисы должны регистрироваться. Для каждого ""микросервиса"" создать свой docker image, и объедить все docker image в один работающий проект с помощью docker-compose  Для этого:
1. Нужно было создать три отдельных приложения:
    1.а либо три отдельных проекта
    1.б либо один проект с тремя модулями 
2. Создать dockerfile для каждого ""микросервиса""
3. Создать конфигурационный файл docker-compose.yml, в котором нужно описатьт параметры проекта. С помощью docker-compose up должна подниматься вся ваши микросервисная инфраструктура. 


Автоматизирована установку приложения через ansible (разные микросервисы на разные сервера) (деплоить в каталог /edu/{{FIO}}/), для этого:
1. установлен ansbile на свою машину (она будет называться хост-машиной)
2. написан ansible playbook, который :
    - собирает docker image на удаленных машинах (или собирает на хост и передает готовые на удаленные)
    - запускает проект 

Весь проект перенесен (""микросервисы"", БД) из docker-compose в kubernetes:
1. сделаны поды для контейнеров своих микросервисов и 
2. сделан под для postgres. 
3. Настроен кубернетовские service’ы, чтобы микросервисы могли общаться друг с другом и service’ы для обращения к подам микросервисов вне кластера. 
4. Настроена репликацию со значением два для каждого пода.
