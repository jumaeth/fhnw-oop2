# Vorbereitung Midterm-Prüfung 2, OOPI2

Dieses Projekt enthält Programmieraufgaben, welche Sie zur Vorbereitung für die zweite Midterm-Prüfung verwenden können.


## Callbacks & Generics

Eine `ReportingList` ist eine generische Liste, welche folgende Methoden unterstützt: `add` (mit _einem_ Parameter), `get`, `size`, `clear`; diese verhalten sich grundsätzlich wie die gleichnamigen Methoden von `ArrayList`. Das Spezielle an `ReportingList` ist, dass man Callbacks registrieren kann, welche bei jeder Änderung der Liste aufgerufen werden.

Eine `ReportingList` könnte beispielsweise so verwendet werden:

```java
var list = new ReportingList<String>();
list.setOnAdded((i, elem) -> {
    System.out.println(elem + " beim Index " + i + " hinzugefügt");
});
list.setOnCleared(() -> {
    System.out.println("Liste wurde geleert");
});

list.add("Foo");
list.add("Bar");
list.clear();
list.add("Baz");
```

Dieser Code sollte folgende Ausgabe produzieren:

```
Foo beim Index 0 hinzugefügt
Bar beim Index 1 hinzugefügt
Liste wurde geleert
Baz beim Index 0 hinzugefügt
```

Implementieren Sie die Klasse `ReportingList` im Package `reporting`. Es ist bereits eine minimale Hülle und eine Klasse mit (auskommentierten) Unit-Tests vorgegeben. Um die Elemente zu speichern, können Sie intern eine `ArrayList` verwenden.

**Zusatzanforderung:** Verwenden Sie für die Callbacks vordefinierte [Funktionsinterfaces aus der Java-Standardbibliothek](functional-interfaces.md).


## Sortieren und Vergleichen

Im Package `apartments` finden Sie eine fertige Klasse `Address` und eine Klasse `AddressBook` mit einer Methode `sort`, welche noch keine Implementation hat. Implementieren Sie die Methode so, dass die Adressen im Adressbuch nach folgenden Kriterien sortiert werden:
* Aufsteigend nach PLZ (`zipCode`)
* Falls die PLZ zweier Adressen gleich sind, alphabetisch nach Ort (`city`)
* Falls PLZ & Ort gleich sind, alphabetisch nach Strasse und Hausnummer (`streetAndNumber`)

**Zusatzanforderung:** Für diese Aufgabe müssen Sie einen Lambda-Ausdruck verwenden.


## Datenverarbeitung mit Streams

Das Package `games` enthält einige Klassen, welche Teil einer Online-Game-Plattform (à la Steam) sein könnten. In der Hauptklasse `GamePlatform` ist eine Reihe von leeren Methoden vorhanden, welche Sie implementieren sollen. Es handelt sich zum Teil um dieselben Aufgaben wie in der Vorbereitung für die erste Midterm-Prüfung, aber dieses Mal sollen Sie die Aufgaben mit Streams lösen.

Die ersten beiden Methoden (`namesOfLargeGames` und `highScoresFor`) kann man elegant mit einer einzigen Stream-Pipeline lösen; die anderen brauchen mehr (mehrere Pipelines und/oder zusätzliche Schleifen). An der Midterm-Prüfung könnte es Aufgaben geben, welche man mit Streams lösen muss, um die volle Punktzahl zu erhalten.


