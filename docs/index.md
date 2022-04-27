# Project DevOps

![Coverage](https://img.shields.io/endpoint?url=https://raw.githubusercontent.com/Timacx/DevOps_Project/fixBugWeb/.github/badges/jacoco.json)
![branches](https://img.shields.io/endpoint?url=https://raw.githubusercontent.com/Timacx/DevOps_Project/fixBugWeb/.github/badges/jacoco.json)
![jdk](https://img.shields.io/badge/jdk-17.0.1.12-blue)
![maven](https://img.shields.io/badge/maven-4.0.0-blue)
![jacoco](https://img.shields.io/badge/jacoco-0.8.2-blue)
![junit](https://img.shields.io/badge/junit-3.8.1-blue)

# Fonctionnalités :

### Constructeurs de DataFrame :

```DataFrame(ArrayList<Object> strucList, boolean typed)```

Constructeur de DataFrame depuis une ArrayList, avec la première ligne contenant les labels, puis toutes les lignes suivantes contiennent les données, avec un type par ligne.

Le boolean ici spécifie si notre ArrayList est déjà typé.


```DataFrame(String fileName)```

Ce deuxième constructeur permet à partir d'un fichier csv, de créer notre DataFrame.

## Affichage :

Nous disposons de trois méthodes d'affichage, 'displayAll', 'displayFirstLines', et 'displayLastLines', qui vont respectivement afficher toute la DataFrame, seulement les premières lignes, ou les dernières lignes.

## Selection :

Nous disposons de trois méthodes de selection :
*    - ```getDataFrameFromIndex``` : Obtenir un sous-ensemble de lignes à partir de leur index
*    - ```getDataFrameFromCollumns``` : Obtenir un sous-ensemble de colonnes  à partir de leur labels
*    - ```getDataFrameFromBooleanIndexing``` : Obtenir un sous-ensemble en triant sur une des données d'une collonne

## Statistique :

Nous disposons également de trois méthodes de statistique :
*    - ```average``` : Obtenir la moyenne d'une colonne de type 'int' ou 'double', sinon une exception est levée.
*    - ```minVal``` : Obtenir la plus petite valeur sur les types 'in' et 'double', sinon la 'string' ayant le plus petit code ASCII (ex Abc < abc)
*    - ```maxVal``` : Obtenir la plus grande valeur sur les types 'in' et 'double', sinon la 'string' ayant le plus grand code ASCII (ex Zxy > Yz)

# WorkFlow Git :

Pour coordonner notre git, nous somme partie sur le GitFlow WorkFlow. Notre dépot est donc composé de deux branches principales, la branches master contenant une version 100% fonctionnelle, et une branche develop qui contient la dernière version peut-être non abouti, mais surtout sans bug. Ainsi, pour chaque nouvelle fonctionnalité qu'on veut developer, on créer une nouvelle branche, et une fois cette fonctionnalité fini, et fonctionnelle, on merge avec la branche develop.

Pour ce faire, voici notre procédure de validation PULL/MERGE requests :

```
# Récupérer les dernières modifications sur develop
$ git switch develop
$ git pull 

# Merger au sein de la branche -- résoudre les conflits si besoin
$ git switch new-feature
$ git merge develop

# Fast forward de develop (intégrer les changements dans develop)
$ git switch develop
$ git merge new-feature

# (Pousser les modifications) et supprimer la branche
$ git branch -d new-feature
$ git push origin --delete new-feature

``` 

# FeedBack :

Projet enrichissant, nous permettant de mettre en pratique les methodes du cours, notamment avec la mise en place de maven, et d'un workflow git.

La mise en place de l'integration contine est un peu compliqué, mais est vraiment utile.

