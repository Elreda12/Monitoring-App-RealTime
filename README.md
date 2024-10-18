﻿# Monitoring-App-RealTime
 
L'objectif est de mettre en place un système de gestion des opérations asynchrones et 
réactives en utilisant Spring Boot avec Webflux, garantissant ainsi une performance optimale 
même lors de larges charges de données.

Le projet vise à créer une interface utilisateur conviviale en React, permettant aux 
administrateurs et aux utilisateurs de visualiser de manière claire les flux de données, de 
consulter des statistiques en temps réel et d'accéder aisément à la gestion des anomalies. 
L'objectif de cette interface est d'optimiser l'expérience des utilisateurs, facilitant ainsi le suivi 
et l'intervention en cas de nécessité.

![Capture d'écran 2024-10-17 215806](https://github.com/user-attachments/assets/092377ac-2fa3-42bb-94e6-20f980653e1a)

 
L'architecture illustrée montre un système de collecte et de traitement des flux de données en 
temps réel. La première étape consiste à collecter les flux de données via un système de collecte 
centralisé (SI Collecte). Ces flux proviennent de différentes sources, notamment des 
équipements MSC de Nokia et Ericsson, des équipements Huawei pour les PGW et SGW, ainsi 
que des systèmes OCS avec des flux de données nommés Rec20 et Data20. Les flux collectés 
sont ensuite acheminés vers cinq serveurs Edge (Edge1 à Edge5), chacun recevant des flux 
spécifiques selon leur type et leur source. Sur chaque serveur Edge, Apache NiFi est utilisé pour 
décoder et dispatcher les flux de données.  

L'industrialisation de la supervision d'une chaîne data et orchestration des alertes 
NiFi traite les données en temps réel, les décode pour en extraire les informations pertinentes, 
puis les distribue vers les clusters de stockage MapR6 et MapR7. Ces clusters servent à stocker 
et gérer les données traitées, permettant une récupération et une analyse efficaces. Ainsi, cette 
architecture permet une gestion robuste et en temps réel des flux de données depuis leur collecte 
jusqu'à leur stockage. 
