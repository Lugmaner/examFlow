# ExamFlow (Java)

Ein kleines Java-Projekt zur automatischen Planung von Lernblöcken für Prüfungen.
Die Idee ist, aus Prüfungsterminen, geschätztem Lernaufwand und bereits belegten Zeitfenstern einen einfachen Lernplan zu erzeugen.

Der Fokus liegt aktuell auf der internen Scheduling-Logik, sauberer Modellierung der Daten und einer nachvollziehbaren Projektstruktur. Das Projekt ist noch im Aufbau und wird Schritt für Schritt erweitert.

---

## Überblick

ExamFlow plant Lernblöcke für Prüfungen vor der jeweiligen Deadline ein.

Dabei werden aktuell berücksichtigt:

* Prüfungstermine
* geschätzter Lernaufwand
* Tagesstart und Tagesende
* Dauer einer Lerneinheit
* Pausenzeit
* bereits belegte Zeitfenster
* einfache Konfliktprüfung mit bestehenden Terminen

Der aktuelle Kern ist ein `GreedyScheduler`, der Prüfungen nach Deadline sortiert und dann versucht, passende freie Slots vor dem Prüfungstermin zu finden.

---

## Aktueller Stand

Das Projekt besteht momentan vor allem aus zwei Bereichen:

* `model`
* `scheduler`

Die Modelle beschreiben Prüfungen, Lernblöcke und belegte Zeitfenster.
Der Scheduler enthält die konkrete Logik, mit der Lernblöcke geplant werden.

Aktuell ist ExamFlow noch kein fertiges Endnutzer-Tool, sondern eher der Kern eines Lernplaners. CLI, Export und ausführlichere Tests sind sinnvolle nächste Schritte.

---

## Scheduling-Ansatz

Der `GreedyScheduler` verwendet einen einfachen Greedy-Ansatz:

1. Prüfungen werden nach Deadline sortiert.
2. Für jede Prüfung wird berechnet, wie viele Lernsessions benötigt werden.
3. Die Tage bis zur Deadline werden durchlaufen.
4. Pro Tag wird geprüft, ob ein freier Zeitslot vorhanden ist.
5. Wenn ein Slot frei ist, wird ein `StudyBlock` eingeplant.
6. Der neue Lernblock wird anschließend als belegter Slot gespeichert.
7. Wenn nicht genug Zeit vor der Deadline gefunden wird, wird eine eigene Exception geworfen.

Der Ansatz ist bewusst einfach gehalten. Er ist gut geeignet, um die Grundlogik eines Schedulers zu verstehen und später gezielt zu erweitern.

---

## Projektstruktur

```text
src/main/java/at/lugmaner/examflow
├── model
│   ├── Difficulty.java
│   ├── Exam.java
│   ├── FixedAppointment.java
│   ├── Schedulable.java
│   ├── StudyBlock.java
│   └── TimeSlot.java
│
└── scheduler
    ├── FailedToScheduleExamException.java
    ├── GreedyScheduler.java
    └── SchedulingStrategy.java
```

---

## Wichtige Klassen und Interfaces

### `Exam`

Repräsentiert eine Prüfung.

Enthält aktuell:

* Name
* Deadline
* Schwierigkeitsgrad
* geschätzte Lernzeit in Stunden

Eine Prüfung implementiert `Schedulable` und kann dadurch vom Scheduler verarbeitet werden.

---

### `Schedulable`

Ein Interface für Dinge, die eingeplant werden können.

Aktuell definiert es:

* `getName()`
* `getDeadline()`
* `getDifficultyLevel()`
* `getTotalStudyHours()`

Dadurch bleibt die Scheduling-Logik etwas allgemeiner und ist nicht komplett fest an eine konkrete Klasse gebunden.

---

### `TimeSlot`

Ein Interface für belegte oder geplante Zeitfenster.

Es definiert:

* Datum
* Startzeit
* Endzeit

Sowohl fixe Termine als auch Lernblöcke können dadurch gleich behandelt werden.

---

### `FixedAppointment`

Repräsentiert einen bereits belegten Termin.

Ein `FixedAppointment` besteht aus:

* Datum
* Startzeit
* Endzeit

Diese Termine werden dem Scheduler übergeben, damit Lernblöcke nicht in bereits belegte Zeiträume gelegt werden.

---

### `StudyBlock`

Ein geplanter Lernblock.

Ein `StudyBlock` enthält:

* Datum
* Startzeit
* Endzeit
* zugehörige Prüfung

Der Lernblock implementiert ebenfalls `TimeSlot`, damit er nach dem Einplanen direkt als belegter Zeitraum behandelt werden kann.

---

### `GreedyScheduler`

Die aktuelle Kernklasse des Projekts.

Der Scheduler bekommt:

* eine Liste von Prüfungen
* belegte Zeitfenster
* Tagesstart
* Tagesende
* Pausenzeit
* Dauer einer Lerneinheit

und gibt eine Liste von geplanten `StudyBlock`s zurück.

Wenn eine Prüfung vor ihrer Deadline nicht vollständig eingeplant werden kann, wird eine `FailedToScheduleExamException` geworfen.

---

## Build

Das Projekt verwendet Maven.

```bash
mvn clean package
```

Zum Kompilieren:

```bash
mvn compile
```

---

## Tests

Automatisierte Tests sind ein sinnvoller nächster Schritt und sollten besonders die Scheduling-Logik abdecken.

Wichtige Testfälle wären zum Beispiel:

* Lernblock hat die erwartete Dauer
* Gesamtlernzeit wird eingeplant
* belegte Termine werden nicht überschrieben
* Pausen werden berücksichtigt
* mehrere Prüfungen werden nach Deadline sortiert
* Exception wird geworfen, wenn nicht genug Zeit verfügbar ist
* Tagesgrenzen werden eingehalten

Gerade bei einem Scheduler sind Tests wichtig, weil kleine Änderungen an der Logik schnell unerwartete Auswirkungen auf die Planung haben können.

---

## Aktuelle Einschränkungen

Das Projekt ist noch in einem frühen Zustand.

Aktuelle Einschränkungen:

* keine CLI im öffentlichen Repo
* kein Export-Modul im öffentlichen Repo
* noch keine persistente Speicherung
* noch keine Priorisierung nach Difficulty
* einfache Greedy-Strategie
* keine Optimierung über mehrere mögliche Pläne
* aktuell keine ausführlichen automatisierten Tests im Repo
* Zeiten werden noch relativ einfach behandelt

---

## Mögliche nächste Schritte

Sinnvolle Erweiterungen wären:

* JUnit-Tests für den `GreedyScheduler`
* Umstellung auf Minuten statt Stunden für feinere Planung
* Difficulty stärker in die Planung einbeziehen
* CLI zum Eingeben von Prüfungen und Terminen
* späterer Usage-Abschnitt im README, sobald die Bedienung feststeht
* Export als Text, CSV oder Kalenderformat
* bessere Fehlerausgabe bei nicht planbaren Prüfungen
* Sortierung und schönere Ausgabe der Lernblöcke
* Validierung von ungültigen Eingaben
* spätere Unterstützung mehrerer Scheduling-Strategien

---

## Warum dieses Projekt?

ExamFlow ist für mich ein gutes Projekt, um Java, objektorientiertes Design und Algorithmen praktisch zu verbinden.

Besonders interessant ist dabei, dass ein Scheduler auf den ersten Blick simpel wirkt, aber schnell viele kleine Entscheidungen enthält: Wie werden Deadlines behandelt? Was zählt als belegter Slot? Wie vermeidet man Überschneidungen? Was passiert, wenn sich ein Lernplan nicht mehr ausgeht?

Der aktuelle Stand ist noch bewusst überschaubar, aber genau dadurch eignet sich das Projekt gut, um die Scheduling-Logik sauber aufzubauen und anschließend Schritt für Schritt zu verbessern.
