# Vorbereitung Midterm-Prüfung 1, OOPI2

Dieses Projekt enthält Programmieraufgaben, welche Sie zur Vorbereitung für die erste Midterm-Prüfung verwenden können.


## Generische Klasse schreiben

Implementieren Sie die Klasse `NonNullList`, welche eine einfache, generische Liste darstellt, welche keine `null`-Elemente erlaubt. Sobald versucht wird, einen `null`-Wert in die Liste einzufügen, wirft sie eine `NullPointerException`. Intern darf die Klasse eine existierende Listen-Klasse, z. B. `ArrayList`, verwenden, um die Elemente zu speichern.

In der Klasse `NonNullListTest` finden Sie eine Reihe von Unit-Tests, welche definieren, welche Methoden die Klasse unterstützen soll und wie sie sich verhalten sollen. Die Tests sind zu Beginn auskommentiert; entfernen Sie Test für Test die Kommentarzeichen und bringen Sie die Tests zum Laufen.

(Die Klasse muss _nicht_ das `List`-Interface implementieren.)


## Datenverarbeitung mit Collections

Das Package `games` enthält einige Klassen, welche Teil einer Online-Game-Plattform (à la Steam) sein könnten. Die Hauptklasse `GamePlatform` speichert die Daten der Plattform in Attributen: die vorhandenen Games (`games`), die registrierten Spieler (`players`) und die von den Spielern erreichten Punktzahlen (`scores`). Es sind eine Reihe von leeren Methoden vorhanden, welche Sie implementieren sollen. In allen davon sollen Sie auf die Attribute `games`, `players` und/oder `scores` zugreifen. Implementieren Sie die Methoden gemäss Methodenkommentaren und Unit-Tests.
